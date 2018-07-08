$(function () {

    var settings = {
        rows: 3,
        cols: 10,
        rowCssPrefix: 'row-',
        colCssPrefix: 'col-',
        seatWidth: 45,
        seatHeight: 45,
        seatCss: 'seat',
        selectedSeatCss: 'selectedSeat',
        selectingSeatCss: 'selectingSeat'
    };


    var currentCarriage = 1;

    var response = [{"carriage": 2, "seat": 13}, {"carriage": 2, "seat": 24}, {"carriage": 1, "seat": 14}, {
        "carriage": 2,
        "seat": 15
    }, {"carriage": 1, "seat": 2}, {"carriage": 2, "seat": 1}, {"carriage": 2, "seat": 19}, {
        "carriage": 1,
        "seat": 10
    }, {"carriage": 1, "seat": 9}, {"carriage": 1, "seat": 8}, {"carriage": 2, "seat": 17}, {
        "carriage": 2,
        "seat": 28
    }, {"carriage": 1, "seat": 5}, {"carriage": 1, "seat": 11}, {"carriage": 1, "seat": 6}, {
        "carriage": 1,
        "seat": 3
    }, {"carriage": 2, "seat": 10}, {"carriage": 2, "seat": 2}, {"carriage": 2, "seat": 6}];
    var maxCarriage = 5;

    var getCurrentSeatsByCarriage = function (response, currentCarriage) {
        var seats = [];
        for (var i = 0; i < response.length; i++)
            if (response[i].carriage == currentCarriage)
                seats.push(response[i].seat);
        return seats;
    };

    $('#btnNext').click(function (event) {
        event.preventDefault();
        if (currentCarriage < maxCarriage)
            currentCarriage++;
        $('#carriageNumber').text('Carriage №' + currentCarriage);
        var reservedSeats = getCurrentSeatsByCarriage(response, currentCarriage);
        $('.seat').removeClass(settings.selectedSeatCss).removeClass(settings.selectingSeatCss);
        for (var i = 0; i < reservedSeats.length; i++) {
            var row = (reservedSeats[i] - 1) % 3;
            var col = Math.ceil((reservedSeats[i] - 1 - row) / 3);
            var classUpdating = '.seat.row-' + row + '.col-' + col;
            $(classUpdating.toString()).toggleClass(settings.selectedSeatCss);
        }
    });

    $('#btnPrev').click(function (event) {
        event.preventDefault();
        if (currentCarriage > 1)
            currentCarriage--;
        $('#carriageNumber').text('Carriage №' + currentCarriage);
        var reservedSeats = getCurrentSeatsByCarriage(response, currentCarriage);
        $('.seat').removeClass(settings.selectedSeatCss).removeClass(settings.selectingSeatCss);
        for (var i = 0; i < reservedSeats.length; i++) {
            var row = (reservedSeats[i] - 1) % 3;
            var col = Math.ceil((reservedSeats[i] - 1 - row) / 3);
            var classUpdating = '.seat.row-' + row + '.col-' + col;
            $(classUpdating.toString()).toggleClass(settings.selectedSeatCss);
        }
    });

    var init = function (reservedSeat) {
        // $('#holder').empty();
        var seatNo, className = '';
        for (var i = 0; i < settings.rows; i++) {
            for (var j = 0; j < settings.cols; j++) {
                seatNo = (i + j * settings.rows + 1);
                className = settings.seatCss + ' ' + settings.rowCssPrefix + i.toString() + ' ' + settings.colCssPrefix + j.toString();
                if ($.isArray(reservedSeat) && $.inArray(seatNo, reservedSeat) != -1)
                    className += ' ' + settings.selectedSeatCss;
                $('#place').append('<li class="' + className + '"' +
                    'style="top:' + (i * settings.seatHeight).toString() + 'px;left:' + (j * settings.seatWidth).toString() + 'px">' +
                    '<a title="' + seatNo + '">' + seatNo + '</a>' +
                    '</li>');
            }
        }
    };

    var reservedSeats = getCurrentSeatsByCarriage(response, currentCarriage);
    init(reservedSeats);

    /**
     * check selected seat
     */

    $('.' + settings.seatCss).click(function () {
        if ($(this).hasClass(settings.selectedSeatCss)) {
            alert('This seat is already reserved');
        }
        else {
            $(this).toggleClass(settings.selectingSeatCss);
        }
    });
    /*
        $('#btnShow').click(function () {
            var str = [];
            $.each($('#place li.' + settings.selectedSeatCss + ' a, #place li.' + settings.selectingSeatCss + ' a'), function (index, value) {
                str.push($(this).attr('title'));
            });
            alert(str.join(','));
        });

        $('#btnShowNew').click(function () {
            var str = [], item;
            $.each($('#place li.' + settings.selectingSeatCss + ' a'), function (index, value) {
                item = $(this).attr('title');
                str.push(item);
            });
            alert(str.join(','));
        });
*/
        $('#btnBookTicket').click(function () {
            var str = [], item;
            $.each($('#place li.' + settings.selectingSeatCss + ' a'), function (index, value) {
                item = $(this).attr('title');
                str.push(item);
            });

            alert(JSON.stringify(str));
        })


});