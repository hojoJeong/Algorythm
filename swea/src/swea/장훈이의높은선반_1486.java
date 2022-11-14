import java.util.*;
import java.io.*;

public class 장훈이의높은선반_1486 {
	static int[] people, arr;
	static boolean[] vis;
	static int ans, n, b;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			people = new int[n];
			ans = Integer.MAX_VALUE;
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				people[i] = Integer.parseInt(st.nextToken());
			}
			
			arr = new int[people.length];
			vis = new boolean[people.length];
			find(0);
			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	static void find(int index) {
		int sum = 0;
		for(int i=0; i<vis.length; i++) {
			if(vis[i]) {
				sum += people[i];
			}
		}
		if(sum >= b) {
			ans = Math.min(ans, sum-b);
			return;
		}
		if(index == people.length) {
			if(sum >= b) {
				ans = Math.min(ans, sum-b);
				return;
			} else {
				return;
			}
		}
		
		vis[index] = true;
		find(index+1);
		vis[index] = false;
		find(index+1);
	}

}
