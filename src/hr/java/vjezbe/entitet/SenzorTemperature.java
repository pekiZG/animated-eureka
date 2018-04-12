package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.Random;

import hr.java.vjezbe.iznimke.NiskaTemperaturaException;
import hr.java.vjezbe.iznimke.VisokaTemperaturaException;

public class SenzorTemperature extends Senzor {

	private String nazivElektronickeKomponente;

	public SenzorTemperature(String nazivElektronickeKomponente) {
		super("°C", (byte) Integer.parseInt("2"));
		this.nazivElektronickeKomponente = nazivElektronickeKomponente;
	}

	@Override
	public String dohvatiPodatkeSenzora() {
		return "Komponenta: " + this.nazivElektronickeKomponente + ", vrijednost: " + super.getVrijednost() + " "
				+ super.getMjernaJedinica();
	}

	public void generirajVrijednost() throws VisokaTemperaturaException {
		Integer randomValue = -50 + new Random().nextInt(100);
		BigDecimal nasumicniBroj = new BigDecimal(randomValue);
		super.setVrijednost(nasumicniBroj);
		if (randomValue > 40) {
			throw new VisokaTemperaturaException("Temperatura od " + randomValue + "°C je previsoka");
		}
		if (randomValue < -10) {
			throw new NiskaTemperaturaException("Temperatura od " + randomValue + "°C je preniska");
		}
	}

}
