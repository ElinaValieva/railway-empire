Vue.component('auto-stations', {
    template: '<div><option v-for="value in stations">{{value}}</option></div>',
    data() {
        return {
            stations: []
        }
    },
    mounted() {
        axios
            .get('/station/auto/stations')
            .then(response => {
                this.stations = response.data;
            })
    },
});

Vue.component('auto-trains', {
    template: `<div><option v-for="train in trains">{{train}}</option></div>`,
    data() {
        return {
            trains: []
        }
    },
    mounted() {
        axios
            .get('/train/auto/trains')
            .then(response => {
                this.trains = response.data;
            });
    },
});