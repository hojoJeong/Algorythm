package swea;

import java.util.*;
import java.io.*;

public class 보호필름_2112_모의SW테스트 {
	static int[][] film;
	static int[][] copy;
	static int[] che;
	static boolean[] vis;
	static int D, W, K, ans;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<= t; tc++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			ans = Integer.MAX_VALUE;
			film = new int[D][W];
			for(int i=0; i<D; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			che = new int[D];
			vis = new boolean[D];
			dfs(0, 3, D);
			if(ans == Integer.MAX_VALUE) ans = 1;
			System.out.printf("#%d %d\n", tc, ans);
		}
	}
	
	static void dfs(int index, int n, int m) {
		if(index == m) {
//			for(int i=0; i<m; i++) {
//				System.out.print(che[i]);
//			}
//			System.out.println();
			checkfilm();
			return;
		}
		for(int i=0; i<n; i++) {
			che[index] = i;
			dfs(index+1, n, m);
		}
	}
	
	static void checkfilm() {
		int min = 1;
		int chemical = 1;
		//원본 배열 훼손 방지를 위해 배열 복사
		copy = new int[D][W];
		for(int i=0; i<D; i++) {
			for(int j=0; j<W; j++) {
				copy[i][j] = film[i][j];
			}
		}
		// 약품을 넣어줌
		for(int i=0; i<D; i++) {
			if(che[i] == 0) continue;
			if(che[i] == 1) {
				chemical++;
				for(int j=0; j<W; j++) {
					copy[i][j] = 0;
				}
			}
			if(che[i] == 2) {
				chemical++;
				for(int j=0; j<W; j++) {
					copy[i][j] = 1;
				}
			}
		}

		// K만큼 연속하는지 check
		int result = 1;
		int cnt = 0;
		for(int i=0; i<W; i++) {
			cnt = 1;
			for(int j=0; j<D-1; j++) {
				if(cnt == K) break;
				if(copy[j][i] == copy[j+1][i]) {
					cnt++;
				} else {
					cnt = 1;
				}
			}
			result += cnt;
		}
		if(result == K*W) {
			min = chemical;
		} 
		ans = Math.min(min, ans);
	}
}
