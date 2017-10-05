package com.wtyczkaopiekuntest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class Nastroj extends MatkaAktywnosci{

	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nastroj);
        
        Silnik.zamknijMenuGlowne();
        ImageButton przyciskWyloguj = (ImageButton) findViewById(R.id.przyciskWylogujNastroj);
        ImageButton przyciskWstecz = (ImageButton) findViewById(R.id.przyciskWsteczNastroj);
        this.ustawPrzezroczystoscPrzyciskow(przyciskWyloguj, przyciskWstecz);
	    TextView zalogowany = (TextView) findViewById(R.id.zalogowanyUserNastroj);
	    this.odbierzKtoZalogowany (zalogowany);   
        
        przyciskWyloguj.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){     
        		 new Handler().postDelayed(new Thread() {
     		    	@Override
     		    	public void run() {
     		    		Silnik.wyloguj();
 	        			Intent menuLogowania = new Intent(Nastroj.this, MenuLogowania.class);
 	        			Nastroj.this.startActivity(menuLogowania);
 	        			Nastroj.this.finish();
     		    	}
     		    }, Silnik.opoznienieWatku);
        	}
        });
        
        przyciskWstecz.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){     
        		 new Handler().postDelayed(new Thread() {
     		    	@Override
     		    	public void run() {
 	        			Intent menuWyboru = new Intent(Nastroj.this, WyborDzieci.class);
 	        			Nastroj.this.startActivity(menuWyboru);
 	        			Nastroj.this.finish();
     		    	}
     		    }, Silnik.opoznienieWatku);
        	}
        });
        
        
 }//onCreate
	
}
