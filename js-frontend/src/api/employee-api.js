var employee = require('../../data/employee.json');

var EmployeeAPI = {
    // eslint-disable-next-line no-unused-vars
    getEmployeeByArticlesIds: function getEmployeeByArticlesIds(ids) {
        return Promise.resolve(employee);
    }
};

module.exports = EmployeeAPI;
