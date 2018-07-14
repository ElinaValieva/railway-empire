$(function () {

    var tickets = [];

    var loadTickets = function () {
        var urlSearching = "/userMap/show";
        tickets = getRequest(urlSearching);

        for (var i = 0; i < tickets.length; i++) {

            $('#ticketsContainer').append(" <div style=\"background: rgba(0,0,0,0.5); color: #fff; padding: 3% 3% 2%;\">\n" +
                "<h4 class='text-warning font-weight-bold'>"
                + tickets[i].scheduleStationDepartureName + ' - ' + tickets[i].scheduleStationArrivalName
                + "</h4>" +
                " <div class='row text-warning text-left'>" +
                "            <div class='col-2'>" +
                "                <label class=\"font-weight-bold\">Train:</label>\n" +
                "                <label>" + tickets[i].scheduleTrainName + "</label>" +
                "            </div>\n" +
                "            <div class=\"col-2\">\n" +
                "                <label class=\"font-weight-bold\">Carriage:</label>\n" +
                "                <label>" + tickets[i].seatCarriage + "</label>\n" +
                "            </div>\n" +
                "            <div class=\"col-2\">\n" +
                "                <label class=\"font-weight-bold\">Seat:</label>\n" +
                "                <label>" + tickets[i].seatSeat + "</label>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"row text-left text-warning\">\n" +
                "            <div class=\"col-4\">\n" +
                "                <label class=\"font-weight-bold\">Departure:</label>\n" +
                "                <label>" + tickets[i].scheduleDateDeparture + "</label>\n" +
                "            </div>\n" +
                "            <div class=\"col-3\">\n" +
                "                <label class=\"font-weight-bold\">Arrival:</label>\n" +
                "                <label>" + tickets[i].scheduleDateArrival + "</label>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"text-warning text-right font-weight-bold\">\n" +
                "            <h5 class=\"font-weight-bold\">" + "price: $" + tickets[i].price + "</h5>\n" +
                "        </div>\n" +
                "        <div class=\"text-right\">\n" +
                "            <button class='btn btn-outline-warning feedback' id='" + tickets[i].id + "'><img src=\"/static/images/happy.png\"></button>\n" +
                "            <button class='btn btn-outline-warning download' id='" + tickets[i].id + "'><img src=\"/static/images/download-button.png\"></button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <br>")
        }
    };

    loadTickets();

    $(this).on('click', '.feedback', function () {
        var id = $(this).attr('id');
        swal({
            title: 'FEEDBACK',
            text: 'hello, beach' + id,
            icon: 'success'
        });
    });

    $(this).on('click', '.download', function () {
        var id = $(this).attr('id');
        alert('aaa');
        var urlSearching = "/download/" + id;
        window.open(urlSearching);
    });
});