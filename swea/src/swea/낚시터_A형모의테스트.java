package swea;

import java.util.*;
import java.io.*;

public class ������_A�������׽�Ʈ {

		static int N;
		static int[][] gates = new int[3][2]; // ����Ʈ value ���ò� value
		static int[] visited; // ������
		static int[] dx = { 1, -1 }; // ���⺤��
		static int min = Integer.MAX_VALUE;

		public static void main(String[] args) throws Exception {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

			int T = Integer.parseInt(br.readLine());

			for (int tc = 1; tc <= T; tc++) {
				N = Integer.parseInt(br.readLine());
				for (int i = 0; i < gates.length; i++) {
					StringTokenizer token = new StringTokenizer(br.readLine());
					gates[i][0] = Integer.parseInt(token.nextToken());
					gates[i][1] = Integer.parseInt(token.nextToken());
				}

				int[][] order = { { 0, 1, 2 }, { 0, 2, 1 }, { 1, 0, 2 }, { 1, 2, 0 }, { 2, 0, 1 }, { 2, 1, 0 } }; // 3���� ����Ʈ
																													// ����
				min = Integer.MAX_VALUE;
				for (int i = 0; i < order.length; i++) {
					visited = new int[N + 1];
					int first = order[i][0]; // ù��° ����Ʈ
					int second = order[i][1]; // �ι�° ����Ʈ
					int third = order[i][2]; // ����° ����Ʈ
					first(0, gates[first], gates[second], gates[third]); // Ž�� ����
				}

				bw.write("#" + String.valueOf(tc) + " " + String.valueOf(min) + "\n");
			}
			bw.flush();
			bw.close();

		}

		static void copy(int[] temp, int[] original) { // ��������

			for (int i = 1; i <= N; i++) {
				temp[i] = original[i];
			}
		}

		static void first(int size, int[] gate1, int[] gate2, int[] gate3) { // ù��° ����Ʈ Ž��
			int sum = 0;
			int distance = 0;
			int gc = gate1[0];

			if (visited[gc] == 0) { // ���������� ��������� �Ÿ� +1 ������ ����-1
				visited[gc] = 1;
				sum++;
				size++;
			}
			distance++;

			while (true) {
				if (size == gate1[1]) { // ���ʿ� �Ѵ� �� ���
					int[] temp = new int[N + 1];
					copy(temp, visited);
					second(0, gate2, gate3, sum, temp);
					break;
				} else if (size == gate1[1] - 1) { // �ش� �Ÿ��� ���ò��� �Ѹ� ���� ���
					int right = gc + distance;
					int left = gc - distance;
					Queue<Integer> queue = new LinkedList();
					while (true) {

						for (int i = 0; i < 2; i++) { // ���⺤�͸� ���� �¿쿡 �� �� �ִ� ��ġ�� add
							int location = gc + dx[i] * distance;
							if (location >= 1 && location <= N) {
								if (visited[location] == 0) {
									queue.add(location);
								}
							}
						}

						if (queue.isEmpty()) { // ���� �� �� �� ���� �Ÿ� +1
							distance++;
						} else {
							break;
						}
					}

					if (queue.size() == 1) { // �¿��� �� �ڸ��� ��� �ִ� ���
						int location = queue.poll();
						visited[location] = 1;
						sum += distance + 1;
						int[] temp = new int[N + 1];
						copy(temp, visited);
						second(0, gate2, gate3, sum, temp);
					} else { // �¿� �Ѵ� �ڸ��� ����־ �ΰ��� ��찡 ��Ÿ�� ���
						int location = queue.poll();
						sum += distance + 1;
						visited[location] = 1;
						int[] temp = new int[N + 1];
						copy(temp, visited);
						second(0, gate2, gate3, sum, temp); // 2��° �ڸ� Ž��
						visited[location] = 0; // �ش� �ڸ��� 1�� �־��ְ� 0���� ���½�Ű�� 2��° �ڸ� Ž��
						location = queue.poll();
						visited[location] = 1;
						temp = new int[N + 1];
						copy(temp, visited);
						second(0, gate2, gate3, sum, temp);
					}
					break;
				}

				for (int i = 0; i < 2; i++) { // �¿쿡 �� �� �ִ� ��ġ�� ���ò� �־��ֱ�
					int x = gc + dx[i] * distance;
					if (x >= 1 && x <= N) {
						if (visited[x] == 0) {
							visited[x] = 1;
							sum += distance + 1;
							size++;
						}
					}
				}
				distance++; // �ѹ� Ž���� ������ ���ذŸ� +1

			}

		}

		static void second(int size, int[] gate2, int[] gate3, int sum, int[] temp) { // �ι�° ����Ʈ Ž�� first()�κа� ���� ����

			int distance = 0;
			int gc = gate2[0];

			if (temp[gc] == 0) {
				temp[gc] = 2;
				sum++;
				size++;
			}

			distance++;

			while (true) {
				if (size == gate2[1]) {
					int[] temp2 = new int[N + 1];
					copy(temp2, temp);
					third(0, gate3, sum, temp2);
					break;
				} else if (size == gate2[1] - 1) {
					int right = gc + distance;
					int left = gc - distance;
					Queue<Integer> queue = new LinkedList();
					while (true) {

						for (int i = 0; i < 2; i++) {
							int location = gc + dx[i] * distance;
							if (location >= 1 && location <= N) {
								if (temp[location] == 0) {
									queue.add(location);
								}
							}
						}

						if (queue.isEmpty()) {
							distance++;
						} else {
							break;
						}

					}

					if (queue.size() == 1) {
						int location = queue.poll();
						temp[location] = 2;
						sum += distance + 1;
						int[] temp2 = new int[N + 1];
						copy(temp2, temp);
						third(0, gate3, sum, temp2);

					} else {
						int location = queue.poll();
						sum += distance + 1;
						temp[location] = 2;
						int[] temp2 = new int[N + 1];
						copy(temp2, temp);
						third(0, gate3, sum, temp2);
						temp[location] = 0;
						location = queue.poll();
						temp[location] = 2;
						temp2 = new int[N + 1];
						copy(temp2, temp);
						third(0, gate3, sum, temp2);

					}
					break;
				}

				for (int i = 0; i < 2; i++) {
					int x = gc + dx[i] * distance;
					if (x >= 1 && x <= N) {
						if (temp[x] == 0) {
							temp[x] = 2;
							sum += distance + 1;
							size++;
						}
					}
				}

				distance++;

			}
		}

		static void third(int size, int[] gate3, int sum, int[] temp2) { // ����° ����Ʈ Ž�� first()�κа� ���� ����
			int distance = 0;
			int gc = gate3[0];

			if (temp2[gc] == 0) {
				temp2[gc] = 3;
				sum++;
				size++;
			}

			distance++;

			while (true) {
				if (size == gate3[1]) {
					min = Math.min(min, sum); // Ž���� ������ min�� ���Ž����ֱ�
					break;
				} else if (size == gate3[1] - 1) {
					int right = gc + distance;
					int left = gc - distance;
					Queue<Integer> queue = new LinkedList();
					while (true) {

						for (int i = 0; i < 2; i++) {
							int location = gc + dx[i] * distance;
							if (location >= 1 && location <= N) {
								if (temp2[location] == 0) {
									queue.add(location);
								}
							}
						}

						if (queue.isEmpty()) {
							distance++;
						} else {
							break;
						}

					}

					if (queue.size() == 1) {
						int location = queue.poll();
						temp2[location] = 3;
						sum += distance + 1;
						min = Math.min(min, sum);
					} else {
						int location = queue.poll();
						sum += distance + 1;
						temp2[location] = 3;
						min = Math.min(min, sum);
						temp2[location] = 0;
						location = queue.poll();
						temp2[location] = 3;
						min = Math.min(min, sum);
					}
					break;
				}

				for (int i = 0; i < 2; i++) {
					int x = gc + dx[i] * distance;
					if (x >= 1 && x <= N) {
						if (temp2[x] == 0) {
							temp2[x] = 3;
							sum += distance + 1;
							size++;
						}
					}
				}

				distance++;

			}
		}
	}