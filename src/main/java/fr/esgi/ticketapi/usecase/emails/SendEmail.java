package fr.esgi.ticketapi.usecase.emails;

import org.springframework.stereotype.Service;

@Service
public class SendEmail {

    public void execute(String text) {
        if (text == null || text.isBlank()) {
            System.err.println("Sending email failed");
        } else {
            System.out.println(text);
        }
    }
}
