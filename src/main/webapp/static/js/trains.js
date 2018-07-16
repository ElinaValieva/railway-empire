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
    var train = $('#trainItemsRailway').val();
    var carriage = $('#cntCarriageItemsRailway').val();
    if (checkFieldsTrains(train, carriage)) {
        var scheduleDTO = {
            trainName: train,
            cntCarriage: carriage,
            cntSeats: $('#cntSeatsItemsRailway').val()
        };
        var urlSearching = "/train/add";
        postRequest(scheduleDTO, urlSearching, "You add new train");
    }
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


var reestablishTrain = function (station) {
    var urlSearching = "/train/reestablish/" + station;
    getRequest(urlSearching);
};

var getAllDeletedTrains = function () {
    var urlSearching = "/train/deletedTrains";
    return getRequest(urlSearching);
};
var checkFieldsTrains = function (train, carriage) {
    if (!/^[a-zA-Z]+$/.test(train.charAt(0))) {
        swal({
            title: 'Oops...',
            text: 'Fields for train name should start with letter. Try again!',
            type: 'error'
        });
        return false;
    }
    else if (carriage < 0 || carriage > 25) {
        swal({
            title: 'Oops...',
            text: 'Fields for carriage wrong, max count carriage should be 25! Try again.',
            type: 'error'
        });
        return false;
    }
    else return true;
};