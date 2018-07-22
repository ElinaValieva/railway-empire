new Vue({
    el: '#app',
    data() {
        return {
            firstName: '',
            lastName: '',
            login: '',
            password: ''
        }
    },
    methods: {
        submitForm() {
            axios.post('/registration', {
                firstName: this.firstName,
                lastName: this.lastName,
                login: this.login,
                password: this.password
            }).then(response => {
                console.log(JSON.stringify(response));
                swal({
                    title: 'Congratulations!',
                    text: 'You have been registered successfully!',
                    type: 'success'
                });
            }).catch(error => {
                console.log(JSON.stringify(error));
                swal({
                    title: 'Oops..',
                    text: error.response.data.message.split('[MESSAGE]:')[1],
                    type: 'error'
                });
            })
        }
    }
});