const EmployeeAPI = {
    getEmployeeByIds: function (ids) {
        return fetch('http://localhost:3000/api/employee/id', {
            method: 'POST',
            headers: {
                'Content-type': 'application/json',
            },
            body: ids,
            mode: 'cors',
        });
    },
};

module.exports = EmployeeAPI;