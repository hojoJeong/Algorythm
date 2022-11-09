package swea;

import java.util.Arrays;
import java.util.Scanner;

public class flatten {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
			
		for(int tc = 1; tc<=10; tc++) {
			int cnt = sc.nextInt();
			int[] arr = new int[100];
			for(int i=0; i<100; i++) {
				arr[i] = sc.nextInt();
			}
				
			int max = 0;
			int min = 101;
			for(int i=0; i<cnt; i++) {
				Arrays.sort(arr);
				arr[0]++;
				arr[arr.length-1]--;
			}
			Arrays.sort(arr);

			System.out.printf("#%d %d\n", tc, arr[arr.length-1] - arr[0]);
		}
	}
}
