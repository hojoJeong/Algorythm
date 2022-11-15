import java.util.*;
import java.io.*;

public class 보호필름_2112 {
	static int[][] film, copy;
	static int ans, D, W, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());

			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			film = new int[D][W];
			copy = new int[D][W];
			ans = Integer.MAX_VALUE;

			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			if(K == 1) {
				ans = 0;
			} else {
				copyArray();
				getSituation(0, 0);
			}
			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	static void copyArray() {
		for (int i = 0; i < D; i++) {
			for (int j = 0; j < W; j++) {
				copy[i][j] = film[i][j];
			}
		}
	}

	static void getSituation(int index, int cnt) {
		if (index == D) {
			check(cnt);
			return;
		}

		// 약품 주입 안하는 경우
		getSituation(index + 1, cnt);

		// A약품 주입하는 경우
		injection(index, 0);
		getSituation(index + 1, cnt+1);

		// B약품 주입하는 경우
		injection(index, 1);
		getSituation(index + 1, cnt+1);
		
		rollBack(index);
	}

	static void injection(int r, int chemi) {
		for (int i = 0; i < W; i++) {
			copy[r][i] = chemi;
		}
		
	}
	
	static void rollBack(int r) {
		for (int i = 0; i < W; i++) {
			copy[r][i] = film[r][i];
		}
	}

	static void check(int cnt) {
		int test = 0;
		for (int j = 0; j < W; j++) {
			int count = 1;
			for (int i = 0; i < D - 1; i++) {
				if(copy[i][j] == copy[i+1][j]) {
					count++;
				} else { 
					count = 1;
				}
				if(count >= K) {
					test++;
					break;
				}
			}
			if(test != j+1) return; //한 번이라도 조건에 부합하지 않으면 바로 종료
		}
		
		if(test == W) {
			ans = Math.min(ans, cnt);
		} 
	}
}
