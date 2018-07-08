var addSchedule = function (scheduleDTO, token) {

    var urlSearching = "/schedule/add";
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
        alert('success');
    }).fail(function (qXHR, textStatus, errorThrown) {
        alert(JSON.stringify(qXHR));
        console.log('request: ', qXHR);
        console.log('status text: ', textStatus);
        console.log('thrown error: ', JSON.stringify(errorThrown));
    });
};