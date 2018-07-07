$(function () {

    /*TODO
    notification message
    date departure not null
     */

    var urlSearching = "/schedule/direct";
    $('#checkBoxId').prop('hidden', true);
    /**
     * check parameters for searching
     */
    $('#searchingDirectForAllParameters').click(function () {
        $('#searchingDirectForAllParameters').addClass('active');
        $('#searchingByStationsAndDate').removeClass('active');
        $('#searchingByDate').removeClass('active');
        $('#searchingByTrainAndDate').removeClass('active');
        $('#stationDeparture').prop('disabled', false);
        $('#stationArrival').prop('disabled', false);
        $('#train').prop('disabled', false);
        $('#checkBoxId').prop('hidden', true);
        urlSearching = "/schedule/direct";
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
        $('#checkBoxId').prop('hidden', false);
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
        $('#checkBoxId').prop('hidden', true);
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
        $('#checkBoxId').prop('hidden', true);
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
        sendRequest(urlSearching, scheduleDTO, token);
    });

    /**
     * set content for direct schedule
     * @param response
     */
    var setContentForScheduleDirect = function (response) {
        $('#tableScheduleDirect').append("<tr>" +
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
    };


    var setContentForScheduleTransfer = function (response) {
        $('#tableScheduleTransfer').append("<tr>" +
            "<th scope='row'>" +
            "<img src='static/images/icoschedule.png'>" +
            "<div>" + response.trainDepartureName + "</div>" +
            "</th>" +
            "<th scope='row'>" +
            "<div class='text-danger'>" + response.dateDeparture + "</div>" +
            "<div class='font-weight-normal'>" + response.stationDepartureName + "</div>" +
            "</th>" +
            "<th scope='row'>" +
            "<div>" +
            "<img src='/static/images/arrow-13-xxl.png' height='50'></div>" +
            "<div>" + getDelayBetweenTwoDates(response.dateDeparture, response.dateIntermediateDeparture) + "</div>" +
            "</th>" +
            "<th scope='row'>" +
            "<div class='text-danger'>" + response.dateIntermediateDeparture + "</div>" +
            "<div class='font-weight-normal'>" + response.stationIntermediateName + "</div>" +
            "</th>" +
            "<th scope='row'><img src='static/images/icoschedule.png'>" +
            "<div>" + response.trainArrivalName + "</div>" +
            "</th>" +
            "<th scope='row'>" +
            "<div class='text-danger'>" + response.dateIntermediateArrival + "</div>" +
            "<div class='font-weight-normal'>" + response.stationIntermediateName + "</div>" +
            "</th>" +
            "<th scope='row'>" +
            "<div><img src='/static/images/arrow-13-xxl.png' height='50'></div>" +
            "<div>" + getDelayBetweenTwoDates(response.dateIntermediateArrival, response.dateArrival) + "</div>" +
            "</th>" +
            "<th scope='row'><div class='text-danger'>" + response.dateArrival + "</div>" +
            "<div class='font-weight-normal'>" + response.stationArrivalName + "</div>" +
            "</th>" +
            "</tr>")
    };
    /**
     * calculate delay between two datetime
     * @param dateStart
     * @param dateEnd
     * @returns {string}
     */

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
    };


    /**
     * send ajax request
     * @param urlSearching
     * @param scheduleDTO
     * @param token
     * @param direct
     */
    var sendRequest = function (urlSearching, scheduleDTO, token) {
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
                $('#tableScheduleDirect').empty();
                for (var i = 0; i < response.length; i++) {
                    setContentForScheduleDirect(response[i]);
                }
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
                            $('#tableScheduleTransfer').empty();
                            for (var i = 0; i < response.length; i++) {
                                setContentForScheduleTransfer(response[i]);
                            }
                        }
                    }).fail(function (qXHR, textStatus, errorThrown) {
                        alert(JSON.stringify(qXHR));
                        console.log('request: ', qXHR);
                        console.log('status text: ', textStatus);
                        console.log('thrown error: ', JSON.stringify(errorThrown));
                    });
                }
                else $('#tableScheduleTransfer').empty();
            }
            else
                $('#mainScheduleContainer').hide();
        }).fail(function (qXHR, textStatus, errorThrown) {
            alert(JSON.stringify(qXHR));
            console.log('request: ', qXHR);
            console.log('status text: ', textStatus);
            console.log('thrown error: ', JSON.stringify(errorThrown));
        });
    }
});