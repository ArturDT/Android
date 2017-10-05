package com.wtyczkaopiekuntest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;


public class MenuGlowne extends MatkaAktywnosci {
	
	
    private void wczytajGrupy (final MenuGlowne pos, final Spinner spinnerGrupy) {  
    	Silnik.paczka.listaGrup.add("Michalina");
    	Silnik.dataAdapter = new ArrayAdapter<String>(pos,android.R.layout.simple_spinner_item, Silnik.paczka.listaGrup);
		spinnerGrupy.setAdapter(Silnik.dataAdapter);	    	        
    }

    private void przejdzDoWyboruDzieciakow (final int zakladka,final Spinner spinnerGrupy, View v, final String zakladkaNazwa) {
		klikWIkonke(spinnerGrupy);
		Silnik.zakladka = zakladka;
		Silnik.zakladkaNazwa = zakladkaNazwa;
		Intent menuWybor = new Intent(MenuGlowne.this, WyborDzieci.class);
		MenuGlowne.this.startActivity(menuWybor);
		Silnik.zamknijMenuGlowne();
		MenuGlowne.this.finish();
		EditText test = (EditText) findViewById(R.id.editText1);
		test.setTextSize(6.0f);
    }
	
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);
        Silnik.zamknijMenuLogowania();
        
        final Spinner spinnerGrupy = (Spinner) findViewById(R.id.spinnerGrupyPosilki);
        if (Silnik.wczytanoGrupy == false) {
			wczytajGrupy(this, spinnerGrupy);
			Silnik.wczytanoGrupy = true;
        } else {
        	spinnerGrupy.setAdapter(Silnik.dataAdapter);
	    	wczytajGrupy(this, spinnerGrupy);
        }
		
        ImageButton przyciskWyloguj = (ImageButton) findViewById(R.id.przyciskWyloguj);
        this.ustawPrzezroczystoscPrzycisku(przyciskWyloguj);
       	TextView zalogowany = (TextView) findViewById(R.id.zalogowanyUser);
       	this.odbierzKtoZalogowany(zalogowany);
       	
       	
        przyciskWyloguj.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){     
     		    Silnik.wyloguj();
 	        	Intent menuLogowania = new Intent(MenuGlowne.this, MenuLogowania.class);
 	        	MenuGlowne.this.startActivity(menuLogowania);
 	        	Silnik.zamknijMenuGlowne();
 	        	MenuGlowne.this.finish();
        	}
        });
        


        
	}//onCreate
	
}
