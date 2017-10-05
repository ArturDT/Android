package com.wtyczkaopiekuntest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class Sen extends MatkaAktywnosci{

	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sen);
        
        Silnik.zamknijMenuGlowne();
        ImageButton przyciskWyloguj = (ImageButton) findViewById(R.id.przyciskWylogujSen);
        ImageButton przyciskWstecz = (ImageButton) findViewById(R.id.przyciskWsteczSen);
        this.ustawPrzezroczystoscPrzyciskow(przyciskWyloguj, przyciskWstecz);
	    TextView zalogowany = (TextView) findViewById(R.id.zalogowanyUserSen);
	    this.odbierzKtoZalogowany (zalogowany);   
        
        przyciskWyloguj.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){     
        		 new Handler().postDelayed(new Thread() {
     		    	@Override
     		    	public void run() {
     		    		Silnik.wyloguj();
 	        			Intent menuLogowania = new Intent(Sen.this, MenuLogowania.class);
 	        			Sen.this.startActivity(menuLogowania);
 	        			Sen.this.finish();
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
 	        			Intent menuWyboru = new Intent(Sen.this, WyborDzieci.class);
 	        			Sen.this.startActivity(menuWyboru);
 	        			Sen.this.finish();
     		    	}
     		    }, Silnik.opoznienieWatku);
        	}
        });
        
        
 }//onCreate
	
}
