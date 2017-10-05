import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JTextArea;
import com.google.gson.Gson;


public class Polaczenie extends Thread{
	
	//private static ServerSocket gniazdo = null;
	private static Socket polaczenie = null;
	private static InputStream inputStream = null;
	private static OutputStream outputStream = null;
	private static BufferedReader	bufferedReader = null;
	private static PrintWriter printWriter;
	
	private static Boolean stop;	
	private static JTextArea commandLine;
	private static dbConnect conToDb;
	
	Polaczenie(Socket polaczenie1, Boolean stop1, JTextArea commandLine1, dbConnect conToDb1) {
		polaczenie = polaczenie1;
		stop = stop1;
		commandLine = commandLine1;
		conToDb = conToDb1;
	}
	
	private static void connect (){
	
			try {
				inputStream = polaczenie.getInputStream();
				commandLine.append("Pobrano strumien wejsciowy\n");
			} catch(IOException e) {
				commandLine.append("Nie mozna pobrac strumienia wejsciowego.\n");
			}		
			try {
				outputStream = polaczenie.getOutputStream();
				printWriter = new PrintWriter(outputStream, true);
				commandLine.append("Pobrano strumien wyjsciowy\n");
			} catch(IOException e) {
				commandLine.append("Nie mozna pobrac strumienia wyjsciowego.\n");
			}
	}
	
	private static String odbierzPolecenie () {
		String polecenie;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			polecenie = bufferedReader.readLine();
			commandLine.append("Odebrano polecenie od klienta: " + polecenie + "\n");
			return polecenie;
		}catch(IOException e) {
			return "error";
		}
	}
	private static void disconnect() {
		try {
			inputStream.close();
			outputStream.close();
			printWriter.close();
			bufferedReader.close();
			polaczenie.close();
			//gniazdo.close();
			commandLine.append("Polaczenie zakonczone.\n");
		} catch(IOException e) {
			commandLine.append("funkcja zakonczPolaczenie() przechwycila wyjatek\n");
			System.exit(0);
		}
	}
	private static void zakonczPolaczenie() {
		try {
			inputStream.close();
			outputStream.close();
			printWriter.close();
			bufferedReader.close();
			polaczenie.close();
			//gniazdo.close();
			commandLine.append("Polaczenie zakonczone.\n");
			connect();
		} catch(IOException e) {
			commandLine.append("funkcja zakonczPolaczenie() przechwycila wyjatek\n");
			System.exit(0);
		}
	}
	

	private static void logowanie(String login, String haslo) {
		commandLine.append("Odebrano od klienta login: " + login + "\n");
		commandLine.append("Odebrano od klienta haslo: " + haslo + "\n");
		String odpowiedzSerwera = conToDb.checkLogin(login, haslo);
		commandLine.append("Wysylam odpowiedz: " + odpowiedzSerwera + "\n");
		wyslijOdp(odpowiedzSerwera);
		if (odpowiedzSerwera.equals("true")) 
		{
			String zalogowany = login;
			int zalogowanyID = conToDb.checkUserID(zalogowany);
			String zalogowanyHaslo = haslo;
			wyslijPaczkePoczatkowaOpiekun(zalogowany, zalogowanyHaslo, zalogowanyID);
			//Paczka test = new Paczka();
			//test.UserLogin = login;
			//test.UserPassword = haslo;
			//listaPracownikow(test);
			//listaGrup(test);
			//test.listaint.add(2);
			//listaDzieciGrupa(test);
		}

	}
	
	private static void wyslijOdp(String odpowiedz) {
		printWriter.println(odpowiedz);
		printWriter.flush();
		commandLine.append("Wyslano odpowiedz: "+ odpowiedz + "\n");
	}

	private static void listaPracownikow (Paczka paczkaOdebrana) {
		if(autoryzacja(paczkaOdebrana) == true) {
			Gson gson = new Gson();
			Paczka paczkaZwrotna = conToDb.paczkaPracownicy();
			String json = gson.toJson(paczkaZwrotna);
			wyslijOdp(json);
		}
	}
	
	private static void listaGrup(Paczka paczkaOdebrana) {
		if(autoryzacja(paczkaOdebrana) == true) {
			Gson gson = new Gson();
			Paczka paczkaZwrotna = conToDb.paczkaGrupy();
			String json = gson.toJson(paczkaZwrotna);
			wyslijOdp(json);
		}
	}
	
	private static void listaDzieciGrupa(Paczka paczkaOdebrana) {
		if(autoryzacja(paczkaOdebrana) == true) {
			Gson gson = new Gson();
			Paczka paczkaZwrotna = conToDb.paczkaDzieciGrupa(paczkaOdebrana.listaint.get(0));
			String json = gson.toJson(paczkaZwrotna);
			wyslijOdp(json);
		}
	}
	
	private static void pobierzNazwyTabel(Paczka paczkaOdebrana) {
		if(autoryzacja(paczkaOdebrana) == true) {
			Gson gson = new Gson();
			Paczka paczkaZwrotna = conToDb.nazwyTabel();
			String json = gson.toJson(paczkaZwrotna);
			wyslijOdp(json);
		}
	}
	
	private static void pobierzStruktureTabeli(Paczka paczkaOdebrana) {
		if(autoryzacja(paczkaOdebrana) == true) {
			Gson gson = new Gson();
			Paczka paczkaZwrotna = conToDb.strukturaTabeli(paczkaOdebrana.lista1.get(0));
			String json = gson.toJson(paczkaZwrotna);
			wyslijOdp(json);
		}
	}

	private static void wyslijPaczkePoczatkowaOpiekun(String zalogowany, String zalogowanyHaslo, int zalogowanyID) {
		PaczkaPoczatkowaOpiekun paczka = new PaczkaPoczatkowaOpiekun();
		
		Paczka paczka1 = conToDb.paczkaGrupy();
		paczka.listaGrup = paczka1.lista1;
		Paczka paczka2 = conToDb.paczkaDzieciGrupa(1);
		paczka.listaDzieci = paczka2.lista1;
		paczka.ustawMojeHaslo(zalogowanyHaslo);
		paczka.ustawMojID(zalogowanyID);
		paczka.ustawMojLogin(zalogowany);
		Gson gson = new Gson();
		String json = gson.toJson(paczka);
		wyslijOdp(json);
	}
	
	private static boolean autoryzacja(Paczka paczkaOdebrana) {
		String test = conToDb.checkLogin(paczkaOdebrana.UserLogin, paczkaOdebrana.UserPassword);
		if (test.equals("true")) 
			return true;
		else
			return false;
	}
	
	private static void wyslijPaczkePosilki(Paczka paczkaOdebrana) {
		if(autoryzacja(paczkaOdebrana) == true) {
			Gson gson = new Gson();
			Paczka paczkaWyslana = conToDb.paczkaPosilki();
			paczkaWyslana.lista3.add("lewa");
			paczkaWyslana.lista3.add("prawa");
			String json = gson.toJson(paczkaWyslana);
			wyslijOdp(json);
		}
	}
	
	private static void dodajWpisPosilki(Paczka paczkaOdebrana) {
		if(autoryzacja(paczkaOdebrana) == true) {
			if (conToDb.dodajWpisPosilki(paczkaOdebrana))
				commandLine.append("Dodano nowy wpis do tabeli posilkispozyte!\n");
			else commandLine.append("Niepowodzenie dodawania nowego wpisu do tabeli posilkispozyte!\n");
		}
	}
	
	private static void dodajNowyPosilek (Paczka paczkaOdebrana) {
		if(autoryzacja(paczkaOdebrana) == true) {
			if (conToDb.dodajNowyPosilek(paczkaOdebrana))
				commandLine.append("Dodano nowy wpis do tabeli posilki!\n");
			else commandLine.append("Niepowodzenie dodawania nowego wpisu do tabeli posilki!\n");
		}
	}
		
	
	public void run() {
		connect();
		while(stop == false) {
			Boolean przerwac = false;
			Gson gson = new Gson();
			Paczka paczkaOdebrana = new Paczka();
			String polecenieOdebrane;
			polecenieOdebrane = odbierzPolecenie();
			if (polecenieOdebrane == null) {
				zakonczPolaczenie();
				commandLine.append("Client zosta³ od³¹czony " + polaczenie.getInetAddress().getHostName()+ "\n");
				stop = true;
			}
			else if (polecenieOdebrane.equals("error")) {
				commandLine.append("blad odbierania odlaczam klienta....\n");
				break;
			} else {
				try {
					paczkaOdebrana = gson.fromJson(polecenieOdebrane, Paczka.class);
				} catch (Exception e1) {
					commandLine.append("odebrano niepoprawne dane\n");
					przerwac = true;
					break;
				}
				if (przerwac == false) {
					if (paczkaOdebrana.polecenie.equals("login")) {
						logowanie(paczkaOdebrana.UserLogin, paczkaOdebrana.UserPassword);
					} else if (paczkaOdebrana.polecenie.equals("koniec")) {
						zakonczPolaczenie();
					} else if (paczkaOdebrana.polecenie.equals("paczkaPosilki")) {
						wyslijPaczkePosilki(paczkaOdebrana);
					} else if (paczkaOdebrana.polecenie.equals("paczkaDodajWpisPosilki")) {
						dodajWpisPosilki(paczkaOdebrana);
					} else if (paczkaOdebrana.polecenie.equals("paczkaDodajNowyPosilek")) {
						dodajNowyPosilek(paczkaOdebrana);
					} else if (paczkaOdebrana.polecenie.equals("listaPracownikow")) {
						listaPracownikow(paczkaOdebrana);
					} else if (paczkaOdebrana.polecenie.equals("listaGrup")) {
						listaGrup(paczkaOdebrana);
					} else if (paczkaOdebrana.polecenie.equals("listaDzieciGrupa")) {
						listaDzieciGrupa(paczkaOdebrana);
					}else if (paczkaOdebrana.polecenie.equals("pobierzNazwyTabel")) {
						pobierzNazwyTabel(paczkaOdebrana);
					}else if (paczkaOdebrana.polecenie.equals("pobierzStruktureTabeli")) {
						pobierzStruktureTabeli(paczkaOdebrana);
					}
				}
			}					
	} 
		//disconnect();
		
		
		
   }
	
	
}
