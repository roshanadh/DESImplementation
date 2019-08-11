public class DESImplementation {
	int[] ip;
	int[] p;
	int[] finalIp;
	int[] eSelect;

	// S-Boxes
	int[] s1;
	int[] s2 ;
	int[] s3;
	int[] s4;
	int[] s5;
	int[] s6;
	int[] s7;
	int[] s8;

	String samplePlainText;
	String binPlainText;
	String ipOfText;
	String l0;
	String r0;
	String[] l;
	String[] r;

	public DESImplementation() {
		ip = new int[]{
				// Elements of Initial Permutation Matrix
				58, 50, 42, 34, 26, 18, 10, 2,
				60, 52, 44, 36, 28, 20, 12, 4,
				62, 54, 46, 38, 30, 22, 14, 6,
				64, 56, 48, 40, 32, 24, 16, 8,
				57, 49, 41, 33, 25, 17, 9, 1,
				59, 51, 43, 35, 27, 19, 11, 3,
				61, 53, 45, 37, 29, 21, 13, 5,
				63, 55, 47, 39, 31, 23, 15, 7
		};
		p = new int[]{
				// Elements of Permutation Matrix
				16, 7, 20, 21,
				29, 12, 28, 17,
				1, 15, 23, 26,
				5, 18, 31, 10,
				2, 8, 24, 14,
				32, 27, 3, 9,
				19, 13, 30, 6,
				22, 11, 4, 25
		};
		finalIp = new int[]{
				// Elements of Final Permutation Matrix
				40, 8, 48, 16, 56, 24, 64, 32,
				39, 7, 47, 15, 55, 23, 63, 31,
				38, 6, 46, 14, 54, 22, 62, 30,
				37, 5, 45, 13, 53, 21, 61, 29,
				36, 4, 44, 12, 52, 20, 60, 28,
				35, 3, 43, 11, 51, 19, 59, 27,
				34, 2, 42, 10, 50, 18, 58, 26,
				33, 1, 41, 9, 49, 17, 57, 25
		};
		eSelect = new int[]{
				// Elements of E bit-selection Matrix
				32, 1, 2, 3, 4, 5,
				4, 5, 6, 7, 8, 9,
				8, 9, 10, 11, 12, 13,
				12, 13, 14, 15, 16, 17,
				16, 17, 18, 19, 20, 21,
				20, 21, 22, 23, 24, 25,
				24, 25, 26, 27, 28, 29,
				28, 29, 30, 31, 32, 1
		};
		// S-Boxes
		s1 = new int[]{
				14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7,
				0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8,
				4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0,
				15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13
		};
		s2 = new int[]{
				15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10,
				3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5,
				0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15,
				13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9
		};
		s3 = new int[]{
				10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8,
				13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1,
				13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7,
				1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12
		};
		s4 = new int[]{
				7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15,
				13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9,
				10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4,
				3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14
		};
		s5 = new int[]{
				2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9,
				14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6,
				4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14,
				11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3
		};
		s6 = new int[]{
				12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11,
				10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8,
				9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6,
				4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13
		};
		s7 = new int[]{
				4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1,
				13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6,
				1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2,
				6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12
		};
		s8 = new int[]{
				13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7,
				1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2,
				7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8,
				2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11
		};

		samplePlainText = "0123456789ABCDEF";
		binPlainText = new String();
		ipOfText = new String();
		l0 = new String();
		r0 = new String();
		l = new String[17];
		r = new String[17];

		KeyRotation kr = new KeyRotation();
		kr.decrementArrayKeys(ip);
		kr.decrementArrayKeys(p);
		kr.decrementArrayKeys(eSelect);
		kr.decrementArrayKeys(finalIp);

		for (int i = 0; i < samplePlainText.length(); i++)
			binPlainText += kr.hexToBinary(samplePlainText.charAt(i));
	}

	public void mapPlainTextToIp() {
		// Generate Initial Permutation of Binary Plain Text
		for (int i = 0; i < 64; i++)
			ipOfText += binPlainText.charAt(ip[i]) + "";
	}

	public void divideIp() {
		// Divide Initial Permutation of Binary Plain Text..
		// into L0 and R0 (32-bits each)
		for (int i = 0; i < 32; i++)
			l0 += ipOfText.charAt(i);
		for (int j = 32; j < 64; j++)
			r0 += ipOfText.charAt(j);

		l[0] = l0;
		r[0] = r0;
	}

	public String mapRToE(String r) {
		// Expand 32-bits input R to 48-bits using..
		// E bit-selection matrix
		String mappedR = new String();
		for (int i = 0; i < eSelect.length; i++)
			mappedR += r.charAt(eSelect[i]);
		return mappedR;
	}

	public String XOR(String string1, String string2) {
		// Returns XOR of two input binary strings
		String xor = new String();
		for (int i = 0; i < string1.length(); i++) {
			xor += Integer.parseInt(string1.charAt(i) + "", 2)
					^ Integer.parseInt(string2.charAt(i) + "", 2);
		}
		return xor;
	}
	public String dec2bin(int dec) {
		// Returns base-2 equivalent of input decimal
		int a = -1;
		String x = new String();
		while(dec > 0) {
			a = dec % 2;
			x = a + x;
			dec = dec / 2;
		}

		// Return a 4-bit base-2 number by..
		// appending zeros (if needed)
		if(x.length() != 4) {
			if(x.length() == 3) {
				x = "0" + x;
			} else if (x.length() == 2) {
				x = "00" + x;
			} else if (x.length() == 1) {
				x = "000" + x;
			} else {
				x = "0000";
			}
		}
		return x;
	}

	public String[] getSOfB(String[] b) {
		// Get S function of input b array
		String[] s = new String[8];
		for (int i = 0; i < s.length; i++) {
			String input = b[i];
			String firstBit = input.charAt(0) + "";
			String lastBit = input.charAt(5) + "";
			String midBits = new String();

			int row = Integer.parseInt(firstBit + lastBit + "", 2);
			for (int j = 1; j < 5; j++)
				midBits += input.charAt(j);
			int column = Integer.parseInt(midBits, 2);

			// Output: 4 bits in Sn[i, j]
			switch (i) {
				case 0:
					s[i] = dec2bin(s1[row * 16 + column]);
					continue;
				case 1:
					s[i] = dec2bin(s2[row * 16 + column]);
					continue;
				case 2:
					s[i] = dec2bin(s3[row * 16 + column]);
					continue;
				case 3:
					s[i] = dec2bin(s4[row * 16 + column]);
					continue;
				case 4:
					s[i] = dec2bin(s5[row * 16 + column]);
					continue;
				case 5:
					s[i] = dec2bin(s6[row * 16 + column]);
					continue;
				case 6:
					s[i] = dec2bin(s7[row * 16 + column]);
					continue;
				case 7:
					s[i] = dec2bin(s8[row * 16 + column]);
					continue;
				default:
					 System.out.println("ERROR: IN getSOfB()!");
					break;
			}
		}
		// Returns S1(B2)S2(B2).....S8(B8)
		return s;
	}

	public void sixteenRounds(String[] roundKeys) {
		// Performs the 16 iterations of L and R calculations
		for (int i = 1; i <= 16; i++) {
			String lTemp = l[i - 1];
			String rTemp = r[i - 1];
			l[i] = r[i - 1];

			String funcTemp = XOR(roundKeys[i], mapRToE(rTemp)); // Steps - 1 and 2 of calculating f(_)
			String[] b = getB(funcTemp);  // Step - 3 of calculating f(_)
			String[] sOfB = getSOfB(b); // Step - 3 of calculating f(_)
			String stringSOfB = new String(); // Step - 3 of calculating f(_)

			for (int j = 0; j < sOfB.length; j++) // Step - 3 of calculating f(_)
				stringSOfB += sOfB[j]; // Step - 3 of calculating f(_)

			String mappedToP = mapToP(stringSOfB); // Step - 4 of calculating f(_)
			r[i] = XOR(lTemp, mappedToP);
		}
	}

	public String[] getB(String string) {
		// Returns 8 groups of 6-bits
		// string is 48-bits in length
		String[] b = new String[8];
		for(int i = 0; i < b.length; i++)
			b[i] = "";
		for (int i = 0; i < 6; i++)
			b[0] += string.charAt(i);
		for (int i = 6; i < 12; i++)
			b[1] += string.charAt(i);
		for (int i = 12; i < 18; i++)
			b[2] += string.charAt(i);
		for (int i = 18; i < 24; i++)
			b[3] += string.charAt(i);
		for (int i = 24; i < 30; i++)
			b[4] += string.charAt(i);
		for (int i = 30; i < 36; i++)
			b[5] += string.charAt(i);
		for (int i = 36; i < 42; i++)
			b[6] += string.charAt(i);
		for (int i = 42; i < 48; i++)
			b[7] += string.charAt(i);

		// Returns B1B2B3B4...B8
		return b;
	}

	public String mapToP(String input) {
		// Generates Permutation of input String
		String output = new String();
		for (int i = 0; i < p.length; i++)
			output += input.charAt(p[i]);
		return output;
	}

	public String mapToFinalIp(String input) {
		// Generates Final Permutation of input String
		String output = new String();
		for (int i = 0; i < input.length(); i++) {
			output += input.charAt(finalIp[i]);
		}
		return output;
	}
	public String[] moveArrayUp(String[] input) {
		// Moves array elements up by one index
		String[] output = new String[input.length + 1];
		for (int i = 1; i < output.length; i++) {
			output[i] = input [i - 1];
		}
		output[0] = "empty!";
		return output;
	}

	public static void main(String[] args) {
		DESImplementation des = new DESImplementation();

		System.out.println("PLAIN TEXT (Hex.): " + des.samplePlainText);
		System.out.println("PLAIN TEXT (Bin.): " + des.binPlainText);

		KeyRotation kr = new KeyRotation();

		String[] initRoundKeys = kr.generateRoundKeys();
		String[] roundKeys = des.moveArrayUp(initRoundKeys);

		des.mapPlainTextToIp();
		des.divideIp();
		des.sixteenRounds(roundKeys);

		// Reverse order: R[16]L[16]
		String finalBits = des.r[16] + des.l[16];
		String cipher = des.mapToFinalIp(finalBits);
		System.out.println("CIPHER: " + cipher);
	}
}
