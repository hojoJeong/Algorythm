package swea;

import java.util.*;
import java.io.*;

public class 원자소멸시뮬레이션_5648 {
	static int[] dx = { 0, 0, 1, 0, -1 };
	static int[] dy = { 0, -1, 0, 1, 0 };
	static Person A, B;
	static int[] dirA, dirB;
	static Charger[] charger;
	static int m, a, resultA, resultB, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			ans = 0;
			dirA = new int[m + 1];
			dirB = new int[m + 1];
			charger = new Charger[a + 1];

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= m; i++) {
				dirA[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= m; i++) {
				dirB[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 1; i <= a; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int range = Integer.parseInt(st.nextToken());
				int power = Integer.parseInt(st.nextToken());
				charger[i] = new Charger(r, c, range, power);
			}
			A = new Person(1, 1);
			B = new Person(10, 10);

			for(int i=0; i<=m; i++) {
				A.r = A.r + dx[dirA[i]];
				A.c = A.c + dy[dirA[i]];
				B.r = B.r + dx[dirB[i]];
				B.c = B.c + dy[dirB[i]];
				int max = 0;
				Charger tempa = new Charger(-1, -1, -1, -1);
				Charger tempb = new Charger(-1, -1, -1, -1);
				for(int j=1; j<=a; j++) {
					int sum = 0;
					//범위 되는 것들 넣어줌
					if(Math.abs(A.r - charger[j].r)+Math.abs(A.c - charger[j].c) < charger[j].range) {
						tempa = charger[j];
					}
					if(Math.abs(B.r - charger[j].r)+Math.abs(B.c - charger[j].c) < charger[j].range) {
						tempb = charger[j];
					}
					//둘 다 해당 없을 때
					if(tempa.power == -1 && tempb.power == -1) {
						continue;
					}
					//둘 다 충전 가능할 때
					if(tempa.power != -1 && tempb.power != -1) {
						//같은 충전기 쓸 때
						if(tempa.r == tempb.r && tempa.c == tempb.c) {
							sum += tempa.power;
						} else {	//다른 충전기 쓸 때
							sum += tempa.power + tempb.power;
						}
					}
					//둘 중 하나만 쓸 수 있을 때
					if(tempa.power == -1 || tempb.power == -1) {
						if(tempa.power != -1) {
							sum += tempa.power;
						}
						if(tempb.power != -1) {
							sum += tempb.power;
						}
					}
					max = Math.max(max, sum);
				}
				ans += max;
			}
			
			
			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	public static class Charger {
		int r, c, range, power;

		public Charger(int r, int c, int range, int power) {
			this.r = r;
			this.c = c;
			this.range = range;
			this.power = power;

		}
	}

	public static class Person {
		int r, c;

		public Person(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}