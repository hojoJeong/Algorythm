package swea;

import java.util.*;
import java.io.*;

public class shuffle_A형모의테스트 {

	static int[] arr, deck;
	static int[] x, lprio, rprio; // x는 중복순열로 뽑아줌, 왼쪽 우선순위는 x[]에 따라 뽑아줌, 오른쪽 우선순위는 그냥 N/2 ~ N-1까지 넣어줌
	static int N, ans, cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {

			N = Integer.parseInt(br.readLine());

			arr = new int[N];
			deck = new int[N];
			lprio = new int[N / 2];
			rprio = new int[N / 2];
			ans = Integer.MAX_VALUE;

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			for (int i = 0; i < N; i++) {
				deck[i] = arr[i];
			}
			
			x = new int[5];
			makex(0, N, 5);
			System.out.printf("#%d %d\n", tc, ans);
		}
	}
	
	static void makex(int index, int n, int m) {
		if (index == m) {
			makeprio(0);
			return;
		}
		for (int i = 0; i < n; i++) {
			x[index] = i;
			makex(index + 1, n, m);

		}
	}

	static void makeprio(int index) {
		if (index == 5) {
			ans = -1;
			return;
		}
		
		// 오름차순인지 확인
		int check = 0;
		for (int i = 0; i < N - 1; i++) {
			if (deck[i] < deck[i + 1])
				check++;
		}
		if (check == N - 1) {
			ans = Math.min(ans, index);
			return;
		}

		// 내림차순인지 확인
		check = 0;
		for (int i = 0; i < N - 1; i++) {
			if (deck[i] > deck[i + 1])
				check++;
		}
		if (check == N - 1) {
			ans = Math.min(ans, index);
			return;
		}
		
		
		for (int i = 0; i < N / 2; i++) {
			rprio[i] = i + N / 2;
		}
		for (int i = 0; i < N / 2; i++) {
			lprio[i] = x[index] + i;
		}
		shuffle(index);

	}

	static void shuffle(int index) {

		if(lprio[0] < N/2) {
			for(int i=0; i<rprio[0] - lprio[0]; i++) {
				deck[i] = arr[i];
			}
			for(int i=0; i<x[index]; i++) {
				deck[rprio[i]] = arr[rprio[i]];
				deck[rprio[i] - x[index]] = arr[rprio[i] - x[index]];
			}
			for(int i=x[index]; i<N/2; i++) {
				deck[rprio[i]] = arr[rprio[i]];
			}
		}
		if(lprio[0]>=N/2) {
			for(int i=0; i<lprio[0] - rprio[0]; i++) {
				deck[rprio[i]] = arr[rprio[i]];
			}
			for(int i=0; i<x[index]; i++) {
				deck[rprio[i]] = arr[rprio[i]];
				deck[rprio[i] - x[index]] = arr[rprio[i] - x[index]];
			}
			for(int i=rprio[N/2-1]-x[index]+1; i<N/2; i++) {
				deck[i] = arr[i];
			}
		}
//		for (int i = 0; i < N / 2; i++) {
//			if (lprio[i] < rprio[i]) {
//				deck[i] = arr[i];
//			} else if (lprio[i] >= rprio[i]) {
//				deck[i] = arr[i + N / 2];
//			}
//		}
		for (int i = 0; i < N; i++) {
			arr[i] = deck[i];
		}
		makeprio(index + 1);
	}
}