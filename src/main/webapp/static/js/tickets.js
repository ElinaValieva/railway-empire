function tickets(id, seats, token, settings) {

    var urlSearching = "/schedule/ticket";
    var data = {
        schedule: parseInt(id),
        seatDTO: seats
    };
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
        data: JSON.stringify(data),
    }).done(function () {
        $('.' + settings.selectingSeatCss).removeClass(settings.selectingSeatCss).addClass(settings.selectedSeatCss);
        swal({
            title: "Congratulations!",
            text: "You book ticket",
            icon: "success",
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'home page!',
        }).then((result) => {
            if (result.value) {
                window.location = "/home";
            }
        });
    }).fail(function (qXHR, textStatus, errorThrown) {
        var messageError = JSON.parse(qXHR.responseText)['message'].split('[MESSAGE]:')[1];
        swal("Oops..", messageError, "error");
        console.log('request: ', qXHR);
        console.log('status text: ', textStatus);
        console.log('thrown error: ', JSON.stringify(errorThrown));
    });
};

var getTickets = function (id) {
    var urlSearching = "/schedule/ticket/all/" + id;
    return getRequest(urlSearching);
}

var getUserTickets = function () {
    var urlSearching = "/userMap/show";
    return getRequest(urlSearching);
}