$(function () {
    $('#stationTable').hide();
    $('#trainTable').hide();

    $('#addTrainBtn').click(function () {
        $('#stationTable').hide();
        $('#trainTable').show();
        $('#scheduleTable').hide();
    });

    $('#addStationBtn').click(function () {
        $('#stationTable').show();
        $('#trainTable').hide();
        $('#scheduleTable').hide();
    });

    $('#addScheduleBtn').click(function () {
        $('#stationTable').hide();
        $('#trainTable').hide();
        $('#scheduleTable').show();
    });
})