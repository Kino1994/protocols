function GetWeather(call, callback){    

    console.log('Request received: '+JSON.stringify(call));

    var { city } = call.request;

    var randomTimeInMs = Math.random() * (2000) + 1000;

    (async function() {
        await executeLater(functionToExecute, randomTimeInMs)

        var vocal = city.toUpperCase().substr(0,1);

        var isVocal = vocal === "A" | vocal === "E" | vocal === "I" | vocal === "O" | vocal === "U"
                    | vocal === "Á" | vocal === "É" | vocal === "Í" | vocal === "Ó" | vocal === "Ú"
                    | vocal === "À" | vocal === "È" | vocal === "Ì" | vocal === "Ò" | vocal === "Ù"
                    | vocal === "Â" | vocal === "Ê" | vocal === "Î" | vocal === "Ô" | vocal === "Û"
                    | vocal === "Ä" | vocal === "Ë" | vocal === "Ï" | vocal === "Ö" | vocal === "Ü"
                    | vocal === "Ã" | vocal === "Õ" 

        if (isVocal) {
            callback(null, { city: city, weather: "Rainy"});
        }

        else {
            callback(null, { city: city, weather: "Sunny"});
            
        }
    }());
}

const functionToExecute = (delay) => console.log(`Ended after ${delay}`)

const executeLater = (functionToExecute, delay) => {
    return new Promise((resolve) => {
        setTimeout(() => {
            resolve(functionToExecute(delay))
        }, delay);
    });
}

exports.GetWeather = GetWeather