/**
 * @author Valieva Elina
 *
 * TRAIN:
 * addTrain - create train with ajax request
 * getTrain - get trains with info with last schedule
 * getTrains - get trains
 * deleteTrain - remove train by train name
 * updateTrain - update trains name
 */


var addTrain = function () {

    var scheduleDTO = {
        trainName: $('#trainItemsRailway').val(),
        cntCarriage: $('#cntCarriageItemsRailway').val(),
        cntSeats: $('#cntSeatsItemsRailway').val()
    };
    var urlSearching = "/train/add";
    postRequest(scheduleDTO, urlSearching, "You add new train");
};

var getTrains = function () {
    var urlSearching = "/train/all";
    return getRequest(urlSearching);
};

var getAllTrains = function () {
    var urlSearching = "/train/allTrains";
    return getRequest(urlSearching);
};

var deleteTrain = function (name) {
    var urlSearching = "/train/delete/" + name;
    deleteRequest(urlSearching, "You delete train");
};


var updateTrain = function (train) {
    var urlSearching = "/train/update";
    putRequest(train, urlSearching, "You edit train.");
};