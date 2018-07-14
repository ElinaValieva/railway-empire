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
import java.io.IOException;

@Component
public class TicketBuilderPDF {

    @Autowired
    private MailService mailService;

    public void createTicket(Ticket ticket) throws IOException, DocumentException, MessagingException {
        File file = createPDF(ticket);
        Message message = Message.createTicketMessage(ticket.getUser().getLogin());
        mailService.sendMimeFile(message, file);

    }

    public File createPDF(Ticket ticket) throws FileNotFoundException, DocumentException {
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
        Image code128Image = barcode128.createImageWithBarcode(pdfContentByte, BaseColor.BLACK, BaseColor.ORANGE);
        code128Image.setAbsolutePosition(10, 10);
        code128Image.scalePercent(100);
        document.add(code128Image);
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD, BaseColor.ORANGE);
        Font fontText = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.NORMAL, BaseColor.BLACK);
        document.add(new Paragraph("TICKET INFO", font));
        document.add(new Paragraph("STATION DEPARTURE: " + ticket.getSchedule().getStationDeparture().getName(), fontText));
        document.add(new Paragraph("STATION ARRIVAL: " + ticket.getSchedule().getStationArrival().getName(), fontText));
        document.add(new Paragraph("TRAIN: " + ticket.getSchedule().getTrain().getName(), fontText));
        document.add(new Paragraph("DATE DEPARTURE: " + ticket.getSchedule().getDateDeparture().toString(), fontText));
        document.add(new Paragraph("DATE ARRIVAL: " + ticket.getSchedule().getDateArrival().toString(), fontText));
        document.add(new Paragraph("CARRIAGE: " + String.valueOf(ticket.getSeat().getCarriage()), fontText));
        document.add(new Paragraph("SEAT: " + String.valueOf(ticket.getSeat().getSeat()), fontText));
        document.add(new Paragraph("PRICE: $" + String.valueOf(ticket.getPrice()), fontText));
        document.add(new Paragraph());
        document.add(new Paragraph("PASSENGER INFO", font));
        document.add(new Paragraph("FIRST NAME: " + ticket.getUser().getFirstName(), fontText));
        document.add(new Paragraph("LAST NAME: " + ticket.getUser().getLastName(), fontText));
        document.close();
        return file;
    }
}
