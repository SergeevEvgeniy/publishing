var EmployeeAPI = {
    getEmployeeById: function getEmployeeById(id) {
        return fetch('http://localhost:3000/api/employee/id', {
            method: 'POST',
            headers: {
                'Content-type': 'application/json',
            },
            body: id,
            mode: 'cors',
        });
    },
};

module.exports = EmployeeAPI;