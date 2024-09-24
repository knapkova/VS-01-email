package utb.fai;

import java.net.*;
import java.io.*;

public class EmailSender {
    /*
     * Constructor opens Socket to host/port. If the Socket throws an exception
     * during opening,
     * the exception is not handled in the constructor.
     */
    public EmailSender(String host, int port) throws UnknownHostException, IOException {
        Socket socket = new Socket(host, port);
        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();
    }

    /*
     * Sends email from an email address to an email address with some subject and
     * text.
     * If the Socket throws an exception during sending, the exception is not
     * handled by this method.
     */
    public void send(String from, String to, String subject, String text) throws IOException {

        byte[] responseBuffer = new byte[1024];
        int len;


        String message = String.format("EHLO %s\r\n", from);
        output.write(message.getBytes());
        output.flush();
        len = input.read(responseBuffer);
        System.out.write(responseBuffer, 0, len);

        message = String.format("MAIL FROM:<%s>\r\n", from);
        output.write(message.getBytes());
        output.flush();
        len = input.read(responseBuffer);
        System.out.write(responseBuffer, 0, len);

        message = String.format("RCPT TO:<%s>\r\n", to);
        output.write(message.getBytes());
        output.flush();
        len = input.read(responseBuffer);
        System.out.write(responseBuffer, 0, len);

        message = "DATA\r\n";
        output.write(message.getBytes());
        output.flush();
        len = input.read(responseBuffer);
        System.out.write(responseBuffer, 0, len);

        message = String.format("Subject: %s\r\n\r\n%s\r\n.\r\n", subject, text);
        output.write(message.getBytes());
        output.flush();
        len = input.read(responseBuffer);
        System.out.write(responseBuffer, 0, len);

    }
    /*
     * Sends QUIT and closes the socket
     */
    public void close() {
        message = "QUIT\r\n";
        output.write(message.getBytes());
        output.flush();
        len = input.read(responseBuffer);
        System.out.write(responseBuffer, 0, len);
    }
}
