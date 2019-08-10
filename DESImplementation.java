public class DESImplementation {
	int[] ip = {
		58, 50, 42, 34, 26, 18, 10, 2,
		60, 52, 44, 36, 28, 20, 12, 4,
		62, 54, 46, 38, 30, 22, 14, 6,
		64, 56, 48, 40, 32, 24, 16, 8,
		57, 49, 41, 33, 25, 17, 9,  1,
		59, 51, 43, 35, 27, 19, 11, 3,
		61, 53, 45, 37, 29, 21, 13, 5,
		63, 55, 47, 39, 31, 23, 15, 7
	};

	int[] p = {
		16,   7,  20,  21,
		29,  12,  28,  17,
		1 , 15 , 23 , 26,
		5 , 18 , 31 , 10,
		2 ,  8 , 24 , 14,
		32,  27,   3,   9,
		19,  13,  30,   6,
		22,  11,   4,  25
	};

	int[] finalIp = {
		40, 8, 48, 16, 56, 24, 64, 32,
		39, 7, 47, 15, 55, 23, 63, 31,
		38, 6, 46, 14, 54, 22, 62, 30,
		37, 5, 45, 13, 53, 21, 61, 29,
		36, 4, 44, 12, 52, 20, 60, 28,
		35, 3, 43, 11, 51, 19, 59, 27,
		34, 2, 42, 10, 50, 18, 58, 26,
		33, 1, 41,  9, 49, 17, 57, 25
	};

	int[] eSelect = {
		32, 1, 2, 3, 4, 5,
		4, 5, 6, 7, 8, 9,
		8, 9, 10, 11, 12, 13,
		12, 13, 14, 15, 16, 17,
		16, 17, 18, 19, 20, 21,
		20, 21, 22, 23, 24, 25,
		24, 25, 26, 27, 28, 29,
		28, 29, 30, 31, 32,  1
	};

	int[] s1 = {
		14,  4,  13,  1,   2, 15,  11,  8,   3, 10,   6, 12,   5,  9,   0,  7,
		0 ,15 ,  7 , 4 , 14 , 2 , 13 , 1 , 10 , 6 , 12 ,11 ,  9 , 5 ,  3 , 8,
		4 , 1,  14,  8,  13,  6,   2, 11,  15, 12,   9,  7,   3, 10,   5,  0,
		15, 12,   8,  2,   4,  9,   1,  7,   5, 11,   3, 14,  10,  0,   6, 13
	};

	int[] s2 = {
		15,  1,   8, 14,   6, 11,   3,  4,   9,  7,   2, 13,  12,  0,   5, 10,
		3 ,13,   4,  7,  15,  2,   8, 14,  12,  0,   1, 10,   6,  9,  11,  5,
		0 ,14,   7, 11,  10,  4,  13,  1,   5,  8,  12,  6,   9,  3,   2, 15,
		13,  8,  10,  1,   3, 15,   4,  2,  11,  6,   7, 12,   0,  5,  14,  9
	};

	int[] s3 = {
		10,  0,   9, 14,   6,  3,  15,  5,   1, 13,  12,  7,  11,  4,   2,  8,
		13,  7,   0,  9,   3,  4,   6, 10,   2,  8,   5, 14,  12, 11,  15,  1,
		13,  6,   4,  9,   8, 15,   3,  0,  11,  1,   2, 12,   5, 10,  14,  7,
		1 ,10,  13,  0,   6,  9,   8,  7,   4, 15,  14,  3,  11,  5,   2, 12
	};

	int[] s4 = {
		7 ,13,  14,  3,   0,  6,   9, 10,   1,  2,   8,  5,  11, 12,   4, 15,
		13,  8,  11,  5,   6, 15,   0,  3,   4,  7,   2, 12,   1, 10,  14,  9,
		10,  6,   9,  0,  12, 11,   7, 13,  15,  1,   3, 14,   5,  2,   8,  4,
		3 ,15,   0,  6,  10,  1,  13,  8,   9,  4,   5, 11,  12,  7,   2, 14
	};

	int[] s5 = {
		2 ,12 ,  4 , 1 ,  7 ,10 , 11 , 6 ,  8 , 5 ,  3 ,15 , 13 , 0 , 14 , 9,
		14, 11 ,  2 ,12 ,  4 , 7 , 13 , 1 ,  5 , 0 , 15 ,10 ,  3 , 9 ,  8 , 6,
		4 , 2  , 1, 11,  10, 13,   7,  8,  15,  9,  12,  5,   6,  3,   0, 14,
		11,  8,  12,  7,   1, 14,   2, 13,   6, 15,   0,  9,  10,  4,   5,  3
	};
	int[] s6 = {
		12,  1,  10, 15,   9,  2,   6,  8,   0, 13,   3,  4,  14,  7,   5, 11,
		10, 15,   4,  2,   7, 12,   9,  5,   6,  1,  13, 14,   0, 11,   3,  8,
		9 ,14,  15,  5,   2,  8,  12,  3,   7,  0,   4, 10,   1, 13,  11,  6,
		4 , 3,   2, 12,   9,  5,  15, 10,  11, 14,   1,  7,   6,  0,   8, 13
	};
	int[] s7 = {
		4 ,11  , 2 ,14  ,15  ,0 ,  8 ,13 ,  3 ,12 ,  9 , 7 ,  5 ,10 ,  6 , 1,
		13,  0 , 11,  7 ,  4 , 9,   1, 10,  14,  3,   5, 12,   2, 15,   8,  6,
		1 , 4 , 11, 13 , 12 , 3,   7, 14,  10, 15,   6,  8,   0,  5,   9,  2,
		6 ,11 , 13,  8 ,  1 , 4,  10,  7,   9,  5,   0, 15,  14,  2,   3, 12
	};
	int[] s8 = {
		13,  2,   8,  4,   6, 15,  11,  1,  10,  9,   3, 14,   5,  0,  12,  7,
		1 ,15,  13,  8,  10,  3,   7,  4,  12,  5,   6, 11,   0, 14,   9,  2,
		7 ,11,   4,  1,   9, 12,  14,  2,   0,  6,  10, 13,  15,  3,   5,  8,
		2 , 1,  14,  7,   4, 10,   8, 13,  15, 12,   9,  0,   3,  5,   6, 11
	};

	String samplePlainText = "0123456789ABCDEF";
	String binPlainText = new String();
	String ipOfText = new String();
	String l0 = new String();
	String r0 = new String();
	String[] l = new String[17];
	String[] r = new String[17];
	String[] roundKeys = KeyRotation.generateRoundKeys();
//	public void generateRoundKeys() {
//		String input = new String();
//		int shiftSize;
//		input = KeyRotation.keyPc1;
//		String[] roundKeys = new String[16];
//		for(int i = 1; i <= 16; i++) {
//			KeyRotation.divideForShift(input);
//			shiftSize = i == 1 || i == 2 || i == 9 || i == 16 ? 1 : 2;
//			KeyRotation.roundFirstHalfShift = KeyRotation.leftCircularShift(KeyRotation.stringToIntArray(KeyRotation.firstHalf), shiftSize);
//			KeyRotation.roundSecondHalfShift = KeyRotation.leftCircularShift(KeyRotation.stringToIntArray(KeyRotation.secondHalf), shiftSize);
//			String roundKey = KeyRotation.mapHalvesToPc2(KeyRotation.intArrayToString(KeyRotation.roundFirstHalfShift), KeyRotation.intArrayToString(KeyRotation.roundSecondHalfShift));
//			roundKeys[i - 1] = roundKey;
////			System.out.println("Round-" + (keyCount++) + " key (48-bit):        " + roundKey);
//			input = KeyRotation.intArrayToString(KeyRotation.roundFirstHalfShift) + KeyRotation.intArrayToString(KeyRotation.roundSecondHalfShift);
//		}
//	}

	public DESImplementation() {
		KeyRotation.decrementArrayKeys(ip);
		KeyRotation.decrementArrayKeys(eSelect);
		KeyRotation.decrementArrayKeys(finalIp);

//		generateRoundKeys();

		for (int i = 0; i < samplePlainText.length(); i++)
			binPlainText += KeyRotation.hexToBinary(samplePlainText.charAt(i));
		System.out.println("Plain Text in binary: " + binPlainText);

		// Generate Initial Permutation (64-bit) of the plain text
		mapPlainTextToIp();

		// Divide IP into two
		divideIp();

		// Map R0 to E Bit-Selection table
		System.out.println("Mapped: " + mapRToE(r0));

		// XOR Addition
		System.out.println("XOR add: " + XOR("111011", "001011"));

		sixteenRounds();

		// Reverse order: R[16]L[16]
		String finalBits = r[16] + l[16];
		String cipher = mapToFinalIp(finalBits);
		System.out.println(cipher);

	}

	public void mapPlainTextToIp() {
		for (int i = 0; i < 64; i++)
			ipOfText += binPlainText.charAt(ip[i]) + "";
		System.out.println("IP of Text " + ipOfText);
	}

	public void divideIp() {
		for (int i = 0; i < 32; i++)
			l0 += ipOfText.charAt(i);
		for (int j = 32; j < 64; j++)
			r0 += ipOfText.charAt(j);

		l[0] = l0;
		r[0] = r0;
		System.out.println("l = " + l0);
		System.out.println("r = " + r0);
	}

	public String mapRToE(String r) {
		String mappedR = new String();
		for (int i = 0; i < r.length(); i++) {
			mappedR += r.charAt(eSelect[i]);
		}
		return mappedR;
	}

	public String XOR(String string1, String string2) {
		String xor = new String();
		int bits1 = Integer.parseInt(string1, 2);
		int bits2 = Integer.parseInt(string2, 2);
		for (int i = 0; i < string1.length(); i++) {
			xor += Integer.parseInt(string1.charAt(i) + "", 2)
					^ Integer.parseInt(string2.charAt(i) + "", 2);
		}
		return xor;
	}
	public String dec2bin(int dec) {
		int a = -1;
		String x = new String();
		while(dec > 0) {
			a = dec % 2;
			x = a + x;
			dec = dec / 2;
		}
		System.out.println("Binary number:" + x);
		return x;
	}

	public String[] getSOfB(String[] b) {
		String[] s = new String[8];
		for (int i = 0; i < 8; i++) {
			String input = b[i];
			char firstBit = input.charAt(0);
			char lastBit = input.charAt(5);
			String midBits = new String();
			int row = Integer.parseInt(firstBit + lastBit + "", 2);
			for (int j = 1; j < 5; j++)
				midBits += input.charAt(i);
			int column = Integer.parseInt(midBits, 2);

			// Output: 4 bits in Sn[i, j]
			switch (i) {
				case 0:
					s[i] = dec2bin(s1[row * 16 + column]);
					break;
				case 1:
					s[i] = dec2bin(s2[row * 16 + column]);
					break;
				case 2:
					s[i] = dec2bin(s3[row * 16 + column]);
					break;
				case 3:
					s[i] = dec2bin(s4[row * 16 + column]);
					break;
				case 4:
					s[i] = dec2bin(s5[row * 16 + column]);
					break;
				case 5:
					s[i] = dec2bin(s6[row * 16 + column]);
					break;
				case 6:
					s[i] = dec2bin(s7[row * 16 + column]);
					break;
				case 7:
					s[i] = dec2bin(s8[row * 16 + column]);
					break;
				default:
					System.out.println("ERROR: IN getSOfB()!");
					break;
			}
		}
		return s;
		// returns S1(B2)S2(B2).....S8(B8)
	}

	public void sixteenRounds() {
		for (int i = 1; i <= 16; i++) {
			String lTemp = l[i - 1];
			String rTemp = r[i - 1];
			l[i] = r[i - 1];

			String funcTemp = XOR(roundKeys[i], mapRToE(rTemp)); // Steps - 1 and 2 of calculating f(_)
			String[] b = getB(funcTemp);  // Step - 3 of calulcating f(_)
			String[] sOfB = getSOfB(b); // Step - 3 of calulcating f(_)
			String stringSOfB = new String(); // Step - 3 of calulcating f(_)

			for (int j = 0; j < sOfB.length; j++) // Step - 3 of calulcating f(_)
				stringSOfB += sOfB[i]; // Step - 3 of calulcating f(_)
			String mappedToP = mapToP(stringSOfB);
			r[i] = XOR(lTemp, mappedToP);
		}
	}

	public String[] getB(String string) {
		// String is 48-bits in length
		String[] b = new String[48];
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
		return b;
		// returns B1B2B3B4...B8
	}

	public String mapToP(String input) {
		// Maps input to permutation matrix
		String output = new String();
		for (int i = 0; i < input.length(); i++) {
			output += input.charAt(p[i]);
		}
		return output;
	}

	public String mapToFinalIp(String input) {
		// Maps input to final permutation matrix
		String output = new String();
		for (int i = 0; i < input.length(); i++) {
			output += input.charAt(finalIp[i]);
		}
		return output;
	}

	public static void main(String[] args) {
		new DESImplementation();
	}
}
