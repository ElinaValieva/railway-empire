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
    var token = $("meta[name='_csrf']").attr("content");
    var urlSearching = "/train/all";
    var dataResponse = null;
    $.ajax({
        beforeSend: function (xhr) {
            xhr.setRequestHeader('X-CSRF-TOKEN', token);
        },
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        method: "GET",
        url: urlSearching,
        async: false
    }).done(function (response) {
        dataResponse = response;
    }).fail(function (qXHR, textStatus, errorThrown) {
        var messageError = JSON.parse(qXHR.responseText)['message'].split('[MESSAGE]:')[1];
        swal("Oops..", messageError, "error");
        console.log('request: ', qXHR);
        console.log('status text: ', textStatus);
        console.log('thrown error: ', JSON.stringify(errorThrown));
    });
    return dataResponse;
};

var getAllTrains = function () {
    var token = $("meta[name='_csrf']").attr("content");
    var urlSearching = "/train/allTrains";
    var dataResponse = null;
    $.ajax({
        beforeSend: function (xhr) {
            xhr.setRequestHeader('X-CSRF-TOKEN', token);
        },
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        method: "GET",
        url: urlSearching,
        async: false
    }).done(function (response) {
        dataResponse = response;
    }).fail(function (qXHR, textStatus, errorThrown) {
        var messageError = JSON.parse(qXHR.responseText)['message'].split('[MESSAGE]:')[1];
        swal("Oops..", messageError, "error");
        console.log('request: ', qXHR);
        console.log('status text: ', textStatus);
        console.log('thrown error: ', JSON.stringify(errorThrown));
    });
    return dataResponse;
};

var deleteTrain = function (name) {
    var urlSearching = "/train/delete/" + name;
    deleteRequest(urlSearching, "You delete train");
};


var updateTrain = function (train) {
    var urlSearching = "/train/update";
    putRequest(train, urlSearching, "You edit train.");
};