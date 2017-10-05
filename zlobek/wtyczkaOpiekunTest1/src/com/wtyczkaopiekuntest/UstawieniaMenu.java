package com.wtyczkaopiekuntest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class UstawieniaMenu extends MatkaAktywnosci{

	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ustawieniamenu);
        
        Silnik.zamknijMenuGlowne();
        ImageButton przyciskWyloguj = (ImageButton) findViewById(R.id.przyciskWylogujUstawieniaMenu);
        ImageButton przyciskWstecz = (ImageButton) findViewById(R.id.przyciskWsteczUstawieniaMenu);
        this.ustawPrzezroczystoscPrzyciskow(przyciskWyloguj, przyciskWstecz);
	    TextView zalogowany = (TextView) findViewById(R.id.zalogowanyUserUstawieniaMenu);
	    this.odbierzKtoZalogowany (zalogowany);   
        
        przyciskWyloguj.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){     
        		 new Handler().postDelayed(new Thread() {
     		    	@Override
     		    	public void run() {
 	        			Intent menuLogowania = new Intent(UstawieniaMenu.this, MenuLogowania.class);
 	        			UstawieniaMenu.this.startActivity(menuLogowania);
 	        			UstawieniaMenu.this.finish();
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
 	        			Intent menuGlowne = new Intent(UstawieniaMenu.this, MenuGlowne.class);
 	        			UstawieniaMenu.this.startActivity(menuGlowne);
 	        			UstawieniaMenu.this.finish();
     		    	}
     		    }, Silnik.opoznienieWatku);
        	}
        });
        
        
 }//onCreate
	
}