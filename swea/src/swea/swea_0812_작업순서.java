package swea;

import java.util.ArrayList;
import java.util.Scanner;

public class swea_0812_작업순서 {

	static ArrayList<Integer>[] list;
	static boolean[] vis;
	static int ans;
	
	static void dfs(int index) {
		if(vis[index]) {
			return;
		}
		vis[index] = true;
		System.out.printf("%d ", list[index]);
		dfs(index+1);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int tc = 1; tc <= 10; tc++) {
			int v = sc.nextInt();
			int e = sc.nextInt();
			list = new ArrayList[v];
			vis = new boolean[v];
			while(!sc.hasNext()){
				int start = sc.nextInt();
				int end = sc.nextInt();
				if (list[start] == null) list[start] = new ArrayList<>();
				list[start].add(end);
			}
			
			System.out.printf("#%d ", tc);
			dfs(0);
			System.out.println();
		}
	}
}