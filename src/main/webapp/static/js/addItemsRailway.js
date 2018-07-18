$(function () {

    var autocomplete = function () {
        var urlSearching = "/station/auto/stations";
        var stations = getRequest(urlSearching);

        urlSearching = "/train/auto/trains";
        var trains = getRequest(urlSearching);


        for (var i = 0; i < stations.length; i++)
            $('#stationsList').append("<option value='" + stations[i] + "'>");

        for (var i = 0; i < trains.length; i++)
            $('#trainsList').append("<option value='" + trains[i] + "'>");
    };

    autocomplete();

    $('#addScheduleBtn').click(function (event) {
        event.preventDefault();
        $('#newItemForm').prop('hidden', false);
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
        $('#addItem1').show();
        $('#addItem2').hide();
        $('#addItem3').hide();
        $('#addItem1').click(function (event) {
            event.preventDefault();
            addSchedule();
        });
    });

    $('#addTrainBtn').click(function (event) {
        event.preventDefault();
        $('#newItemForm').prop('hidden', false);
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
        $('#addItem2').show();
        $('#addItem1').hide();
        $('#addItem3').hide();
        $('#addItem2').click(function (event) {
            event.preventDefault();
            addTrain();
        });
    });

    $('#addStationBtn').click(function (event) {
        event.preventDefault();
        $('#newItemForm').prop('hidden', false);
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
        $('#addItem3').show();
        $('#addItem2').hide();
        $('#addItem1').hide();
        $('#addItem3').click(function (event) {
            event.preventDefault();
            var coordX = $('#coordinatesX').val();
            var coordY = $('#coordinatesY').val();
            var name = $('#stationNameItemsRailway').val();
            if (coordX == '' || coordY == '')
                getCoordinates(name);
            else
                addStation(name, coordX, coordY);
        });
    });
})