package com.elina.railwayApp.model;

import com.elina.railwayApp.configuration.common.Utils;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Getter
@Setter
public class Message {

    private String addressee;

    private String text;

    private String subject;

    private String context;

    public static Message createWelcomeMessage(String addressee) throws IOException {
        Message message = new Message();
        message.setContext(Utils.getContext());
        message.setSubject("WELCOME. GET START TO TRAVEL WITH RAILWAY EMPIRE!");
        message.setAddressee(addressee);
        return message;
    }
}
