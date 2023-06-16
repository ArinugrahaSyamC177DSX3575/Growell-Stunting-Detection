const sql = require('../config/database');
const axios = require('axios');

// API Get All Stuntings entries
const getAllStuntings = async (req, res) => {
  const query = 'SELECT * FROM stuntings';
  sql.query(query, (err, result) => {
    if (err) {
      console.error('Error retrieving stunting entries:', err);
      res.status(500).json({ error: true, message: 'Internal Server Error' });
    } else {
      res.json({ error: false, message: 'success', uploadResult: result });
    }
  });
};

const newStuntingEntry = async (req, res) => {
  const { name, gender, age, height, weight } = req.body;

  if (!name || !gender || !age || !height || !weight) {
    res.status(400).json({ error: true, message: 'Missing required fields in request body' });
    return;
  }

  try {
    const statusResponse = await axios.post('https://growell-flask-api-fkegjceqka-et.a.run.app/api/predict', {
      gender,
      age,
      height,
      weight
    });

    const { status } = statusResponse.data;
    console.log(status);

    const query = `INSERT INTO stuntings (name, gender, age, height, weight, status) VALUES (?, ?, ?, ?, ?, ?)`;
    const values = [name, gender, age, height, weight, status];

    sql.query(query, values, (err, result) => {
      if (err) {
        console.error('Error adding stunting:', err);
        res.status(500).json({ error: true, message: 'Internal Server Error' });
      } else {
        const newStuntingEntry = {
          id: result.insertId,
          name,
          gender,
          age,
          height,
          weight,
          status
        };
        res.status(201).json({ error: false, message: 'success', uploadResult: newStuntingEntry });
      }
    });
  } catch (error) {
    console.error('Error making status request:', error);
    res.status(500).json({ error: true, message: 'Error Making Status' });
  }
};

module.exports = {
  getAllStuntings,
  newStuntingEntry
};