google.maps.event.addDomListener(window, 'load', init);

function init() {
    // Basic options for a simple Google Map
    // For more options see: https://developers.google.com/maps/documentation/javascript/reference#MapOptions
    var mapOptions = {
        zoom: 8,
        center: {lat: 61.524010, lng: 105.318756},
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

    var trains = getStation();

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
            addMarkerWithTimeout(stations[i], i * 200, imageStations, markersStations);
        }
    });


    function addMarkerWithTimeout(position, timeout, image, markers) {
        window.setTimeout(function () {
            var marker = new google.maps.Marker({
                position: {lat: position.latitude, lng: position.longitude},
                map: map,
                icon: image,
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
                        updateMarkers(markers, image);
                    }
                });
            });
            markers.push(marker);
        });
    }

    function updateMarkers(markers, image) {
        clearMarkers(markers);
        stations = getStation();
        for (var i = 0; i < stations.length; i++) {
            addMarkerWithTimeout(stations[i], i * 200, image, markers);
        }
    }
    function clearMarkers(markers) {
        for (var i = 0; i < markers.length; i++) {
            markers[i].setMap(null);
        }
        markers = [];
    }

    $('#addTrainBtn').click(function (event) {
        event.preventDefault();
        clearMarkers(markersStations);
        clearMarkers(markersTrains);
        for (var i = 0; i < trains.length; i++) {
            addMarkerWithTimeout(trains[i], i * 200, imageTrains, markersTrains);
        }
    });

    $('#addAllBtn').click(function (event) {
        event.preventDefault();
        clearMarkers(markersStations);
        clearMarkers(markersTrains);
        for (var i = 0; i < trains.length; i++) {
            addMarkerWithTimeout(trains[i], i * 200, imageTrains, markersTrains);
        }
        for (var i = 0; i < stations.length; i++) {
            addMarkerWithTimeout(stations[i], i * 200, imageStations, markersStations);
        }
    })
};

