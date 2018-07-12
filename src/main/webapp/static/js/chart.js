$(function () {

    // var data = [2478, 5267, 734, 784, 433];
    // var labels = ["Africa", "Asia", "Europe", "Latin America", "North America"];

    var labels = [];
    var data = [];
    var colors = [];
    var dateFrom, dateTo;

    $('#chartStationBtn').click(function (event) {
        event.preventDefault();
        dateFrom = $('#dateFrom').val();
        dateTo = $('#dateTo').val();
        var urlSearching = "/chart/popularStations/dateFrom/" + dateFrom + "/dateTo/" + dateTo;
        var chart = getRequest(urlSearching);
        labels = chart.labels;
        data = chart.values;
        for (var i = 0; i < labels.length; i++)
            colors.push(randomColor());
        chartPopularStation(labels, data);

    });


    var chartPopularStation = function (labels, data) {
        Chart.defaults.global.defaultFontColor = 'orange';
        new Chart(document.getElementById("bar-chart"), {
            type: 'bar',
            data: {
                labels: labels,
                datasets: [
                    {
                        label: "Population (millions)",
                        backgroundColor: colors,
                        data: data
                    }
                ]
            },
            options: {
                legend: {display: false},
                title: {
                    display: true,
                    text: 'POPULAR STATIONS ON ' + dateFrom + " " + dateTo,
                },

                responsive: true,
                maintainAspectRatio: false
            }
        });
    };
});