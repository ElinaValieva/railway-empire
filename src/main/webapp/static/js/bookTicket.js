function bookTicket(id, seats, token, settings) {

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
        alert('success');
        $('.' + settings.selectingSeatCss).removeClass(settings.selectingSeatCss).addClass(settings.selectedSeatCss);
    }).fail(function (qXHR, textStatus, errorThrown) {
        alert(JSON.stringify(qXHR));
        console.log('request: ', qXHR);
        console.log('status text: ', textStatus);
        console.log('thrown error: ', JSON.stringify(errorThrown));
    });
};