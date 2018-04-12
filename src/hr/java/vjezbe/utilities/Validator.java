package hr.java.vjezbe.utilities;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Validator {

	public Validator() {
		// TODO Auto-generated constructor stub
	}
	
	public static BigDecimal bigDecimal(Scanner scanner) {
		
		System.out.println("Unesi broj:");
		
		BigDecimal broj = null;
		while (broj == null) {
			try {
				String korisnickiUnos = scanner.nextLine().replaceAll(",", ".");
				broj = new BigDecimal(korisnickiUnos);
			} catch (NumberFormatException e) {
				System.out.println("Nije unesen BigDecimal, pokusaj opet:");
			} 
		}
		return broj;
	}
	
	public static Integer integer(Scanner scanner) {
		
		System.out.println("Unesi broj:");
		
		Integer broj = null;
		while (broj == null) {
			try {
				broj = scanner.nextInt();
				scanner.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Nije unesen Integer, pokusaj opet:");
				scanner.nextLine();
			} 
		}
		return broj;
	}

}
