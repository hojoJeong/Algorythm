package swea;

import java.util.*;
import java.io.*;
public class swea_1217_°ÅµìÁ¦°ö {

	static int ans;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int tc=1; tc<=10; tc++) {
			br.readLine();
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int mob = Integer.parseInt(st.nextToken());
			ans = 1;
			mob(num, mob);
			System.out.printf("#%d %d\n", tc, ans);
		}
	}
	
	static void mob(int num, int mob) {
		if(mob == 0) return;
		ans = ans * num;
		mob(num, mob-1);
	}
}
