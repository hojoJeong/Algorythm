package swea;

import java.util.*;
import java.io.*;

public class 암호코드스캔_1242 {
	static char[] hex = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	static String[] bin = { "0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010",
			"1011", "1100", "1101", "1110", "1111" };
	static String[] coderule = { "3211", "2221", "2122", "1411", "1132", "1231", "1114", "1312", "1213", "3112" };
	static int N, M, ans;
	static Set<String> code;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			ans = 0;
			code = new HashSet<String>();

			for (int i = 0; i < N; i++) {
				String temp = br.readLine();
				int start = 0;
				int end = 0;
				for (int j = 0; j < M; j++) {
					if (temp.charAt(j) != 0 && start == 0) {
						start = j;
					}
					if (temp.charAt(j) == 0 && start != 0) {
						end = j;
					}
					if (start != 0 && end != 0) {

						code.add(temp.substring(start, end)); // 16진수 코드들을 set에 넣어줌
						start = 0;
						end = 0;

					}
				}
			}

			Iterator<String> it = code.iterator();
			for (int i = 0; i < code.size(); i++) {
				changeHaxToBin(it.next());
			}
			System.out.printf("#%d %d\n", tc, ans);
		}

	}

	// 16진수를 2진수로 변환
	static void changeHaxToBin(String code) {
		String bicode = "";
		for (int i = 0; i < code.length(); i++) {
			int hexToDec = Integer.parseInt(Character.toString(code.charAt(i)), 16); // 16진수 -> 10진수
			bicode += Integer.toBinaryString(hexToDec); // 10진수 -> 2진수
		}

//		for (int i = 0; i < code.length(); i++) {
//			switch (code.charAt(i)) {
//			case '0':
//				bicode += Character.toString(hex[0]);
//				break;
//			case '1':
//				bicode += Character.toString(hex[1]);
//				break;
//			case '2':
//				bicode += Character.toString(hex[2]);
//				break;
//			case '3':
//				bicode += Character.toString(hex[3]);
//				break;
//			case '4':
//				bicode += Character.toString(hex[4]);
//				break;
//			case '5':
//				bicode += Character.toString(hex[5]);
//				break;
//			case '6':
//				bicode += Character.toString(hex[6]);
//				break;
//			case '7':
//				bicode += Character.toString(hex[7]);
//				break;
//			case '8':
//				bicode += Character.toString(hex[8]);
//				break;
//			case '9':
//				bicode += Character.toString(hex[9]);
//				break;
//			case 'a':
//				bicode += Character.toString(hex[10]);
//				break;
//			case 'b':
//				bicode += Character.toString(hex[11]);
//				break;
//			case 'c':
//				bicode += Character.toString(hex[12]);
//				break;
//			case 'd':
//				bicode += Character.toString(hex[13]);
//				break;
//			case 'e':
//				bicode += Character.toString(hex[14]);
//				break;
//			case 'f':
//				bicode += Character.toString(hex[15]);
//				break;
//			default:
//			}
//		}
		changecoderule(bicode);
	}

	static void changecoderule(String binarycode) {
		String[] revcoderule = { "1123", "1222", "2212", "1141", "1132", "1321", "4111", "2131", "3121", "2113" };

		int cnt = 1;
		String times = "";
		for (int i = binarycode.length() - 1; i > 0; i--) {
			if (binarycode.charAt(i) == binarycode.charAt(i - 1)) {
				cnt++;
			} else {
				times += Integer.toString(cnt);
				cnt = 1;
			}
			if (times.length() == 4)
				break;
		}
		int gcd1 = getgcd(times.charAt(0) - '0', times.charAt(1) - '0');
		int gcd2 = getgcd(times.charAt(2) - '0', times.charAt(3) - '0');
		int gcd = getgcd(gcd1, gcd2);

		String resultcode;
		if (binarycode.length() < gcd * 56) {
			resultcode = appendzero(binarycode, gcd); //모자란 만큼 0을 append
		} else resultcode = binarycode;
		
		int count = 0;
		String[] temp = new String[8];
		String code = "";
		int index = 0;
		for(int i=resultcode.length()-1; i>=0; i--) {
			if (resultcode.charAt(i) == resultcode.charAt(i - 1)) {
				count++;
			} else {
				temp[index] = Integer.toString(count);
				count = 1;
				index++;
			}
			if(index == 8) break;
		}

		for(int i=temp.length-1; i>=0; i++) {
			for(int j=0; j<revcoderule.length; j++) {
				if(temp[i] == revcoderule[j]) {
					code += Integer.toString(j);
				}
			}
		}
		
		checkcode(code);
	}
	
	static void checkcode(String code) {
		int sumodd = 0; //홀수 합
		int sumeven = 0; //짝수 합
		for(int i=0; i<code.length()-1; i++) {
			
			if(i%2 == 0) {	//홀수 자리일 때
				sumodd += code.charAt(i)-'0';
			} else if(i%2 == 1) {	//짝수 자리일 때
				sumeven += code.charAt(i)-'0';
			}
		}
		int result = sumodd*3 + sumeven + (code.charAt(7)-'0');
		if(result%10 == 0) {
			ans += result;
		}
	}

	static int getgcd(int num1, int num2) {
		if (num1 % num2 == 0)
			return num2;
		return getgcd(num2, num1 % num2);
	}

	static String appendzero(String binarycode, int q) {
		String resultcode = "";
		for (int i = 0; i < q * 56 - binarycode.length(); i++) {
			resultcode += "0";
		}

		return resultcode += binarycode;
	}

}
