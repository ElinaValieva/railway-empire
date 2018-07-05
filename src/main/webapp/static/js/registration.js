$(function () {
    $('#registrationBtn').click(function (event) {
        event.preventDefault();
        var token = $("meta[name='_csrf']").attr("content");
        var user = {
            firstName: $('#firstName').val(),
            lastName: $('#lastName').val(),
            login: $('#login').val(),
            password: $('#password').val()
        }
        $.ajax({
            beforeSend: function(xhr) {
                xhr.setRequestHeader('X-CSRF-TOKEN', token);
            },
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'text/html; charset=utf-8'
            },
            url: "/registration",
            data: JSON.stringify(user),
            method: "POST"
        }).done(function () {
            window.location.href = "/login"
        }).fail(function (qXHR, textStatus, errorThrown) {
            console.log('request: ', JSON.stringify(qXHR));
            console.log('status text: ', textStatus);
            console.log('thrown error: ', JSON.stringify(errorThrown));
        });
    })
});