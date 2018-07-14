$(function () {
    $('#registrationBtn').click(function (event) {
        event.preventDefault();
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
        postRequest(userDTO, urlSearching, "You have been registered!");
    })
});