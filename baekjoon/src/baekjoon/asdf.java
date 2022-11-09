package baekjoon;

import java.util.*;

public class asdf {
	public static void main(String[] args) {
		int[] ingredient = new int[9];
		int answer = 0;
		int cnt = 0;
		ArrayList<Integer> ham = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < 9; i++) {
			ingredient[i] = sc.nextInt();
		}
		for (int i = 0; i < ingredient.length; i++) {
			ham.add(ingredient[i]);
		}
		
		for (int k = 0; k < ham.size(); k++) {
			System.out.printf(ham.get(k) + " ");
		}
		System.out.println();
		
		while (ham.size() >= 4) {
			for (int i = 0; i < ham.size() - 3; i++) {
				if (ham.get(i) == 1 && ham.get(i + 1) == 2 && ham.get(i + 2) == 3 && ham.get(i + 3) == 1) {

					for(int j=0; j<4; j++) {
						ham.remove(i);
					}

				}
			}
			for (int k = 0; k < ham.size(); k++) {
				System.out.printf(ham.get(k) + " ");
			}
			System.out.println();
			if (answer == cnt)
				break;
			cnt = answer;
		}
		System.out.println(answer);

	}
}
