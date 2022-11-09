package baekjoon;

import java.util.*;
import java.io.*;

public class ������_14502 {
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int[][] arr, copy;
	static boolean[][] vis;
	static int n, m, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new int[n][m];
		ans = Integer.MIN_VALUE;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0);
		System.out.println(ans);
	}

	//�� ���� �ڸ� 3�� ����
	static void dfs(int index) {
		if (index == 3) {
			bfs();
			return;
		}
		for (int i = 0; i < n; i++) {  //������� ���µ� ���� vis�� �ʿ� ����
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == 0) {
					arr[i][j] = 1;
					dfs(index + 1);
					arr[i][j] = 0;

				}
			}
		}
	}
	
	//�� ���� ���� ���̷��� ����
	static void bfs() {
		vis = new boolean[n][m];
		copy = new int[n][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				copy[i][j] = arr[i][j];
			}
		}
		Queue<Point> q = new LinkedList<>();
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(copy[i][j] == 2) {
					q.add(new Point(i, j));
					vis[i][j] = true;
				}
			}
		}
		while(!q.isEmpty()) {
			Point temp = q.poll();
			for(int i=0; i<4; i++) {
				int rr = temp.r + dy[i];
				int cc = temp.c + dx[i];
				
				if(0<=rr && rr<n && 0<=cc & cc<m) {
					if(copy[rr][cc] == 0 && vis[rr][cc] == false) {
						
						q.add(new Point(rr, cc));
						copy[rr][cc] = 2;
						vis[rr][cc] = true;
					}
				}
			}
		}
		check();
	}
	
	//�ִ밪 ��
	static void check() {
		int cnt = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(copy[i][j] == 0) cnt++;  //���� �����ؾ��� �迭 �� Ȯ���ϱ�
			}
		}
		
		ans = Math.max(ans, cnt);
	}
	
	public static class Point{
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
