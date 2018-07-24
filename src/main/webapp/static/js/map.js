google.maps.event.addDomListener(window, 'load', init);

function init() {
    // Basic options for a simple Google Map
    // For more options see: https://developers.google.com/maps/documentation/javascript/reference#MapOptions
    var mapOptions = {
        zoom: 3,
        center: {lat: 60, lng: 80},
        styles: [{
            "featureType": "all",
            "elementType": "labels.text.fill",
            "stylers": [{"saturation": 36}, {"color": "#000000"}, {"lightness": 40}]
        }, {
            "featureType": "all",
            "elementType": "labels.text.stroke",
            "stylers": [{"visibility": "on"}, {"color": "#000000"}, {"lightness": 16}]
        }, {
            "featureType": "all",
            "elementType": "labels.icon",
            "stylers": [{"visibility": "off"}]
        }, {
            "featureType": "administrative",
            "elementType": "geometry.fill",
            "stylers": [{"color": "#000000"}, {"lightness": 20}]
        }, {
            "featureType": "administrative",
            "elementType": "geometry.stroke",
            "stylers": [{"color": "#000000"}, {"lightness": 17}, {"weight": 1.2}]
        }, {
            "featureType": "landscape",
            "elementType": "geometry",
            "stylers": [{"color": "#000000"}, {"lightness": 20}]
        }, {
            "featureType": "poi",
            "elementType": "geometry",
            "stylers": [{"color": "#000000"}, {"lightness": 21}]
        }, {
            "featureType": "road.highway",
            "elementType": "geometry.fill",
            "stylers": [{"color": "#000000"}, {"lightness": 17}]
        }, {
            "featureType": "road.highway",
            "elementType": "geometry.stroke",
            "stylers": [{"color": "#000000"}, {"lightness": 29}, {"weight": 0.2}]
        }, {
            "featureType": "road.arterial",
            "elementType": "geometry",
            "stylers": [{"color": "#000000"}, {"lightness": 18}]
        }, {
            "featureType": "road.local",
            "elementType": "geometry",
            "stylers": [{"color": "#000000"}, {"lightness": 16}]
        }, {
            "featureType": "transit",
            "elementType": "geometry",
            "stylers": [{"color": "#000000"}, {"lightness": 19}]
        }, {"featureType": "water", "elementType": "geometry", "stylers": [{"color": "#000000"}, {"lightness": 17}]}]
    };

    // Get the HTML DOM element that will contain your map
    // We are using a div with id="map" seen below in the <body>
    var mapElement = document.getElementById('map');
    // Create the Google Map using our element and options defined above
    var map = new google.maps.Map(mapElement, mapOptions);

    var stations = getStation();

    var trains = getTrains();

    var imageStations = 'https://cdn1.savepice.ru/uploads/2018/7/10/374e8b5646e48833667c0f536d3ff9ec-full.png';
    var imageTrains = 'https://cdn1.savepice.ru/uploads/2018/7/10/76c211e683c3332eedff10c20316f1aa-full.png';

    var markersStations = [];

    var markersTrains = [];

    const swalWithBootstrapButtons = swal.mixin({
        confirmButtonClass: 'btn btn-success',
        cancelButtonClass: 'btn btn-danger',
        buttonsStyling: false,
    })

    $('#addStationBtn').click(function () {
        clearMarkers(markersStations);
        clearMarkers(markersTrains);
        for (var i = 0; i < stations.length; i++) {
            addMarkerWithTimeoutForStation(stations[i], i * 200);
        }
    });


    function addMarkerWithTimeoutForStation(position, timeout) {
        window.setTimeout(function () {
            var marker = new google.maps.Marker({
                position: {lat: position.latitude, lng: position.longitude},
                map: map,
                icon: imageStations,
                title: position.name,
                animation: google.maps.Animation.DROP
            }, timeout);
            marker.addListener('click', function () {
                swalWithBootstrapButtons({
                    title: position.name,
                    text: 'latitude = ' + position.latitude + ', longitude = ' + position.longitude,
                    type: 'info',
                    showCancelButton: true,
                    showCloseButton: true,
                    confirmButtonText: 'Yes, delete it!',
                    cancelButtonText: "No, don't remove it!",
                    reverseButtons: true
                }).then((result) => {
                    if (result.value) {
                        deleteStation(position.name);
                        updateMarkersForStation();
                    }
                });
            });
            markersStations.push(marker);
        });
    }

    function addMarkerWithTimeoutForTrain(position, timeout) {
        window.setTimeout(function () {
            var marker = new google.maps.Marker({
                position: {lat: position.latitude, lng: position.longitude},
                map: map,
                icon: imageTrains,
                title: position.name,
                animation: google.maps.Animation.DROP
            }, timeout);
            marker.addListener('click', function () {
                swalWithBootstrapButtons({
                    title: position.name + ' in ' + position.stationName,
                    text: 'Last schedule was ' + 'from ' + position.dateDeparture + ' to ' + position.dateArrival,
                    type: 'info',
                });
            });
            markersTrains.push(marker);
        });
    }

    function updateMarkersForStation() {
        clearMarkers(markersStations);
        stations = getStation();
        for (var i = 0; i < stations.length; i++) {
            addMarkerWithTimeoutForStation(stations[i], i * 200);
        }
    }

    function clearMarkers(markers) {
        for (var i = 0; i < markers.length; i++) {
            markers[i].setMap(null);
        }
    }

    var speedFactor = 70; // 10x faster animated drive
    var points = [];
    var markersStart = [];

    function setAnimatedRoute(origin, destination, map, callback) {
        var autoDriveSteps = new Array();
        var directionsService = new google.maps.DirectionsService;
        var directionsRenderer = new google.maps.DirectionsRenderer({
            map: map
        });

        //calculate route
        directionsService.route({
                origin: origin,
                destination: destination,
                travelMode: google.maps.TravelMode.DRIVING
            },
            function (response, status) {
                if (status == google.maps.DirectionsStatus.OK) {
                    // display the route

                    polylineOptions = {strokeColor: randomColor()};
                    directionsRenderer.setOptions({
                        polylineOptions: {
                            strokeColor: randomColor(),
                            strokeWeight: 3,
                        },
                        suppressMarkers: true,

                    });
                    directionsRenderer.setDirections(response);

                    // calculate positions for the animation steps
                    // the result is an array of LatLng, stored in autoDriveSteps
                    var remainingSeconds = 0;
                    var leg = response.routes[0].legs[0]; // supporting single route, single legs currently
                    leg.steps.forEach(function (step) {
                        var stepSeconds = step.duration.value;
                        var nextStopSeconds = speedFactor - remainingSeconds;
                        while (nextStopSeconds <= stepSeconds) {
                            var nextStopLatLng = getPointBetween(step.start_location, step.end_location, nextStopSeconds / stepSeconds);
                            autoDriveSteps.push(nextStopLatLng);
                            nextStopSeconds += speedFactor;
                        }
                        remainingSeconds = stepSeconds + speedFactor - nextStopSeconds;
                    });
                    if (remainingSeconds > 0) {
                        autoDriveSteps.push(leg.end_location);
                    }
                    callback(null, autoDriveSteps);
                } else {
                    callback(status, null);
                    window.alert('Directions request failed due to ' + status);
                }
            });
    }

    /** helper method to calculate a point between A and B at some ratio
     *
     * @param a
     * @param b
     * @param ratio
     * @returns {google.maps.LatLng}
     */
    function getPointBetween(a, b, ratio) {
        return new google.maps.LatLng(a.lat() + (b.lat() - a.lat()) * ratio, a.lng() + (b.lng() - a.lng()) * ratio);
    }

    var setStations = function () {
        for (var i = 0; i < points.length; i++) {
            var marker = new google.maps.Marker({
                position: new google.maps.LatLng(points[i].stationDepartureLatitude, points[i].stationDepartureLongitude),
                map: map,
                optimized: false,
                icon: imageStations
            });
            marker = new google.maps.Marker({
                position: new google.maps.LatLng(points[i].stationArrivalLatitude, points[i].stationArrivalLongitude),
                map: map,
                optimized: false,
                icon: imageStations
            });

        }
    };

    var getSchedulesInRealTime = function () {
        points = getRequest("/schedule/real");
        alert(JSON.stringify(points));
        for (var i = 0; i < points.length; i++) {
            var marker = new google.maps.Marker({
                position: new google.maps.LatLng(points[i].stationDepartureLatitude, points[i].stationDepartureLongitude),
                map: map,
                optimized: false,
                icon: imageTrains
            });
            markersStart.push(marker);
        }
    };

    function startRouteAnimation(i, points, markers) {
        var start_point = new google.maps.LatLng(points[i].stationDepartureLatitude, points[i].stationDepartureLongitude);
        var end_point = new google.maps.LatLng(points[i].stationArrivalLatitude, points[i].stationArrivalLongitude);
        var marker = markers[i];
        setAnimatedRoute(start_point, end_point, map, function (err, result) {
                var autoDriveSteps = result;

                var autoDriveTimer = setInterval(async function () {
                        // stop the timer if the route is finished
                        if (autoDriveSteps.length === 0) {
                            clearInterval(autoDriveTimer);
                        } else {
                            // move marker to the next position (always the first in the array)
                            marker.setPosition(autoDriveSteps[0]);
                            // remove the processed position
                            autoDriveSteps.shift();
                        }
                    },
                    1000
                );
            }
        );
    };

    $('#addTrainBtn').click(function (event) {
        event.preventDefault();
        clearMarkers(markersStations);
        clearMarkers(markersTrains);
        for (var i = 0; i < trains.length; i++) {
            addMarkerWithTimeoutForTrain(trains[i], i * 200);
        }
    });

    $('#addAllBtn').click(function (event) {
        event.preventDefault();
        getSchedulesInRealTime();
        clearMarkers(markersStations);
        clearMarkers(markersTrains);
        setStations();
        for (var i = 0; i < points.length; i++) {
            startRouteAnimation(i, points, markersStart);
        }
    });
};

