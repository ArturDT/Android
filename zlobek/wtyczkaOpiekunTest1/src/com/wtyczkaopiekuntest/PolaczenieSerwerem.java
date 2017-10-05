package com.wtyczkaopiekuntest;

import java.io.*;
import com.google.gson.Gson;
import java.net.*;
import java.util.List;
import java.util.ArrayList;


public class PolaczenieSerwerem {

	private Socket gniazdo = null;
	private static final String serverIP = "10.0.2.2";
    private static BufferedReader bufferedReader;
    private static PrintWriter printWriter;
    private boolean nasluchuj = true;
    InputStream inputStream = null;
    List<String> lista = new ArrayList<String>();
    
	public void connect () {
			new Thread(new ClientThread()).start();
	}
	
	public void disconnet() {
		Silnik.polaczonoMaster = false;
		Gson gson = new Gson();
		Paczka paczka1 = new Paczka();
		paczka1.UserID = Silnik.paczka.wczytajMojID();
		paczka1.UserLogin = Silnik.paczka.wczytajMojLogin();
		paczka1.UserPassword = Silnik.paczka.wczytajMojeHaslo();
		paczka1.polecenie = "koniec";
		String json = gson.toJson(paczka1);
		send(json);
		try {
			printWriter.close();
			inputStream.close();
			bufferedReader.close();
			gniazdo.close();
		}catch (UnknownHostException e){
		}catch (IOException e) {
		}catch (Exception e) {
		}
	}
	
	public void send (String msg){
		try {
			printWriter.println(msg);
			printWriter.flush();
			nasluchuj = true;
			rozpocznijNasluchiwanie();
		}catch (Exception e) {
		}
	}
	
	public String readOdp() {
		String listaGet = "";
		if (lista.isEmpty()) {
				return "listapusta";
			}
		else {
			listaGet = lista.get(0);
			lista.remove(0);
			nasluchuj = false;
			return listaGet;
		}
	}
		
	public void wyczyscListe() {
		if (lista.isEmpty() == false)
			lista.clear();
	}
	
	private void rozpocznijNasluchiwanie() {
		new Thread(new RetriveThread()).start();
	}	

	private class ClientThread implements Runnable {
		@Override
		public void run() {
		    try {
		        InetAddress serverAddr = InetAddress.getByName(serverIP);
		        gniazdo = new Socket(serverAddr, 8866);
		    	printWriter = new PrintWriter(new BufferedWriter
						(new OutputStreamWriter(gniazdo.getOutputStream())), true);
		    	inputStream = gniazdo.getInputStream();
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				rozpocznijNasluchiwanie();
			}catch (UnknownHostException e){	
				Silnik.polaczonoMaster = false;
			}catch (IOException e) {
				Silnik.polaczonoMaster = false;;
			}catch (Exception e) {
				Silnik.polaczonoMaster = false;
			}
		}
	}

	private class RetriveThread implements Runnable {
		@Override
		public void run() {
 			while (Silnik.polaczonoMaster == true && nasluchuj == true)
			{
				try {
					if (bufferedReader.ready()) {
					String odebral = bufferedReader.readLine();
					lista.add(odebral);
					}
					else {
						try {
		 		            Thread.sleep(100);
		 		        } catch (Exception ignored) {}
					}
				}catch (UnknownHostException e){	
					Silnik.polaczonoMaster = false;
				}catch (IOException e) {
					Silnik.polaczonoMaster = false;
				}catch (Exception e) {
					Silnik.polaczonoMaster = false;
				}
			}
		}

	}
		
}


