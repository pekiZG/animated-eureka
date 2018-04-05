package hr.java.vjezbe.glavna;

import java.math.BigDecimal;
import java.util.Scanner;

import hr.java.vjezbe.entitet.Drzava;
import hr.java.vjezbe.entitet.GeografskaTocka;
import hr.java.vjezbe.entitet.MjernaPostaja;
import hr.java.vjezbe.entitet.Mjesto;
import hr.java.vjezbe.entitet.RadioSondaznaMjernaPostaja;
import hr.java.vjezbe.entitet.Senzor;
import hr.java.vjezbe.entitet.SenzorTemperature;
import hr.java.vjezbe.entitet.SenzorVjetra;
import hr.java.vjezbe.entitet.SenzorVlage;
import hr.java.vjezbe.entitet.Zupanija;

public class Glavna {

	private static final int KOLICINA_MJERNIH_SENZORA = 3;
	private static final int KOLICINA_MJERNIH_POSTAJA = 3;

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		MjernaPostaja[] mjernePostaje = new MjernaPostaja[KOLICINA_MJERNIH_POSTAJA];

		// kreiranje
		for (int i = 0; i < mjernePostaje.length; i++) {
			if (i < 2) {
				mjernePostaje[i] = kreirajMjernuPostaju(scanner);
			} else {
				mjernePostaje[i] = kreirajRadioSondaznuMjernuPostaju(scanner);
			}

		}

		// ispis
		for (int i = 0; i < mjernePostaje.length; i++) {

			MjernaPostaja mjernaPostaja = mjernePostaje[i];

			System.out.println("--------------------");
			System.out.println("Naziv mjerne postaje: " + mjernaPostaja.getNaziv());
			System.out.println("Postaja se nalazi u mjestu " + mjernaPostaja.getMjesto().getNaziv() + ", županiji "
					+ mjernaPostaja.getMjesto().getZupanija().getNaziv() + ", drzavi "
					+ mjernaPostaja.getMjesto().getZupanija().getDrzava().getNaziv());
			System.out.println("Točne koordinate postaje su x:" + mjernaPostaja.getGeografskaTocka().getX() + " y:"
					+ mjernaPostaja.getGeografskaTocka().getX());
			System.out.println("Vrijednosti senzora postaje su:");

			Senzor[] sortiraniSenzori = mjernaPostaja.dohvatiSenzore();
			for (int j = 0; j < sortiraniSenzori.length; j++) {
				System.out.println(sortiraniSenzori[j].dohvatiPodatkeSenzora());
			}

			if (mjernaPostaja instanceof RadioSondaznaMjernaPostaja) {
				((RadioSondaznaMjernaPostaja) mjernaPostaja).povecajVisinu(((RadioSondaznaMjernaPostaja) mjernaPostaja).dohvatiVisinuPostaje());
			}
		}

	}

	private static GeografskaTocka kreirajGeografskuTocku(Scanner scanner) {

		System.out.println("Unesite X geo tocku");
		BigDecimal x = new BigDecimal(scanner.nextLine().replaceAll(",", "."));
		// scanner.nextLine();

		System.out.println("Unesite Y geo tocku");
		BigDecimal y = new BigDecimal(scanner.nextLine().replaceAll(",", "."));
		// scanner.nextLine();

		return new GeografskaTocka(x, y);
	}

	private static MjernaPostaja kreirajRadioSondaznuMjernuPostaju(Scanner scanner) {
		System.out.println("Unesi naziv radio sondažne mjerne postaje:");
		String nazivMjernePostaje = scanner.nextLine();

		Mjesto mjesto = kreirajMjesto(scanner);
		GeografskaTocka geografskaTocka = kreirajGeografskuTocku(scanner);

		Senzor[] senzori = kreirajSenzore(scanner);

		RadioSondaznaMjernaPostaja radioSondaznaMjernaPostaja = new RadioSondaznaMjernaPostaja(nazivMjernePostaje,
				mjesto, geografskaTocka, senzori);

		System.out.println("Unesi visinu radio sondažne mjerne postaje:");
		Integer visinaNaKojojLebdi = scanner.nextInt();
		scanner.nextLine();
		radioSondaznaMjernaPostaja.podesiVisinuPostaje(visinaNaKojojLebdi);

		return radioSondaznaMjernaPostaja;
	}

	private static MjernaPostaja kreirajMjernuPostaju(Scanner scanner) {

		System.out.println("Unesi naziv mjerne postaje:");
		String nazivMjernePostaje = scanner.nextLine();

		Mjesto mjesto = kreirajMjesto(scanner);
		GeografskaTocka geografskaTocka = kreirajGeografskuTocku(scanner);

		Senzor[] senzori = kreirajSenzore(scanner);

		return new MjernaPostaja(nazivMjernePostaje, mjesto, geografskaTocka, senzori);

	}

	private static Senzor[] kreirajSenzore(Scanner scanner) {
		Senzor[] senzori = new Senzor[KOLICINA_MJERNIH_SENZORA];

		for (int i = 0; i < senzori.length; i++) {

			switch (i) {
			case 0:
				System.out.println("Unesi senzor Vlage");
				senzori[i] = new SenzorVlage();
				break;
			case 1:
				System.out.println("Unesi naziv Elektronicke Komponente senzora Temperature");
				String nazivElektronickeKomponente = scanner.nextLine();
				senzori[i] = new SenzorTemperature(nazivElektronickeKomponente);
				break;
			case 2:
				System.out.println("Unesi veličinu senzora Vjetra");
				String velicina = scanner.nextLine();
				senzori[i] = new SenzorVjetra(velicina);
				break;

			default:
				break;
			}

		}

		return senzori;
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
		// scanner.nextLine();

		return new Drzava(nazivDrzave, povrsina);
	}

}
