package com.wtyczkaopiekuntest;


import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.content.Intent;
import android.content.Context;
import android.app.Activity;

public class Silnik extends Activity{

	public static final int opoznienieEkranuPowitalnego = 1000;
	public static final int MENU_BUTTON_ALPHA = 0;
	public static final boolean HAPTIC_BUTTON_FEEDBACK = true;
	public static int opoznienieWatku = 1;
	public static int opoznienieWysylania = 10;
	public static int opoznienieOdbierania = 1000;
	
    public static PolaczenieSerwerem polaczenie = new PolaczenieSerwerem();
    public static String zakladkaNazwa = "";
    public static int zakladka = 0;
    public static Boolean polaczonoMaster = false, wczytanoGrupy = false;
	public static ArrayAdapter<String> dataAdapter;
	public static PaczkaPoczatkowaOpiekun paczka = new PaczkaPoczatkowaOpiekun();
	public static List<String> listaGrupTest = new ArrayList<String>();
	public static List<Integer> wybraneDzieciaki = new ArrayList<Integer>();
    
	public static Context context;
	
	public static void wyloguj() {
		polaczonoMaster = false;
		wczytanoGrupy = false;
		paczka.ustawMojeHaslo("");
		paczka.ustawMojID(0);
		paczka.ustawMojLogin("");
		paczka.listaDzieciClear();
		paczka.listaGrupClear();
		paczka.listaIDDzieciClear();
		listaGrupTest.clear();
		zakladkaNazwa = "";
		zakladka = 0;
		polaczenie.disconnet();
	}
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logowanie);
	}
	
	public static Boolean zamknijMenuLogowania() {
		try{
			Intent menuLog = new Intent(context, MenuLogowania.class);
			context.stopService(menuLog);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	public static Boolean zamknijMenuGlowne() {
		try{
			Intent menuGl = new Intent(context, MenuGlowne.class);
			context.stopService(menuGl);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	
	public static boolean onExit(View v) {
		try {
			zamknijMenuLogowania();
			return true;
		} catch (Exception e){
			return false;
		}
	}
	
}
