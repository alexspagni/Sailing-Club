package javaClass;

import java.util.ArrayList;

public class FiscalCode {
	private ArrayList<Character> lettereArrayList = new ArrayList<>() {
		{
			add('A');
			add('B');
			add('C');
			add('D');
			add('E');
			add('F');
			add('G');
			add('H');
			add('I');
			add('J');
			add('K');
			add('L');
			add('M');
			add('N');
			add('O');
			add('P');
			add('Q');
			add('R');
			add('S');
			add('T');
			add('U');
			add('V');
			add('W');
			add('X');
			add('Y');
			add('Z');
		}
	};
	private ArrayList<Character> numeriArrayList = new ArrayList<>() {
		{
			add('1');
			add('2');
			add('3');
			add('4');
			add('5');
			add('6');
			add('7');
			add('8');
			add('9');

		}
	};

	public FiscalCode() {

	}

	public int checkLetters(String stringa) {
		int firstCheck;
		for (int i = 0; i < stringa.length(); i++) {
			firstCheck = -1;
			Character letter = stringa.charAt(i);
			System.out.println(letter);
			for (int j = 0; j < lettereArrayList.size(); j++) {

				if (Character.compare(letter, lettereArrayList.get(j)) == 0) {
//					System.out.println("ho trovato corrispondenza");
					firstCheck = 1;
					break;
				}
			}
			if (firstCheck == -1) {
				return -1;
			}
		}
		return 1;
	}

	public int checkNumbers(String stringa) {
		int firstCheck;
		for (int i = 0; i < stringa.length(); i++) {
			firstCheck = -1;
			Character letter = stringa.charAt(i);
			System.out.println(letter);
			for (int j = 0; j < numeriArrayList.size(); j++) {

				if (Character.compare(letter, numeriArrayList.get(j)) == 0) {
					firstCheck = 1;
//					System.out.println("ho trovato corrispondenza");
					break;
				}
			}
			if (firstCheck == -1) {
				return -1;
			}
		}
		return 1;
	}

	public int checkFiscalCode(String fiscalCode) {
//		Character[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', '', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
//		List<Character> array = new ArrayList<Character>();
//		array.addAll(Arrays.asList(alphabet));
//		int firstCheck = -1, secondCheck = -1, thirthCheck = -1, fourCheck = -1, fifthCheck = -1;
		if (fiscalCode.length() < 16 || fiscalCode.length() > 16) {
			return -1;
		}
		String firstString = fiscalCode.substring(0, 6);
//		System.out.println("sono nel primo pezzo");
		if (checkLetters(firstString) == -1) {
			return -1;
		}
//		System.out.println("sono nel secondo pezzo");
		String secondString = fiscalCode.substring(6, 8);
		if (checkNumbers(secondString) == -1) {
			return -1;
		}
//		System.out.println("sono nel terzo pezzo");
		String thirtString = fiscalCode.substring(8, 9);
		if (checkLetters(thirtString) == -1) {
			return -1;
		}
//		System.out.println("sono nel quarto pezzo");
		String fourString = fiscalCode.substring(9, 11);
		if (checkNumbers(fourString) == -1) {
			return -1;
		}
//		System.out.println("sono nel quinto pezzo");
		String fiveString = fiscalCode.substring(11, 13);
		if (checkLetters(fiveString) == -1) {
			return -1;
		}
//		System.out.println("sono nel sesto pezzo");
		String sixString = fiscalCode.substring(13, 15);
		if (checkNumbers(sixString) == -1) {
			return -1;
		}
		System.out.println("sono in fondo");
		return 1;
	}
}
