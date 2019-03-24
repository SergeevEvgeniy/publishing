var Handlebars = require('handlebars/runtime');

Handlebars.registerHelper('if_eq', function handlebarsHelper(firstParam, secondParam, opts) {
    if (firstParam === secondParam) {
        // eslint-disable-next-line no-invalid-this
        return opts.fn(this);
    } else {
        // eslint-disable-next-line no-invalid-this
        return opts.inverse(this);
    }
});
