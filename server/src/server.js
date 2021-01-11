const { Sequelize } = require('sequelize');
const express = require('express');
const mysql = require('./mysql.js');
const eoloRouter = require('./routers/eoloRouter');

async function main() {

    mysql.getSequelize();
    
    const app = express();    
    app.use(express.json());
    app.use('/api/eoloplants', eoloRouter);

    app.listen(3000, () => {
        console.log('Example app listening on port 3000!');
    });

}

main();