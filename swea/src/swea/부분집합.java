package swea;

public class 부분집합 {
	static String[] cards = {"A","B","C"};
	static boolean[] select = new boolean[cards.length];
	
	public static void main(String[] args) {
		powerset(0);
	}
	
	static void powerset(int idx) {
		if(idx == cards.length) {
			System.out.print("지금 만들어진 부분집합 : { ");
			for(int i=0; i<cards.length; i++) {
				if(select[i])
					System.out.print(cards[i]+" ");
			}
			System.out.println("}");
			return;
		}
		
		select[idx] = true;
		powerset(idx+1);
		select[idx] = false;
		powerset(idx+1);
	}
}
