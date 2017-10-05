package com.wtyczkaopiekuntest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.CheckBox;
import com.google.gson.Gson;


public class Posilki  extends MatkaAktywnosci {
	
	Paczka paczka1 = new Paczka();
	int rodzaj = 0;
	
	private void odbierzPaczkePosilki() {
		new Handler().postDelayed(new Thread() {
		    	@Override
		    	public void run() {
	    				Gson gson = new Gson();
	    				String odp = Silnik.polaczenie.readOdp();
	    				paczka1 = gson.fromJson(odp, Paczka.class);
		    	}
		    }, Silnik.opoznienieOdbierania);

	}

	private void wyslijPaczkePosilki() {
		Paczka paczka1 = new Paczka();
		Gson gson = new Gson();
		paczka1 = this.ladujLogin(paczka1);
		paczka1.polecenie = "paczkaPosilki";
		String json = gson.toJson(paczka1);
		Silnik.polaczenie.send(json);
	}
	
	private void ustawRodzaj (int i, Spinner spinner) {
		rodzaj = i;
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, paczka1.lista1);
		if (i == 1)
			dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, paczka1.lista1);
		else if (i == 2)
			dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, paczka1.lista2);
		else if (i ==3)
			dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, paczka1.lista3);
		spinner.setAdapter(dataAdapter);
	}
	
    @Override
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.posilki);
	        wyslijPaczkePosilki();
	        ImageButton przyciskWyloguj = (ImageButton) findViewById(R.id.przyciskWylogujPosilki);
	        ImageButton przyciskWstecz = (ImageButton) findViewById(R.id.przyciskWsteczPosilki);
	        final ImageButton przyciskPosilkiStale = (ImageButton) findViewById(R.id.przyciskPosilkiStalePosilki);
	        final ImageButton przyciskButelka = (ImageButton) findViewById(R.id.przyciskButelkaPosilki);
	        final ImageButton przyciskPiers = (ImageButton) findViewById(R.id.przyciskPiersPosilki);
	        final ImageButton przyciskOk = (ImageButton) findViewById(R.id.przyciskOkPosilki);
	        final ImageButton przyciskNowy = (ImageButton) findViewById(R.id.przyciskNowyPosilki);
	        this.ustawPrzezroczystoscPrzyciskow(przyciskPosilkiStale, przyciskButelka, przyciskPiers);
	        this.ustawPrzezroczystoscPrzyciskow(przyciskWyloguj, przyciskWstecz, przyciskOk);
	        this.ustawPrzezroczystoscPrzycisku(przyciskNowy);
		    TextView zalogowany = (TextView) findViewById(R.id.zalogowanyUserPosilki);
			this.odbierzKtoZalogowany (zalogowany);    
			
			final TextView textViewRodzajPosilku = (TextView) findViewById(R.id.textViewRodzajPosilku);
			final TextView textViewPoczatekPosilki = (TextView) findViewById(R.id.textViewPoczatekPosilki);
			final TextView textViewKoniecPosilki = (TextView) findViewById(R.id.textViewKoniecPosilki);
			final TextView textViewNotatkiPosilki = (TextView) findViewById(R.id.textViewNotatkiPosilki);
			
			final TextView textViewIlosc = (TextView) findViewById(R.id.textViewIloscPosilki);
			final TextView textViewMl = (TextView) findViewById(R.id.textViewMlPosilki);
			final EditText editTextIlosc = (EditText)  findViewById(R.id.editTextIloscPosilki);
			final EditText editTextPoczatek = (EditText) findViewById(R.id.editTextPoczatekPosilki);	
			final EditText editTextKoniec = (EditText) findViewById(R.id.editTextKoniecPosilki);
			final EditText editTextNotatki = (EditText) findViewById(R.id.editTextNotatkiPosilki);
			final Spinner spinner1Posilki = (Spinner) findViewById(R.id.spinner1Posilki);
			final Spinner spinnerReka = (Spinner) findViewById(R.id.spinnerRekaPosilki);
			final CheckBox checkBoxOdmowa = (CheckBox) findViewById(R.id.checkBoxOdmowaPosilki);
		
			textViewRodzajPosilku.setVisibility(View.INVISIBLE);
			textViewPoczatekPosilki.setVisibility(View.INVISIBLE);
			textViewKoniecPosilki.setVisibility(View.INVISIBLE);
			textViewNotatkiPosilki.setVisibility(View.INVISIBLE);
			textViewIlosc.setVisibility(View.INVISIBLE);
			textViewMl.setVisibility(View.INVISIBLE);
			editTextIlosc.setVisibility(View.INVISIBLE);
			editTextPoczatek.setVisibility(View.INVISIBLE);
			editTextKoniec.setVisibility(View.INVISIBLE);
			editTextNotatki.setVisibility(View.INVISIBLE);
			spinner1Posilki.setVisibility(View.INVISIBLE);
			spinnerReka.setVisibility(View.INVISIBLE);
			checkBoxOdmowa.setVisibility(View.INVISIBLE);
			przyciskOk.setVisibility(View.INVISIBLE);
			przyciskNowy.setVisibility(View.INVISIBLE);
			odbierzPaczkePosilki();
			
			ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
			        R.array.rece_array, android.R.layout.simple_spinner_item);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinnerReka.setAdapter(adapter);

			
			przyciskPosilkiStale.setOnClickListener(new OnClickListener(){
	        	@Override
	        	public void onClick(View v){     
	        		przyciskPosilkiStale.setImageResource(R.drawable.posilkistaleclick);
	        		przyciskButelka.setImageResource(R.drawable.selektorbutelka);
	        		przyciskPiers.setImageResource(R.drawable.selektorpiers);
	        		
	        		textViewRodzajPosilku.setVisibility(View.VISIBLE);
	    			textViewPoczatekPosilki.setVisibility(View.VISIBLE);
	    			textViewKoniecPosilki.setVisibility(View.VISIBLE);
	    			textViewNotatkiPosilki.setVisibility(View.VISIBLE);
	    			textViewIlosc.setVisibility(View.VISIBLE);
	    			textViewMl.setVisibility(View.VISIBLE);
	    			editTextIlosc.setVisibility(View.VISIBLE);
	    			editTextPoczatek.setVisibility(View.VISIBLE);
	    			editTextKoniec.setVisibility(View.VISIBLE);
	    			editTextNotatki.setVisibility(View.VISIBLE);
	    			spinner1Posilki.setVisibility(View.VISIBLE);
	    			spinnerReka.setVisibility(View.VISIBLE);
	    			checkBoxOdmowa.setVisibility(View.VISIBLE);
	    			przyciskOk.setVisibility(View.VISIBLE);
	    			przyciskNowy.setVisibility(View.VISIBLE);
	    			textViewMl.setText("g");
	    			ustawRodzaj(1, spinner1Posilki);
	     		    		
	        	}
	        });
			
			przyciskButelka.setOnClickListener(new OnClickListener(){
	        	@Override
	        	public void onClick(View v){     
	        		przyciskPosilkiStale.setImageResource(R.drawable.selektorposilkistale);
	        		przyciskButelka.setImageResource(R.drawable.butelkaclick);
	        		przyciskPiers.setImageResource(R.drawable.piers);
	        		
	        		textViewRodzajPosilku.setVisibility(View.VISIBLE);
	    			textViewPoczatekPosilki.setVisibility(View.VISIBLE);
	    			textViewKoniecPosilki.setVisibility(View.VISIBLE);
	    			textViewNotatkiPosilki.setVisibility(View.VISIBLE);
	    			textViewIlosc.setVisibility(View.VISIBLE);
	    			textViewMl.setVisibility(View.VISIBLE);
	    			editTextIlosc.setVisibility(View.VISIBLE);
	    			editTextPoczatek.setVisibility(View.VISIBLE);
	    			editTextKoniec.setVisibility(View.VISIBLE);
	    			editTextNotatki.setVisibility(View.VISIBLE);
	    			spinner1Posilki.setVisibility(View.VISIBLE);
	    			spinnerReka.setVisibility(View.VISIBLE);
	    			checkBoxOdmowa.setVisibility(View.VISIBLE);
	    			przyciskOk.setVisibility(View.VISIBLE);
	    			przyciskNowy.setVisibility(View.VISIBLE);
	    			
	    			textViewMl.setText("ml");
	    			ustawRodzaj(2, spinner1Posilki);
	        	}
	        });
			
			przyciskPiers.setOnClickListener(new OnClickListener(){
	        	@Override
	        	public void onClick(View v){     
	        		przyciskPosilkiStale.setImageResource(R.drawable.selektorposilkistale);
	        		przyciskButelka.setImageResource(R.drawable.selektorbutelka);
	        		przyciskPiers.setImageResource(R.drawable.piersclick);
	        		
	        		textViewRodzajPosilku.setVisibility(View.INVISIBLE);
	    			textViewPoczatekPosilki.setVisibility(View.VISIBLE);
	    			textViewKoniecPosilki.setVisibility(View.VISIBLE);
	    			textViewNotatkiPosilki.setVisibility(View.VISIBLE);
	    			textViewIlosc.setVisibility(View.INVISIBLE);
	    			textViewMl.setVisibility(View.INVISIBLE);
	    			editTextIlosc.setVisibility(View.INVISIBLE);
	    			editTextPoczatek.setVisibility(View.VISIBLE);
	    			editTextKoniec.setVisibility(View.VISIBLE);
	    			editTextNotatki.setVisibility(View.VISIBLE);
	    			spinner1Posilki.setVisibility(View.VISIBLE);
	    			spinnerReka.setVisibility(View.INVISIBLE);
	    			checkBoxOdmowa.setVisibility(View.INVISIBLE);
	    			przyciskOk.setVisibility(View.VISIBLE);
	    			przyciskNowy.setVisibility(View.INVISIBLE);
	    			ustawRodzaj(3, spinner1Posilki);
	        	}
	        });
			
			przyciskOk.setOnClickListener(new OnClickListener(){
	        	@Override
	        	public void onClick(View v){     
	     		    if (rodzaj != 0) {
	     		    	Paczka paczka1 = new Paczka();
	     				Gson gson = new Gson();
	     				paczka1 = ladujLogin(paczka1);
	     				paczka1.polecenie = "paczkaDodajWpisPosilki";
	     				if (rodzaj == 1) paczka1.lista1.add("staly");
	     				else if (rodzaj == 2) paczka1.lista1.add("butelka");
	     				else paczka1.lista1.add("piers");
	     				paczka1.lista1.add(spinner1Posilki.getSelectedItem().toString());
	     				paczka1.lista1.add(editTextIlosc.getText().toString());
	     				paczka1.lista1.add(editTextPoczatek.getText().toString());
	     				paczka1.lista1.add(editTextKoniec.getText().toString());
	     				paczka1.lista1.add(editTextNotatki.getText().toString());
	     				if (checkBoxOdmowa.isChecked()) paczka1.lista1.add("true");
	     				else paczka1.lista1.add("false");
	     				paczka1.lista1.add(spinnerReka.getSelectedItem().toString());
	     				for (int i = 0; i < Silnik.wybraneDzieciaki.size(); i++) {
	     					paczka1.listaint.add(Silnik.wybraneDzieciaki.get(i));
	     				}
	     				String json = gson.toJson(paczka1);
	     				Silnik.polaczenie.send(json);	
	     		    	Intent menuGlowne = new Intent(Posilki.this, MenuGlowne.class);
	     		    	Posilki.this.startActivity(menuGlowne);
	     		    	Posilki.this.finish();
	     		    }
	        	}
	        });
			
			przyciskNowy.setOnClickListener(new OnClickListener(){
	        	@Override
	        	public void onClick(View v){     
	        		Intent menuNowy = new Intent(Posilki.this, NowyPosilek.class);
	 	        	Posilki.this.startActivity(menuNowy);
	 	        	Posilki.this.finish();
	        	}
	        });
			
	        przyciskWyloguj.setOnClickListener(new OnClickListener(){
	        	@Override
	        	public void onClick(View v){          		 
	     		    Silnik.wyloguj();
	 	        	Intent menuLogowania = new Intent(Posilki.this, MenuLogowania.class);
	 	        	Posilki.this.startActivity(menuLogowania);
	 	        	Posilki.this.finish();
	        	}
	        });
	        
	        przyciskWstecz.setOnClickListener(new OnClickListener(){
	        	@Override
	        	public void onClick(View v){     
	 	        	Intent menuWyboru = new Intent(Posilki.this, WyborDzieci.class);
	 	        	Posilki.this.startActivity(menuWyboru);
	 	        	Posilki.this.finish();
	        	}
	        });
	        
	        
	 }//onCreate
	 	
}
