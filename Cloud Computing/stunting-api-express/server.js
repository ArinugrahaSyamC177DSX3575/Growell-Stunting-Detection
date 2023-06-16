const express = require('express');
const { getAllStuntings, newStuntingEntry } = require('./handlers/stunting');
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

app.get('/api/get-all-stuntings', getAllStuntings);
app.post('/api/new-stunting-entry', newStuntingEntry);


app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});