import java.util.*;
import java.io.*;

public class 미생물격리_2382 {
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static ArrayList<Microbe> microbe = new ArrayList<>();
	static int ans, N, M, K;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			ans = 0;

			microbe = new ArrayList<>();
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int value = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken()) - 1;
				microbe.add(new Microbe(r, c, value, dir));
			}
			
			for (int i = 0; i < M; i++) {	//M시간 동안 이동
				move();
			}

			for (int i = 0; i < microbe.size(); i++) {
				ans += microbe.get(i).value;
			}

			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	static void move() {
		//좌표 이동시켜주고 이동한 좌표가 가장자리이면 방향전환과 value값을 절반 줄여줌
		for (int i = 0; i < microbe.size(); i++) {
			int dir = microbe.get(i).dir;
			int rr = microbe.get(i).r + dy[dir];
			int cc = microbe.get(i).c + dx[dir];

			microbe.get(i).r = rr;
			microbe.get(i).c = cc;

			if (rr == 0 || rr == N - 1 || cc == 0 || cc == N - 1) {
				microbe.get(i).value = microbe.get(i).value / 2;
				changeDir(i, dir);
				if (microbe.get(i).value == 0) {
					microbe.remove(i);
					i--;
				}
			}
		}

		//한 셀에 모인 군집들 중 value가 가장 큰 군집의 방향을 따라야하므로 value에 대해 내림차순으로 정렬
		microbe.sort(new Comparator<Microbe>() {

			@Override
			public int compare(Microbe o1, Microbe o2) {
				// TODO Auto-generated method stub
				return o2.value - o1.value;
			}
		});

		//value에 대해 내림차순으로 정렬돼있으므로 앞에서부터 검사했을 때 같은 좌표인 미생물이 있으면 value만 더해줌
		for (int i = 0; i < microbe.size() - 1; i++) {
			for(int j=i+1; j<microbe.size(); j++) {
				if (microbe.get(i).r == microbe.get(j).r && microbe.get(i).c == microbe.get(j).c) {
					microbe.get(i).value += microbe.get(j).value;
					microbe.remove(j);
					j--;
				}
			}
		}
		
		
	}

	static void changeDir(int index, int dir) {
		if (dir == 0) {
			dir = 1;
		} else if (dir == 1) {
			dir = 0;
		} else if (dir == 2) {
			dir = 3;
		} else if (dir == 3) {
			dir = 2;
		}
		microbe.get(index).dir = dir;
	}

	public static class Microbe {
		int r, c, value, dir;

		public Microbe(int r, int c, int value, int dir) {
			this.r = r;
			this.c = c;
			this.value = value;
			this.dir = dir;
		}
	}
}
