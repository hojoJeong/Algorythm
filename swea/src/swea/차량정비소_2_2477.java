package swea;

import java.util.*;
import java.io.*;

public class ���������_2_2477 {
	static Queue<Customer> receptq, repairq;
	static ArrayList<Customer> customer;
	static ArrayList<Desk> receptDesk, repairDesk;
	static int N, M, K, A, B, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			receptq = new LinkedList<>();
			repairq = new LinkedList<>();
			receptDesk = new ArrayList<>();
			repairDesk = new ArrayList<>();
			customer = new ArrayList<>();
			ans = 0;

			Customer custemp = new Customer(-1, -1, -1, -1, -1);
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				int time = Integer.parseInt(st.nextToken());
				receptDesk.add(new Desk(0, time, custemp));
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				int time = Integer.parseInt(st.nextToken());
				repairDesk.add(new Desk(0, time, custemp));
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < K; i++) {
				int tk = Integer.parseInt(st.nextToken());
				receptq.add(new Customer(tk, -1, 0, -1, 0));
			}

			carcenter();
			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	static void carcenter() {
		int time = 0;
		int cnt = 0;

		while (true) {
			if (customer.size() == K) {
				System.out.println("check");
				for (int i = 0; i < K; i++) {
					if (customer.get(i).receptnum == A && customer.get(i).repairnum == B) {
						ans += i + 1;
					}
				}
				break;
			}
			cnt = 0;
			// ����â��
			// ����â������ ���� �� �ִ� ���� ������� Ȯ��
			int receptOutCnt = 0;
			for (int i = 0; i < N; i++) {
				if (receptDesk.get(i).customer != null) {
					int ctime = receptDesk.get(i).customer.recepttime;
					if (receptDesk.get(i).status == 1 && ctime + receptDesk.get(i).time == time) {
						receptOutCnt++;
					}
				}
			}
			// ����â������ ���� �� �ִ� �ο��� ������
			for (int i = 0; i < N; i++) {
				if (receptOutCnt == 1) { // ���� ���� �ο��� 1�� �� ��
					repairq.add(receptDesk.get(i).customer);
					receptDesk.get(i).status = 0;
					receptDesk.get(i).customer = null;

				} else if (receptOutCnt > 1) { // ���� ���� �ο��� 2�� �̻��� ��
					int ctime = receptDesk.get(i).customer.recepttime;
					Customer[] temp = new Customer[receptOutCnt];
					int idx = 0;
					for (int j = 0; j < N; j++) { // ���� ������ �ο��� ���� ���� temp�� �����
						if (receptDesk.get(j).status == 1 && ctime + receptDesk.get(j).time == time) {
							temp[idx] = receptDesk.get(j).customer;
							receptDesk.get(j).customer = null;
							idx++;
						}
					}
					// ���� temp�� receptnum�� ���� ������������ ����
					for (int j = 0; j < receptOutCnt - 1; j++) {
						for (int h = j + 1; h < receptOutCnt; h++) {
							if (temp[j].receptnum > temp[h].receptnum) {
								Customer ttemp = temp[j];
								temp[j] = temp[h];
								temp[h] = ttemp;
							}
						}
					}

					for (int j = 0; j < receptOutCnt; j++) {
						repairq.add(temp[j]);
						receptDesk.get(i).status = 0;
						receptDesk.get(i).customer = null;

					}
				}
				// �ð� �Ǹ� ����â���� �� �־���
				// Desk�� �־��� �� ���� �ð��� â����ȣ�� �Է�
				if (receptDesk.get(i).status == 0) {
					if (!receptq.isEmpty() && receptq.peek().tk == time) {
						receptDesk.get(i).customer = receptq.poll();
						receptDesk.get(i).customer.receptnum = i;
						receptDesk.get(i).customer.recepttime = time;
						receptDesk.get(i).status = 1;
					}
				}
			}

			// ����â��
			for (int i = 0; i < M; i++) {
				// ���� �Ϸ�Ǹ� ������
				if (repairDesk.get(i).customer != null) {
					int ctime = repairDesk.get(i).customer.repairtime;
					if (repairDesk.get(i).status == 1 && ctime + repairDesk.get(i).time == time) {
						customer.add(repairDesk.get(i).customer);
						repairDesk.get(i).customer = null;
						repairDesk.get(i).status = 0;
						////////////// ������� ����
					}
				}
				// â�� ����ְ� ��� �� ������ �־���
				if (repairDesk.get(i).status == 0 && !repairq.isEmpty()) {
					repairDesk.get(i).customer = repairq.poll();
					repairDesk.get(i).customer.repairnum = i;
					repairDesk.get(i).customer.repairtime = time;
					repairDesk.get(i).status = 1;
				}
			}

			// �������� Ȯ��
			for (int i = 0; i < M; i++) {
				if (repairDesk.get(i).status == 0)
					cnt++;
			}

			time++;
		}
	}

// receptq, repairq -> Queue
	public static class Customer {
		int tk, receptnum, recepttime, repairnum, repairtime;

		public Customer(int tk, int receptnum, int recepttime, int repairnum, int repairtime) {
			this.tk = tk;
			this.receptnum = receptnum;
			this.recepttime = recepttime;
			this.repairnum = repairnum;
			this.repairtime = repairtime;
		}
	}

// receptDesk, repairDesk -> List
	public static class Desk {
		int time, status;
		Customer customer;

		public Desk(int status, int time, Customer customer) {
			this.status = status;
			this.time = time;
			this.customer = customer;
		}
	}
}
