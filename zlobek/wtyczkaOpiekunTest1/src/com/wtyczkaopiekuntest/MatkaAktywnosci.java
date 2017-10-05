package com.wtyczkaopiekuntest;

import android.app.Activity;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Spinner;

public class MatkaAktywnosci extends Activity {

	public Paczka ladujLogin (Paczka paczka1) {
		paczka1.UserID = Silnik.paczka.wczytajMojID();
		paczka1.UserLogin = Silnik.paczka.wczytajMojLogin();
		paczka1.UserPassword = Silnik.paczka.wczytajMojeHaslo();
		return paczka1;
	}
	
	protected void klikWIkonke (Spinner spinner) {
		String temp = Silnik.paczka.listaGrupGetElement(0);
		if (temp.equals(spinner.getSelectedItem().toString()) == false) {
			int ind = Silnik.paczka.listaGrup.indexOf(spinner.getSelectedItem().toString());
			Silnik.paczka.listaGrup.add(0, spinner.getSelectedItem().toString());
			Silnik.paczka.listaGrup.remove(1);
			Silnik.paczka.listaGrup.add(ind, temp);
			Silnik.paczka.listaGrup.remove(ind + 1);
		}
	}
	
	protected void wyslijPolecenie(final String polecenie) {
		 new Handler().postDelayed(new Thread() {
		    	@Override
		    	public void run() {
		    		Silnik.polaczenie.send(polecenie);
		    	}
		    }, Silnik.opoznienieWysylania);
	}

	protected void odbierzKtoZalogowany (final TextView zalogowany) {
		zalogowany.setText("Zalogowany jako: " + Silnik.paczka.wczytajMojLogin());
	}

	protected void ustawPrzezroczystoscPrzyciskow (ImageButton p1, ImageButton p2, ImageButton p3){
		ustawPrzezroczystoscPrzycisku(p1);
		ustawPrzezroczystoscPrzycisku(p2);
		ustawPrzezroczystoscPrzycisku(p3);
	}
	protected void ustawPrzezroczystoscPrzyciskow (ImageButton p1, ImageButton p2){
		ustawPrzezroczystoscPrzycisku(p1);
		ustawPrzezroczystoscPrzycisku(p2);
	}
	protected void ustawPrzezroczystoscPrzycisku (ImageButton p1){
		p1.getBackground().setAlpha(Silnik.MENU_BUTTON_ALPHA);
        p1.setHapticFeedbackEnabled(Silnik.HAPTIC_BUTTON_FEEDBACK);
	}
	
}
