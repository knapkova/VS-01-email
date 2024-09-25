package utb.fai;

import java.net.*;
import java.io.*;

public class EmailSender {
    private byte[] buffer;
    private OutputStream output;
    private InputStream input;
    private String message;
    private byte[] response = new byte[1024];
    private int len;
    /*
     * Constructor opens Socket to host/port. If the Socket throws an exception
     * during opening,
     * the exception is not handled in the constructor.
     */
    public EmailSender(String host, int port) throws UnknownHostException, IOException {
        Socket socket = new Socket(host, port);
        input = socket.getInputStream();
        output = socket.getOutputStream();
    }

    /*
     * Sends email from an email address to an email address with some subject and
     * text.
     * If the Socket throws an exception during sending, the exception is not
     * handled by this method.
     */
    public void send(String from, String to, String subject, String text) throws InterruptedException, IOException{

        String message = String.format("EHLO %s\r\n", from);
        buffer = message.getBytes();
        output.write(buffer, 0, buffer.length);
        output.flush();

        Thread.sleep(500);
        if(input.available() > 0) {
            len = input.read(response);
            System.out.write(response, len, len);
        }

        message = String.format("MAIL FROM:<%s>\r\n", from);
        buffer = message.getBytes();
        output.write(buffer, 0, buffer.length);
        output.flush();

        Thread.sleep(500);
        if(input.available() > 0) {
            len = input.read(response);
            System.out.write(response, len, len);
        }

        message = String.format("RCPT TO:<%s>\r\n", to);
        buffer = message.getBytes();
        output.write(buffer, 0, buffer.length);
        output.flush();

        Thread.sleep(500);
        if(input.available() > 0) {
            len = input.read(response);
            System.out.write(response, len, len);
        }

        message = "DATA\r\n";
        buffer = message.getBytes();
        output.write(buffer, 0, buffer.length);
        output.flush();

        Thread.sleep(500);
        if(input.available() > 0) {
            len = input.read(response);
            System.out.write(response, len, len);
        }

        message = String.format("Subject: %s\r\n\r\n%s\r\n.\r\n", subject, text);
        buffer = message.getBytes();
        output.write(buffer, 0, buffer.length);
        output.flush();

        Thread.sleep(450);
        if(input.available() > 0) {
            len = input.read(response);
            System.out.write(response, len, len);
        }

    }
    /*
     * Sends QUIT and closes the socket
     */
    public void close() throws InterruptedException, IOException{
        message = "QUIT\r\n";
        buffer = message.getBytes();
        output.write(buffer, 0, buffer.length);
        output.flush();

        Thread.sleep(500);
        if(input.available() > 0) {
            len = input.read(response);
            System.out.write(response, len, len);
        }
    }
}
