package hr.java.vjezbe.entitet;

public class RadioSondaznaMjernaPostaja extends MjernaPostaja implements RadioSondazna {

	Integer visinaNaKojojLebdi;
	
	public RadioSondaznaMjernaPostaja(String naziv, Mjesto mjesto, GeografskaTocka geografskaTocka, Senzor[] senzori) {
		super(naziv, mjesto, geografskaTocka, senzori);
	}

	@Override
	public void podesiVisinuPostaje(Integer visinaNaKojojLebdi) {
		this.visinaNaKojojLebdi = visinaNaKojojLebdi;
	}

	@Override
	public Integer dohvatiVisinuPostaje() {
		return this.visinaNaKojojLebdi;
	}

}
