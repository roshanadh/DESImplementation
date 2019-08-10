import java.util.*;

// Generate 16 round unique key
public class KeyRotation {
	// 64-bit key in hex
	static String sampleKey = "ABCDEF1234567890";
	// 64-bit key
	static  String binaryKey = null;
	// PC matrices (of indices)
	static int[] pc1 = {
			57,	49,	41,	33,	25,	17,	9,
			1,	58,	50,	42,	34,	26,	18,
			10,	2,	59,	51,	43,	35,	27,
			19,	11,	3,	60,	52,	44,	36,
			63,	55,	47,	39,	31,	23,	15,
			7,	62,	54,	46,	38,	30,	22,
			14,	6,	61,	53,	45,	37,	29,
			21,	13,	5,	28,	20,	12,	4
	};

	static int[] pc2 = {
			14, 17, 11, 24, 1,  5,
			3,  28, 15, 6,  21, 10,
			23, 19, 12, 4,  26, 8,
			16, 7,  27, 20, 13, 2,
			41, 52, 31, 37, 47, 5,
			30, 40, 51, 45, 33, 8,
			44, 49, 39, 56, 34, 3,
			46, 42, 50, 36, 29, 2
	};

	// PC matrices (of values)
	static String keyPc1 = new String();
	static String keyPc2 = new String();
	// Divide 56-bit PC1: 28-bit (0-27) and 28-bit (28-55)
	static String firstHalf;
	static String secondHalf;
	// Left Check Shift-operated halves
	static int[] roundFirstHalfShift;
	static  int[] roundSecondHalfShift;
	// Generated key count
	static int keyCount = 1;

	public KeyRotation() {
		decrementArrayKeys(pc1);
		decrementArrayKeys(pc2);

		// Convert hex key to binary key
		binaryKey = hexToBinary(sampleKey.charAt(0));
		for(int i = 1; i < 16; i++) {
			binaryKey += hexToBinary(sampleKey.charAt(i));
		}
		System.out.println("Binary key (64-bit): " + binaryKey);

		// Generate PC-1 matrix
		mapBinKeyToPc1();

		// Generate 16 round keys
		generateRoundKeys();
	}

	public static void main(String[] args) {
		new KeyRotation();
	}

	// Generate round keys
	public static String[] generateRoundKeys() {
		String input = new String();
		int shiftSize;
		input = keyPc1;
		String[] roundKeys = new String[16];
		for(int i = 1; i <= 16; i++) {
			divideForShift(input);
			shiftSize = i == 1 || i == 2 || i == 9 || i == 16 ? 1 : 2;
			roundFirstHalfShift = leftCircularShift(stringToIntArray(firstHalf), shiftSize);
			roundSecondHalfShift = leftCircularShift(stringToIntArray(secondHalf), shiftSize);
			String roundKey = mapHalvesToPc2(intArrayToString(roundFirstHalfShift), intArrayToString(roundSecondHalfShift));
			roundKeys[i - 1] = roundKey;
//			System.out.println("Round-" + (keyCount++) + " key (48-bit):        " + roundKey);
			input = intArrayToString(roundFirstHalfShift) + intArrayToString(roundSecondHalfShift);
		}
		return roundKeys;
	}

	// Decrement array elements by 1
	public static void decrementArrayKeys(int array[]) {
		for(int i = 0; i < array.length; i++)
			array[i] -= 1;
	}

	public static String intArrayToString(int[] array) {
		return Arrays.toString(array).replaceAll("\\[|\\]|,|\\s", "");
	}

	public static String hexToBinary(char a) {
		switch (a) {
			case 'A':
				return "1010";
			case 'B':
				return "1011";
			case 'C':
				return "1100";
			case 'D':
				return "1101";
			case 'E':
				return "1110";
			case 'F':
				return "1111";
			case '1':
				return "0001";
			case '2':
				return "0010";
			case '3':
				return "0011";
			case '4':
				return "0100";
			case '5':
				return "0101";
			case '6':
				return "0110";
			case '7':
				return "0111";
			case '8':
				return "1000";
			case '9':
				return "1001";
			case '0':
				return "0000";
			default:
				return null;
		}
	}

	public static void divideForShift(String string) {
		firstHalf = string.charAt(0) + "";
		for(int i = 1; i <= 27; i++)
			firstHalf += string.charAt(i);

		secondHalf = string.charAt(28) + "";

		for(int i = 29; i <= 55; i++)
			secondHalf += string.charAt(i);
//		System.out.println("First half (28-bit):        " + firstHalf);
//		System.out.println("Second half (28-bit):       " + secondHalf);
	}

	public static void mapBinKeyToPc1() {
		// Generates PC-1 matrix for binary key
		for(int i = 0; i < pc1.length; i++)
			keyPc1 += binaryKey.charAt(pc1[i]);
//		System.out.println("PC-1 matrix (56-bit):       " + keyPc1);
	}

	public static String mapHalvesToPc2(String firstHalf, String secondHalf) {
		// Generates PC-2 matrix from shifted bits
		// Merge two halves
		keyPc2 = "";
		String mergedKey = firstHalf + secondHalf;
		for(int i = 0; i < pc2.length; i++)
			keyPc2 += mergedKey.charAt(pc2[i]);
//		System.out.println("PC-2 matrix (48-bit):       " + keyPc2);
		return keyPc2;
	}

	public static int[] leftCircularShift(int[] array, int shiftSize) {
		int[] tempArray = new int[shiftSize + 1];
		// Reserve first few keys
		for(int i = 0; i < shiftSize; i++)
			tempArray[i] = array[i];
		// Shift
		for(int j = 0; j < array.length - shiftSize; j++)
			array[j] = array[j + shiftSize];
		// Place reserved keys back
		for(int k = array.length - tempArray.length + 1, l = 0; l < tempArray.length - 1; k++, l++)
			array[k] = tempArray[l];
		// Return shifted array
		return array;
	}

	public static int[] stringToIntArray(String string) {
		int[] intArray = new int[string.length()];
		for(int i = 0; i < intArray.length; i++)
			intArray[i] = Integer.parseInt(string.charAt(i) + "", 2);
		return intArray;
	}
}
