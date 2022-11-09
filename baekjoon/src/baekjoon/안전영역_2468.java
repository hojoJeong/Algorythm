package baekjoon;

import java.util.*;
import java.io.*;

public class 안전영역_2468 {
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	static int[][] arr;
	static boolean[][] vis;
	static int min, max, safezon, cnt, n;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		
		arr = new int[n][n];
		safezon = 0;
		cnt = 0;
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		
		//배열 입력, 최소 최대 강수량 지정
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(min > arr[i][j]) min = arr[i][j];
				if(max < arr[i][j]) max = arr[i][j];
			}
		}
		for(int r=min-1; r<=max; r++) {
			vis = new boolean[n][n];
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(arr[i][j] > r && vis[i][j] == false) {
						bfs(i,j, r);
						cnt++;
					}
				}
			}
			safezon = Math.max(safezon, cnt);
			cnt = 0;
		}
		System.out.println(safezon);
	}
	
	static void bfs(int r, int c, int rain) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(r,c));
		vis[r][c] = true;
		while(!q.isEmpty()) {
			Point temp = q.poll();
			for(int i=0; i<4; i++) {
				int rr = temp.r + dy[i];
				int cc = temp.c + dx[i];
				if(rr>=0 && cc>=0 && rr<n && cc<n) {
					if(vis[rr][cc] == false && arr[rr][cc] > rain) {
						q.add(new Point(rr, cc));
						vis[rr][cc] = true;
					}
				}
			}
		}
	}
	
	public static class Point{
		int r;
		int c;
		public Point(int r, int c) {  //void 아님 주의!!
			this.r = r;
			this.c = c;
		}
	}
}