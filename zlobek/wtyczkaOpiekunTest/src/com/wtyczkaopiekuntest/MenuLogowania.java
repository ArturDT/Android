package com.wtyczkaopiekuntest;

import android.os.Bundle;
import android.widget.ImageButton;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.os.Handler;
import com.google.gson.Gson;

public class MenuLogowania extends MatkaAktywnosci {
	
	
	protected void wyslijDaneLogowania() {
		    Silnik.polaczenie.wyczyscListe();
		    Gson gson =  new Gson();
		    Paczka paczka1 = new Paczka();
	        EditText loginField = (EditText) findViewById(R.id.editText2);
	        EditText passField = (EditText) findViewById(R.id.editText1);
	        paczka1.UserLogin = loginField.getText().toString();
	        paczka1.UserPassword = passField.getText().toString();
	        paczka1.polecenie = "login";
	        String json = gson.toJson(paczka1);
	        Silnik.polaczenie.send(json);
	        if (Silnik.polaczonoMaster == true)
	        	loginField.setText("connected!");
	        else loginField.setText("not connected!");
	}
	
	protected void odbierzPaczke() {
			Gson gson =  new Gson();
		    String odp = Silnik.polaczenie.readOdp();
		    Silnik.paczka = gson.fromJson(odp, PaczkaPoczatkowaOpiekun.class);
		    for (int i = 0; i < Silnik.paczka.listaGrupSize(); i++)
		    	Silnik.listaGrupTest.add(Silnik.paczka.listaGrupGetElement(i));
	}
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logowanie);        
        ImageButton przyciskLogowania = (ImageButton) findViewById(R.id.przyciskZaloguj);
        ImageButton przyciskWyjscia = (ImageButton) findViewById(R.id.przyciskWyjscie);
        ImageButton przyciskUstawien = (ImageButton) findViewById(R.id.przyciskUstawienia);
        this.ustawPrzezroczystoscPrzyciskow(przyciskLogowania, przyciskWyjscia, przyciskUstawien);  
        if (Silnik.polaczonoMaster == false) {
        	Silnik.polaczenie.connect();
        }
              
        przyciskLogowania.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){ 
	        		 wyslijDaneLogowania();
	        		 new Handler().postDelayed(new Thread() {
	     		    	@Override
	     		    	public void run() {
	     	        		TextView zleDane = (TextView) findViewById(R.id.textView2);
	     	        		String logPass = "";
	     	        		logPass = Silnik.polaczenie.readOdp();
	     	        		if (logPass.equals("true") || logPass == "true") {
	     	        			zleDane.setVisibility(View.INVISIBLE);
	     	        			odbierzPaczke();
	     	        			Intent ekranGlowny = new Intent(MenuLogowania.this, MenuGlowne.class);
	     	        			MenuLogowania.this.startActivity(ekranGlowny);
	     	        			Silnik.zamknijMenuLogowania();
	     	        			MenuLogowania.this.finish();
	     	        		} else {
	     	        			zleDane.setVisibility(View.VISIBLE);
	     	        			if (Silnik.polaczonoMaster == false) {
	     	        				Silnik.polaczenie.connect();
	     	                   }
	     	        		}
	     		    	}
	     		    }, Silnik.opoznienieOdbierania);
        		}
        });
        
        przyciskUstawien.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		//akcja po kliknieciu ikony ustawienia
        	}
        });
        
        przyciskWyjscia.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		Silnik.polaczenie.disconnet();
        		boolean clean = false;
        		clean = Silnik.onExit(v);
        		if (clean){
        			int pid = android.os.Process.myPid();
        			android.os.Process.killProcess(pid);
        		}
        	}
        });
    }
}