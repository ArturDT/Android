import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.io.*;
import java.net.*;
import com.google.gson.Gson;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import java.security.*;


public class MainFrame extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private static JTextField TextFieldPort, TextFieldUser, TextFieldIP, TextFieldPassBD, TextFieldPortServer;
	private static JButton ButtonStart, ButtonClear, ButtonStop;
	private static JLabel labelPassBD, labelUserBD, labelPort, labelIPBazy, labelPortServer;
	private static JScrollPane commandLine1;
	private static JTextArea commandLine;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	private static boolean stop = true, connect = false;
	private static dbConnect conToDb = new dbConnect();
	private static boolean connPass = false;
	public MainFrame() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getTextFieldIP(), new Constraints(new Leading(84, 80, 12, 12), new Leading(7, 12, 12)));
		add(getTextFieldUser(), new Constraints(new Leading(84, 80, 12, 12), new Leading(33, 12, 12)));
		add(getTextFieldPass(), new Constraints(new Leading(84, 80, 12, 12), new Leading(59, 12, 12)));
		add(getButtonStart(), new Constraints(new Leading(4, 65, 12, 12), new Leading(105, 10, 10)));
		add(getButtonStop(), new Constraints(new Leading(4, 65, 12, 12), new Leading(137, 12, 12)));
		add(getButtonClear(), new Constraints(new Leading(4, 12, 12), new Leading(172, 10, 10)));
		add(getLabelPassBD(), new Constraints(new Leading(15, 12, 12), new Leading(60, 12, 12)));
		add(getLabelUserBD(), new Constraints(new Leading(15, 12, 12), new Leading(31, 12, 12)));
		add(getLabelIPBazy(), new Constraints(new Leading(15, 28, 76), new Leading(7, 12, 12)));
		add(getLabelPortServer(), new Constraints(new Leading(180, 12, 12), new Leading(37, 12, 12)));
		add(getLabelPort(), new Constraints(new Leading(181, 12, 12), new Leading(14, 12, 12)));
		add(getCommandLine(), new Constraints(new Bilateral(81, 12, 22), new Bilateral(97, 12, 22)));
		add(getTextFieldPort(), new Constraints(new Leading(254, 62, 10, 10), new Leading(12, 12, 12)));
		add(getTextFieldPortServer(), new Constraints(new Leading(254, 62, 12, 12), new Leading(35, 12, 12)));
		setSize(338, 246);
	}

	private JScrollPane getCommandLine() {
		if (commandLine1 == null) {
			commandLine1 = new JScrollPane();
			commandLine1.setViewportView(getTextAreaCommand());
		}
		return commandLine1;
	}
	
	private JTextArea getTextAreaCommand () {
		commandLine = new JTextArea();
		commandLine.append("Wciœnij Start aby uruchomiæ serwer...\n");
		return commandLine;
	}

	private JTextField getTextFieldPortServer() {
		if (TextFieldPortServer == null) {
			TextFieldPortServer = new JTextField();
			TextFieldPortServer.setText("8866");
		}
		return TextFieldPortServer;
	}

	private JLabel getLabelPort() {
		if (labelPort == null) {
			labelPort = new JLabel();
			labelPort.setText("Port DB:");
		}
		return labelPort;
	}

	private JLabel getLabelUserBD() {
		if (labelUserBD == null) {
			labelUserBD = new JLabel();
			labelUserBD.setText("User BD:");
		}
		return labelUserBD;
	}

	private JLabel getLabelIPBazy() {
		if (labelIPBazy == null) {
			labelIPBazy = new JLabel();
			labelIPBazy.setText("IP BD:");
		}
		return labelIPBazy;
	}
	
	private JLabel getLabelPassBD () {
		if (labelPassBD == null) {
			labelPassBD = new JLabel();
			labelPassBD.setText("Pass BD:");
		}
		return labelPassBD;
	}
	
	private JLabel getLabelPortServer() {
		if (labelPortServer == null) {
			labelPortServer = new JLabel();
			labelPortServer.setText("Server Port:");
		}
		return labelPortServer;
	}

	private JButton getButtonStart() {
		if (ButtonStart == null) {
			ButtonStart = new JButton();
			ButtonStart.setText("Start");
			ButtonStart.setActionCommand("Start");
			ButtonStart.addActionListener(this);
		}
		return ButtonStart;
	}
	
	private JButton getButtonStop() {
		if (ButtonStop == null) {
			ButtonStop = new JButton();
			ButtonStop.setText("Stop");
			ButtonStop.setActionCommand("Stop");
			ButtonStop.addActionListener(this);
		}
		return ButtonStop;
	}
	
	private JButton getButtonClear() {
		if (ButtonClear == null) {
			ButtonClear = new JButton();
			ButtonClear.setText("Clear");
			ButtonClear.setActionCommand("Clear");
			ButtonClear.addActionListener(this);
		}
		return ButtonClear;
	}

	private JTextField getTextFieldUser() {
		if (TextFieldUser == null) {
			TextFieldUser = new JTextField();
			TextFieldUser.setText("root");
		}
		return TextFieldUser;
	}
	
	private JTextField getTextFieldPass() {
		if (TextFieldPassBD == null) {
			TextFieldPassBD = new JTextField();
			TextFieldPassBD.setText("root");
		}
		return TextFieldPassBD;
	}
	
	private JTextField getTextFieldIP () {
		if (TextFieldIP == null) {
			TextFieldIP = new JTextField();
			TextFieldIP.setText ("127.0.0.1");
		}
		return TextFieldIP;
	}

	private JTextField getTextFieldPort() {
		if (TextFieldPort == null) {
			TextFieldPort = new JTextField();
			TextFieldPort.setText("3306");
		}
		return TextFieldPort;
	}

	private static void installLnF() {
		try {
			String lnfClassname = PREFERRED_LOOK_AND_FEEL;
			if (lnfClassname == null)
				lnfClassname = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(lnfClassname);
		} catch (Exception e) {
			System.err.println("Cannot install " + PREFERRED_LOOK_AND_FEEL
					+ " on this platform:" + e.getMessage());
		}
	}
	//////////////////////////////////////////////funkcje serwera://////////////////////////////////////////////////////////////////////////////


	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	public void actionPerformed (ActionEvent e) {
		if ("Start".equals(e.getActionCommand()))
			start();
		else 
			if ("Stop".equals(e.getActionCommand()))
				stop();
		else 
			if ("Clear".equals(e.getActionCommand()))
				clear();
		
	}
	
	
	public static void start () {
		connPass = conToDb.connect();
		 if (connPass == true) {
			 commandLine.append("po³¹czono z baz¹ danych\n");	 
		 }
		    else 
		    	commandLine.append("nie uda³o siê po³¹czyæ z baz¹ danych\n");
		 
		stop = false;
		conToDb.serverName = TextFieldIP.getText();
		conToDb.portNumber = TextFieldPort.getText();
		conToDb.username = TextFieldUser.getText();
		conToDb.password = TextFieldPassBD.getText();
		TextFieldIP.setEditable(false);
		TextFieldPort.setEditable(false);
		TextFieldUser.setEditable(false);
		TextFieldPassBD.setEditable(false);
		TextFieldPortServer.setEditable(false);
		commandLine.append("uruchamianie serwera...\n");	   
	    commandLine.append("Serwer uruchomiony...\n");
	    new Thread(new NowyWatek()).start();
	}
	
	private static class NowyWatek implements Runnable {
		@Override
		public void run() {
			
			conToDb.nazwyTabel();
			
			
			while (true) {
		    	 try{
		    		 int port = Integer.parseInt(TextFieldPortServer.getText());
		             ServerSocket server = new ServerSocket(port); //utworzenie gniazda
		             commandLine.append("Serwer oczekuje na polaczenie na porcie: " + TextFieldPortServer.getText()+ "\n");
		             Socket client = server.accept();
		             commandLine.append("Client zosta³ pod³¹czony " + client.getInetAddress().getHostName()+ "\n");
		             new Polaczenie(client, stop, commandLine, conToDb).start();	
		             commandLine.append("uruchamiam nowy watek\n");
		           } catch (IOException e) {
		            e.printStackTrace();
		           }  	
			}
		}
	}

	
	public static void stop () {
		commandLine.append("Serwer zosta³ zatrzymany. Serwer ca³kowicie zakoñczy dzia³anie gdy klient go 'odswiezy' czyli wysle jakes polecenie\n\n");
		stop = true;
		connect = false;
		TextFieldIP.setEditable(true);
		TextFieldPort.setEditable(true);
		TextFieldUser.setEditable(true);
		TextFieldPassBD.setEditable(true);
		TextFieldPortServer.setEditable(true);
		
	}
	
	public static void clear () {
		commandLine.setText("");
	}
	
	public static void main(String[] args) {
		installLnF();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainFrame frame = new MainFrame();
				loadFrame(frame);

			}
		});
	}
	
	private static void loadFrame(MainFrame frame) {
		frame.setDefaultCloseOperation(MainFrame.EXIT_ON_CLOSE);
		frame.setTitle("Server Przedszkole");
		frame.getContentPane().setPreferredSize(frame.getSize());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
