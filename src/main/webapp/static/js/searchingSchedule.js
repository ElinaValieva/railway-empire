$(function () {

    var token = $("meta[name='_csrf']").attr("content");

    autocomplete();

    $('#searchingBtnByDates').click(function (event) {
        event.preventDefault();
        var urlSearching = "/schedule/directByDates";
        var scheduleDTO = {
            stationDepartureName: '',
            stationArrivalName: '',
            trainName: '',
            dateDeparture: $('#dateDepartureInSearchingByDates').val(),
            dateArrival: $('#dateArrivalInSearchingByDates').val()
        };
        $.ajax({
            beforeSend: function (xhr) {
                xhr.setRequestHeader('X-CSRF-TOKEN', token);
            },
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            method: "POST",
            url: urlSearching,
            data: JSON.stringify(scheduleDTO),
        }).done(function (response) {
            $('#containerForSearching').show();
            $('#containerForSearchingDirect').empty();
            for (var i = 0; i < response.length; i++)
                setContextForDirect(response[i]);
        }).fail(function (qXHR, textStatus, errorThrown) {
            var messageError = JSON.parse(qXHR.responseText)['message'].split('[MESSAGE]:')[1];
            swal("Oops..", messageError, "error");
            console.log('request: ', qXHR);
            console.log('status text: ', textStatus);
            console.log('thrown error: ', JSON.stringify(errorThrown));
        });
    });

    $('#searchingBtnByStationsAndDates').click(function () {
        event.preventDefault();
        var urlSearching = "/schedule/directByStations";
        var scheduleDTO = {
            stationDepartureName: $('#stationDepartureSearchingByStationsAndDates').val(),
            stationArrivalName: $('#stationArrivalSearchingByStationsAndDates').val(),
            trainName: '',
            dateDeparture: $('#dateDepartureSearchingByStationsAndDates').val(),
            dateArrival: $('#dateArrivalSearchingByStationsAndDates').val()
        };
        $.ajax({
            beforeSend: function (xhr) {
                xhr.setRequestHeader('X-CSRF-TOKEN', token);
            },
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            method: "POST",
            url: urlSearching,
            data: JSON.stringify(scheduleDTO),
        }).done(function (response) {
            $('#containerForSearching').show();
            $('#containerForSearchingDirect').empty();
            $('#containerForSearchingTransfer').empty();
            for (var i = 0; i < response.length; i++)
                setContextForDirect(response[i]);
            if ($('input:checkbox').prop('checked')) {
                urlSearching = "/schedule/transferByStations";
                $.ajax({
                    beforeSend: function (xhr) {
                        xhr.setRequestHeader('X-CSRF-TOKEN', token);
                    },
                    headers: {
                        'Content-Type': 'application/json',
                        'Accept': 'application/json'
                    },
                    method: "POST",
                    url: urlSearching,
                    data: JSON.stringify(scheduleDTO),
                }).done(function (response) {
                    if (response.length != 0) {
                        $('#mainScheduleContainer').show();
                        $('#containerForSearchingTransfer').empty();
                        for (var i = 0; i < response.length; i++) {
                            setContextForTransfer(response[i]);
                        }
                    }
                }).fail(function (qXHR, textStatus, errorThrown) {
                    var messageError = JSON.parse(qXHR.responseText)['message'].split('[MESSAGE]:')[1];
                    swal("Oops..", messageError, "error");
                    console.log('request: ', qXHR);
                    console.log('status text: ', textStatus);
                    console.log('thrown error: ', JSON.stringify(errorThrown));
                });
            }
            else $('#containerForSearchingTransfer').empty();
        }).fail(function (qXHR, textStatus, errorThrown) {
            var messageError = JSON.parse(qXHR.responseText)['message'].split('[MESSAGE]:')[1];
            swal("Oops..", messageError, "error");
            console.log('request: ', qXHR);
            console.log('status text: ', textStatus);
            console.log('thrown error: ', JSON.stringify(errorThrown));
        });
    });

    $('#searchingBtnByAllParameters').click(function (event) {
        event.preventDefault();
        var urlSearching = "/schedule/direct";
        var scheduleDTO = {
            stationDepartureName: $('#stationDepartureSearchingByAllParameters').val(),
            stationArrivalName: $('#stationArrivalSearchingByAllParameters').val(),
            trainName: $('#trainSearchingByAllParameters').val(),
            dateDeparture: $('#dateDepartureSearchingByAllParameters').val(),
            dateArrival: $('#dateArrivalSearchingByAllParameters').val()
        };
        $.ajax({
            beforeSend: function (xhr) {
                xhr.setRequestHeader('X-CSRF-TOKEN', token);
            },
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            method: "POST",
            url: urlSearching,
            data: JSON.stringify(scheduleDTO),
        }).done(function (response) {
            $('#containerForSearching').show();
            $('#containerForSearchingDirect').empty();
            for (var i = 0; i < response.length; i++)
                setContextForDirect(response[i]);
        }).fail(function (qXHR, textStatus, errorThrown) {
            var messageError = JSON.parse(qXHR.responseText)['message'].split('[MESSAGE]:')[1];
            swal("Oops..", messageError, "error");
            console.log('request: ', qXHR);
            console.log('status text: ', textStatus);
            console.log('thrown error: ', JSON.stringify(errorThrown));
        });
    });

    $('#searchingBtnByDatesAndTrain').click(function (event) {
        event.preventDefault();
        var urlSearching = "/schedule/directByTrain";
        var scheduleDTO = {
            stationDepartureName: '',
            stationArrivalName: '',
            trainName: $('#trainInSearchingByDatesAndTrain').val(),
            dateDeparture: $('#dateDepartureInSearchingByDatesAndTrain').val(),
            dateArrival: $('#dateArrivalInSearchingByDatesAndTrain').val()
        };
        $.ajax({
            beforeSend: function (xhr) {
                xhr.setRequestHeader('X-CSRF-TOKEN', token);
            },
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            method: "POST",
            url: urlSearching,
            data: JSON.stringify(scheduleDTO),
        }).done(function (response) {
            alert(JSON.stringify(response));
            $('#containerForSearching').show();
            $('#containerForSearchingDirect').empty();
            for (var i = 0; i < response.length; i++)
                setContextForDirect(response[i]);
        }).fail(function (qXHR, textStatus, errorThrown) {
            var messageError = JSON.parse(qXHR.responseText)['message'].split('[MESSAGE]:')[1];
            swal("Oops..", messageError, "error");
            console.log('request: ', qXHR);
            console.log('status text: ', textStatus);
            console.log('thrown error: ', JSON.stringify(errorThrown));
        });
    });

    var setContextForDirect = function (response) {
        var disabledFlag = response.price == 0 ? 'hidden' : '';
        var price = response.price == 0 ? 'Train arrived' : '$' + response.price;
        $('#containerForSearchingDirect').append("<tr><th scope='row'><div><div><img src='static/images/trainIcoWarn.png'></div><label class='font-weight-normal'>" +
            response.trainName +
            "</label></div></th><td><br><div><label class='font-weight-normal'>" +
            response.dateDeparture +
            "</label></div><div><label class='font-weight-bold'>" +
            response.stationDepartureName +
            "</label></div></td><td><div style='margin-left: 50%'><label class='font-weight-light'>" +
            getDelayBetweenTwoDates(response.dateDeparture, response.dateArrival) +
            "</label></div><div style='margin-left: 50%'><img src='static/images/arrow-13-xxl.png' width='50'></div></td><td><br>" +
            "<div style='margin-left: 50%'><label class='font-weight-normal'>" +
            response.dateArrival +
            "</label></div><div style='margin-left: 50%'><label class='font-weight-bold'>" +
            response.stationArrivalName +
            "</label></div></td><td><br>" +
            "<div style='margin-left: 50%'><label>" + price +
            "</label></div><button type='button' style='margin-left: 50%' class='btn btn-lg btn-outline-warning btnFindTicket' id='" + response.id + "'" + disabledFlag +
            ">FIND TICKET</button>" +
            "</td></tr>");
    };

    var setContextForTransfer = function (response) {
        var disabledFlag = response.price == 0 ? 'hidden' : '';
        var price = response.price == 0 ? 'Train arrived' : '$' + response.price;
        $('#containerForSearchingTransfer').append("<tr><th scope='row'><div><div><img src='static/images/trainIcoWarn.png'></div><label class='font-weight-normal'>" +
            response.trainDepartureName +
            "</label></div></th><td><br><div><label class='font-weight-normal'>" +
            response.dateDeparture +
            "</label></div><div><label class='font-weight-bold'>" +
            response.stationDepartureName +
            "</label></div></td><td><div><label class='font-weight-light'>" +
            getDelayBetweenTwoDates(response.dateDeparture, response.dateArrival) +
            "</label></div><img src='static/images/arrow-13-xxl.png' width='50'></td><td><br><div><label class='font-weight-normal'>" +
            response.dateIntermediateDeparture +
            "</label></div><div><label class='font-weight-bold'>" +
            response.stationIntermediateName +
            "</label></div></td>" +
            "<td scope='row'><div><img src='static/images/hourglass.png'></div><br><label>" +
            getDelayBetweenTwoDates(response.dateIntermediateDeparture, response.dateIntermediateArrival) +
            "</label><div><img src='static/images/share-option.png'></div><th scope='row'><div><div><img src='static/images/trainIcoWarn.png'></div><label class='font-weight-normal'>" +
            response.trainArrivalName +
            "</label></div></th><td><br><div><label class='font-weight-normal'>" +
            response.dateIntermediateArrival +
            "</label></div><div><label class='font-weight-bold'>" +
            response.stationIntermediateName +
            "</label></div></td><td><div><label class='font-weight-light'>" +
            getDelayBetweenTwoDates(response.dateDeparture, response.dateArrival) +
            "</label></div><img src='static/images/arrow-13-xxl.png' width='50'></td><td><br><div><label class='font-weight-normal'>" +
            response.dateArrival +
            "</label></div><div><label class='font-weight-bold'>" +
            response.stationArrivalName +
            "</label></div></td><td><br>" +
            "<label>" + price + ' ' + "</label>" +
            "<button class='btn btn-lg btn-outline-warning btnFindTicketTransfer' id='dep"
            + response.idScheduleDeparture +
            "arr" + response.idScheduleArrival + "'" + disabledFlag + ">FIND TICKET</button></td></tr>"
        );
    };

    var getDelayBetweenTwoDates = function (dateStart, dateEnd) {
        var hourDiff = new Date(dateEnd).getTime() - new Date(dateStart).getTime();
        var minDiff = Math.ceil(hourDiff / 60 / 1000); //in minutes
        var hDiff = hourDiff / 3600 / 1000; //in hours
        var humanReadable = {};
        humanReadable.hours = Math.floor(hDiff);
        humanReadable.minutes = minDiff - 60 * humanReadable.hours;
        if (humanReadable.minutes >= 0 && humanReadable.minutes < 10)
            (humanReadable.minutes == 0) ? humanReadable.minutes = '00' : humanReadable.minutes = '0' + humanReadable.minutes;
        return humanReadable.hours + ':' + humanReadable.minutes;
    };

    $(this).on('click', '.btnFindTicket', function () {
        var id = $(this).attr('id'); // $(this) refers to button that was clicked
        window.location = "/seat?id=" + id;
    });

    $(this).on('click', '.btnFindTicketTransfer', function () {
        var idDeparture = $(this).attr('id').split('arr')[1];
        var idArrival = $(this).attr('id').split('dep')[1].split('arr')[0];
        window.location = "/seat?id=" + idDeparture;
        window.open('http://localhost:8000/seat?id=' + idArrival);
    });
});


var getSchedules = function () {
    var token = $("meta[name='_csrf']").attr("content");
    var urlSearching = "/schedule/all";
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

var deleteSchedule = function (id, callback) {
    var urlSearching = "/schedule/delete/" + id;
    deleteRequest2(urlSearching, "You delete schedule", callback);
};

var updateSchedule = function (schedule, callback) {
    var urlSearching = "/schedule/update";
    putRequest2(schedule, urlSearching, "You edit schedule.", callback);
};

var addSchedule = function () {
    var date = $('#dateDepartureItemsRailway').val();
    if (checkFieldsSchedule(date)) {
        var dateArrival = '';
        if ($('#dateArrivalItemsRailway').val() != "")
            dateArrival = $('#dateArrivalItemsRailway').val().replace("T", " ") + ":00";
        var scheduleDTO = {
            stationDepartureName:
                $('#stationDepartureItemsRailway').val(),
            stationArrivalName:
                $('#stationArrivalItemsRailway').val(),
            trainName:
                $('#trainItemsRailway').val(),
            dateDeparture:
            $('#dateDepartureItemsRailway').val().replace("T", " ") + ":00",
            dateArrival: dateArrival
        };
        var urlSearching = "/schedule/add";
        postRequest(scheduleDTO, urlSearching, "You add new schedule", "success");
    }
};

var checkFieldsSchedule = function (date) {
    if (date == "") {
        swal({
            title: 'Oops..',
            text: 'Date departure is empty. Try again!',
            type: 'error'
        });
        return false;
    }
    else return true;
};