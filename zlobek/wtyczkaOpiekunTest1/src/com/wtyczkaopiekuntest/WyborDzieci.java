package com.wtyczkaopiekuntest;

import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.CheckBox;
import android.widget.TextView;

public class WyborDzieci extends MatkaAktywnosci{
	
	List<CheckBox> checkboxy = new ArrayList<CheckBox>();
	
	private void ladujCheckBox() {
		final CheckBox check0 = (CheckBox) findViewById(R.id.checkBox0Wybor);
		final CheckBox check1 = (CheckBox) findViewById(R.id.checkBox1Wybor);
		final CheckBox check2 = (CheckBox) findViewById(R.id.checkBox2Wybor);
		final CheckBox check3 = (CheckBox) findViewById(R.id.checkBox3Wybor);
		final CheckBox check4 = (CheckBox) findViewById(R.id.checkBox4Wybor);
		final CheckBox check5 = (CheckBox) findViewById(R.id.checkBox5Wybor);
		final CheckBox check6 = (CheckBox) findViewById(R.id.checkBox6Wybor);
		final CheckBox check7 = (CheckBox) findViewById(R.id.checkBox7Wybor);
		final CheckBox check8 = (CheckBox) findViewById(R.id.checkBox8Wybor);
		final CheckBox check9 = (CheckBox) findViewById(R.id.checkBox9Wybor);
		final CheckBox check10 = (CheckBox) findViewById(R.id.checkBox10Wybor);
		final CheckBox check11 = (CheckBox) findViewById(R.id.checkBox11Wybor);
		final CheckBox check12 = (CheckBox) findViewById(R.id.checkBox12Wybor);
		final CheckBox check13 = (CheckBox) findViewById(R.id.checkBox13Wybor);
		final CheckBox check14 = (CheckBox) findViewById(R.id.checkBox14Wybor);
		checkboxy.add(check0);
		checkboxy.add(check1);
		checkboxy.add(check2);
		checkboxy.add(check3);
		checkboxy.add(check4);
		checkboxy.add(check5);
		checkboxy.add(check6);
		checkboxy.add(check7);
		checkboxy.add(check8);
		checkboxy.add(check9);
		checkboxy.add(check10);
		checkboxy.add(check11);
		checkboxy.add(check12);
		checkboxy.add(check13);
		checkboxy.add(check14);
	}
	
	private void ustawRadioText () {
		int ind = 0;
		for (int i = 0; i < Silnik.paczka.listaGrupSize(); i ++) {
			if (Silnik.paczka.listaGrupGetElement(0).equals(Silnik.listaGrupTest.get(i))) {
				ind = i * 15;
			} 
		}
		for (int i = 0; i < 15; i++) {
			checkboxy.get(i).setText(Silnik.paczka.listaDzieci.get(ind + i));
		}
	}
	
	private void wczytajListeGrupy() {
		    ustawRadioText();
	}
	private void sprawdzWybranych() {
		int ind = 0;
		for (int i = 0; i < Silnik.paczka.listaGrupSize(); i ++) {
			if (Silnik.paczka.listaGrupGetElement(0).equals(Silnik.listaGrupTest.get(i))) {
				ind = i * 15;
			} 
		}
		for (int i = 0; i < 15; i++) {
			if (checkboxy.get(i).isChecked())
				Silnik.wybraneDzieciaki.add(Silnik.paczka.listaIDDzieci.get(ind + i));
		}
	}
	
	
    @Override
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.wybordzieciakow);
	        Silnik.zamknijMenuGlowne();
	        ladujCheckBox();
	        Silnik.wybraneDzieciaki.clear();
	        ImageButton przyciskWyloguj = (ImageButton) findViewById(R.id.przyciskWylogujWybor);
	        ImageButton przyciskWstecz = (ImageButton) findViewById(R.id.przyciskWsteczWybor);
	        ImageButton przyciskOk = (ImageButton) findViewById(R.id.przyciskOkWybor);
	        ImageButton przyciskSelectAll = (ImageButton) findViewById(R.id.przyciskZaznaczWybor);
	        ImageButton przyciskDeselectAll = (ImageButton) findViewById(R.id.przyciskOdznaczWybor);
	        this.ustawPrzezroczystoscPrzyciskow(przyciskWyloguj, przyciskWstecz);
		    TextView zalogowany = (TextView) findViewById(R.id.zalogowanyUserWybor);
		    final TextView nieWybrano = (TextView) findViewById(R.id.textViewNieWybranoWybor);
			this.odbierzKtoZalogowany (zalogowany); 
			this.wczytajListeGrupy();
			this.ustawPrzezroczystoscPrzycisku(przyciskOk);
			TextView grupaJaka = (TextView) findViewById(R.id.textViewJakaGrupaWybor);
			TextView zakladka = (TextView) findViewById(R.id.textView1Wybor);
			grupaJaka.setText("Grupa: " + Silnik.paczka.listaGrupGetElement(0));
			zakladka.setText("zak³adka " + Silnik.zakladkaNazwa);
			nieWybrano.setVisibility(View.INVISIBLE);
			
				
			przyciskOk.setOnClickListener(new OnClickListener(){
	        	@Override
	        	public void onClick(View v){  
	        		boolean notClick = true;
	        		for (int i = 0 ; i < 15 ; i++) {
	        			if (checkboxy.get(i).isChecked()){
	        				notClick = false;
	        				break;
	        			}
	        		}
	        		if (notClick == true) {
	        			nieWybrano.setVisibility(View.VISIBLE);
	        		} else {
	        			nieWybrano.setVisibility(View.INVISIBLE);
	        			sprawdzWybranych();
	        			Intent noweOkno= new Intent(WyborDzieci.this, MenuGlowne.class) ;
	        			switch (Silnik.zakladka) {
	        				case 1: noweOkno = new Intent(WyborDzieci.this, Posilki.class); break;
	        				case 2: noweOkno = new Intent(WyborDzieci.this, Przewijanie.class);break;
	        				case 3: noweOkno = new Intent(WyborDzieci.this, Sen.class);break;
			       		 	case 4: noweOkno = new Intent(WyborDzieci.this, Nastroj.class);break;
			       		 	case 5: noweOkno = new Intent(WyborDzieci.this, Aktywnosc.class);break;
			       		 	case 6: noweOkno = new Intent(WyborDzieci.this, Zdrowie.class);break;
			       		 	case 7: noweOkno = new Intent(WyborDzieci.this, WiadomoscRodzic.class);break;
			       		 	case 8: noweOkno = new Intent(WyborDzieci.this, Przelomowe.class);break;
			       		 }  		
			       		 WyborDzieci.this.startActivity(noweOkno);
			       		 WyborDzieci.this.finish();
	        		}
	        	}
	        });
			
			przyciskSelectAll.setOnClickListener(new OnClickListener(){
	        	@Override
	        	public void onClick(View v){     
	     		    for (int i = 0; i < 15; i++)
	     		    	checkboxy.get(i).setChecked(true);		    		
	        	}
	        });
			
			przyciskDeselectAll.setOnClickListener(new OnClickListener(){
	        	@Override
	        	public void onClick(View v){     
	     		    for (int i = 0; i < 15; i++)
	     		    	checkboxy.get(i).setChecked(false);	
	        	}
	        }); 
			
	        przyciskWyloguj.setOnClickListener(new OnClickListener(){
	        	@Override
	        	public void onClick(View v){     
	     		    Silnik.wyloguj();
	 	        	Intent menuLogowania = new Intent(WyborDzieci.this, MenuLogowania.class);
	 	        	WyborDzieci.this.startActivity(menuLogowania);
	 	        	WyborDzieci.this.finish();
	        	}
	        });
	        
	        przyciskWstecz.setOnClickListener(new OnClickListener(){
	        	@Override
	        	public void onClick(View v){     
	 	        	Intent menuGlowne = new Intent(WyborDzieci.this, MenuGlowne.class);
	 	        	WyborDzieci.this.startActivity(menuGlowne);
	 	        	WyborDzieci.this.finish();
	        	}
	        });
	        
	        
	 }//onCreate
	 	
}
