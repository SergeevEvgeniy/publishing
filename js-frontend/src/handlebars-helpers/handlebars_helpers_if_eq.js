var Handlebars = require('handlebars/runtime');

Handlebars.registerHelper('if_eq', function handlebarsHelper(a, b, opts) {
    if (a === b) {
        // eslint-disable-next-line no-invalid-this
        return opts.fn(this);
    } else {
        // eslint-disable-next-line no-invalid-this
        return opts.inverse(this);
    }
});
