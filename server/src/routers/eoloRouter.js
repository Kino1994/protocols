const express = require('express');
const {EoloPlant} = require('../models/eoloplant.js');

let eoloRouter = express.Router();

eoloRouter.route('')
    .post(async(req, res) => {
        if (!req.body.city) {
            return res.status(400).send({ "error": "Missing City" });
        }
        let eoloPlant = new EoloPlant({
            city: req.body.city,
            progress: req.body.progress,
            completed: req.body.completed,
            weather: req.body.weather,
            landscape: req.body.landscape
        });
        eoloPlant = await EoloPlant.create({ city: eoloPlant.city, progress: 0, completed: false, weather: null, landscape: null});
        return res.status(201).jsonp(eoloPlant);
    })
    .put(async (req, res) =>{
        if (!req.body.id) {
            res.status(404).send('Missing id');
        } else {
            let eoloPlant = new EoloPlant({
                id : req.body.id,
                city: req.body.city,
                progress: req.body.progress,
                completed: req.body.completed,
                weather: req.body.weather,
                landscape: req.body.landscape
            });
            await EoloPlant.update({ city: eoloPlant.city, progress: eoloPlant.progress, completed: eoloPlant.completed,
                weather: eoloPlant.weather, landscape: eoloPlant.landscape},
                    { where: { id: eoloPlant.id } });
            return res.status(201).jsonp(eoloPlant);
        }
    });

eoloRouter.route('/:id')
    .get( async (req, res) => {
        if (!req.params.id) {
            res.status(404).send('Missing id');
        } else {
            let eoloPlant = await EoloPlant.findByPk(Number(req.params.id));
            console.log(eoloPlant);
            if (eoloPlant.city){
                res.status(200).jsonp(eoloPlant);
            }
            else {
                return res.status(404).send({ "error": "Plant not found" });
            }
        }
    });

module.exports = eoloRouter;