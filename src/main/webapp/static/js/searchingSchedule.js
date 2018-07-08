$(function () {

    $('#searchingBtnByDates').click(function (event) {
        event.preventDefault();
        var token = $("meta[name='_csrf']").attr("content");
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
            alert(JSON.stringify(qXHR));
            console.log('request: ', qXHR);
            console.log('status text: ', textStatus);
            console.log('thrown error: ', JSON.stringify(errorThrown));
        });
    });

    $('#searchingBtnByStationsAndDates').click(function () {
        event.preventDefault();
        var token = $("meta[name='_csrf']").attr("content");
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
            for (var i = 0; i < response.length; i++)
                setContextForDirect(response[i]);
        }).fail(function (qXHR, textStatus, errorThrown) {
            alert(JSON.stringify(qXHR));
            console.log('request: ', qXHR);
            console.log('status text: ', textStatus);
            console.log('thrown error: ', JSON.stringify(errorThrown));
        });
    });

    $('#searchingBtnByAllParameters').click(function (event) {
        event.preventDefault();
        alert('click');
        var token = $("meta[name='_csrf']").attr("content");
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
            alert(JSON.stringify(qXHR));
            console.log('request: ', qXHR);
            console.log('status text: ', textStatus);
            console.log('thrown error: ', JSON.stringify(errorThrown));
        });
    });

    var setContextForDirect = function (response) {
        $('#containerForSearchingDirect').append("<tr><th scope='row'><div><div><img src='static/images/trainIcoWarn.png'></div><label class='font-weight-normal'>" +
            response.trainName +
            "</label></div></th><td><br><div><label class='font-weight-normal'>" +
            response.dateDeparture +
            "</label></div><div><label class='font-weight-bold'>" +
            response.stationDepartureName +
            "</label></div></td><td><div><label class='font-weight-light'>" +
            getDelayBetweenTwoDates(response.dateDeparture, response.dateArrival) +
            "</label></div><img src='static/images/arrow-13-xxl.png' width='50'></td><td><br><div><label class='font-weight-normal'>" +
            response.dateArrival +
            "</label></div><div><label class='font-weight-bold'>" +
            response.stationArrivalName +
            "</label></div></td><td><br><button class='btn btn-lg btn-outline-warning'>FIND TICKET</button></td></tr>");
    };

    var getDelayBetweenTwoDates = function (dateStart, dateEnd) {
        var hourDiff = new Date(dateEnd).getTime() - new Date(dateStart).getTime();
        var minDiff = hourDiff / 60 / 1000; //in minutes
        var hDiff = hourDiff / 3600 / 1000; //in hours
        var humanReadable = {};
        humanReadable.hours = Math.floor(hDiff);
        humanReadable.minutes = minDiff - 60 * humanReadable.hours;
        if (humanReadable.minutes >= 0 && humanReadable.minutes < 10)
            (humanReadable.minutes == 0) ? humanReadable.minutes = '00' : humanReadable.minutes = '0' + humanReadable.minutes;
        return humanReadable.hours + ':' + humanReadable.minutes;
    };
});