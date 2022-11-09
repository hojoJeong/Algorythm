package swea;

import java.util.*;
public class swea_2669_직사각형넓이 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] board = new int[101][101];
		int maxX = 0;
		int minX = Integer.MAX_VALUE;
		int maxY = 0;
		int minY = Integer.MAX_VALUE;
		
		int cnt = 0;
		for(int i=0; i<4; i++) {
			int startX = sc.nextInt();
			int startY = sc.nextInt();
			int endX = sc.nextInt();
			int endY = sc.nextInt();
			
			if(minX > startX) minX = startX;
			if(minY > startY) minY = startY;
			if(maxX < endX) maxX = endX;
			if(maxY < endY) maxY = endY;
			
			
			for(int j=startY; j<endY; j++) {
				for(int k=startX; k<endX; k++) {
					board[j][k] = 1;
				}
			}
		}
		for(int i=minY; i<=maxY; i++) {
			for(int j=minX; j<=maxX; j++) {
				if(board[i][j] == 1) cnt++;
			}
		}
		
		System.out.println(cnt);
	}
}
