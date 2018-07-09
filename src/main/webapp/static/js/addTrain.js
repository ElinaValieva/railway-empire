var addTrain = function () {

    var token = $("meta[name='_csrf']").attr("content");
    var scheduleDTO = {
        trainName: $('#trainItemsRailway').val(),
        cntCarriage: $('#cntCarriageItemsRailway').val(),
        cntSeats: $('#cntSeatsItemsRailway').val()
    };
    alert(JSON.stringify(scheduleDTO));
    var urlSearching = "/train/add";
    $.ajax({
        beforeSend: function (xhr) {
            xhr.setRequestHeader('X-CSRF-TOKEN', token);
        },
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        method: "PUT",
        url: urlSearching,
        data: JSON.stringify(scheduleDTO),
    }).done(function () {
        swal("Good job!", "You add new train", "success");
    }).fail(function (qXHR, textStatus, errorThrown) {
        var messageError = JSON.parse(qXHR.responseText)['message'].split('[MESSAGE]:')[1];
        swal("Oops..", messageError, "error");
        console.log('request: ', qXHR);
        console.log('status text: ', textStatus);
        console.log('thrown error: ', JSON.stringify(errorThrown));
    });
};