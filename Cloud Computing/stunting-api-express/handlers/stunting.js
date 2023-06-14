const sql = require('../config/database');
const axios = require('axios');

// API Get All Stuntings entries
const getAllStuntings = async (req, res) => {
    const query = 'SELECT * FROM stuntings';
    sql.query(query, (err, result) => {
      if (err) {
        console.error('Error retrieving stunting entries: ', err);
        res.status(500).json({ error: 'Internal Server Error' });
      } else {
        res.json(result);
      }
    });
  };

//API Entry New Stunting
const newStuntingEntry = (req, res) => {
  const { name, gender, age, height, weight } = req.body;

  // Melakukan permintaan POST ke API Flask untuk mendapatkan hasil prediksi
  axios.post('URL_API_FLASK_CLOUD_RUN/api/predict', {
    name,
    gender,
    age,
    height,
    weight
  })
  .then(response => {
    // Menyimpan hasil prediksi ke dalam database
    const status = response.data.prediction.status;
    const query = `INSERT INTO stuntings (name, gender, age, height, weight, status) VALUES ('${name}','${gender}','${age}','${height}','${weight}','${status}')`;
    const values = [name, gender, age, height, weight, status];

    sql.query(query, values, (err, result) => {
      if (err) {
        console.error('Error adding stunting: ', err);
        res.status(500).json({ error: 'Internal Server Error' });
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
        res.status(201).json(newStuntingEntry);
      }
    });
  })
  .catch(error => {
    console.error('Error requesting prediction from Flask API: ', error);
    res.status(500).json({ error: 'Internal Server Error' });
  });
};

module.exports = {
  getAllStuntings,
  newStuntingEntry
};