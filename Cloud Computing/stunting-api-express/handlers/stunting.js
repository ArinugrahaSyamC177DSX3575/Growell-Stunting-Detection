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

const newStuntingEntry = (req, res) => {
  const { name, gender, age, height, weight } = req.body;

  if (!name || !gender || !age || !height || !weight) {
    res.status(400).json({ error: true, message: 'Missing required fields in request body' });
    return;
  }

  const query = `INSERT INTO stuntings (name, gender, age, height, weight) VALUES ('${name}','${gender}','${age}','${height}','${weight}')`;
  const values = [name, gender, age, height, weight];

  sql.query(query, values, (err, result) => {
    if (err) {
      console.error('Error adding stunting:', err);
      res.status(500).json({ error: true, message: 'Internal Server Error' });
    } else {
      const newStuntingEntry = {
          name,
          gender,
          age,
          height,
          weight
      };
      res.status(201).json({ error: false, message: 'success', uploadResult: newStuntingEntry, result });
    }
  });
};

const statusStunting = async (req, res) => {
  const { name, gender, age, height, weight } = req.body;

  // Perform the prediction based on the newStuntingEntry
  try {
    const response = await axios.post('https://growell-flask-api-fkegjceqka-et.a.run.app/api/predict', {
      name,
      gender,
      age,
      height,
      weight
    });

  const status = response.data.status;

    const query = `UPDATE stuntings SET status = ${status}`;
    sql.query(query, (err, result) => {
      if (err) {
        console.error('Error updating status:', err);
        res.status(500).json({ error: true, message: 'Internal Server Error' });
      } else {
        const newStatusEntry = {
          id: result.insertid,
          name,
          gender,
          age,
          height,
          weight
        };

        res.status(200).json({
          error: false,
          message: 'Success',
          uploadResult: newStatusEntry,
          status: status,
        });
      }
    });
  } catch (error) {
    console.error('Error making status request:', error);
    res.status(500).json({ error: true, message: 'Error Making Status' });
  }
};

module.exports = {
  getAllStuntings,
  newStuntingEntry,
  statusStunting
};
