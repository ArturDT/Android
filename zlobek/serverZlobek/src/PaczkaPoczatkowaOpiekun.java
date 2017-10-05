
import java.util.ArrayList;
import java.util.List;

public class PaczkaPoczatkowaOpiekun {
	//informacje u¿ywane przez wtyczke Rodzic:
	
	//tabela grupy:
	public List<String> listaGrup = new ArrayList<String>();
	
	//tabela Dzieciaki - wszystkie dzieciaki nalezace do grup danego opiekuna
	public List<String> listaDzieci = new ArrayList<String>();
	public List<Integer> listaIDDzieci = new ArrayList<Integer>();
	
	//tabela opiekunowie:
		private int mojID;
		private String mojLogin;
		private String mojeHaslo;
		
	////////////////////////////////////////////////////////////////////////////////////////////
	//funkcje grupy
	public void listaGrupAdd(String nazwaGrupy) {
		listaGrup.add(nazwaGrupy);
	}
	public void listaGrupClear() {
		listaGrup.clear();
	}
	public String listaGrupGetElement(int indeks) {
		return listaGrup.get(indeks);
	}
	public int listaGrupSize() {
		return listaGrup.size();
	}
	
	//funkcje opiekunowie
	public void ustawMojeHaslo(String haslo) {
		mojeHaslo = haslo;
	}
	public String wczytajMojeHaslo() {
		return mojeHaslo;
	}
	
	public void ustawMojLogin(String login) {
		mojLogin = login;
	}
	public String wczytajMojLogin() {
		return mojLogin;
	}
	
	public void ustawMojID(int ID) {
		mojID = ID;
	}
	public int wczytajMojID() {
		return mojID;
	}
	
	
	
	//funkcje tabela dzieci
	public void listaDzieciAdd(String nazwaGrupy) {
		listaDzieci.add(nazwaGrupy);
	}
	public void listaDzieciClear() {
		listaDzieci.clear();
	}
	public String listaDzieciGetElement(int indeks) {
		return listaDzieci.get(indeks);
	}
	
	public void listaIDDzieciAdd(int nazwaGrupy) {
		listaIDDzieci.add(nazwaGrupy);
	}
	public void listaIDDzieciClear() {
		listaIDDzieci.clear();
	}
	public int listaIDDzieciGetElement(int indeks) {
		return listaIDDzieci.get(indeks);
	}
}
