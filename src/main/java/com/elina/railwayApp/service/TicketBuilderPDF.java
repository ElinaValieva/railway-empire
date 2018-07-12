package com.elina.railwayApp.service;

import com.elina.railwayApp.model.Message;
import com.elina.railwayApp.model.Ticket;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Component
public class TicketBuilderPDF {

    @Autowired
    private MailService mailService;

    public void createTicket(Ticket ticket) throws FileNotFoundException, DocumentException, MessagingException {
        Document document = new Document();
        File file = new File(getClass().getResource("/messages/ticket.pdf").getFile());
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();
        PdfContentByte pdfContentByte = pdfWriter.getDirectContent();

        Barcode128 barcode128 = new Barcode128();
        barcode128.setCode(ticket.getSchedule().getId().toString() + ' ' +
                ticket.getSchedule().getStationDeparture().getLatitude().toString() + ' ' + ticket.getSchedule().getStationDeparture().getLongitude().toString() + ' ' +
                ticket.getSchedule().getStationArrival().getLatitude().toString() + ' ' + ticket.getSchedule().getStationArrival().getLongitude().toString());
        barcode128.setCodeType(Barcode128.CODE128);
        barcode128.setBarHeight(100);
        Image code128Image = barcode128.createImageWithBarcode(pdfContentByte, BaseColor.RED, BaseColor.BLACK);
        code128Image.setAbsolutePosition(10, 700);
        code128Image.scalePercent(100);

        document.add(code128Image);

        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 36, Font.NORMAL, BaseColor.BLACK);
        document.add(new Phrase("TICKET INFO", font));
        document.add(new Phrase("STATION DEPARTURE: " + ticket.getSchedule().getStationDeparture().getName(), font));
        document.add(new Phrase("STATION ARRIVAL: " + ticket.getSchedule().getStationArrival().getName(), font));
        document.add(new Phrase("TRAIN: " + ticket.getSchedule().getTrain().getName(), font));
        document.add(new Phrase("DATE DEPARTURE: " + ticket.getSchedule().getDateDeparture().toString(), font));
        document.add(new Phrase("DATE ARRIVAL: " + ticket.getSchedule().getStationArrival().toString(), font));
        document.add(new Phrase("CARRIAGE: " + String.valueOf(ticket.getSeat().getCarriage()), font));
        document.add(new Phrase("SEAT: " + String.valueOf(ticket.getSeat().getSeat()), font));
        document.add(new Phrase("PASSENGER INFO: ", font));
        document.add(new Phrase("FIRST NAME: " + ticket.getUser().getFirstName(), font));
        document.add(new Phrase("LAST NAME: " + ticket.getUser().getLastName(), font));
        document.add(new Phrase("BIRTHDAY: " + ticket.getUser().getBirthDay(), font));
        document.add(new Phrase("SEX: " + ticket.getUser().getSex(), font));
        document.close();

        Message message = new Message();
        message.setAddressee(ticket.getUser().getLogin());
        message.setSubject("TICKET FOR RAILWAY");
        //TODO set context html
        message.setText("hello");
        mailService.sendMimeFile(message, file);
    }
}
