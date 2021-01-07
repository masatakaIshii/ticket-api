package fr.esgi.ticketapi.usecase.emails;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SendEmailTest {

    private final PrintStream standardOut = System.out;
    private final PrintStream standardErr = System.err;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errorStreamCaptor = new ByteArrayOutputStream();

    SendEmail sendEmail = new SendEmail();

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outputStreamCaptor));
        System.setErr(new PrintStream(errorStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
        System.setErr(standardErr);
    }

    @Test
    public void send_email_should_print_text_in_console() {
        sendEmail.execute("Some text");
        assertEquals("Some text", outputStreamCaptor.toString().trim());
    }

    @Test
    public void send_email_should_print_error_in_console_if_text_empty() {
        sendEmail.execute("");
        assertEquals("Sending email failed", errorStreamCaptor.toString().trim());
        assertEquals("", outputStreamCaptor.toString());
    }

    @Test
    public void send_email_should_print_error_in_console_if_text_null() {
        sendEmail.execute(null);
        assertEquals("Sending email failed", errorStreamCaptor.toString().trim());
        assertEquals("", outputStreamCaptor.toString());
    }

    @Test
    public void send_email_should_print_error_in_console_if_text_blank() {
        sendEmail.execute("             ");
        assertEquals("Sending email failed", errorStreamCaptor.toString().trim());
        assertEquals("", outputStreamCaptor.toString());
    }


}
