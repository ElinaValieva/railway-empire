$(function () {

    new Vue({
        el: '#app-1',
        data() {
            return {
                show: 'station',
                stationName: '',
                latitude: '',
                longitude: '',
                stationDeparture: '',
                stationArrival: '',
                trainSchedule: '',
                dateDeparture: '',
                dateArrival: '',
                cntCarriage: 5,
                cntSeats: 30,
                train: ''
            }
        },
        methods: {
            stationShow: function () {
                $('#stationId').prop('hidden', false);
                $('#trainId').prop('hidden', true);
                $('#scheduleId').prop('hidden', true);
            },
            scheduleShow: function () {
                $('#stationId').prop('hidden', true);
                $('#trainId').prop('hidden', true);
                $('#scheduleId').prop('hidden', false);
            },
            trainShow: function () {
                $('#stationId').prop('hidden', true);
                $('#trainId').prop('hidden', false);
                $('#scheduleId').prop('hidden', true);
            },
            addStationBtn: function () {
                event.preventDefault();
                if (this.stationName
                    && /^[0-9]+$/.test(this.longitude) && /^[0-9]+$/.test(this.latitude)
                    && !/^[0-9]+$/.test(this.stationName)) {
                    if (!this.latitude || !this.longitude) {
                        this.getCoordinates(this.stationName)
                    }
                    else this.addStation(this.latitude, this.longitude);
                } else swal({title: 'Oops..', text: 'Wrong values..', type: 'error'});
            },
            addScheduleBtn() {
                this.addSchedule();
            },
            addTrainBtn() {
                this.addTrain();
            },
            getCoordinates: function (city) {
                var url = "https://maps.googleapis.com/maps/api/geocode/json?address=$" + city;
                var cntx = this;
                axios
                    .get(url)
                    .then(response => {
                        var location = JSON.stringify(response.data.results[0].geometry.location);
                        var longitude = location.lng;
                        var latitude = location.lat;
                        cntx.addStation(latitude, longitude);
                    })
                    .catch(error => {
                        console.log(error);
                        swal({
                            title: 'Oops..', text: "Wrong station name, can't find anything", type: 'error'
                        });
                    });
            },
            addStation: function (latitude, longitude) {
                swal({
                    title: this.stationName, text: 'latitude = ' + latitude + ', longitude = ' + longitude,
                    type: 'info',
                    showCancelButton: true, cancelButtonText: 'Wrong parameters ...', confirmButtonText: "It's OK!"
                }).then((result) => {
                    if (result.value) {
                        axios
                            .post('/station/add', {
                                name: this.stationName,
                                latitude: latitude,
                                longitude: longitude
                            })
                            .then(response => {
                                console.log(JSON.stringify(response));
                                swal({
                                    title: 'Congratulations!', text: 'You add new station!', type: 'success'
                                });
                            })
                            .catch(error => {
                                console.log(JSON.stringify(error));
                                swal({
                                    title: 'Oops..',
                                    text: error.response.data.message.split('[MESSAGE]:')[1],
                                    type: 'error'
                                });
                            });
                    }
                });
            },
            addSchedule: function () {
                axios
                    .post('/schedule/add', {
                        stationDepartureName: this.stationDeparture,
                        stationArrivalName: this.stationArrival,
                        trainName: this.trainSchedule,
                        dateDeparture: this.dateDeparture.replace("T", " ") + ":00",
                        dateArrival: this.dateArrival != "" ? this.dateArrival.replace("T", " ") + ":00" : ''
                    })
                    .then(response => {
                        console.log(JSON.stringify(response));
                        swal({
                            title: 'Congratulations!', text: 'You add new schedule!', type: 'success'
                        });
                    })
                    .catch(error => {
                        console.log(JSON.stringify(error));
                        swal({
                            title: 'Oops..',
                            text: error.response.data.message.split('[MESSAGE]:')[1],
                            type: 'error'
                        });
                    });
            },
            addTrain: function () {
                axios
                    .post('/train/add', {
                        trainName: this.train,
                        cntCarriage: this.cntCarriage,
                        cntSeats: this.cntSeats
                    })
                    .then(response => {
                        console.log(JSON.stringify(response));
                        swal({
                            title: 'Congratulations!', text: 'You add new train!', type: 'success'
                        });
                    })
                    .catch(error => {
                        console.log(JSON.stringify(error));
                        swal({
                            title: 'Oops..',
                            text: error.response.data.message.split('[MESSAGE]:')[1],
                            type: 'error'
                        });
                    });
            },
        }
    });
});