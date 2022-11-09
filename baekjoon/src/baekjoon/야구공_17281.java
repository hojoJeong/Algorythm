package baekjoon;

import java.util.*;
import java.io.*;
public class ¾ß±¸°ø_17281 {

	static int[][] arr, player;
	static boolean[][] vis;
	static int[] base;
	static int inning, inningcnt, out, max;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		inning = Integer.parseInt(br.readLine());
		
		arr = new int[inning][9];
		player = new int[inning][9];
		vis = new boolean[inning][10];
		base = new int[3];
		inningcnt = 1;
		out = 0;
		max = 0;
		
		for(int i=0; i<inning; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<9; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0; i<inning; i++) {
			int temp = arr[i][0];
			arr[i][0] = arr[i][3];
			arr[i][3] = temp;
			vis[i][3] = true;
		}
		dfs(0, 0);
		System.out.println(max);
		
	}
	static void dfs(int inningindex, int index) {
		if(inningindex == inning-1 && index == 8) {
			baseball(player, base, 0, 1, 0, 0);
			return;
		}
		for(int i = 0; i<9; i++) {
			if(vis[inningindex][i] || i== 3) continue;
			player[inningindex][index] = arr[inningindex][i];
			vis[inningindex][i] = true;
			dfs(inningindex, index+1);
			if(i != 3) vis[inningindex][i] = false;
		}
		
	}
	
	static void baseball(int[][] player, int[] base, int playerindex, int inningcnt, int score, int out) {
		if(inningcnt > inning) {
			if(max < score) max = score;
			return;
		}
		int curbase = 0;
		for(int i=0; i<3; i++) {
			if(base[i]==1) curbase++;
		}
		
		switch(player[inningcnt][playerindex]) {
		case 0:{
			out++;
			break;
		}
		case 1:{
			if(curbase == 0) {
				base[0]++;
			}else if(curbase == 1) {
				if(base[0] == 1) base[1]++;
				else base[0] =1;
			}else if(curbase == 2) {
				if(base[0] == 1 && base[1] == 1) base[2]++;
				else if(base[0] == 1 && base[2] == 1) base[1]++;
				else base[0]++;
			}
			break;
		}
		case 2:{
			if(curbase == 0) base[1]++;
			else if(curbase ==1) {
				if(base[0] == 1) base[2]++;
				else base[1]++;
			} else if(curbase == 2) {
				if(base[0] == 1 && base[1] == 1) {
					score++;
					base[0] = 0;
					base[2]++;
				}else if(base[0] == 1 && base[2] == 1) {
					score++;
					base[1]++;
					base[0] = 0;
				}else base[1] ++;
			}
			break;
		}
		case 3:{
			score = score + curbase;
			base[0] = 0;
			base[1] = 0;
			base[2] = 1;
			break;
		} case 4:{
			score = score + curbase;
			break;
		}
		}
		if(out == 3) {
			out = 0;
			inningcnt ++;
		}
		if(playerindex == 9) {
			inningcnt++;
			playerindex = 0;
		}
		baseball(player, base, playerindex, inningcnt, score, out);
	}
}
