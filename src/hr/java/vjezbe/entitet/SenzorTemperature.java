package hr.java.vjezbe.entitet;

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

}
