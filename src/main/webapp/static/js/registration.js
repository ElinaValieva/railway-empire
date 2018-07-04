$(function () {
    $('#registrationBtn').click(function (event) {
        event.preventDefault();
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        alert(header);
        alert(token);
        var user = {
            firstName: $('#firstName').val(),
            lastName: $('#lastName').val(),
            login: $('#login').val(),
            password: $('#password').val()
        }
        $.ajax({
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            url: "/registration",
            data: JSON.stringify(user),
            method: "POST",
            contentType: "application/json",
            dataType: 'json'
        }).done(function () {
            window.location.href = "/login"
        }).fail(function (qXHR, textStatus, errorThrown) {
            console.log('request: ', JSON.stringify(qXHR));
            console.log('status text: ', textStatus);
            console.log('thrown error: ', JSON.stringify(errorThrown));
        });
    })
});