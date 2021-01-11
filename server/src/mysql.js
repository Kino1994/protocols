const { Sequelize } = require('sequelize');

function getSequelize() {
    sequelize = new Sequelize('eoloplants', 'root', 'pass', {
        host: 'localhost',
        dialect: 'mysql'
    });
    return sequelize;
}

module.exports = { getSequelize }