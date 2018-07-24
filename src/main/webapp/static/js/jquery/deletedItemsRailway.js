$(function () {

    var trains = [];
    var stations = [];
    var audits = [];


    $('#addTrainBtn').click(function () {
        $('#stationTable').prop('hidden', true);
        $('#trainTable').prop('hidden', false);
        $('#auditTable').prop('hidden', true);
        updateTrains();
    });

    $('#addStationBtn').click(function () {
        $('#stationTable').prop('hidden', false);
        $('#trainTable').prop('hidden', true);
        $('#auditTable').prop('hidden', true);
        updateStations();
    });

    $('#auditBtn').click(function () {
        $('#stationTable').prop('hidden', true);
        $('#trainTable').prop('hidden', true);
        $('#auditTable').prop('hidden', false);
        updateAudit();
    })

    $(this).on('click', '.reestablishTrain', function () {
        var trainName = $(this).attr('id');
        reestablishTrain(trainName);
        updateTrains();
    });

    var updateTrains = function () {
        trains = getAllDeletedTrains();
        if (trains.length > 0) {
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
        }
        else swal({
            title: 'Oops..',
            text: 'All trains working ',
            type: 'info'
        })
    };

    $(this).on('click', '.reestablishStation', function () {
        var stationName = $(this).attr('id');
        reestablishStation(stationName);
        updateStations();
    });

    var updateStations = function () {
        stations = getAllDeletedStations();
        if (stations.length > 0) {
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
        } else swal({
            title: 'Oops..',
            text: 'All stations available',
            type: 'info'
        })
    };

    var updateAudit = function () {
        audits = getAuditInfo();
        $('#auditTableId').empty();
        for (var i = 0; i < audits.length; i++)
            $('#auditTableId').append("<tr>\n" +
                "            <td>" + audits[i].date + "</td>\n" +
                "            <td>" + audits[i].userLogin + "</td>\n" +
                "            <td>" + audits[i].operations + "</td>\n" +
                "            <td>" + audits[i].newValue + "</td>\n" +
                "            <td>\n" +
                "                <button class='btn btn-outline-warning audit'  id=" + audits[i].id + "><img src='static/images/restore.png'></button>\n" +
                "            </td>\n" +
                "        </tr>");
    };

    var getAuditInfo = function () {
        var urlSearching = "/audit";
        return getRequest(urlSearching);
    };

    $(this).on('click', '.audit', function () {
        var id = $(this).attr('id');
        var audit = audits.filter(x => x.id == id);
        alert(JSON.stringify(audit));
        swal({
            title: 'AUDIT HISTORY',
            html:
                "<input id='swal-input1' class='swal2-input' value='" + audit[0].userFirstName + "' disabled>" +
                "<input id='swal-input2' class='swal2-input' value='" + audit[0].userLastName + "' disabled>" +
                "<input id='swal-input2' class='swal2-input' value='" + audit[0].userLogin + "' disabled>" +
                "<input id='swal-input3' class='swal2-input' value='" + audit[0].oldValue == null ? '-' : audit[0].oldValue + "' disabled>" +
                    "<input id='swal-input3' class='swal2-input' value='" + audit[0].newValue + "' disabled>",

            icon: "info",
        });
    });
});