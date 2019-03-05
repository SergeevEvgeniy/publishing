function LoaderData() {

    function sendRequest(url) {
        return $.when($.ajax({
            url: url,
            method: 'GET'
        }));
    }

    this.recieveData = function (obj, callBack) {
        obj.forEach(function (item) {
            sendRequest(item.url)
            .then(function (response) {
                console.log(`Загружен с ${item.url}`)
                item.data = response;
                callBack(item);
            })
            .catch(function (error) {
                console.log(`error ${error}`);
            })
        })
    };

    /**
     * roudmap
     */
    this.recieveSingleData = function (url) {
        return sendRequest(url)
            .then(function (response) {
                return response;
            })
            .catch(function (error) {
                return error;              
            });
    };
}

module.exports = {
    LoaderData: LoaderData
};