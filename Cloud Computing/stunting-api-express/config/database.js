const mysql = require('mysql2');
const { promisify } = require("util");
const connection = mysql.createConnection({
    host     : '34.101.170.151',
    user     : 'root',
    password : 'stunting123',
    database : 'db-stunting'
})
connection.connect((err) => {
    if(!err) {
        console.log("Database is connected!");
    } else {
        console.log("Error connecting database!");
    }
    });

connection.query = promisify(connection.query);

module.exports = connection;


