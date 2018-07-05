$(function () {

    /*TODO
    notification message
    date departure not null
     */
    var urlSearching = "/schedule/direct";

    $('#searchingDirectForAllParameters').click(function () {
        $('#searchingDirectForAllParameters').addClass('active');
        $('#searchingByStationsAndDate').removeClass('active');
        $('#searchingByDate').removeClass('active');
        $('#searchingByTrainAndDate').removeClass('active');
        $('#stationDeparture').prop('disabled', false);
        $('#stationArrival').prop('disabled', false);
        $('#train').prop('disabled', false);
    });

    $('#searchingByStationsAndDate').click(function () {
        $('#searchingByStationsAndDate').addClass('active');
        $('#searchingByDate').removeClass('active');
        $('#searchingByTrainAndDate').removeClass('active')
        $('#searchingDirectForAllParameters').removeClass('active');
        $('#stationDeparture').prop('disabled', false);
        $('#stationArrival').prop('disabled', false);
        $('#train').prop('disabled', true);
        urlSearching = "/schedule/directByStations";

    });

    $('#searchingByTrainAndDate').click(function () {
        $('#searchingByStationsAndDate').removeClass('active');
        $('#searchingByDate').removeClass('active');
        $('#searchingByTrainAndDate').addClass('active');
        $('#searchingDirectForAllParameters').removeClass('active');
        $('#train').prop('disabled', false);
        $('#stationDeparture').prop('disabled', true);
        $('#stationArrival').prop('disabled', true);
        urlSearching = "/schedule/directByTrain";
    });

    $('#searchingByDate').click(function () {
        $('#searchingByStationsAndDate').removeClass('active');
        $('#searchingByTrainAndDate').removeClass('active');
        $('#searchingByDate').addClass('active');
        $('#searchingDirectForAllParameters').removeClass('active');
        $('#train').prop('disabled', true);
        $('#stationDeparture').prop('disabled', true);
        $('#stationArrival').prop('disabled', true);
        urlSearching = "/schedule/directByDates";
    })

    $('#btnSearchSchedule').click(function (event) {
        event.preventDefault();
        var token = $("meta[name='_csrf']").attr("content");
        var scheduleDTO = {
            stationDepartureName:
                $('#stationDeparture').val(),
            stationArrivalName:
                $('#stationArrival').val(),
            trainName:
                $('#train').val(),
            dateDeparture:
                $('#dateDeparture').val(),
            dateArrival:
                $('#dateArrival').val()
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
            if (response.length != 0) {
                $('#tableSchedule').empty();
                $('#mainScheduleContainer').show();
                for (var i = 0; i < response.length; i++) {
                    $('#tableSchedule').append("<tr><th scope='row'>" + response[i].stationDepartureName + "</th>" +
                        "<th scope='row'>" + response[i].stationArrivalName + "</th>" +
                        "<th scope='row'>" + response[i].trainName + "</th>" +
                        "<th scope='row'>" + response[i].dateDeparture + "</th>" +
                        "<th scope='row'>" + response[i].dateArrival + "</th>"
                        + "</tr>");
                }
            }
            else
                $('#mainScheduleContainer').hide();
        }).fail(function (qXHR, textStatus, errorThrown) {
            alert(JSON.stringify(qXHR));
            console.log('request: ', qXHR);
            console.log('status text: ', textStatus);
            console.log('thrown error: ', JSON.stringify(errorThrown));
        });
    });
});