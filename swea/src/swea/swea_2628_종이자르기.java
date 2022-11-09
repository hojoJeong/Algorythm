package swea;

import java.util.*;

public class swea_2628_종이자르기 {
	
	static int n, m, cnt;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		m = sc.nextInt();
		n = sc.nextInt();
		cnt = sc.nextInt();
		ArrayList<Integer> row = new ArrayList<>();
		ArrayList<Integer> col = new ArrayList<>();
		
		
		for(int i=0; i<cnt; i++) {
			int rc = sc.nextInt();
			if(rc == 0) {
				int cut = sc.nextInt();
				row.add(cut);
			} else if(rc == 1) {
				int cut = sc.nextInt();
				col.add(cut);
			}
		}
		
		
		int maxrow = n;
		if(row.size() >0 ) {
			Collections.sort(row);
			maxrow = row.get(0);
			for(int i=0; i<row.size()-1; i++) {
				maxrow = Math.max(maxrow, row.get(i+1) - row.get(i));
			}
			int endr = n - row.get(row.size()-1);
			maxrow = Math.max(maxrow, endr);
			
		}
		
		int maxcol = m;
		if(col.size() > 0) {
			Collections.sort(col);
			maxcol = col.get(0);
			if(col.size() > 1) {
				for(int i=0; i<col.size()-1; i++) {
					maxcol = Math.max(maxcol, col.get(i+1) - col.get(i));
				}
			}
			int endc = m - col.get(col.size()-1);
			maxcol = Math.max(maxcol, endc);
		}
		
		
		System.out.println(maxrow * maxcol);
	}
	
}
