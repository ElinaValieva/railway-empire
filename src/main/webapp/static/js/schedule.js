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
        // urlSearching = "/schedule/directByStations";
        urlSearching = "/schedule/transferByStations"

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
                $('#dateArrival').val(),
            timeInTrip: ''
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
                $('#tableSchedule').html(response);
                for (var i = 0; i < response.length; i++)
                    setContentForSchedule(response[i]);
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

    var setContentForSchedule = function (response) {
        $('#tableSchedule').append("<tr>" +
            "<th scope='row'><img src='static/images/icoschedule.png'>" +
            "<div>" + response.trainName + "</div>" +
            "</th>" +
            "<th scope='row'><div class='text-danger'>" + response.dateDeparture + "</div>" +
            "<div class='font-weight-normal'>" + response.stationDepartureName + "</div>" +
            "</th>" +
            "<th scope='row'>" +
            "<div><img src='/static/images/arrow-13-xxl.png' height='50'></div>" +
            "<div>" + getDelayBetweenTwoDates(response.dateDeparture, response.dateArrival) + "</div>" +
            "</th>" +
            "<th scope='row'><div class='text-danger'>" + response.dateArrival + "</div>" +
            "<div class='font-weight-normal'>" + response.stationArrivalName + "</div>" +
            "</th></tr>");
    }

    var getDelayBetweenTwoDates = function (dateStart, dateEnd) {
        var hourDiff = new Date(dateEnd).getTime() - new Date(dateStart).getTime();
        var minDiff = hourDiff / 60 / 1000; //in minutes
        var hDiff = hourDiff / 3600 / 1000; //in hours
        var humanReadable = {};
        humanReadable.hours = Math.floor(hDiff);
        humanReadable.minutes = minDiff - 60 * humanReadable.hours;
        if (humanReadable.minutes == 0)
            humanReadable.minutes = '00';
        return humanReadable.hours + ':' + humanReadable.minutes;
    }
});