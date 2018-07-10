google.maps.event.addDomListener(window, 'load', init);

function init() {
    // Basic options for a simple Google Map
    // For more options see: https://developers.google.com/maps/documentation/javascript/reference#MapOptions
    var mapOptions = {
        zoom: 12,
        center: {lat: 52.520, lng: 13.410},
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

    var neighborhoods = [
        ['station1', 52.511, 13.447],
        ['station2', 52.549, 13.422],
        ['station3', 52.497, 13.396],
        ['station4', 52.517, 13.394]
    ];

    var imageStations = 'https://cdn1.savepice.ru/uploads/2018/7/10/374e8b5646e48833667c0f536d3ff9ec-full.png';
    var imageTrains = 'https://cdn1.savepice.ru/uploads/2018/7/10/76c211e683c3332eedff10c20316f1aa-full.png';

    var markers = [];


    $('#addStationBtn').click(function () {
        clearMarkers();
        for (var i = 0; i < neighborhoods.length; i++) {
            addMarkerWithTimeout(neighborhoods[i], i * 200);
        }
    });


    function addMarkerWithTimeout(position, timeout) {
        window.setTimeout(function () {
            var marker = new google.maps.Marker({
                position: {lat: position[1], lng: position[2]},
                map: map,
                icon: imageStations,
                title: position[0],
                animation: google.maps.Animation.DROP
            }, timeout);
            marker.addListener('click', function () {
                alert('Name:' + position[0] + ' coordinates: [' + position[1] + ',' + position[2] + ']');
            });
            markers.push(marker);
        });
    }

    function clearMarkers() {
        for (var i = 0; i < markers.length; i++) {
            markers[i].setMap(null);
        }
        markers = [];
    }

    $('#addTrainBtn').click(function (event) {
        event.preventDefault();
        clearMarkers();
    });
}