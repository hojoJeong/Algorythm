package swea;

import java.util.*;
import java.io.*;

public class swea_1861_정사각형방 {

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[][] room;
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=t;tc++) {
			int n = Integer.parseInt(br.readLine());
			room = new int[n][n];
			
			for(int i=0;i<n;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<n;j++) {
					room[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[] c = new int[n*n+1];
			int max = 0;
			int start_room = n*n; 
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					cnt = 1;
					dfs(room, i, j, n, room[i][j]);
					c[room[i][j]] = cnt;
					if(cnt>=max) {
						if (cnt>max) {
							start_room = room[i][j];
							max = cnt;
						}
						else {
							if(start_room>room[i][j]) {
								start_room = room[i][j];
							}
						}
						
					}
				}
			}
			sb.append("#"+tc).append(" ").append(start_room+" ").append(max).append("\n");
		}
		System.out.println(sb);
	}
	public static void dfs(int[][] r, int x, int y, int N, int now_n) {
		for(int i=0;i<4;i++) {
			int nowx = x+dx[i];
			int nowy = y+dy[i];
			if((0<=nowx&&nowx<N)&&(0<=nowy&&nowy<N)&&(r[nowx][nowy]==now_n+1)) {
				cnt++;
				dfs(r, nowx, nowy, N, now_n+1);
			}
		}
	}
}
