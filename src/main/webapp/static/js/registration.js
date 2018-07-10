$(function () {
    $('#registrationBtn').click(function (event) {
        event.preventDefault();
        var token = $("meta[name='_csrf']").attr("content");
        var userDTO = {
            firstName: $('#firstName').val(),
            lastName: $('#lastName').val(),
            login: $('#login').val(),
            password: $('#password').val(),
            sex: '',
            birthDay: ''
        };
        alert(JSON.stringify(userDTO));
        var urlSearching = "/registration";
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
            data: JSON.stringify(userDTO),
        }).done(function () {
            swal("Good job!", "You have been registered!", "success");
        }).fail(function (qXHR, textStatus, errorThrown) {
            var messageError = JSON.parse(qXHR.responseText)['message'].split('[MESSAGE]:')[1];
            swal("Oops..", messageError, "error");
            console.log('request: ', qXHR);
            console.log('status text: ', textStatus);
            console.log('thrown error: ', JSON.stringify(errorThrown));
        });
    })
});