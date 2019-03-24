var Handlebars = require('handlebars/runtime');

Handlebars.registerHelper('inc', function handlebarsHelper(value) {
    return parseInt(value) + 1;
});
