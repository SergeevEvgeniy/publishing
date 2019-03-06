var express = require('express');
var app = express();
var bodyParser = require('body-parser');
var cors = require('cors');
var port = 3010;
app
    .use(cors())
    .use(bodyParser.json())
    .use(bodyParser.urlencoded({
        extended: true
    }))
    .get('/', (req, res) => {
        res.send('hello');
    })
    .get('/api/publishings', (req, res) => {
        var publishings = require('./data/publishings');
        res.json(publishings);
    })
    .get('/api/topics', (req, res) => {
        var publishings = require('./data/topics');
        res.json(publishings);
    })
    .get('/api/issues', function (req, res) {
        var issues = require('./data/issues');
        var id = + req.query.id;
        var publishingIssues = issues.find((issues) => issues.publishingId === id);
        res.json(publishingIssues);
    })
    .post('/api/journalists', function (req, res) {
        var journalists = require('./data/journalists');
        var body = req.body;
        res.json(journalists);
    })
    .listen(port, () => {
        console.log('App work on ' + port + ' locallhost');
    });