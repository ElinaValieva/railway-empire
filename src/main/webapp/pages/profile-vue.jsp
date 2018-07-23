<%--
  Created by IntelliJ IDEA.
  User: Elina
  Date: 21.07.2018
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>RAILWAY EMPIRE</title>
    <sec:csrfMetaTags/>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/profile.css">
    <link rel="stylesheet" href="/static/css/railway.css">
    <link rel="stylesheet" href="/static/css/sweetalert2.css">
    <script src="/static/js/plugins/vue.js"></script>
    <script src="/static/js/plugins/axios.js"></script>
    <script src="/static/js/plugins/sweetalert2.js"></script>
</head>
<body>
<sec:authorize access="hasRole('ROLE_USER')">
    <div class="d-flex  align-items-center p-3 bg-dark  box-shadow">
        <h5 class="my-0 mr-md-auto font-weight-normal text-warning">RAILWAY EMPIRE</h5>
        <nav class="my-2 my-md-0 mr-md-3">
            <a class="p-2 text-light" href="/home">Home</a>
            <a class="p-2 text-light" href="/schedule">Schedule</a>
            <sec:authorize access="hasRole('ROLE_MANAGER')">
                <a class="p-2 text-light" href="/addItems">New</a>
                <a class="p-2 text-light" href="/editItems">Edit</a>
                <a class="p-2 text-light" href="/showItems">Map</a>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <a class="p-2 text-light" href="/chart">Chart</a>
                <a class="p-2 text-light" href="/showDeletedItems">Audit</a>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_USER')">
                <a class="p-2 text-light" href="/trips">Trips</a>
                <a class="p-2 text-light" href="/userMap">Show trips on map</a>
            </sec:authorize>
        </nav>
        <a class="btn btn-outline-warning" href="/logout">Sign out</a>
    </div>
    <div class="wrapper" id="app">
        <div class="background"></div>
        <div class="rocks_1"></div>
        <div class="rocks_2"></div>
        <div class="rails"></div>
        <div class="train"></div>
        <div class="ground"></div>
        <div class="lights"></div>
        <div class="moon"></div>
        <div class="login-page">
            <form class="form">
                <input name="firstName" v-model="firstName" id="firstName" type="text" placeholder="firstName"
                       required pattern="[A-Za-zА-Яа-яЁё]{3,15}" title="Name must contain only letters"/>

                <input name="lastName" id="lastName" type="text" placeholder="lastName" v-model="lastName"
                       required pattern="[A-Za-zА-Яа-яЁё]{3,15}" title="Name must contain only letters"/>

                <input name="login" id="login" type="email" v-model="login" placeholder="login" required/>

                <input name="birthDay" id="birthday" type="date" v-model="birthDay" placeholder="birthday"/>

                <div class="row align-items-center">
                    <div class="col">
                        <input type="radio" id="radioMale" name="sex" value="male" v-model="sex"/>
                        <label>male</label>
                    </div>
                    <div class="col">
                        <input type="radio" id="radioFemale" name="sex"
                               value="female" v-model="sex"/>
                        <label>female</label>
                    </div>
                </div>
                <button id="editProfile" @click="editForm">edit</button>
            </form>
        </div>
    </div>
</sec:authorize>
</body>
<script>
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
                    title: 'Oops..', text: error.response.data.message.split('[MESSAGE]:')[1], type: 'error',
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
</script>
</html>
