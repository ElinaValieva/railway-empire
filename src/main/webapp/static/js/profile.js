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
        if (checkFields()) {
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
            putRequest(userDTO, urlSearching, "Your profile was updated!");
        }
    });

    /**
     * 1. Fields firstName, lastName don't contain numbers
     * 2. Length for field password >6
     */
    var checkFields = function () {

        var firstName = $('#firstName').val();
        var lastName = $('#lastName').val();

        if (!/^[a-zA-Z]+$/.test(firstName) || !/^[a-zA-Z]+$/.test(lastName)) {
            swal({
                title: 'Oops...',
                text: 'Fields for name and last name must contain only letters!',
                type: 'error'
            });
            return false;
        }
        else return true;
    };
})