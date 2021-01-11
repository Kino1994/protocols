const { Sequelize, DataTypes, Model } = require('sequelize');
const sequelize = require('../mysql.js').getSequelize();

class EoloPlant extends Model { }

    EoloPlant.init({
      id: {
        type: DataTypes.INTEGER,
        autoIncrement: true,
        primaryKey: true
      },
      city: {
        type: DataTypes.STRING,
        allowNull: false
      },
      progress: {
        type: DataTypes.INTEGER,
        allowNull: false,
        defaultValue: 0
      },
      completed: {
        type: DataTypes.BOOLEAN,
        allowNull: false,
        defaultValue: false
      },
      weather: DataTypes.STRING,
      landscape: DataTypes.STRING
    },{
        sequelize,
        modelName: 'EoloPlant',
        tableName: 'EoloPlants'
      });   

module.exports = {EoloPlant}