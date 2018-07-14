$(function () {
    var urlSearching = "/home/profile/get";
    var user = getRequest(urlSearching);

    $('#firstName').val(user.firstName);
    $('#lastName').val(user.lastName);
    $('#login').val(user.login);
    $('#birthday').val(user.birthDay);
    if (user.sex == 'male')
        $('#radioMale').prop('checked', true);
    else $('#radioFemale').prop('checked', true);


    $('#editProfile').click(function (event) {
        event.preventDefault();
        var sex = $('input:checked').val();
        var userDTO = {
            firstName: $('#firstName').val(),
            lastName: $('#lastName').val(),
            login: $('#login').val(),
            password: $('#password').val(),
            sex: sex,
            birthDay: $('#birthday').val()
        };
        var urlSearching = "/home/update";
        putRequest(userDTO, urlSearching, "Your profile was updated!")
    });
})