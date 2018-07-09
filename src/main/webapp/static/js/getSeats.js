function getSeats(id, token) {

    var urlSearching = "/schedule/seat/" + id;
    var dataResponse = null;
    $.ajax({
        beforeSend: function (xhr) {
            xhr.setRequestHeader('X-CSRF-TOKEN', token);
        },
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        method: "GET",
        url: urlSearching,
        async: false
    }).done(function (response) {
        alert('success');
        dataResponse = response;
    }).fail(function (qXHR, textStatus, errorThrown) {
        alert(JSON.stringify(qXHR));
        console.log('request: ', qXHR);
        console.log('status text: ', textStatus);
        console.log('thrown error: ', JSON.stringify(errorThrown));
    });
    return dataResponse;
};