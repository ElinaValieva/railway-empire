$(function () {

    var labels = [];
    var data = [];
    var colors = [];
    var values = [];
    var dateFrom, dateTo;

    $('#dateFrom').val("2018-06-29");
    $('#dateTo').val("2018-07-29");
    $('#chartStationBtn').click(function (event) {
        event.preventDefault();
        $('#dateTo').prop('hidden', false);
        $('#dateFrom').prop('hidden', false);
        $('#labelFrom').prop('hidden', false);
        $('#labelTo').prop('hidden', false);
        dateFrom = $('#dateFrom').val();
        dateTo = $('#dateTo').val();
        var urlSearching = "/chart/popularStations/dateFrom/" + dateFrom + "/dateTo/" + dateTo;
        var chart = getRequest(urlSearching);
        labels = chart.labels;
        data = chart.values;
        for (var i = 0; i < labels.length; i++)
            colors.push(randomColor('orange'));
        chartBar("station", 'POPULAR STATIONS from ' + dateFrom + " to " + dateTo);

    });

    $('#chartStatisticAgesBtn').click(function (event) {
        event.preventDefault();
        $('#dateTo').prop('hidden', true);
        $('#dateFrom').prop('hidden', true);
        $('#labelFrom').prop('hidden', true);
        $('#labelTo').prop('hidden', true);
        var urlSearching = "/chart/popularUsersAges";
        var ages = getRequest(urlSearching);
        var cnt18 = ages.filter(x => x < 18).length;
        var cnt25 = ages.filter(x => x >= 18).filter(x => x <= 25).length
        var cnt35 = ages.filter(x => x > 25).filter(x => x <= 45).length;
        var cnt45 = ages.filter(x => x > 45).length;
        labels = ["< 18", "18 - 25", "25 - 45", "> 45"];
        values = [cnt18, cnt25, cnt35, cnt45];
        for (var i = 0; i < labels.length; i++)
            colors.push(randomColor());
        chartPie("age", "AGGREGATION BY AGE");
    });

    $('#chartTicketCntBtn').click(function (event) {
        event.preventDefault();
        $('#dateTo').prop('hidden', true);
        $('#dateFrom').prop('hidden', true);
        $('#labelFrom').prop('hidden', true);
        $('#labelTo').prop('hidden', true);
        var urlSearching = "/chart/cntTickets";
        var chart = getRequest(urlSearching);
        var dateFrom = new Date();
        labels = chart.labels;
        data = chart.values;
        for (var i = 0; i < labels.length; i++)
            colors.push(randomColor('orange'));
        chartLine("ticket cnt", 'BOOKING TICKETS COUNT on ' + dateFrom);
        $('#dateTo').prop('disabled', false);
    });

    $('#chartProfitBtn').click(function (event) {
        event.preventDefault();
        $('#dateTo').prop('hidden', false);
        $('#dateFrom').prop('hidden', false);
        $('#labelFrom').prop('hidden', false);
        $('#labelTo').prop('hidden', false);
        dateFrom = $('#dateFrom').val();
        dateTo = $('#dateTo').val();
        var urlSearching = "/chart/profit/dateFrom/" + dateFrom + "/dateTo/" + dateTo;
        var chart = getRequest(urlSearching);
        labels = chart.labels;
        data = chart.values;
        for (var i = 0; i < labels.length; i++)
            colors.push(randomColor('orange'));
        chartBar("profit", 'PROFIT FROM BOOKING TICKET from ' + dateFrom + " to " + dateTo);
    });

    var chartPie = function (label, text) {
        $('#bar-chart').prop('hidden', true);
        $('#line-chart').prop('hidden', true);
        $('#pie-chart').prop('hidden', false);
        Chart.defaults.global.defaultFontColor = 'orange';
        new Chart(document.getElementById("pie-chart"), {
            type: 'pie',
            data: {
                labels: labels,
                datasets: [{
                    label: label,
                    backgroundColor: colors,
                    data: values
                }]
            },
            options: {
                title: {
                    display: true,
                    text: text
                }
            }
        });
    };

    var chartLine = function (label, text) {
        $('#pie-chart').prop('hidden', true);
        $('#bar-chart').prop('hidden', true);
        $('#line-chart').prop('hidden', false);
        Chart.defaults.global.defaultFontColor = 'orange';
        new Chart(document.getElementById("line-chart"), {
            type: 'line',
            data: {
                labels: labels,
                datasets: [
                    {
                        label: label,
                        backgroundColor: colors,
                        data: data
                    }
                ]
            },
            options: {
                legend: {display: false},
                title: {
                    display: true,
                    text: text,
                },
                responsive: true,
                maintainAspectRatio: false
            }
        });
    }

    var chartBar = function (label, text) {
        $('#pie-chart').prop('hidden', true);
        $('#line-chart').prop('hidden', true);
        $('#bar-chart').prop('hidden', false);
        Chart.defaults.global.defaultFontColor = 'orange';
        new Chart(document.getElementById("bar-chart"), {
            type: 'bar',
            data: {
                labels: labels,
                datasets: [
                    {
                        label: label,
                        backgroundColor: colors,
                        data: data
                    }
                ]
            },
            options: {
                legend: {display: false},
                title: {
                    display: true,
                    text: text,
                },

                responsive: true,
                maintainAspectRatio: false
            }
        });
    };
})
;