const express = require('express');
const { getAllStuntings, newStuntingEntry, statusStunting } = require('./handlers/stunting');
const bodyParser = require('body-parser');

const app = express();
const port = 8080;

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

// Define your API endpoints using the router
app.get('/', (req, res) => {
  const response = {
    message: 'Hello from the API!'
  };
  res.json(response);
});

app.get('/api/getallstuntings', getAllStuntings);
app.post('/api/newstuntingentry', newStuntingEntry);
app.post('/api/statusstunting', statusStunting);


app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});

