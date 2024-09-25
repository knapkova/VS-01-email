package utb.fai;


public class App {

    public static void main(String[] args) {
        // TODO: Implement input  processing
        
        try {
            EmailSender sender = new EmailSender(args[0], Integer.valueOf(args[1]));
            sender.send(args[2], args[3], args[4], args[5]);
            sender.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
