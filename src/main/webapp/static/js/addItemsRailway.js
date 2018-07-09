$(function () {

    $('#stationDepartureItemsRailway').hide();
    $('#stationArrivalItemsRailway').hide();
    $('#dateDepartureItemsRailway').hide();
    $('#dateArrivalItemsRailway').hide()
    $('#trainItemsRailway').hide();
    $('#stationNameItemsRailway').hide();
    $('#coordinatesX').hide();
    $('#coordinatesY').hide();

    $('#addScheduleBtn').click(function (event) {
        event.preventDefault();
        $('#stationDepartureItemsRailway').show();
        $('#stationArrivalItemsRailway').show();
        $('#dateDepartureItemsRailway').show();
        $('#dateArrivalItemsRailway').show();
        $('#trainItemsRailway').show();
        $('#stationNameItemsRailway').hide();
        $('#coordinatesX').hide();
        $('#coordinatesY').hide();
        var scheduleDTO = {
            stationDepartureName:
                $('#stationDepartureItemsRailway').val(),
            stationArrivalName:
                $('#stationArrivalItemsRailway').val(),
            trainName:
                $('#trainItemsRailway').val(),
            dateDeparture:
                $('#dateDepartureItemsRailway').val().replace("T", " "),
            dateArrival:
                $('#dateArrivalItemsRailway').val().replace("T", " "),
        };
    });

    $('#addTrainBtn').click(function (event) {
        event.preventDefault();
        $('#stationDepartureItemsRailway').hide();
        $('#stationArrivalItemsRailway').hide();
        $('#dateDepartureItemsRailway').hide();
        $('#dateArrivalItemsRailway').hide()
        $('#trainItemsRailway').show();
        $('#stationNameItemsRailway').hide();
        $('#coordinatesX').hide();
        $('#coordinatesY').hide();
        var trainName = $('#trainItemsRailway').val();
    });

    $('#addStationBtn').click(function (event) {
        event.preventDefault();
        var urlSearching = "/schedule/add";
        var token = $("meta[name='_csrf']").attr("content");
        $('#stationDepartureItemsRailway').hide();
        $('#stationArrivalItemsRailway').hide();
        $('#dateDepartureItemsRailway').hide();
        $('#dateArrivalItemsRailway').hide()
        $('#trainItemsRailway').hide()
        $('#stationNameItemsRailway').show();
        $('#coordinatesX').show();
        $('#coordinatesY').show();
        var scheduleDTO = {
            stationDepartureName:
                $('#stationDepartureItemsRailway').val(),
            stationArrivalName:
                $('#stationArrivalItemsRailway').val(),
            trainName:
                $('#trainItemsRailway').val(),
            dateDeparture:
                $('#dateDepartureItemsRailway').val().replace("T", " "),
            dateArrival:
                $('#dateArrivalItemsRailway').val().replace("T", " "),
        };
        var token = $("meta[name='_csrf']").attr("content");
        addSchedule(scheduleDTO, token);
    });
})