package swea;

import java.util.*;
import java.io.*;
public class swea_1267_작업순서 {
	
	static int[] arr;
	static boolean[] vis;
	static ArrayList<ArrayList> list;
	static int v, e, cnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int tc=1; tc<=10; tc++) {
			st = new StringTokenizer(br.readLine());
			v = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			list = new ArrayList<>();
			
			for(int i=0; i<=v; i++) {
				list.add(new ArrayList<>());
			}
			
			st = new StringTokenizer(br.readLine());
			
			for(int i=0; i<e; i++) {
				int s = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				list.get(s).add(c);
			}
			
			for(int i=1; i<=v; i++) {
				cnt = 1;
				arr = new int[v+1];
				vis = new boolean[v+1];
				arr[1] = i;
				vis[i] = true;
				dfs(i, 2);
				if(cnt == v) {
					System.out.println(cnt);
					break;
				}
			}
			System.out.printf("#%d ", tc);
			for(int i=1; i<=v; i++) {
				System.out.printf("%d ", arr[i]);
			}
			System.out.println();
		}
	}
	
	static void dfs(int startnode, int index) {
		if(list.get(startnode).size() == 0) return;
		for(int i=0; i<list.get(startnode).size(); i++) {
			arr[index] = (int) list.get(startnode).get(i);
			vis[(int) list.get(startnode).get(i)] = true;
			cnt++;
			index++;
			dfs((int) list.get(startnode).get(i), index);
		}
	}
}
