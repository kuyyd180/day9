package dallhyun.submit06;

public class DallHyun {

	public static void main(String[] args) {
		
		makeCard("정찬웅", "010-7398-7332", "akow283@gmail.com");

		makeTree(5);
		makeTree(7);
		
		String nTwo = Integer.toBinaryString(23); 
		System.out.println(nTwo);
	}
	
	static void makeCard (String name, String phone, String email) {
		System.out.println("이름: " + name);
		System.out.println("연락처: " + phone);
		System.out.println("이메일: " + email);
	}
	
	static void makeTree (int star) {

		for (int i = 0; i < star; i++) {
			for (int j = star; j > i; j--) {
				System.out.print(" ");
			}
			for(int k = i  * 2 + 1; k > 0; k--) {
				System.out.print("*");
			}
			
			System.out.println();
		}
	}
	
}
	






	
	
	


