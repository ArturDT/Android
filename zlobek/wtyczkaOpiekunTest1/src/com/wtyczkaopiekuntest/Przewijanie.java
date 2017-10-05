package com.wtyczkaopiekuntest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class Przewijanie extends MatkaAktywnosci {
	
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.przewijanie);

	        Silnik.zamknijMenuGlowne();
	        ImageButton przyciskWyloguj = (ImageButton) findViewById(R.id.przyciskWylogujPrzewijanie);
	        ImageButton przyciskWstecz = (ImageButton) findViewById(R.id.przyciskWsteczPrzewijanie);
	        this.ustawPrzezroczystoscPrzyciskow(przyciskWyloguj, przyciskWstecz);
		    TextView zalogowany = (TextView) findViewById(R.id.zalogowanyUserPrzewijanie);
		    this.odbierzKtoZalogowany (zalogowany);   
	        
	        przyciskWyloguj.setOnClickListener(new OnClickListener(){
	        	@Override
	        	public void onClick(View v){     
	        		 new Handler().postDelayed(new Thread() {
	     		    	@Override
	     		    	public void run() {
	     		    		Silnik.wyloguj();
	 	        			Intent menuLogowania = new Intent(Przewijanie.this, MenuLogowania.class);
	 	        			Przewijanie.this.startActivity(menuLogowania);
	 	        			Przewijanie.this.finish();
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
	 	        			Intent menuWyboru = new Intent(Przewijanie.this,WyborDzieci.class);
	 	        			Przewijanie.this.startActivity(menuWyboru);
	 	        			Przewijanie.this.finish();
	     		    	}
	     		    }, Silnik.opoznienieWatku);
	        	}
	        });
	        
	        
	 }//onCreate

}
