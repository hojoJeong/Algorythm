package baekjoon;

import java.util.*;
import java.io.*;
public class Âü¿Ü¹ç_2477 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int k = Integer.parseInt(br.readLine());
		int rmax = Integer.MIN_VALUE;
		int rmin = Integer.MAX_VALUE;
		int cmax = Integer.MIN_VALUE;
		int cmin = Integer.MAX_VALUE;
		int ans = 0;
		
		for(int i=0; i<6; i++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken());
			int len = Integer.parseInt(st.nextToken());
			if(dir == 2 || dir == 1) {
				if(rmax < len) rmax = len;
				if(rmin > len) rmin = len;
			}
			if(dir == 3 || dir == 4) {
				if(cmax < len) cmax = len;
				if(cmin > len) cmin = len;
			}
		}
		
		ans = ((rmax * cmax) - (rmin * cmin))*k;
		System.out.println(rmax);
		System.out.println(rmin);
		System.out.println(cmax);
		System.out.println(cmin);

		System.out.println(ans);
	}
}
