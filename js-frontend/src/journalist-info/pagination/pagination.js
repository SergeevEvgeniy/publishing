var $ = require('jquery');
var paginationListTemplate = require('./pagination-list.hbs');
var paginationTogglerTemplate = require('./pagination-toggler.hbs');
var tableDataTemplate = require('./table-data.hbs');


//Переделать
function PaginationComponent($tbodyEl, $paginationEl, $selectElement) {
    var startIndex = 0;
    var componentData = [];
    var availableTogglers = [5, 10, 25, 50];
    var maxPageNumber = 0;
    var currentToggler = availableTogglers[0];
    var stopIndex = currentToggler;
    var currentPageObj = {
        page: 1,
        continueNext: true,
        continueBack: false
    };

    function render() {
        checkPageCount();
        createPaginationOnOptionChange()
        createPageList();
        appendDataToTable();
    }

    this.renderTable = function (data) {
        componentData = data;
        render();
    };

    //сюда ещё нужно ивент для page li добавить
    $selectElement.change(function(event) {
        changePages(event.target);
    });

    function checkPageCount() {
        let length = componentData.length;

        if (length < 5) {
            $selectElement.hide();
            return;
        }

        maxPageNumber = Math.ceil(length / currentToggler);
        $selectElement.append(paginationTogglerTemplate({
            togglers: availableTogglers
        }));
        $selectElement.change(function(event) {
            changePages(event.target);
        });
    }
   
    function animationOnPageClick() {
        var page = currentPageObj.page;
        var arrayPage = [];
        var pageObj = {};

        if (page - 1 === 0) {
            pageObj.index = page - 1;
            pageObj.stop = page + 2;
        } else if (page - 3 < 0) {
            pageObj.index = page - 2;
            pageObj.stop = page + 2;
        } else {
            pageObj.index = page - 3;
            pageObj.stop = page + 2;
        }

        if (maxPageNumber < pageObj.stop) {
            pageObj.stop = maxPageNumber;
        }

        $paginationEl.empty();
        for (; pageObj.index < pageObj.stop; pageObj.index++) {
            let obj = {};
            let index = pageObj.index + 1;
            if (page === index) {
                obj.class= 'active page-item';
            } else {
                obj.class = 'page-item';
            }

            obj.type = index;
            arrayPage.push(obj);
        }

        var threeDots = {
            type: '...',
            class: 'page-item'
        };

        if (page > 3) {
            arrayPage.unshift(threeDots);
        }
        if (maxPageNumber - page > 2) {
            arrayPage.push(threeDots);
        }

        $paginationEl.append(paginationListTemplate({
            page: arrayPage
        }));
        $paginationEl.children().click(function (event) {
            event.preventDefault();
            toggleData(event.target);
        });
    }
    function createPageList() {
        var length = componentData.length;

        if (length === 0) {
            return;
        }

        var pageLists = [];
        for (let item = 1; item <= maxPageNumber; item++) {
            let obj = {
                type: item,
                class: 'page-item'
            };

            if (item > 3) {
                obj.type = '...';
                pageLists.push(obj);
                pageLists[0].class = 'page-item active';
                $paginationEl.append(paginationListTemplate({
                    page: pageLists
                }));
                $paginationEl.children().click(function (event) {
                    event.preventDefault();
                    toggleData(event.target);
                });
                
                return;
            }
            pageLists.push(obj);
        }

        if (maxPageNumber <= 3) {
            pageLists[0].class = 'page-item active';
            $paginationEl.append(paginationListTemplate({
                page: pageLists
            }));
            $paginationEl.children().click(function (event) {
                event.preventDefault();
                toggleData(event.target);
            }); 
        }
    }

    function createPaginationOnOptionChange() {
        $paginationEl.empty();
        currentPageObj.continueBack = false;
        currentPageObj.continueNext = true;
        let length = componentData.length;
        maxPageNumber = Math.ceil(length / currentToggler);
    }

    function appendDataToTable() {
        $tbodyEl.empty();
        var dataArray = [];
        for (; startIndex < componentData.length; startIndex++) {
            if (startIndex >= stopIndex) {
                break;
            }

            let currentItem = componentData[startIndex];
            let dataObj = {
                title: currentItem.title,
                journal: currentItem.journal,
                number: currentItem.number,
                date: currentItem.date
            }
            dataArray.push(dataObj);
        }
        $tbodyEl.append(tableDataTemplate({
            data: dataArray
        }));
    }

    var checkPageObj = {
        onBack: function () {
            if (+currentPageObj.page === 1) {
                currentPageObj.continueBack = false;
                currentPageObj.continueNext = true;
            } else {
                currentPageObj.continueBack = true;
                currentPageObj.continueNext = true;
            }
        },
        onNext: function () {
            if (+currentPageObj.page === maxPageNumber) {
                currentPageObj.continueNext = false;
                currentPageObj.continueBack = true;
            } else {
                currentPageObj.continueNext = true;
                currentPageObj.continueBack = true;
            }
        },
        onUserClick: function () {
            if (+currentPageObj.page === 1) {
                currentPageObj.continueBack = false;
                currentPageObj.continueNext = true;
                return;
            }

            this.onNext();
        }
    };

    function toggleData (element) {
        let value = element.dataset.type;

        if (value === 'back' && currentPageObj.continueBack) {
            currentPageObj.page--;
            startIndex = currentPageObj.page * currentToggler - currentToggler;
            stopIndex -= currentToggler;
            checkPageObj.onBack();
            animationOnPageClick()
            appendDataToTable();
            return;
        } else if (value === 'next' && currentPageObj.continueNext) {
            currentPageObj.page++;
            startIndex = currentPageObj.page * currentToggler - currentToggler;
            stopIndex = currentPageObj.page * currentToggler;
            checkPageObj.onNext();
            animationOnPageClick()
            appendDataToTable();
            return;
        } else if (!isNaN(value)) {
            startIndex = value * currentToggler - currentToggler;
            stopIndex = value * currentToggler;
            currentPageObj.page = +value;
            checkPageObj.onUserClick();
            appendDataToTable();
            animationOnPageClick();
        }
    };
    function changePages (target) {
        currentToggler = $(target).val();
        startIndex =  0;
        stopIndex = currentToggler;
        currentPageObj.page = 1;
        appendDataToTable();
        createPaginationOnOptionChange();
        createPageList();
    };
}

module.exports = {
    PaginationComponent: PaginationComponent
};