$(function () {

    $('#stationDeparture').hide();
    $('#stationArrival').hide();
    $('#dateDeparture').hide();
    $('#dateArrival').hide()
    $('#train').hide();
    $('#stationName').hide();
    $('#coordinatesX').hide();
    $('#coordinatesY').hide();

    $('#addScheduleBtn').click(function (event) {
        event.preventDefault();
        $('#stationDeparture').show();
        $('#stationArrival').show();
        $('#dateDeparture').show();
        $('#dateArrival').show();
        $('#train').show();
        $('#stationName').hide();
        $('#coordinatesX').hide();
        $('#coordinatesY').hide();
        var scheduleDTO = {
            stationDepartureName:
                $('#stationDeparture').val(),
            stationArrivalName:
                $('#stationArrival').val(),
            trainName:
                $('#train').val(),
            dateDeparture:
                $('#dateDeparture').val().replace("T", " "),
            dateArrival:
                $('#dateArrival').val().replace("T", " "),
        };
    });

    $('#addTrainBtn').click(function (event) {
        event.preventDefault();
        $('#stationDeparture').hide();
        $('#stationArrival').hide();
        $('#dateDeparture').hide();
        $('#dateArrival').hide()
        $('#train').show();
        $('#stationName').hide();
        $('#coordinatesX').hide();
        $('#coordinatesY').hide();
        var trainName = $('#train').val();
    });

    $('#addStationBtn').click(function (event) {
        event.preventDefault();
        var urlSearching = "/schedule/add";
        var token = $("meta[name='_csrf']").attr("content");
        $('#stationDeparture').hide();
        $('#stationArrival').hide();
        $('#dateDeparture').hide();
        $('#dateArrival').hide()
        $('#train').hide()
        $('#stationName').show();
        $('#coordinatesX').show();
        $('#coordinatesY').show();
        var scheduleDTO = {
            stationDepartureName:
                $('#stationDeparture').val(),
            stationArrivalName:
                $('#stationArrival').val(),
            trainName:
                $('#train').val(),
            dateDeparture:
                $('#dateDeparture').val().replace("T", " "),
            dateArrival:
                $('#dateArrival').val().replace("T", " "),
        };
        var token = $("meta[name='_csrf']").attr("content");
        addSchedule(scheduleDTO, token);
    });
})