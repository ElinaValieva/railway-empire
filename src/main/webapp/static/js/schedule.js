$(function () {

    $('#btnSearchSchedule').click(function () {
        var stationDeparture = $('#stationDeparture').val();
        var stationArrival = $('#stationArrival').val();
        var date = $('#date').val();
        var urlSearching = "/schedule/direct/departure/" + stationDeparture + "/arrival/" + stationArrival + "/date/" + date;

        $.ajax({
            type: "GET",
            url: urlSearching,
            contentType: 'application/json',
            dataType: 'json'
        }).done(function () {
            alert('success')
        }).fail(function () {
            alert('error')
        }).always(function (response) {
            alert(response==null + response.length);
        });
    });

});