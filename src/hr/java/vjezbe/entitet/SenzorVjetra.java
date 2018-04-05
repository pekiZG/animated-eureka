package hr.java.vjezbe.entitet;

public class SenzorVjetra extends Senzor {

	private String velicina;

	public SenzorVjetra(String velicina) {
		super("km/h", (byte) Integer.parseInt("3"));
		this.velicina = velicina;
	}

	@Override
	public String dohvatiPodatkeSenzora() {
		return "Veličina: " + this.velicina + ", vrijednost: " + super.getVrijednost() + " "
				+ super.getMjernaJedinica();
	}

}
