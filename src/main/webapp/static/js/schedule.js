$(function () {

    $('#btnSearchSchedule').click(function (event) {
        event.preventDefault();
        var token = $("meta[name='_csrf']").attr("content");

        var scheduleDTO = {
            stationDepartureName:
                $('#stationDeparture').val(),
            stationArrivalName:
                $('#stationArrival').val(),
            trainName: '',
            dateDeparture:
                $('#date').val(),
            dateArrival: ''
        };
        var urlSearching = "/schedule/direct";
        $.ajax({
            beforeSend: function (xhr) {
                xhr.setRequestHeader('X-CSRF-TOKEN', token);
            },
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            method: "POST",
            url: urlSearching,
            data: JSON.stringify(scheduleDTO),
        }).done(function (response) {
            if (response.length != 0) {
                $('#mainScheduleContainer').show()
                for (var i = 0; i < response.length; i++) {
                    $('#tableSchedule').append("<th scope='row'>" + response[i].stationDepartureName + "</th>" +
                        "<th scope='row'>" + response[i].stationArrivalName + "</th>" +
                        "<th scope='row'>" + response[i].trainName + "</th>" +
                        "<th scope='row'>" + response[i].dateDeparture + "</th>" +
                        "<th scope='row'>" + response[i].dateArrival + "</th>");
                }
            }
            else
                $('#mainScheduleContainer').hide();
        }).fail(function (qXHR, textStatus, errorThrown) {
            alert(JSON.stringify(qXHR));
            console.log('request: ', qXHR);
            console.log('status text: ', textStatus);
            console.log('thrown error: ', JSON.stringify(errorThrown));
        });
    });
});