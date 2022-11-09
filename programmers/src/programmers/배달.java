package programmers;

import java.util.*;

public class ��� {
	static int N = 5;
	static int[][] road = { { 1, 2, 1 }, { 2, 3, 3 }, { 5, 2, 2 }, { 1, 4, 2 }, { 5, 3, 1 }, { 5, 4, 2 } };
	static int K = 3;
	static ArrayList<ArrayList<Node>> nodeList;

	public static void main(String[] args) {

		��� m = new ���();
		m.solution(N, road, K);
	}

	void solution(int N, int[][] road, int K) {
		int answer = 0;

		this.N = N;
		this.road = road;
		this.K = K;

		nodeList = new ArrayList<>();

		for (int i = 0; i <= N; i++) {
			nodeList.add(new ArrayList<>());
		}

		int len = road.length;
		for (int i = 0; i < len; i++) {
			int idx = road[i][0];
			int nodeNum = road[i][1];
			int weight = road[i][2];

			nodeList.get(idx).add(new Node(nodeNum, weight));
			nodeList.get(nodeNum).add(new Node(idx, weight));
		}

		System.out.println(checkResult(dijkstra(1)));
	}

	static int[] dijkstra(int startNum) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] vis = new boolean[N + 1];
		int dist[] = new int[N + 1]; // ��尣 �ִܰŸ��� �� �迭

		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[startNum] = 0;
		pq.add(new Node(startNum, 0));

		while (!pq.isEmpty()) {
			Node pollNode = pq.poll();

			if (!vis[pollNode.nodeNum]) {

				vis[pollNode.nodeNum] = true;

				for (Node nextNode : nodeList.get(pollNode.nodeNum)) {// nodeList�� �ִ� ��� �� ������(pollNode)���� ����ġ üũ
					if (!vis[nextNode.nodeNum] && dist[nextNode.nodeNum] > dist[pollNode.nodeNum] + nextNode.weight) {
						dist[nextNode.nodeNum] = dist[pollNode.nodeNum] + nextNode.weight;
						pq.add(new Node(nextNode.nodeNum, dist[nextNode.nodeNum]));
					}
				}
			}
		}

		return dist;
	}

	static int checkResult(int[] dist) {
		int result = 0;
		for (int i = 1; i <= N; i++) {
			if (dist[i] <= K) {
				result++;
			}
		}

		return result;
	}

	static class Node implements Comparable<Node> {
		int nodeNum, weight;

		public Node(int nodeNum, int weight) {
			this.nodeNum = nodeNum;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return weight - o.weight;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return ("nodeNum : " + nodeNum + ", weight : " + weight);
		}

	}
}