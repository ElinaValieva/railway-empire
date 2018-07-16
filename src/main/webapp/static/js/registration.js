$(function () {

    $('#registrationBtn').click(function (event) {
        event.preventDefault();
        if (checkFields()) {
            var userDTO = {
                firstName: $('#firstName').val(),
                lastName: $('#lastName').val(),
                login: $('#login').val(),
                password: $('#password').val(),
                sex: '',
                birthDay: ''
            };
            var urlSearching = "/registration";
            postRequest(userDTO, urlSearching, "You have been registered!");
        }
    });

    /**
     * 1. Fields firstName, lastName don't contain numbers
     * 2. Length for field password >6
     */
    var checkFields = function () {

        var firstName = $('#firstName').val();
        var lastName = $('#lastName').val();
        var pass = $('#password').val();

        if (!/^[a-zA-Z]+$/.test(firstName) || !/^[a-zA-Z]+$/.test(lastName)) {
            swal({
                title: 'Oops...',
                text: 'Fields for name and last name must contain only letters!',
                type: 'error'
            });
            return false;
        }
        else if (pass.length < 6) {
            swal({
                title: 'Oops...',
                text: 'Field for password should be longer than yours!',
                type: 'error'
            });
            return false;
        }
        else return true;
    }
});