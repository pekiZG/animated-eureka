package hr.java.vjezbe.glavna;

import java.math.BigDecimal;
import java.util.Scanner;

import hr.java.vjezbe.entitet.Drzava;
import hr.java.vjezbe.entitet.GeografskaTocka;
import hr.java.vjezbe.entitet.MjernaPostaja;
import hr.java.vjezbe.entitet.Mjesto;
import hr.java.vjezbe.entitet.Zupanija;

public class Glavna {

	private static final int KOLICINA_MJERNIH_POSTAJA = 3;

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		MjernaPostaja[] mjernePostaje = new MjernaPostaja[KOLICINA_MJERNIH_POSTAJA];

		for (int i = 0; i < mjernePostaje.length; i++) {
			mjernePostaje[i] = kreirajMjernuPostaju(scanner);
		}

		for (int i = 0; i < mjernePostaje.length; i++) {

			MjernaPostaja mjernaPostaja = mjernePostaje[i];

			System.out.println("--------------------");
			System.out.println("Naziv mjerne postaje: " + mjernaPostaja.getNaziv());
			System.out.println("Postaja se nalazi u mjestu " + mjernaPostaja.getMjesto().getNaziv() + ", županiji "
					+ mjernaPostaja.getMjesto().getZupanija().getNaziv() + ", drzavi "
					+ mjernaPostaja.getMjesto().getZupanija().getDrzava().getNaziv());
			System.out.println("Točne koordinate postaje su x:" + mjernaPostaja.getGeografskaTocka().getX() + " y:"
					+ mjernaPostaja.getGeografskaTocka().getX());
			System.out.println("");
		}

	}

	private static GeografskaTocka kreirajGeografskuTocku(Scanner scanner) {


		System.out.println("Unesite X geo tocku");
		BigDecimal x = new BigDecimal(scanner.nextLine().replaceAll(",", "."));
		//scanner.nextLine();

		System.out.println("Unesite Y geo tocku");
		BigDecimal y = new BigDecimal(scanner.nextLine().replaceAll(",", "."));
		//scanner.nextLine();

		return new GeografskaTocka(x, y);
	}

	private static MjernaPostaja kreirajMjernuPostaju(Scanner scanner) {

		System.out.println("Unesi naziv mjerne postaje:");
		String nazivMjernePostaje = scanner.nextLine();

		Mjesto mjesto = kreirajMjesto(scanner);
		GeografskaTocka geografskaTocka = kreirajGeografskuTocku(scanner);

		return new MjernaPostaja(nazivMjernePostaje, mjesto, geografskaTocka);

	}

	private static Mjesto kreirajMjesto(Scanner scanner) {

		System.out.println("Unesite naziv mjessta:");
		String nazivMjesta = scanner.nextLine();

		Zupanija zupanija = kreirajZupaniju(scanner);

		return new Mjesto(nazivMjesta, zupanija);
	}

	private static Zupanija kreirajZupaniju(Scanner scanner) {

		System.out.println("Unesite naziv zupanije:");
		String nazivZupanije = scanner.nextLine();

		Drzava drzava = kreirajDrzavu(scanner);

		return new Zupanija(nazivZupanije, drzava);
	}

	private static Drzava kreirajDrzavu(Scanner scanner) {

		System.out.println("Unesite naziv drzave:");
		String nazivDrzave = scanner.nextLine();

		System.out.println("Unesite povrsinu drzave:");
		BigDecimal povrsina = new BigDecimal(scanner.nextLine().replaceAll(",", "."));
		//scanner.nextLine();
		
		
		String korisničkiUnos = scanner.nextLine();
		String iscisceniKorisničkiUnos = korisničkiUnos.replace(",", ".");
		
		BigDecimal broj = new BigDecimal(iscisceniKorisničkiUnos);
		
		

		return new Drzava(nazivDrzave, povrsina);
	}

}
