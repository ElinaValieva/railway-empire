var getStation = function () {

    var token = $("meta[name='_csrf']").attr("content");
    var urlSearching = "/station/all";
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
        dataResponse = response;
    }).fail(function (qXHR, textStatus, errorThrown) {
        var messageError = JSON.parse(qXHR.responseText)['message'].split('[MESSAGE]:')[1];
        swal("Oops..", messageError, "error");
        console.log('request: ', qXHR);
        console.log('status text: ', textStatus);
        console.log('thrown error: ', JSON.stringify(errorThrown));
    });
    return dataResponse;
};

var deleteStation = function (name) {
    alert('a');
    var token = $("meta[name='_csrf']").attr("content");
    var urlSearching = "/station/delete/" + name;
    $.ajax({
        beforeSend: function (xhr) {
            xhr.setRequestHeader('X-CSRF-TOKEN', token);
        },
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        method: "DELETE",
        url: urlSearching,
        async: false
    }).done(function () {
        swal("Good job!", "You delete station", "success");
    }).fail(function (qXHR, textStatus, errorThrown) {
        var messageError = JSON.parse(qXHR.responseText)['message'].split('[MESSAGE]:')[1];
        swal("Oops..", messageError, "error");
        console.log('request: ', qXHR);
        console.log('status text: ', textStatus);
        console.log('thrown error: ', JSON.stringify(errorThrown));
    });
}