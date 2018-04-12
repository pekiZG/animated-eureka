package hr.java.vjezbe.entitet;

public class SenzorVlage extends Senzor {

	public SenzorVlage() {
		//super("%", (byte) Integer.parseInt("1"));
		super("%", Byte.valueOf((byte) 1));
	}

	@Override
	public String dohvatiPodatkeSenzora() {
		return "Vrijednost: " + super.getVrijednost() + " " + super.getMjernaJedinica() + " vlage zraka";
	}

}