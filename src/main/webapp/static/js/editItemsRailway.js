$(function () {
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

    $('#addScheduleBtn').click(function () {
        $('#stationTable').hide();
        $('#trainTable').hide();
        $('#scheduleTable').show();
        updateSchedules();
    });

    $(this).on('click', '.editTrain', function () {
        alert('click');
        var id = $(this).attr('id');
        alert(id);
    });

    $(this).on('click', '.deleteTrain', function () {
        var trainName = $(this).attr('id');
        deleteTrain(trainName);
        updateTrains();
    });

    var updateTrains = function () {
        var trains = getAllTrains();
        $('#trainTableId').empty();
        for (var i = 0; i < trains.length; i++) {
            $('#trainTableId').append("<tr>\n" +
                "            <td>" + trains[i].trainName + "</td>\n" +
                "            <td>" + trains[i].cntCarriage + "</td>\n" +
                "            <td>" + trains[i].cntSeats + "</td>\n" +
                "            <td>\n" +
                "                <button class='btn btn-outline-warning editTrain'  id=" + trains[i].trainName + "><img src='static/images/edit.png'></button>\n" +
                "                <button class='btn btn-outline-warning deleteTrain' id=" + trains[i].trainName + "><img src='static/images/rubbish-bin.png'></button>\n" +
                "            </td>\n" +
                "        </tr>"
            );
        }
    };

    $(this).on('click', '.editStation', function () {
        alert('click');
        var id = $(this).attr('id');
        alert(id);
    });

    $(this).on('click', '.deleteStation', function () {
        var stationName = $(this).attr('id');
        deleteStation(stationName);
        updateStations();
    });

    var updateStations = function () {
        var stations = getStation();
        $('#stationTableId').empty();
        for (var i = 0; i < stations.length; i++)
            $('#stationTableId').append("<tr>\n" +
                "            <td>" + stations[i].name + "</td>\n" +
                "            <td>" + stations[i].latitude + "</td>\n" +
                "            <td>" + stations[i].longitude + "</td>\n" +
                "            <td>\n" +
                "                <button class='btn btn-outline-warning editStation'  id=" + stations[i].name + "><img src='static/images/edit.png'></button>\n" +
                "                <button class='btn btn-outline-warning deleteStation' id=" + stations[i].name + "><img src='static/images/rubbish-bin.png'></button>\n" +
                "            </td>\n" +
                "        </tr>");
    };

    $(this).on('click', '.editSchedule', function () {
        alert('click');
        var id = $(this).attr('id');
        alert(id);
    });

    $(this).on('click', '.deleteSchedule', function () {
        var id = $(this).attr('id');
        deleteSchedule(id);
        updateSchedules();
    });

    var updateSchedules = function () {
        var schedules = getSchedules();
        $('#scheduleTableId').empty();
        for (var i = 0; i < schedules.length; i++)
            $('#scheduleTableId').append("<tr>\n" +
                "            <td>" + schedules[i].trainName + "</td>\n" +
                "            <td>" + schedules[i].stationDepartureName + "</td>\n" +
                "            <td>" + schedules[i].stationArrivalName + "</td>\n" +
                "            <td>" + schedules[i].dateDeparture + "</td>\n" +
                "            <td>" + schedules[i].dateArrival + "</td>\n" +
                "            <td>\n" +
                "                <button class='btn btn-outline-warning editSchedule'  id=" + schedules[i].id + "><img src='static/images/edit.png'></button>\n" +
                "                <button class='btn btn-outline-warning deleteSchedule' id=" + schedules[i].id + "><img src='static/images/rubbish-bin.png'></button>\n" +
                "            </td>\n" +
                "        </tr>");
    };
})