$(function () {

    $('#btnSearchSchedule').click(function () {
        var stationDeparture = $('#stationDeparture').val();
        var stationArrival = $('#stationArrival').val();
        var date = $('#date').val();
        var urlSearching = "/schedule/direct/departure/" + stationDeparture + "/arrival/" + stationArrival + "/date/" + date;

        $.ajax({
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'text/html; charset=utf-8'
            },
            type: "GET",
            url: urlSearching
        }).fail(function () {
            alert('er');
        }).done(function () {
            alert('ok');
        });
    });
});