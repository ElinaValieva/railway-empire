$(function () {

    var trains = [];
    var stations = [];
    var schedules = [];

    $('#stationTable').hide();
    $('#trainTable').hide();
    $('#scheduleTable').hide();

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
        var id = $(this).attr('id');
        var cntSeats = trains.filter(x => x.trainName == id)[0].cntSeats;
        var cntCarriages = trains.filter(x => x.trainName == id)[0].cntCarriage;
        swal({
            title: 'EDIT TRAIN',
            html:
            "<input id='swal-input1' class='swal2-input' value='" + id + "'>" +
            "<input id='swal-input2' class='swal2-input' value='" + cntCarriages + "' disabled>" +
            "<input id='swal-input3' class='swal2-input' value='" + cntSeats + "' disabled>",
            focusConfirm: false,
            confirmButtonText: 'EDIT',
            preConfirm: function () {
                return new Promise(function (resolve) {
                    resolve([
                        $('#swal-input1').val(),
                        $('#swal-input2').val(),
                        $('#swal-input3').val()
                    ])
                })
            }
        }).then(function () {
            var trainDTO = {
                trainName: id,
                trainNewName: $('#swal-input1').val(),
                cntCarriage: cntCarriages,
                cntSeats: cntSeats
            };
            updateTrain(trainDTO);
            updateTrains();
        }).catch(swal.noop);
    });

    $(this).on('click', '.deleteTrain', function () {
        var trainName = $(this).attr('id');
        deleteTrain(trainName);
        updateTrains();
    });

    var updateTrains = function () {
        trains = getAllTrains();
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
        var id = $(this).attr('id');
        var latitude = stations.filter(x => x.name == id)[0].latitude;
        var longitude = stations.filter(x => x.name == id)[0].longitude;
        swal({
            title: 'EDIT STATION',
            html:
            "<input id='swal-input1' class='swal2-input' value='" + id + "'>" +
            "<input id='swal-input2' class='swal2-input' value='" + latitude + "' disabled>" +
            "<input id='swal-input3' class='swal2-input' value='" + longitude + "' disabled>",
            focusConfirm: false,
            confirmButtonText: 'EDIT',
            preConfirm: function () {
                return new Promise(function (resolve) {
                    resolve([
                        $('#swal-input1').val(),
                        $('#swal-input2').val(),
                        $('#swal-input3').val()
                    ])
                })
            }
        }).then(function () {
            var stationDTO = {
                name: id,
                newName: $('#swal-input1').val(),
                latitude: latitude,
                longitude: longitude
            };
            updateStation(stationDTO);
            updateStations();
        }).catch(swal.noop);
    });

    $(this).on('click', '.deleteStation', function () {
        var stationName = $(this).attr('id');
        deleteStation(stationName);
        updateStations();
    });

    var updateStations = function () {
        stations = getStation();
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
        var id = $(this).attr('id');
        var stationDeparture = schedules.filter(x => x.id == id)[0].stationDepartureName;
        var stationArrival = schedules.filter(x => x.id == id)[0].stationArrivalName;
        var dateDeparture = schedules.filter(x => x.id == id)[0].dateDeparture.replace(' ', 'T');
        var dateArrival = schedules.filter(x => x.id == id)[0].dateArrival.replace(' ', 'T');
        var train = schedules.filter(x => x.id == id)[0].trainName;
        swal({
            title: 'EDIT SCHEDULE',
            html:
            "<input id='swal-input1' class='swal2-input' value='" + train + "'>" +
            "<input id='swal-input2' class='swal2-input' value='" + stationDeparture + "'>" +
            "<input id='swal-input3' class='swal2-input' value='" + stationArrival + "'>" +
            "<input id='swal-input4' type='datetime-local' class='swal2-input' value='" + dateDeparture + "'>" +
            "<input id='swal-input5' type='datetime-local' class='swal2-input' value='" + dateArrival + "'>",
            focusConfirm: false,
            confirmButtonText: 'EDIT',
            preConfirm: function () {
                return new Promise(function (resolve) {
                    resolve([
                        $('#swal-input1').val(),
                        $('#swal-input2').val(),
                        $('#swal-input3').val(),
                        $('#swal-input4').val(),
                        $('#swal-input5').val()
                    ])
                })
            }
        }).then(function () {
            var schedule = {
                id: id,
                trainName: $('#swal-input1').val(),
                stationDepartureName: $('#swal-input2').val(),
                stationArrivalName: $('#swal-input3').val(),
                dateDeparture: $('#swal-input4').val().replace('T', ' ') + ':00',
                dateArrival: $('#swal-input5').val().replace('T', ' ') + ':00'
            };
            updateSchedule(schedule);
            updateSchedules();
        }).catch(swal.noop);
    });

    $(this).on('click', '.deleteSchedule', function () {
        var id = $(this).attr('id');
        deleteSchedule(id);
        updateSchedules();
    });

    $(this).on('click', '.openScheduleTicket', function () {
        var id = $(this).attr('id');
        var tickets = getTickets(id);
        var content = '';
        for (var i = 0; i < tickets.length; i++) {
            if (tickets[i].userBirthDay == null)
                tickets[i].userBirthDay = '-';
            if (tickets[i].userSex == null)
                tickets[i].userSex = '-';
            content = content + "<tr><th class='text-center'>" + tickets[i].userFirstName +
                "</th><th class='text-center'>" + tickets[i].userLastName +
                "</th><th class='text-center'>" + tickets[i].userLogin +
                "</th><th>" + tickets[i].userBirthDay +
                "</th><th>" + tickets[i].userSex +
                "</th><th>" + tickets[i].seatCarriage +
                "</th><th>" + tickets[i].seatSeat + "</th></tr>"
        }
        if (tickets.length > 0) {
            swal({
                title: 'For this schedule bought ' + tickets.length + ' tickets',
                width: '800px',
                html:
                `<table class="table table-striped table-dark text-center">
        <thead class="font-weight-bold">
        <tr>
            <th class="text-center">First name</th>
            <th class="text-center">Last name</th>
            <th class="text-center">Login</th>
            <th>Birthday</th>
			<th>sex</th>
			<th>carriage</th>
			<th>seat</th>
        </tr>
        </thead>
        <tbody class="text-center text-warning" id="ticketTableId">` + content +
                `</tbody>
    </table>`
            });
        }
        else swal({
            title: 'Empty..',
            text: 'For this schedule nobody bought tickets.',
            icon: 'warning'
        });
    });

    var updateSchedules = function () {
        schedules = getSchedules();
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
                "                <button class='btn btn-outline-warning openScheduleTicket' id=" + schedules[i].id + "><img src='static/images/magnifier.png'></button>\n" +
                "            </td>\n" +
                "        </tr>");
    };
});