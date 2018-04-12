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
import hr.java.vjezbe.iznimke.NiskaTemperaturaException;
import hr.java.vjezbe.iznimke.VisokaTemperaturaException;
import hr.java.vjezbe.utilities.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Glavna {

	private static final int KOLICINA_MJERNIH_SENZORA = 3;
	private static final int KOLICINA_MJERNIH_POSTAJA = 3;
	private static final Logger logger = LoggerFactory.getLogger(Glavna.class);

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
				((RadioSondaznaMjernaPostaja) mjernaPostaja)
						.povecajVisinu(((RadioSondaznaMjernaPostaja) mjernaPostaja).dohvatiVisinuPostaje());
			}
		}

		// generiranje
		while (true) {
			for (int i = 0; i < mjernePostaje.length; i++) {
				Senzor[] senzori = mjernePostaje[i].dohvatiSenzore();
				for (int j = 0; j < senzori.length; j++) {
					Senzor senzor = senzori[j];
					if (senzor instanceof SenzorTemperature) {
						try {
							((SenzorTemperature) senzor).generirajVrijednost();
						} catch (VisokaTemperaturaException e) {
							System.out.println("Pogresna temperatura postaje " + mjernePostaje[i].getNaziv());
							logger.error(e.getMessage());
						} catch (NiskaTemperaturaException e) {
							System.out.println("Pogresna temperatura postaje " + mjernePostaje[i].getNaziv());
							logger.error(e.getMessage());
						}
					}
				}
			}

			try {
				Thread.sleep(1 * 1000);
			} catch (InterruptedException e) {
			}
		}

	}

	private static GeografskaTocka kreirajGeografskuTocku(Scanner scanner) {

		System.out.println("Unesite X geo tocku");
		BigDecimal x = Validator.bigDecimal(scanner);

		System.out.println("Unesite Y geo tocku");
		BigDecimal y = Validator.bigDecimal(scanner);

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
		Integer visinaNaKojojLebdi = Validator.integer(scanner);
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
		BigDecimal povrsina = Validator.bigDecimal(scanner);
		// scanner.nextLine();

		return new Drzava(nazivDrzave, povrsina);
	}

}
