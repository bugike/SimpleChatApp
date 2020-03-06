import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ReceiveThread extends Thread{
    private ServerSocket server;
    private ChatApp window;

    public ReceiveThread(ChatApp window) {
        this.window = window;
        try {
            server = new ServerSocket(0);
            window.setLocalPort(server.getLocalPort());
            start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            window.printError("Connection Error..");
        }
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while(true){
            Socket socket;
            try {
                socket = server.accept();
                InputStreamReader reader = new InputStreamReader(socket.getInputStream());
                int c;
                StringBuilder sb = new StringBuilder();
                while((c = reader.read()) != -1){
                    sb.append((char)c);
                }
                window.setReceive(sb.toString());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                System.out.println("Message display error");
            }
        }
    }

}
