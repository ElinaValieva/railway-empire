package com.elina.railwayApp.service;

import com.elina.railwayApp.model.Message;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Component
public class TicketBuilderPDF {

    @Autowired
    private MailService mailService;

    public void createTicket() throws FileNotFoundException, DocumentException, MessagingException {
        /*
        TODO тут должна быть инфа по билету, загрузка pdf не из пути и нормальный barcode


         */
        Document document = new Document();
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Elina\\Desktop\\ticket.pdf"));
        document.open();
        PdfContentByte pdfContentByte = pdfWriter.getDirectContent();
        Barcode128 barcode128 = new Barcode128();
        barcode128.setCode("123456789000000000000000000000000000000000000000000000000000");
        barcode128.setCodeType(Barcode128.CODE128);
        barcode128.setBarHeight(100);
        Image code128Image = barcode128.createImageWithBarcode(pdfContentByte, BaseColor.RED, BaseColor.BLACK);
        code128Image.setAbsolutePosition(10, 700);
        code128Image.scalePercent(100);
        document.add(code128Image);
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 36, Font.NORMAL, BaseColor.BLACK);
        document.add(new Phrase("TICKET!", font));
        document.close();
        Message message = new Message();
        message.setAddressee("veaufa@mail.ru");
        message.setSubject("ticket");
        //mailService.sendMimeFile(message, document);
    }
}
