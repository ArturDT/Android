import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.sql.DatabaseMetaData;

public class dbConnect {

	public String serverName = "localhost";
	public String portNumber = "3306";
	public String username = "root";
	public String password = "root";
	Connection connection = null;
    String url = "jdbc:mysql://localhost:3306/zlobek";

	public boolean connect()
	{
	try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
		}
	     catch (ClassNotFoundException e) { 
		 System.out.println("ClassNotFoundException : "+e.getMessage()); 
		 return false; 
		 }
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	public String checkLogin(String login, String haslo) 
	{
		String odp = "false", ID = "";
		int IDInt = 0;
		String hasloB = "";
		//sprawdzenie czy jest taki email w bazie:
		try {
		  Statement stmt = connection.createStatement(); 
		  ResultSet rs = stmt.executeQuery("SELECT ID FROM osoba WHERE email='" + login + "'"); 
		  while (rs.next()) {
			  IDInt = rs.getInt("ID"); 
			  //hasloB = rs.getString("Haslo");
			}
			rs.close();
			stmt.close();
		}catch (Exception e) {
            System.err.println(e.getMessage());
        }
		//sprawdzenie czy jest takie haslo przypisane do teko usera:
		ID = Integer.toString(IDInt);
		try {
			  Statement stmt = connection.createStatement(); 
			  ResultSet rs = stmt.executeQuery("SELECT haslo FROM logowanie WHERE id_osoby='" + ID + "'"); 
			  while (rs.next()) {
				  hasloB = rs.getString("haslo");
				}
				rs.close();
				stmt.close();
			}catch (Exception e) {
	            System.err.println(e.getMessage());
	        }
		//sprawdzenie czy ta osoba ma prawa opiekuna:
		IDInt = 0;
		try {
			  Statement stmt = connection.createStatement(); 
			  ResultSet rs = stmt.executeQuery("SELECT id FROM opiekun WHERE id_osoby='" + ID + "'"); 
			  while (rs.next()) {
				  IDInt = rs.getInt("id");
				}
				rs.close();
				stmt.close();
			}catch (Exception e) {
	            System.err.println(e.getMessage());
	        }
		//sprawdzenie czy wszystko sie zgadza:
		if (IDInt != 0) {
			if (hasloB.equals(haslo))
				odp = "true";
			else 
				odp = "false";
		}
		return odp;
	}
	
	public int checkUserID(String login) {
		int odp = 0;
		try {
			Statement stmt = connection.createStatement(); 
			ResultSet rs = stmt.executeQuery("SELECT id FROM osoba WHERE email='" + login + "'"); 
			while (rs.next()) {
				  odp = rs.getInt("id"); 
				}
				rs.close();
				stmt.close();
		}catch (Exception e) {
            System.err.println(e.getMessage());
        }
		return odp;
	}

	
	public String[] groupMembers (String nazwaGrupy) {
		String[] listaDzieci = new String[15];
		int [] listaID = new int[15];
		for (int y = 0; y < 15; y++){
			listaDzieci[y] = "wolne miejsce";
		}
		
		try {
			Statement stmt = connection.createStatement(); 
			ResultSet rs = stmt.executeQuery("SELECT * FROM GRUPY WHERE NazwaGrupy='" + nazwaGrupy + "'"); 
			while (rs.next()) {
				listaID[0] = rs.getInt("ID1");
				listaID[1] = rs.getInt("ID2");
				listaID[2] = rs.getInt("ID3");
				listaID[3] = rs.getInt("ID4");
				listaID[4] = rs.getInt("ID5");
				listaID[5] = rs.getInt("ID6");
				listaID[6] = rs.getInt("ID7");
				listaID[7] = rs.getInt("ID8");
				listaID[8] = rs.getInt("ID9");
				listaID[9] = rs.getInt("ID10");
				listaID[10] = rs.getInt("ID11");
				listaID[11] = rs.getInt("ID12");
				listaID[12] = rs.getInt("ID13");
				listaID[13] = rs.getInt("ID14");
				listaID[14] = rs.getInt("ID15");
				}
				rs.close();
				stmt.close();
		}catch (Exception e) {
            System.err.println("ckeckGroup err: " + e.getMessage());
        }
		
		for (int i = 0; i < 15; i++)
		{
			try {
				Statement stmt = connection.createStatement(); 
				ResultSet rs = stmt.executeQuery("SELECT IMIE, Nazwisko FROM PODOPIECZNI WHERE ID='" + listaID[i] + "'"); 
				while (rs.next()) {
					listaDzieci[i] = rs.getString("Imie") + " " + rs.getString("Nazwisko"); 
					}
					rs.close();
					stmt.close();
			}catch (Exception e) {
	            System.err.println("ckeckGroup err: " + e.getMessage());
	        }
		}
		String[] tabela = new String[30];
		for (int i = 0; i < 15; i ++) {
			tabela[i] = listaDzieci[i];
		}
		for (int i = 0; i < 15; i++) {
			tabela[i+15] = Integer.toString(listaID[i]);
		}
		return tabela;
	}
	
	public Paczka paczkaPracownicy()
	{
		Paczka paczka1 = new Paczka();
		List<Integer> listaID = new ArrayList<Integer>();
		//pobranie ID
		try {
			Statement stmt = connection.createStatement(); 
			ResultSet rs = stmt.executeQuery("SELECT id, id_osoby FROM opiekun "); 
			while (rs.next()) {
				  paczka1.listaint.add(rs.getInt("id")); 
				  listaID.add(rs.getInt("id_osoby"));
				}
				rs.close();
				stmt.close();
		}catch (Exception e) {
            System.err.println("blad paczkaPracownicy(1)" + e.getMessage());
        }
		//pobranie imienia i nazwiska:
		for (int i = 0 ; i < listaID.size(); i ++)
		{
			try {
				Statement stmt = connection.createStatement(); 
				ResultSet rs = stmt.executeQuery("SELECT imie, nazwisko FROM osoba  WHERE id='" +  listaID.get(i) + "'"); 
				while (rs.next()) {
						paczka1.lista1.add(rs.getString("imie")); 
						paczka1.lista2.add(rs.getString("nazwisko")); 
					}
					rs.close();
					stmt.close();
			}catch (Exception e) {
	            System.err.println("blad paczkaPracownicy(2)" + e.getMessage());
	        }
		}
		return paczka1;
	}

	public Paczka paczkaGrupy() {
		Paczka paczka1 = new Paczka();
		try {
			Statement stmt = connection.createStatement(); 
			ResultSet rs = stmt.executeQuery("SELECT id, nazwa FROM grupa "); 
			while (rs.next()) {
				  paczka1.listaint.add(rs.getInt("id")); 
				  paczka1.lista1.add(rs.getString("nazwa"));	  
				}
				rs.close();
				stmt.close();
		}catch (Exception e) {
            System.err.println("blad paczkaGrupy(1)" + e.getMessage());
        }
		return paczka1;
	}
	
	public Paczka paczkaDzieciGrupa(int IDZadane) {
		
		Paczka paczka1 = new Paczka();
		//wybranie id dzieci z grupy o zadanym id
		try {
			Statement stmt = connection.createStatement(); 
			ResultSet rs = stmt.executeQuery("SELECT id_osoby FROM ref_grupa_dziecko WHERE id_grupy='" +  IDZadane + "'"); 
			while (rs.next()) {
				  paczka1.listaint.add(rs.getInt("id_osoby"));   
				}
				rs.close();
				stmt.close();
		}catch (Exception e) {
            System.err.println("blad paczkaDzieciGrupa(1)" + e.getMessage());
        }
		//teraz imiona i nazwiska:
		for (int i = 0; i < paczka1.listaint.size(); i++) {
			try {
				Statement stmt = connection.createStatement(); 
				ResultSet rs = stmt.executeQuery("SELECT imie, nazwisko FROM osoba WHERE id='" +  paczka1.listaint.get(i) + "'"); 
				while (rs.next()) {
						paczka1.lista1.add(rs.getString("imie")); 
						paczka1.lista2.add(rs.getString("nazwisko"));   
					}
					rs.close();
					stmt.close();
			}catch (Exception e) {
	            System.err.println("blad paczkaDzieciGrupa(2)" + e.getMessage());
	        }
		}
		//pozostal stan zdrowia:
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		List<Integer> listaID = new ArrayList<Integer>();
		try {
			Statement stmt = connection.createStatement(); 
			ResultSet rs = stmt.executeQuery("SELECT id_osoby FROM choroba_dziecka where stop_data>'" +  year + "/" +  month + "/" +  day + "'"); 
			while (rs.next()) {
					listaID.add(rs.getInt("id_osoby"));
				}
				rs.close();
				stmt.close();
		}catch (Exception e) {
            System.err.println("blad paczkaDzieciGrupa(3)" + e.getMessage());
        }
		String dodano;
		for (int i = 0; i < paczka1.listaint.size(); i++)
		{
			dodano = "false";
			for (int j = 0 ; j < listaID.size(); j++) {
				if (paczka1.listaint.get(i) == listaID.get(j)) {
					dodano = "true";
				}
			}
			paczka1.lista3.add(dodano);
		}	
		return paczka1;
	}
	
	public Paczka paczkaPosilki() {
		Paczka paczka1 = new Paczka();
		try {
			Statement stmt = connection.createStatement(); 
			ResultSet rs = stmt.executeQuery("SELECT NAZWA FROM POSILEK WHERE TYP='staly'"); 
			while (rs.next()) {
				  paczka1.lista1.add(rs.getString("NAZWA")); 
				}
				rs.close();
				stmt.close();
		}catch (Exception e) {
            System.err.println("blad paczkaPosilki(1)" + e.getMessage());
        }
		
		try {
			Statement stmt = connection.createStatement(); 
			ResultSet rs = stmt.executeQuery("SELECT NAZWA FROM POSILEK WHERE TYP='butelka'"); 
			while (rs.next()) {
				  paczka1.lista2.add(rs.getString("NAZWA")); 
				}
				rs.close();
				stmt.close();
		}catch (Exception e) {
            System.err.println("blad paczkaPosilki(2)" + e.getMessage());
        }	
		
		return paczka1;
	}
	
	public boolean dodajWpisPosilki(Paczka paczkaOdebrana) {
		String nic = "";
		String data ="01-01-11";
		try {
			for (int i = 0; i < paczkaOdebrana.listaint.size(); i++)
			{
				Statement stmt = connection.createStatement();
				if (paczkaOdebrana.lista1.get(0).equals("staly")) {
					stmt.execute("INSERT INTO posilkispozyte " +
							"(IDDziecka, IDOpiekuna, typ, rodzaj, ilosc, data, poczatek, koniec, notatki, odmowa, reka) " +
							"VALUES ('"+paczkaOdebrana.listaint.get(i)+"',' "+paczkaOdebrana.UserID+"',' "+paczkaOdebrana.lista1.get(0)+
							"','"+paczkaOdebrana.lista1.get(1)+"','"+paczkaOdebrana.lista1.get(2)+ "','" + data +
							"','"+paczkaOdebrana.lista1.get(3)+"','"+paczkaOdebrana.lista1.get(4)+ 
							"','"+paczkaOdebrana.lista1.get(5)+"','"+paczkaOdebrana.lista1.get(6)+ 
							"','"+nic+"' );"); 
				} else if (paczkaOdebrana.lista1.get(0).equals("butelka")) {
					stmt.execute("INSERT INTO posilkispozyte " +
							"(IDDziecka, IDOpiekuna, typ, rodzaj, ilosc, data, poczatek, koniec, notatki, odmowa, reka) " +
							"VALUES ('"+paczkaOdebrana.listaint.get(i)+"',' "+paczkaOdebrana.UserID+"','"+paczkaOdebrana.lista1.get(0)+
							"','"+paczkaOdebrana.lista1.get(1)+"','"+paczkaOdebrana.lista1.get(2)+ "','" + data +
							"','"+paczkaOdebrana.lista1.get(3)+"','"+paczkaOdebrana.lista1.get(4)+ 
							"','"+paczkaOdebrana.lista1.get(5)+"','"+nic+ 
							"','"+paczkaOdebrana.lista1.get(7)+"' );"); 
				} else {
					stmt.execute("INSERT INTO posilkispozyte " +
							"(IDDziecka, IDOpiekuna, typ, rodzaj, ilosc, data, poczatek, koniec, notatki, odmowa, reka) " +
							"VALUES ('"+paczkaOdebrana.listaint.get(i)+"',' "+paczkaOdebrana.UserID+"',' "+paczkaOdebrana.lista1.get(0)+
							"','"+paczkaOdebrana.lista1.get(1)+"','"+nic+ "','" + data +
							"','"+paczkaOdebrana.lista1.get(3)+"','"+paczkaOdebrana.lista1.get(4)+ 
							"','"+paczkaOdebrana.lista1.get(5)+"','"+nic+ 
							"','"+nic+"' );"); 
				}
				stmt.close();
			}
			return true;
		}catch (Exception e) {
            System.err.println("blad dodajWpisPosilki()" + e.getMessage());
            return false;
        }
		
	}
	
	public boolean dodajNowyPosilek(Paczka paczkaOdebrana) {
		try {
			Statement stmt = connection.createStatement();
			stmt.execute("INSERT INTO rodzajeposilkow (Typ, PodTyp, Nazwa, Opis) " +
					"VALUES ('"+paczkaOdebrana.lista1.get(0)+"',' "+paczkaOdebrana.lista1.get(1)+
					"','"+paczkaOdebrana.lista1.get(2)+"','"+paczkaOdebrana.lista1.get(3)+"' );"); 
			stmt.close();
		}catch (Exception e) {
            System.err.println("blad dodajNowyPosilek()" + e.getMessage());
            return false;
        }
		return true;
	}
	
	public Paczka nazwyTabel () {
		Paczka paczka1 = new Paczka();
		try {
            Statement stmt = connection.createStatement(); 
            ResultSet rs = stmt.executeQuery("select * from information_schema.tables where table_schema = 'zlobek'"); 	
            while (rs.next()) {
            	paczka1.lista1.add(rs.getString("TABLE_NAME"));
            }               
        } 
            catch (SQLException e) {
            e.printStackTrace();
        }
		return paczka1;
	}
	
	public Paczka strukturaTabeli (String nazwa) {
		Paczka paczka1 = new Paczka();
		try {
         
			DatabaseMetaData metaData = connection.getMetaData();
            String   catalog           = null;
            String   schemaPattern     = null;
            String   tableNamePattern  = nazwa;
            String   columnNamePattern = null;
            ResultSet result = metaData.getColumns(
                catalog, schemaPattern,  tableNamePattern, columnNamePattern);

            while(result.next()){
                paczka1.lista1.add(result.getString(4));//nazwa kolumny
                paczka1.lista2.add(result.getString(6));//rodzaj kolumny
            }
            
            
        } 
            catch (SQLException e) {
            e.printStackTrace();
        }
		
		return paczka1;
	}
	

}
