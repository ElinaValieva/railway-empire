$(function () {
    $('#stationTable').hide();
    $('#trainTable').hide();

    $('#addTrainBtn').click(function () {
        $('#stationTable').hide();
        $('#trainTable').show();
        $('#scheduleTable').hide();
        var trains = getAllTrains();
        $('#trainTableId').empty();
        for (var i = 0; i < trains.length; i++) {
            $('#trainTableId').append("<tr>\n" +
                "            <td>" + trains[i].trainName + "</td>\n" +
                "            <td>" + trains[i].cntCarriage + "</td>\n" +
                "            <td>" + trains[i].cntSeats + "</td>\n" +
                "            <td>\n" +
                "                <button class=\"btn btn-outline-warning\"><img src=\"/static/images/edit.png\"></button>\n" +
                "                <button class=\"btn btn-outline-warning\"><img src=\"/static/images/delete.png\"></button>\n" +
                "            </td>\n" +
                "        </tr>");
        }
    });

    $('#addStationBtn').click(function () {
        $('#stationTable').show();
        $('#trainTable').hide();
        $('#scheduleTable').hide();
        var stations = getStation();
        $('#stationTableId').empty();
        for (var i = 0; i < stations.length; i++)
            $('#stationTableId').append("<tr>\n" +
                "            <td>" + stations[i].name + "</td>\n" +
                "            <td>" + stations[i].latitude + "</td>\n" +
                "            <td>" + stations[i].longitude + "</td>\n" +
                "            <td>\n" +
                "                <button class=\"btn btn-outline-warning\"><img src=\"/static/images/edit.png\"></button>\n" +
                "                <button class=\"btn btn-outline-warning\"><img src=\"/static/images/delete.png\"></button>\n" +
                "            </td>\n" +
                "        </tr>");
    });

    $('#addScheduleBtn').click(function () {
        $('#stationTable').hide();
        $('#trainTable').hide();
        $('#scheduleTable').show();
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
                "                <button class=\"btn btn-outline-warning\"><img src=\"/static/images/edit.png\"></button>\n" +
                "                <button class=\"btn btn-outline-warning\"><img src=\"/static/images/delete.png\"></button>\n" +
                "            </td>\n" +
                "        </tr>")
    });
})