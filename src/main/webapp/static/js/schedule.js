$(function () {

    $('#btnSearchSchedule').click(function () {
        var scheduleDTO = {
            stationDeparture:
                $('#stationDeparture').val(),
            stationArrival:
                $('#stationArrival').val(),
            date:
                $('#date').val()
        };
        var urlSearching = "/schedule/direct";
        $.ajax({
            method: "POST",
            url: urlSearching,
            data: JSON.stringify(scheduleDTO),
            contentType: 'application/json',
            dataType: 'json'
        }).done(function (response) {
            alert(JSON.stringify(response));
            alert('success');
        }).fail(function (qXHR, textStatus, errorThrown) {
            console.log('request: ', qXHR);
            console.log('status text: ', textStatus);
            console.log('thrown error: ', JSON.stringify(errorThrown));
        });
    });
});