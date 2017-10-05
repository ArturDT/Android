package com.wtyczkaopiekuntest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class Zdrowie extends MatkaAktywnosci{

	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zdrowie);
        
        Silnik.zamknijMenuGlowne();
        ImageButton przyciskWyloguj = (ImageButton) findViewById(R.id.przyciskWylogujZdrowie);
        ImageButton przyciskWstecz = (ImageButton) findViewById(R.id.przyciskWsteczZdrowie);
        this.ustawPrzezroczystoscPrzyciskow(przyciskWyloguj, przyciskWstecz);
	    TextView zalogowany = (TextView) findViewById(R.id.zalogowanyUserZdrowie);
	    this.odbierzKtoZalogowany (zalogowany);   
        
        przyciskWyloguj.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){     
        		 new Handler().postDelayed(new Thread() {
     		    	@Override
     		    	public void run() {
     		    		Silnik.wyloguj();
 	        			Intent menuLogowania = new Intent(Zdrowie.this, MenuLogowania.class);
 	        			Zdrowie.this.startActivity(menuLogowania);
 	        			Zdrowie.this.finish();
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
 	        			Intent menuWyboru = new Intent(Zdrowie.this, WyborDzieci.class);
 	        			Zdrowie.this.startActivity(menuWyboru);
 	        			Zdrowie.this.finish();
     		    	}
     		    }, Silnik.opoznienieWatku);
        	}
        });
        
        
 }//onCreate
	
}
