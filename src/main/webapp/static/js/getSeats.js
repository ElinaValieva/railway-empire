var getSeats = function (id, token) {
    var urlSearching = "/schedule/getSeat";
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
        data: JSON.stringify(id),
    }).done(function () {
        alert('success');
    }).fail(function (qXHR, textStatus, errorThrown) {
        alert(JSON.stringify(qXHR));
        console.log('request: ', qXHR);
        console.log('status text: ', textStatus);
        console.log('thrown error: ', JSON.stringify(errorThrown));
    });
};