$(function () {


    $('#stationDepartureItemsRailway').hide();
    $('#stationArrivalItemsRailway').hide();
    $('#dateDepartureItemsRailway').hide();
    $('#dateArrivalItemsRailway').hide()
    $('#trainItemsRailway').hide();
    $('#stationNameItemsRailway').hide();
    $('#coordinatesX').hide();
    $('#coordinatesY').hide();
    $('#cntCarriageItemsRailway').hide();
    $('#cntSeatsItemsRailway').hide();

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
        $('#cntCarriageItemsRailway').hide();
        $('#cntSeatsItemsRailway').hide();
        $('#addItem').click(function (event) {
            event.preventDefault();
            addSchedule();
        });
    });

    $('#addTrainBtn').click(function (event) {
        event.preventDefault();
        $('#stationDepartureItemsRailway').hide();
        $('#stationArrivalItemsRailway').hide();
        $('#dateDepartureItemsRailway').hide();
        $('#dateArrivalItemsRailway').hide()
        $('#trainItemsRailway').show();
        $('#cntCarriageItemsRailway').show();
        $('#cntSeatsItemsRailway').show()
        $('#stationNameItemsRailway').hide();
        $('#coordinatesX').hide();
        $('#coordinatesY').hide();
        $('#addItem').click(function (event) {
            alert('click');
            event.preventDefault();
            trains();
        });
    });

    $('#addStationBtn').click(function (event) {
        alert('3')
        event.preventDefault();
        $('#stationDepartureItemsRailway').hide();
        $('#stationArrivalItemsRailway').hide();
        $('#dateDepartureItemsRailway').hide();
        $('#dateArrivalItemsRailway').hide()
        $('#trainItemsRailway').hide()
        $('#cntCarriageItemsRailway').hide();
        $('#cntSeatsItemsRailway').hide();
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
    });
})