package baekjoon;

import java.util.*;
import java.io.*;

public class 청소년상어_19236 {
	static int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[][] map;
	static ArrayList<Point> fish;
	static Point shark;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		map = new int[4][4]; // 상어가 있는 곳과 빈칸인지 물고기 있는 칸인지 구분
		fish = new ArrayList<>();
		ans = 0;
		shark = new Point(0, 0, 0, 0);

		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				map[i][j] = a;
				if (i == 0 && j == 0) {
					map[0][0] = -1;
					shark = new Point(0, 0, b-1, a);
				} else  {
					fish.add(new Point(i, j, b-1, a));
				}
			}
		}
		fish.sort(new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				// TODO Auto-generated method stub
				return o1.value - o2.value;
			}
		});

		moveShark(shark.value);
		
		System.out.println(ans);
	}

	static void moveShark(int result) {
		moveFish();
		Point sharkTemp = shark;
		int cnt = 2;
		int rr = shark.r + dy[sharkTemp.dir];
		int cc = shark.c + dx[sharkTemp.dir];
		while (true) {
			if(rr < 0 || rr >= 4 || cc < 0 || cc >= 4) {
				break;
			} else if(map[rr][cc] != 0){
				
				map[shark.r][shark.c] = 0;
				shark.value += map[rr][cc];
				shark.r = rr;
				shark.c = cc;
				int idx = 0;
				for(int i=0; i<fish.size(); i++) {
					if(rr == fish.get(i).r && cc == fish.get(i).c) {
						idx = i;
						break;
					}
				}
				shark.dir = fish.get(idx).dir;
				Point temp = fish.get(idx);
				fish.remove(idx);
				map[rr][cc] = -1;
				moveShark(sharkTemp.value + shark.value);
				
				map[rr][cc] = temp.value;
				fish.add(temp);
				fish.sort(new Comparator<Point>() {
					@Override
					public int compare(Point o1, Point o2) {
						// TODO Auto-generated method stub
						return o1.value - o2.value;
					}
				});
				shark.r = sharkTemp.r;
				shark.c = sharkTemp.c;
				shark.dir = sharkTemp.dir;
				shark.value = sharkTemp.value;
				map[shark.r][shark.c]= -1; 
				
			}
			rr = sharkTemp.r + dy[sharkTemp.dir]*cnt;
			cc = sharkTemp.c + dx[sharkTemp.dir]*cnt;
			cnt++;
		}
		
		ans = Math.max(ans, result);
	}

	static void moveFish() {
		ArrayList<Point> fishCopy = new ArrayList<>();
	
		for (int i = 0; i < fish.size(); i++) {
			int dir = fish.get(i).dir;
			for (int j = 0; j < 8; j++) {
				int rr = fish.get(i).r + dy[dir];
				int cc = fish.get(i).c + dx[dir];
				if (0 <= rr && rr < 4 && 0 <= cc && cc < 4 && map[rr][cc] != -1) {
					int temp = map[rr][cc];
					map[rr][cc] = map[fish.get(i).r][fish.get(i).c];
					map[fish.get(i).r][fish.get(i).c] = temp;
					
					int idx = 0;
					for(int k=0; k<fish.size(); k++) {
						if(fish.get(j).r == rr && fish.get(j).c == cc) {
							idx = j;
						}
					}
					
//					Point fishTemp = fish.get(i);
//					fish.get(i).r = fish.get(idx).r;
//					fish.get(i).c = fish.get(idx).c;
//					fish.get(i).dir = fish.get(idx).dir;
//					fish.get(i).value = fish.get(idx).value;
//					
//					fish.get(idx).r = fishTemp.r;
//					fish.get(idx).c = fishTemp.c;
//					fish.get(idx).dir = fishTemp.dir;
//					fish.get(idx).value = fishTemp.value;
					
					break;
				} else {
					dir++;
					if (dir >= 8)
						dir = 0;
				}
			}
		}
	}

	public static class Point {
		int r, c, dir, value;

		public Point(int r, int c, int dir, int value) {
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.value = value;
		}
	}
}
