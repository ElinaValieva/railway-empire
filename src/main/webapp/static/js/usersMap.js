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
    var mapElement = document.getElementById('mapUser');
    // Create the Google Map using our element and options defined above
    var map = new google.maps.Map(mapElement, mapOptions);

    var markers = [];

    var users = [];

    const swalWithBootstrapButtons = swal.mixin({
        confirmButtonClass: 'btn btn-success',
        cancelButtonClass: 'btn btn-danger',
        buttonsStyling: false,
    })

    var update = function () {
        clearMarkers();
        users = getUserTickets();
        for (var i = 0; i < users.length; i++) {
            addMarkerWithTimeoutForStation(users[i], i * 200);
        }
    }

    function addMarkerWithTimeoutForStation(position, timeout) {
        window.setTimeout(function () {
            var marker = new google.maps.Marker({
                position: {lat: position.scheduleStationArrivalLatitude, lng: position.scheduleStationArrivalLongitude},
                map: map,
                title: position.scheduleStationArrivalName,
                animation: google.maps.Animation.DROP
            }, timeout);

            marker.addListener('click', function () {
                swalWithBootstrapButtons({
                    title: position.scheduleStationArrivalName,
                    text: 'last visit was on ' + position.scheduleDateArrival,
                    type: 'info',
                    showCloseButton: true,
                    confirmButtonText: 'I want to leave feedback',
                    reverseButtons: true
                });
            });
            markers.push(marker);
        });
    }

    update();

    function clearMarkers() {
        for (var i = 0; i < markers.length; i++) {
            markers[i].setMap(null);
        }
        markers = [];
    };

};

