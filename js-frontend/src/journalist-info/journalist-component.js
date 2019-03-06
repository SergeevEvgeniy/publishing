
var journalistTemplate = require('./journalist.hbs');
var $ = require('jquery');
var { InfoComponent } = require('./journ-info/journalist-info');
var { ArticlesComponent } = require('./journ-articles/journalist-articles');
var { StatisticsComponent } = require('./journ-statistics/journalist-statistics');
var { LoaderData } = require('./data-loader/data-loader');
var { Spinner } = require('./spinner/spinner');
var componentObj = {
    InfoComponent: InfoComponent,
    ArticlesComponent: ArticlesComponent,
    StatisticsComponent: StatisticsComponent
};


function JournalistStatComponent($element) {

    var componentData = {};
    var journalistInfoUrl = 'http://127.0.0.1:3000/getStat';
    var elementSelector = '#journalistInfo';
    var navigationSelector = '.nav-tabs';

    ($element).on('click', navigationSelector, function (event) {
        var target = event.target;
        while (target !== this) {
            if (target.tagName === 'LI') {
                var componentPick = target.dataset.type + 'Component';
                $(this).find('a').removeClass('active');
                $(target).children().first().addClass('active');
                var component = new componentObj[componentPick]($(elementSelector));
                component.setData(componentData);
                component.onActionInChildComponent(function () {
                    console.log('В дочернем элементе произошло событие.');
                });
                return;
            }
            target = target.parentNode;
        }
    });

    function render() {
        $element.empty().append(journalistTemplate({
            data: componentData
        }));
    }

    //получить инфомрмацию о журналисте + добавить параметр data
    this.appendComponent = function () {
        var spinner = new Spinner();
        spinner.appendSpinner($element);
        //наверно, я как-то должен получить id журналиста и отправить запрос
        var loaderData = new LoaderData();
        loaderData.recieveSingleData(journalistInfoUrl)
        .then(function (item) {
            componentData = item;
            render();
            spinner.removeSpinner();
            //по умолчанию вкладка информация
            //нужно ли его вызывать render ?
            var infoComponent = new componentObj.InfoComponent($(elementSelector));
            infoComponent.setData(componentData);
            //сделать эту вкладку активной. Но этот вариант не очень
            $(navigationSelector).find('a').first().addClass('active');
        });
    };
}

module.exports = {
    JournalistStatComponent: JournalistStatComponent
};

