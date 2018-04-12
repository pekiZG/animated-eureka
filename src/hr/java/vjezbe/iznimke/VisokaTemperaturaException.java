package hr.java.vjezbe.iznimke;

public class VisokaTemperaturaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2464415359284430992L;

	/**
	 * Baca Visoka Temperatura exception
	 * @throws Exception
	 */
	public VisokaTemperaturaException() {
	}

	/**
	 * Propagira poruku greška
	 * @param message
	 * @author Petar
	 */
	public VisokaTemperaturaException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public VisokaTemperaturaException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public VisokaTemperaturaException(String message, Throwable cause) {
		super(message, cause);
	}

}
