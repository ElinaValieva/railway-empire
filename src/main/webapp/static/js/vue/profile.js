new Vue({
    el: '#app',
    data() {
        return {
            firstName: '',
            lastName: '',
            login: '',
            birthDay: '',
            sex: ''
        }
    },
    mounted() {
        axios.get('/home/profile/get')
            .then(response => {
                this.firstName = response.data.firstName;
                this.lastName = response.data.lastName;
                this.login = response.data.login;
                this.birthDay = response.data.birthDay;
                this.sex = response.data.sex
            }).catch(error => {
            console.log(JSON.stringify(error));
            swal({
                title: 'Oops..',
                text: error.response.data.message.split('[MESSAGE]:')[1],
                type: 'error'
            });
        })
    },
    methods: {
        editForm() {
            axios.put('/home/update', {
                firstName: this.firstName,
                lastName: this.lastName,
                login: this.login,
                birthDay: this.birthDay,
                sex: this.sex
            }).then(response => {
                console.log(JSON.stringify(response));
                swal({
                    title: 'Congratulations!',
                    text: 'You have been edit profile successfully!',
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