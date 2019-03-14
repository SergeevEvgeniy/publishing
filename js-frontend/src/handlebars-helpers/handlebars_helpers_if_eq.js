var Handlebars = require('handlebars/runtime');

Handlebars.registerHelper('if_eq', function conditionHelper(firstParam, secondParam, opts) {
    if (firstParam === secondParam) {
        return opts.fn(this);
    } else {
        return opts.inverse(this);
    }
});
