$(function () {

    var trains = [];
    var stations = [];

    $('#stationTable').hide();
    $('#trainTable').hide();

    $('#addTrainBtn').click(function () {
        $('#stationTable').hide();
        $('#trainTable').show();
        $('#scheduleTable').hide();
        updateTrains();
    });

    $('#addStationBtn').click(function () {
        $('#stationTable').show();
        $('#trainTable').hide();
        $('#scheduleTable').hide();
        updateStations();
    });

    $(this).on('click', '.reestablishTrain', function () {
        var trainName = $(this).attr('id');
        reestablishTrain(trainName);
        updateTrains();
    });

    var updateTrains = function () {
        trains = getAllDeletedTrains();
        alert(JSON.stringify(trains));
        $('#trainTableId').empty();
        for (var i = 0; i < trains.length; i++) {
            $('#trainTableId').append("<tr>\n" +
                "            <td>" + trains[i].trainName + "</td>\n" +
                "            <td>" + trains[i].cntCarriage + "</td>\n" +
                "            <td>" + trains[i].cntSeats + "</td>\n" +
                "            <td>\n" +
                "                <button class='btn btn-outline-warning reestablishTrain'  id=" + trains[i].trainName + "><img src='static/images/restore.png'></button>\n" +
                "            </td>\n" +
                "        </tr>"
            );
        }
    };

    $(this).on('click', '.reestablishStation', function () {
        var stationName = $(this).attr('id');
        reestablishStation(stationName);
        updateStations();
    });

    var updateStations = function () {
        stations = getAllDeletedStations();
        $('#stationTableId').empty();
        for (var i = 0; i < stations.length; i++)
            $('#stationTableId').append("<tr>\n" +
                "            <td>" + stations[i].name + "</td>\n" +
                "            <td>" + stations[i].latitude + "</td>\n" +
                "            <td>" + stations[i].longitude + "</td>\n" +
                "            <td>\n" +
                "                <button class='btn btn-outline-warning reestablishStation'  id=" + stations[i].name + "><img src='static/images/restore.png'></button>\n" +
                "            </td>\n" +
                "        </tr>");
    };
});