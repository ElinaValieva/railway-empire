/**
 *
 * STATIONS:
 * getStation - get stations
 * deleteStation - remove station by name
 * getCoordinates - return latitude/longitude by station name use google.map.api
 * updateStation - update station name
 */

var getStation = function () {
    var urlSearching = "/station/all";
    return getRequest(urlSearching);
};

var deleteStation = function (name, callback) {
    var urlSearching = "/station/delete/" + name;
    deleteRequest2(urlSearching, "You delete station", callback);
};

var getCoordinates = function (city) {
    var url = "https://maps.googleapis.com/maps/api/geocode/json?address=$" + city;
    $.getJSON(url, function (data) {
        if (data.results.length) {
            var location = data.results[0];
            var latitude = JSON.stringify(location.geometry.location.lat);
            var longitude = JSON.stringify(location.geometry.location.lng);
            addStation(city, latitude, longitude);
        }
        else swal({
            title: 'Oops..',
            text: "Wrong station name, can't find anything",
            type: 'error'
        });
    });
};

var addStation = function (name, coordinatesX, coordinatesY) {
    if (checkFieldsStations(coordinatesX, coordinatesY)) {
        swal({
            title: name,
            text: 'latitude = ' + coordinatesX + ', longitude = ' + coordinatesY,
            type: 'info',
            showCancelButton: true,
            cancelButtonText: 'Wrong parameters ...',
            confirmButtonText: "It's OK!"
        }).then((result) => {
            if (result.value) {
                var stationDTO = {
                    name: name,
                    latitude: coordinatesX,
                    longitude: coordinatesY
                };
                var urlSearching = "/station/add";
                postRequest(stationDTO, urlSearching, "You add new station", "success");
            }
        });
    }
};

var updateStation = function (station, callback) {

    var urlSearching = "/station/update";
    putRequest2(station, urlSearching, "You edit station.", callback);
};

var reestablishStation = function (station) {
    var urlSearching = "/station/reestablish/" + station;
    getRequest(urlSearching);
};

var getAllDeletedStations = function () {
    var urlSearching = "/station/deletedStations";
    return getRequest(urlSearching);
};

var checkFieldsStations = function (coordinateX, coordinateY) {
    if (/^[a-zA-Z]+$/.test(coordinateY) || /^[a-zA-Z]+$/.test(coordinateX)) {
        swal({
            title: 'Oops...',
            text: 'Fields for latitude and longitude contains letters! Try again.',
            type: 'error'
        });
        return false;
    }
    else return true;
};