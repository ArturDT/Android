package com.wtyczkaopiekuntest;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.*;
import com.google.gson.Gson;

public class PolaczenieZPlikiem {
	//zerowa linia pliku jest liczb¹ wpisów!!!
	String filename;
	
	public boolean sprawdzPlik() {
		try {
			File file = new File(filename);
			Scanner in = new Scanner(file);
			in.close();
			return true;
		}catch (FileNotFoundException e) {
			return false;
		}
	}
	
	public void zapiszDoPliku (String wpis) {
		try {
			PrintWriter zapis = new PrintWriter(filename);
			zapis.println(wpis);
			zapis.close();
		}catch (FileNotFoundException e) {}		
	}
	
	public String odczytajZPliku (int indeks) {		
		try {
			File file = new File(filename);
			Scanner in = new Scanner(file);
			String zdanie = in.nextLine();
			for (int i = 0 ; i < indeks; i ++)
				zdanie = in.nextLine();
			in.close();
			return zdanie;
		}catch (FileNotFoundException e) {}
		return "error";
	}
	
	public boolean wyczyscPlik() {
		try {
			File file = new File(filename);
			file.delete();
			file.createNewFile();
			return true;
		}catch (FileNotFoundException e) {
			return false;
		}catch (IOException e) {
			return false;
		}
	}
	
	public int sprawdzLogin(String login, String haslo) {
		String wpis;
		Gson gson = new Gson();
		PaczkaPoczatkowaOpiekun paczka = new PaczkaPoczatkowaOpiekun();
		int liczbaLinii = Integer.parseInt(odczytajZPliku(0));
		for (int i = 1; i < liczbaLinii; i++)
		{
			wpis = odczytajZPliku(0);
			paczka = gson.fromJson(wpis, PaczkaPoczatkowaOpiekun.class);
			if (login.equals(paczka.wczytajMojLogin())) {
				if (haslo.equals(paczka.wczytajMojeHaslo())) {
					return i;
				}
			}
		}
		return 0;
	}

	public boolean podmienWPliku (int indeks, String wpis) {
		try {
			File file = new File(filename);
			Scanner in = new Scanner(file);
			in.nextLine();
			for (int i = 0 ; i < indeks; i ++)
				in.nextLine();
			in.remove();
			zapiszDoPliku(wpis);
			in.close();
			return true;
		}catch (FileNotFoundException e) {}
		return false;
	}
}
