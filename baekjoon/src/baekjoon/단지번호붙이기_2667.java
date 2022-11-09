package baekjoon;

import java.util.*;
import java.io.*;

public class ������ȣ���̱�_2667 {
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int[][] arr;
	static boolean[][] vis;
	static int[] home;
	static int n, cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		cnt = 0;
		vis = new boolean[n][n];
		arr = new int[n][n];
		home = new int[n*n];
		
		//�迭 �Է�
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < n; j++) {
				arr[i][j] = str.charAt(j) - '0';
			}
		}

		//Ž���ϸ鼭 arr[i][j]==1�̸� dfs ȣ��, �ƴϸ� vis[i][j] == false�� �� �ٽ� ȣ��
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(arr[i][j] == 1 && vis[i][j] == false) {
					dfs(i, j);
					cnt++;
				}
			}
		}
		
	
		Arrays.sort(home);
		System.out.println(cnt);
		for(int i=0; i<home.length; i++) {
			if(home[i] == 0) continue;
			System.out.println(home[i]);
		}
		
	}
	
	//dfs�� only ���� ĭ�� 1���� Ȯ���ϰ� 1�̸� ��� ���ؼ� home[cnt]++ ���ְ� vis[][] = true ������ִ� ����
	//�޼ҵ帶�� ������ ��Ȯ�ϰ� �����ϰ� �����ִ� ���� �߿���
	static void dfs(int r, int c) {
		vis[r][c] = true;
		home[cnt]++;
		for(int i=0; i<4; i++) {
			int rr = r+dy[i];
			int cc = c+dx[i];
			if(rr>=0 && cc>=0 && rr<n && cc<n) {
				if(arr[rr][cc] == 1 && vis[rr][cc] != true) {
					arr[rr][cc] = cnt;
					dfs(rr, cc);
				}
			}
		}
	}
}
