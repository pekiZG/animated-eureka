package hr.java.vjezbe.random;

public class Kuca {

	private Integer vrijednost;
	private Integer ponudenaCijena;

	public Kuca() {
		
	}
	
	public Kuca(Integer vrijednost) {
		
	}

	public Integer getVrijednost() {
		return vrijednost;
	}

	public void setVrijednost(Integer vrijednost) {
		this.vrijednost = vrijednost;
	}

	public void setPonudenaCijena(Integer ponudenaCijena) {
		this.ponudenaCijena = ponudenaCijena;
	}
	
	public boolean isplatiLiSePrihvatitiPonudu() {
		if (ponudenaCijena > vrijednost) {
			return true;
		}
		return false;
	}
	
}
