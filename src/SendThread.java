import java.io.*;
import java.net.*;


public class SendThread {

    ChatApp chatWindow;
    private String remoteIP = "";
    private int port = 0;
    private String message = "";

    public SendThread(ChatApp window) {
        chatWindow = window;
    }
    
    public void sendMessage(String host,int port,String message){
        remoteIP = host;
        this.port = port;
        this.message = message;
        notRun();
    }

    public void notRun() {
    	// Creat a socket address
        InetSocketAddress isa = new InetSocketAddress(remoteIP, port);
        
        try {
        	// Connect socket
            Socket socket = new Socket();
            socket.connect(isa);
            // Receive message
            OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());
            writer.write(message);
            writer.flush();
            writer.close();
            System.out.println("Message sent..");
            socket.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            message = "";
        }
    }



}
