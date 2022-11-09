package swea;

import java.io.*;
import java.util.*;

public class �ٱ⼼�����_5653_3t {
	static int n, m, k;
	static int[][] graph;
	static int[][] visited;
	static final int MAX = 350;
	static final int DEFAULT = 150;
	static final int INF = -999;
	static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static Queue<Node> q;
	static int t;
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (t = 1; t <= T; t++) {
			// input
			graph = new int[MAX][MAX];
			visited = new int[MAX][MAX];
			q = new LinkedList<Node>();
			StringTokenizer tk = new StringTokenizer(br.readLine());
			n = Integer.parseInt(tk.nextToken());
			m = Integer.parseInt(tk.nextToken());
			k = Integer.parseInt(tk.nextToken());

			for (int i = 0; i < n; i++) {
				tk = new StringTokenizer(br.readLine());
				for (int j = 0; j < m; j++) {
					int num = Integer.parseInt(tk.nextToken());
					if (num > 0)
						q.add(new Node(DEFAULT + i, DEFAULT + j));

					visited[DEFAULT + i][DEFAULT + j] = num;
					graph[DEFAULT + i][DEFAULT + j] = num;
				}
			}

			// solve
			bfs();

			// output
			bw.write("#" + t + " " + answer + '\n');
		}
		bw.close();
	}

	static void bfs() {
		int time = 0;

		while (!q.isEmpty()) {
			if (time++ == k) {
				return;
			}
			int size = q.size();
			// �� ��
			int[][] temp = new int[MAX][MAX];
			for (int i = 0; i < size; i++) {
				Node curNode = q.poll();
				int cr = curNode.r;
				int cc = curNode.c;

				visited[cr][cc]--;

				// ����
				if (visited[cr][cc] == -1) {
					for (int d = 0; d < 4; d++) {
						int nr = cr + dir[d][0];
						int nc = cc + dir[d][1];
						// ���� ĭ�� �̹� ���� ������ ��ŵ or ����
						if (graph[nr][nc] > 0) {
							// ���� ���ÿ� ���� ��� ��
							if (temp[nr][nc] > 0 && graph[nr][nc] == visited[nr][nc]) {

								if (graph[nr][nc] < graph[cr][cc]) {
									graph[nr][nc] = graph[cr][cc];
									visited[nr][nc] = graph[cr][cc];
									temp[nr][nc] = graph[cr][cc];
								}
							}
						} else {
							if (graph[nr][nc] == INF)
								continue;
							if (graph[nr][nc] == 0) {
								graph[nr][nc] = graph[cr][cc];
								visited[nr][nc] = graph[cr][cc];
								temp[nr][nc] = graph[cr][cc];
							}
						}
					}
				}
				// die
				if (visited[cr][cc] == -graph[cr][cc]) {
					graph[cr][cc] = INF;
				}
				temp[cr][cc] = graph[cr][cc];
			}
			q.clear();
			answer = 0;
			for (int i = 0; i < MAX; i++) {
				for (int j = 0; j < MAX; j++) {
					if (temp[i][j] > 0) {
						answer++;
						q.add(new Node(i, j));
					}
				}
			}
		}

	}

	public static class Node {
		public int r, c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}

}
