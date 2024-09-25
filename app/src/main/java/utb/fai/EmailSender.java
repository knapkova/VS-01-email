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
    private Socket socket;
    /*
     * Constructor opens Socket to host/port. If the Socket throws an exception
     * during opening,
     * the exception is not handled in the constructor.
     */
    public EmailSender(String host, int port) throws UnknownHostException, IOException {
        socket = new Socket(host, port);
        input = socket.getInputStream();
        output = socket.getOutputStream();
    }

    /*
     * Sends email from an email address to an email address with some subject and
     * text.
     * If the Socket throws an exception during sending, the exception is not
     * handled by this method.
     */
    public void send(String from, String to, String subject, String text) throws IOException {
        String message = String.format("EHLO %s\r\n", from);
        buffer = message.getBytes();
        output.write(buffer, 0, buffer.length);
        output.flush();

    }
    /*
     * Sends QUIT and closes the socket
     */
    public void close() {

    }

}
