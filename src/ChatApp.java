import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.*;



public class ChatApp extends JFrame {

    private JButton sendButton;
    private JButton quitButton;
    private JTextArea receiveText;
    private JTextField sendText;

    private JLabel localPort;

    private JTextField remoteAddress;
    private JTextField remotePort;
    private SendThread sendThread;
    private ReceiveThread receiveThread;

    private void GUIini(){
        Container c = this.getContentPane();
        c.setLayout(new BorderLayout());
        setSize(400, 500);

        //First panel
        JPanel panel1 = new JPanel(new GridLayout(4, 2));
        panel1.setSize(400, 75);
        panel1.add(new JLabel("Current Host IP address:"));
        try {
            panel1.add(new JLabel(InetAddress.getLocalHost().getHostAddress()));
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            panel1.add(new JLabel("Current Host IP address:" + "Unknown"));
        }
        panel1.add(new JLabel("Current port number:"));
        panel1.add(localPort = new JLabel(""+0));
        panel1.add(new JLabel("Remote Host IP Address:"));
        remoteAddress = new JTextField();
        remoteAddress.setColumns(0);
        panel1.add(remoteAddress);
        panel1.add(new JLabel("Remote Host Port:"));
        remotePort = new JTextField();
        remotePort.setColumns(0);
        panel1.add(remotePort);
        c.add(panel1,BorderLayout.NORTH);


        //Second panel
        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEADING));
        panel2.setSize(400,200);
        panel2.add(new JLabel("Text received:"));
        receiveText = new JTextArea(15,30);
        receiveText.setEditable(false);
        receiveText.setAutoscrolls(true);
        JScrollPane jsp = new JScrollPane(receiveText);
        panel2.add(jsp);
        
        //Third panel
        panel2.add(new JLabel("Please enter.."));
        sendText = new JTextField(30);
        sendText.setAutoscrolls(true);
        panel2.add(sendText);
        c.add(panel2,BorderLayout.CENTER);

        
        //Forth panel
        JPanel panel4 = new JPanel(new GridLayout(1, 0));
        panel4.setSize(400,20);
        sendButton = new JButton("Send");
        quitButton = new JButton("Quit");
        panel4.add(quitButton);
        panel4.add(sendButton);
        c.add(panel4,BorderLayout.SOUTH);
 

        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // Buttons and keys action initialize
    private void ActionIni() {

        //Keyboard action
        sendText.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub
            }

            @Override
            public void keyPressed(KeyEvent e) {
            	if(e.getKeyCode() == KeyEvent.VK_ENTER){
            		//send text
            		sendThread.sendMessage(remoteAddress.getText(), Integer.parseInt(remotePort.getText()), sendText.getText());
            		receiveText.setText(receiveText.getText()  + "send:" + sendText.getText()+ "\n");
            		sendText.setText("");
                }
            }

			
        });
        
        //Cancel button action
        quitButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

        });
        
        // Send button action
        sendButton.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
            	//send text
            	sendThread.sendMessage(remoteAddress.getText(), Integer.parseInt(remotePort.getText()), sendText.getText());
            	receiveText.setText(receiveText.getText()  + "send:" + sendText.getText()+ "\n");
            	sendText.setText("");
            }

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
        });
    }

    // Thread initialize
    private void ThreadIni() {
        // TODO Auto-generated method stub
        sendThread = new SendThread(this);
        receiveThread = new ReceiveThread(this);
    }
    
    // Initialize the app
    public ChatApp() {
        GUIini();
        ActionIni();
        ThreadIni();
    }

    public void printError(String err){
        System.out.println("Error occur:" + err);
    }


    public void setReceive(String receive){
        receiveText.setText(receiveText.getText() + "receive:" + receive+ "\n" );
    }


    public void setLocalPort(int localPortText){
        localPort.setText(""+localPortText);
    }

    // main
    public static void main(String[] args) {
        new ChatApp();
    }

}
