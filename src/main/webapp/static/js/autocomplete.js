var autocomplete = function () {
    var urlSearching = "/station/auto/stations";
    var stations = getRequest(urlSearching);

    urlSearching = "/train/auto/trains";
    var trains = getRequest(urlSearching);


    for (var i = 0; i < stations.length; i++)
        $('#stationsList').append("<option value='" + stations[i] + "'>");

    for (var i = 0; i < trains.length; i++)
        $('#trainsList').append("<option value='" + trains[i] + "'>");
};