$(function () {
    $('#createScheduleId').click(function (event) {
        event.preventDefault();
        var urlSearching = "/schedule/add";
        var token = $("meta[name='_csrf']").attr("content");
        var scheduleDTO = {
            stationDepartureName:
                $('#stationDeparture').val(),
            stationArrivalName:
                $('#stationArrival').val(),
            trainName:
                $('#train').val(),
            dateDeparture:
            $('#dateDeparture').val().replace("T"," "),
            dateArrival:
            $('#dateArrival').val().replace("T"," "),
        };

        $.ajax({
            beforeSend: function (xhr) {
                xhr.setRequestHeader('X-CSRF-TOKEN', token);
            },
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            method: "PUT",
            url: urlSearching,
            data: JSON.stringify(scheduleDTO),
        }).done(function () {
            alert('success');
        }).fail(function (qXHR, textStatus, errorThrown) {
            alert(JSON.stringify(qXHR));
            console.log('request: ', qXHR);
            console.log('status text: ', textStatus);
            console.log('thrown error: ', JSON.stringify(errorThrown));
        });
    });
});