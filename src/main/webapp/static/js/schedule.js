$(function () {

    $('#btnSearchSchedule').click(function (event) {
        event.preventDefault();
        var token = $("meta[name='_csrf']").attr("content");

        var scheduleDTO = {
            stationDepartureName:
                $('#stationDeparture').val(),
            stationArrivalName:
                $('#stationArrival').val(),
            trainName: '',
            dateDeparture:
                $('#date').val(),
            dateArrival: ''
        };
        var urlSearching = "/schedule/direct";
        $.ajax({
            beforeSend: function(xhr) {
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
        }).fail(function (qXHR, textStatus, errorThrown) {
            alert(JSON.stringify(qXHR));
            console.log('request: ', qXHR);
            console.log('status text: ', textStatus);
            console.log('thrown error: ', JSON.stringify(errorThrown));
        });
    });
});