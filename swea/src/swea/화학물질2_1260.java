package swea;

import java.util.*;
import java.io.*;

public class 화학물질2_1260 {
	static int n, cnt, ans;
	static int[][] arr;
	static boolean[][] vis;
	static boolean[] pvis, pmvis;
	static int[] order, morder;
	static ArrayList<Matrix> matrix;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			n = Integer.parseInt(br.readLine());
			ans = 0;
			cnt = 0;
			arr = new int[n][n];
			vis = new boolean[n][n];
			matrix = new ArrayList<>();

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < n; i++) {
				int index = -1;
				int cnt = 0;
				for (int j = 0; j < n; j++) {
					if (arr[i][j] != 0 && vis[i][j] == false) {
						getMatrix(i, j);
					}
				}
			}
			
			morder = new int[matrix.size()];
			pmvis = new boolean[matrix.size()];
			getMatrixOrder(0, matrix.size(), matrix.size());
			
			System.out.printf("matrix :");
			for(int i=0; i<matrix.size(); i++) {
				System.out.printf(" "+matrix.get(i).r+","+matrix.get(i).c);
			}
			System.out.println();
			
			order = new int[matrix.size() - 1];
			pvis = new boolean[matrix.size() - 1];
			getResult(0, matrix.size() - 1);
			
			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	static void getMatrix(int r, int c) {
		int rcnt = 0;
		int ccnt = 0;

		for (int i = r; i < n; i++) {
			if (arr[i][c] == 0)
				break;
			rcnt++;
			ccnt = 0;
			for (int j = c; j < n; j++) {
				if (arr[i][j] != 0) {
					ccnt++;
					vis[i][j] = true;
				} else if (arr[i][j] == 0) {
					break;
				}
			}
		}

		matrix.add(new Matrix(rcnt, ccnt));
	}
	
	static void getMatrixOrder(int index, int n, int m) {
		if(index == m) {
			int count = 0;
			for(int i=0; i<morder.length-1; i++) {
				if(matrix.get(morder[i]).c == matrix.get(morder[i+1]).r) {
					count++;
				}
			}
			if(count == matrix.size()-1) {
				
				System.out.printf("morder : ");
				for(int i=0; i<morder.length; i++) System.out.printf(" "+morder[i]);
				System.out.println();
				
				for(int i=0; i<morder.length-1; i++) {
					for(int j=i+1; j<morder.length; j++) {
						if(morder[i] >= morder[j]) {
							Matrix temp = matrix.get(morder[i]);
							matrix.set(morder[i], matrix.get(morder[j]));
							matrix.set(morder[j], temp);
						}
					}
				}
			}
			return;
		}
		for(int i=0; i<n; i++) {
			if(!pmvis[i]) {
				morder[index] = i;
				pmvis[i] = true;
				getMatrixOrder(index+1, n, m);
				pmvis[i] = false;
			}
		}
	}

	static void getResult(int index, int n) {
		if (n == 0) {
			cal();
			return;
		}
		for (int i = 0; i < n; i++) {
			order[index] = i;
			getResult(index + 1, n - 1);
		}
	}

	static void cal() {
		if (order.length == 1) {
//			System.out.println("check");
			cnt = matrix.get(order[0]).c * matrix.get(order[0]+1).c * matrix.get(order[0]).r;
			ans = Math.max(ans, cnt);
		} else {
			for (int i = 0; i < order.length; i++) {
				if(matrix.size() == 1) break;
				if(order[i] < matrix.size()) {
					System.out.println("order length : "+order.length);
					System.out.println("order : "+order[i]);
					System.out.println("matrix size : "+matrix.size());
					int Ar = matrix.get(order[i]).r;
					int Ac = matrix.get(order[i]).c;
					int Bc = matrix.get(order[i]+1).c;
//					System.out.println("Ac : "+Ac+" Bc : "+Bc+" Ar : "+Ar);
					cnt +=  Ac* Bc * Ar;
//					System.out.println("cnt : "+cnt);
					matrix.set(order[i], new Matrix(Ar, Bc));
					matrix.remove(order[i]+1);
				}
			}
			ans = Math.max(ans, cnt);
		}
			
	}

	public static class Matrix {
		int r, c;

		public Matrix(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
