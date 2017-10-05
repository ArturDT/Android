package com.wtyczkaopiekuntest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

public class NowyPosilek extends MatkaAktywnosci {
	
	Paczka paczka1 = new Paczka();
	
    @Override
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.nowyposilek);
	        ImageButton przyciskWyloguj = (ImageButton) findViewById(R.id.przyciskWylogujNowyPosilek);
	        ImageButton przyciskWstecz = (ImageButton) findViewById(R.id.przyciskWsteczNowyPosilek);
;
	        final ImageButton przyciskOk = (ImageButton) findViewById(R.id.przyciskOkNowyPosilek);
		    TextView zalogowany = (TextView) findViewById(R.id.zalogowanyUserNowyPosilek);
			this.odbierzKtoZalogowany (zalogowany);   
			this.ustawPrzezroczystoscPrzyciskow(przyciskWyloguj, przyciskOk, przyciskWstecz);
			
			final EditText editTextTyp = (EditText)  findViewById(R.id.editTextTypNowyPosilek);
			final EditText editTextNazwa = (EditText) findViewById(R.id.editTextNazwaNowyPosilek);	
			final EditText editTextOpis= (EditText) findViewById(R.id.editTextOpisNowyPosilek);
			final Spinner spinner = (Spinner) findViewById(R.id.spinner1NowyPosilek);
		
			ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
			        R.array.rodzaj_array, android.R.layout.simple_spinner_item);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinner.setAdapter(adapter);
			
			przyciskOk.setOnClickListener(new OnClickListener(){
	        	@Override
	        	public void onClick(View v){     
	     		    	Paczka paczka1 = new Paczka();
	     				Gson gson = new Gson();
	     				paczka1 = ladujLogin(paczka1);
	     				paczka1.polecenie = "paczkaDodajNowyPosilek";
	     				paczka1.lista1.add(spinner.getSelectedItem().toString());
	     				paczka1.lista1.add(editTextTyp.getText().toString());
	     				paczka1.lista1.add(editTextNazwa.getText().toString());
	     				paczka1.lista1.add(editTextOpis.getText().toString());
	     				String json = gson.toJson(paczka1);
	     				Silnik.polaczenie.send(json);	
	     		    	Intent menuPosilki = new Intent(NowyPosilek.this, Posilki.class);
	     		    	NowyPosilek.this.startActivity(menuPosilki);
	     		    	NowyPosilek.this.finish();
	        	}
	        });
			
			
	        przyciskWyloguj.setOnClickListener(new OnClickListener(){
	        	@Override
	        	public void onClick(View v){          		 
	     		    Silnik.wyloguj();
	 	        	Intent menuLogowania = new Intent(NowyPosilek.this, MenuLogowania.class);
	 	        	NowyPosilek.this.startActivity(menuLogowania);
	 	        	NowyPosilek.this.finish();
	        	}
	        });
	        
	        przyciskWstecz.setOnClickListener(new OnClickListener(){
	        	@Override
	        	public void onClick(View v){     
	 	        	Intent menuWyboru = new Intent(NowyPosilek.this, WyborDzieci.class);
	 	        	NowyPosilek.this.startActivity(menuWyboru);
	 	        	NowyPosilek.this.finish();
	        	}
	        });
	        
	        
	 }//onCreate
	 	
}
