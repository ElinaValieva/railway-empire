$(function () {
    $('#registrationBtn').click(function () {
        alert('aaa');
        var user = {};
        user['firstName'] = $('#firstName').val();
        user['lastName'] = $('#lastName').val();
        user['login'] = $('#login').val();
        user['password'] = $('#password').val();
        user['birthDay'] = '';
        user['sex'] = '';
        $.ajax({
            url: "/registration",
            data: JSON.stringify(user),
            method: "POST",
            contentType: "application/json",
            dataType: 'json'
        }).done(function () {
            alert('success');
            window.location.href = "/login"
        }).fail(function () {
            alert('error');
        })
    })
});