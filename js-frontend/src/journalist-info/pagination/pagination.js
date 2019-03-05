var $ = require('jquery');
var paginationListTemplate = require('./pagination-list.hbs');
var paginationTogglerTemplate = require('./pagination-toggler.hbs');
var tableDataTemplate = require('./table-data.hbs');


//Переделать
function PaginationComponent(tbodyEl, paginationEl, selectElement) {
    var startIndex = 0;
    var pagesNumbers = [];
    var componentData = [];
    var availableTogglers = [5, 10, 25, 50];
    var cuurentToggler = availableTogglers[0];
    var stopIndex = cuurentToggler;
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
    selectElement.change(function(event) {
        changePages(event.target);
    });

    function checkPageCount() {
        let checkPage = 0;

        if (componentData.length < 5) {
            selectElement.hide();
            return;
        }

        for (let i = 0; i < componentData.length; i++) {
            if (i % 5 === 0) {
                checkPage++;
                pagesNumbers.push(checkPage);
            }
        }
        var pageToggler = [];
        availableTogglers.forEach(function (item) {
            pageToggler.push(item);
        });
        selectElement.append(paginationTogglerTemplate({
            togglers: pageToggler
        }));
        selectElement.change(function(event) {
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

        paginationEl.empty();
        for (; pageObj.index < pageObj.stop; pageObj.index++) {
            let obj = {};
            if (pagesNumbers[pageObj.index] === undefined) {
                break;
            }

            if (page === pageObj.index + 1) {
                obj.class= 'active page-item';
            } else {
                obj.class = 'page-item';
            }

            obj.type = pagesNumbers[pageObj.index];
            arrayPage.push(obj);
        }

        var threeDots = {
            type: '...',
            class: 'page-item'
        };

       
        if (page > 3) {
            arrayPage.unshift(threeDots);
        } else if (pagesNumbers.length - page > 2) {
            arrayPage.push(threeDots);
        }

        paginationEl.append(paginationListTemplate({
            page: arrayPage
        }));
        paginationEl.children().click(function (event) {
            event.preventDefault();
            toggleData(event.target);
        });
    }
    function createPageList() {
        if (pagesNumbers.length === 0) {
            return;
        }
        var pageLists = [];
        pagesNumbers.forEach(function (item, index) {
            let obj = {
                type: item,
                class: 'page-item'
            };

            if (index === 3 && pagesNumbers.length >= 3) {
                obj.type = '...';
                pageLists.push(obj);
                pageLists[0].class = 'page-item active';
                paginationEl.append(paginationListTemplate({
                    page: pageLists
                }));
                paginationEl.children().click(function (event) {
                    event.preventDefault();
                    toggleData(event.target);
                });
                
                return;
            }
            pageLists.push(obj);
        });
        if (pagesNumbers.length <= 3) {
            pageLists[0].class = 'page-item active';
            paginationEl.append(paginationListTemplate({
                page: pageLists
            }));
            paginationEl.children().click(function (event) {
                event.preventDefault();
                toggleData(event.target);
            }); 
        }
    }

    function createPaginationOnOptionChange() {
        paginationEl.empty();
        currentPageObj.continueBack = false;
        currentPageObj.continueNext = true;
        let checkPage = 0;
        let length = componentData.length;
        pagesNumbers = [];
        while (length > 0) {
            length = length - cuurentToggler;
            checkPage++;
            pagesNumbers.push(checkPage);
        }
    }

    function appendDataToTable() {
        tbodyEl.empty();
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
        tbodyEl.append(tableDataTemplate({
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
            let lastItemValue = pagesNumbers.length;
            if (+currentPageObj.page === lastItemValue) {
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
            startIndex = currentPageObj.page * cuurentToggler - cuurentToggler;
            stopIndex -= cuurentToggler;
            checkPageObj.onBack();
            animationOnPageClick()
            appendDataToTable();
            return;
        } else if (value === 'next' && currentPageObj.continueNext) {
            currentPageObj.page++;
            startIndex = currentPageObj.page * cuurentToggler - cuurentToggler;
            stopIndex = currentPageObj.page * cuurentToggler;
            checkPageObj.onNext();
            animationOnPageClick()
            appendDataToTable();
            return;
        } else if (!isNaN(value)) {
            startIndex = value * cuurentToggler - cuurentToggler;
            stopIndex = value * cuurentToggler;
            currentPageObj.page = +value;
            checkPageObj.onUserClick();
            appendDataToTable();
            animationOnPageClick();
        }
    };
    function changePages (target) {
        cuurentToggler = $(target).val();
        startIndex =  0;
        stopIndex = cuurentToggler;
        currentPageObj.page = 1;
        appendDataToTable();
        createPaginationOnOptionChange();
        createPageList();
    };
}

module.exports = {
    PaginationComponent: PaginationComponent
};