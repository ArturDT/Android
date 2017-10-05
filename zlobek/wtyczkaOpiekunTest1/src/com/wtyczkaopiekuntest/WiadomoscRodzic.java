package com.wtyczkaopiekuntest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class WiadomoscRodzic extends MatkaAktywnosci{

	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wiadomoscrodzic);
        
        Silnik.zamknijMenuGlowne();
        ImageButton przyciskWyloguj = (ImageButton) findViewById(R.id.przyciskWylogujWiadR);
        ImageButton przyciskWstecz = (ImageButton) findViewById(R.id.przyciskWsteczWiadR);
        this.ustawPrzezroczystoscPrzyciskow(przyciskWyloguj, przyciskWstecz);
	    TextView zalogowany = (TextView) findViewById(R.id.zalogowanyUserWiadR);
	    this.odbierzKtoZalogowany (zalogowany);   
        
        przyciskWyloguj.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){     
        		 new Handler().postDelayed(new Thread() {
     		    	@Override
     		    	public void run() {
     		    		Silnik.wyloguj();
 	        			Intent menuLogowania = new Intent(WiadomoscRodzic.this, MenuLogowania.class);
 	        			WiadomoscRodzic.this.startActivity(menuLogowania);
 	        			WiadomoscRodzic.this.finish();
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
 	        			Intent menuWyboru = new Intent(WiadomoscRodzic.this, WyborDzieci.class);
 	        			WiadomoscRodzic.this.startActivity(menuWyboru);
 	        			WiadomoscRodzic.this.finish();
     		    	}
     		    }, Silnik.opoznienieWatku);
        	}
        });
        
        
 }//onCreate
	
}
