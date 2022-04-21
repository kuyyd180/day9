package ch06_method;

import java.util.Scanner;

public class MethodStudy {

	public static void main(String[] args) {
		// 함수 = function = 메소드 = method
		// 굳이 나누자면 method는 클래스에
		// 종속된 함수를 말하며,
		// function은 클래스에 독립전인 함수를 말한다.
		
		// 1부터 100까지 다 더하면 결과는?
		int sumNum = 0;
		for (int i = 1; i <= 100; i++) {
			sumNum += i;
		}
		System.out.println("1부터 100까지 더한 결과: "+ sumNum);
		
		// 30부터 60까지 다 더하면 결과는?
		sumNum = 0;
		for (int i = 30; i <= 60; i++) {
			sumNum += i;
		}
		System.out.println("30부터 60까지 더한 결과: " + sumNum);
		
		// 25부터 50까지 다 더하면 결과는?
		sumNum = 0;
		for(int i = 25; i <= 50; i++) {
			sumNum += i;
		}
		System.out.println("25부터 50까지 더한결과: " + sumNum);
	
		System.out.println("\n============================\n");
		
		// 70부터 90까지 더하기
		printSum(70, 90);
		
		// 함수를 쓰면 좋은 점
		// 실행부의 코드가 간결해진다
		// 중복된 코드를 한 곳에서 관리할 수 있으므로 수정할 때 용이하다.
		
		// 리턴값이 존재하는 함수 만들기
		// 리턴값이 존재하는 함수는
		// 실행 결과를 변수에 담을 수 있다.
		// 변수 타입은 해당 함수의 리턴 타입과 일치해야 한다.
		int sum = returnSum(70, 90);
		System.out.println(sum);
		
		// 리턴을 굳이 담지 않아도 문제없다.
		returnSum(80, 85);
		
		System.out.println("\n================================\n");
		
		// System.out.println ()을 print()로 만들어쓰기
		print("카카시");
		printInt(returnSum(1, 10));
		
		System.out.println("\n=================================\n");
		
		// Math.abs() 만들기
		System.out.println(absolute(5-10));
		
		System.out.println("\n==================================\n");
		
		// 이름, 국어, 영어, 수학 점수를 입력받아서
		// 이름, 국어, 영어, 수학, 평균, 등급
		// 출력해주는 메소드 makeCard()를 만들어봅시다
		// 90점 이상 A, 80점 이상 B 그외 C
		
		makeCard("찬웅쌤", 83, 86, 73);
		
		System.out.println("\n=================================\n");
		
		// LoopFor 에서 했던 각 자리수 더하기
		// 를 함수화
		
		eachAdd("215515864851");
		eachAdd(221355462156L);
		
		System.out.println("\n============================\n");
		
		// LoopWhile에서 했던 포켓몬도 함수화
		playPokemon();
		
		
		
	} // main 끝 지점
	
	static void playPokemon() {
		// 포켓몬스터
		Scanner sc = new Scanner(System.in);
		int enemyHp = 100;
		outer: while(true) {
			System.out.println("야생의 파이리를 만났습니다.");
			System.out.println("행동을 선택해주세요.");
			System.out.println("1. 공격 | 2. 도망");
			System.out.println(">>>" );
			
			int command = Integer.parseInt(sc.nextLine());
			
			if(command == 1) {
				while(true) {
					System.out.println("공격 방법 선택");
					System.out.println("1. 백만볼트 | 2. 전광석화 | 3. 취소");
					System.out.print(">>> ");
					
					int attack = Integer.parseInt(sc.nextLine());
				
					if (attack == 1) {
						System.out.println("피카츄 백만볼트!!");
						enemyHp -= 20;
					}else if(attack == 2) {
						System.out.println("피카츄 전광석화!!");
						enemyHp -= 10;
					}else if(attack == 3) {
						// 내부 while문 종료
						// 외부 while문은 계속 실행
						break;
					}
					
					// 파이리 꺠꼬닥
					if(enemyHp <= 0) {
						System.out.println("파이리를 잡았다!!");
//						System.exit(0);			// 프로그램 종
//						break outer; 			// 외부 while문 종료
						// void 메소드에서도 return 사용 가능
						return;					// 메소드 종료1
						
					}
					
				}
			
			}else if(command == 2) {
				System.out.println("도망쳤습니다.");
				break;
			}
			
		}
		
		
		
		
	}
	
	static void eachAdd(long num) {
		String example = num + "";
		int result = 0;
		for (int i = 0; i < example.length(); i++) {
			String str = example.substring(i, i+1);
			int strInt = Integer.parseInt(str);
			result += strInt;
	}
		System.out.println("각 자리수를 더한 결과: " + result);
		
	}
	
	static void eachAdd(String num) {
		int result = 0;
		for (int i = 0; i < num.length(); i++) {
			String str = num.substring(i, i+1);
			int strInt = Integer.parseInt(str);
			result += strInt;
	}
		System.out.println("각 자리수를 더한 결과: " + result);
	}	
	static void makeCard(String name, int kor, int eng, int math) {
		System.out.println("이름: " + name);
		System.out.println("국어: " + kor);
		System.out.println("영어: " + eng);
		System.out.println("수학: " + math);
		double avg = ((kor + eng + math)) / 3.0;
		// 우리만의 Math.round()를 만들어보자!!
		double roundAvg = weRound(avg, 2);
		System.out.println("평균: " + roundAvg);
		
		if(avg >= 90) {
			System.out.println("등급: A");
		}else if(avg >= 80) {
			System.out.println("등급: B");
		}else {
			System.out.println("등급: C");
		}
	}
	/**
	 * 입력한 실수를 소수 n번째 자리로 리턴해주는 함,
	 * @param num 반올림하고자 하는 실수
	 * @param n	  소수 n번째 자리까지 리턴
	 * @return	  반올림된 실수 리턴
	 */
	static double weRound(double num, int n) {
		// Math.round()는 소수 첫째자리에서
		// 반올림한 정수를 리턴
		// 3.141592에 Math.round(3.141592)
		// 를 하면 3이 리턴
		// 소수 첫째자리 = 10을 곱한 후 나눔 (10^1)
		// 10을 곱한 31.41592에 Math.round(31.41592)
		// 를 하면 31이 리턴됨, 이후 10으로 나누면 3.1
		// 소수 둘째자리 = 100을 곱한 후 나눔 (10^2)
		// 100을 곱한 314.1592에 Math.round(314.1592)
		// 를 하면 314가 리턴됨, 이후 100으로 나누면 3.14
		
		int one = 1;
		for(int i = 0; i < n; i++) {
			one *= 10;
		}
//		one = Math.pow(10, n); //같
		
		num *= one;
		long temp = Math.round(num);
		double result =(double)temp / one;
		
		return result;
		
		
	}
	
	static int absolute(int num) {
		if(num < 0) {
			num *= -1;
			return num;
		}
		
		return num;
	}
	
	static void print(String text) {
		System.out.println(text);
	}
	
	// 메소드 오버로딩(Overloading)
	// 함수명은 유지한 채로, 파라미터의 타입 또는 파라미터의 개수를 다르게 받아
	// 처리할 수 있도록 하는 방법
	static void print(int intText) {
		System.out.println(intText);
	}
	
	static void printInt(int intText) {
		System.out.println(intText);
	}
	
	// 리턴이 없는 경우 void를 써준다.
	static void printSum(int fromNum, int toNum) {
		int sumNum = 0;
		for(int i = fromNum; i <= toNum; i++) {
			sumNum += i;
		}
		System.out.println(fromNum + "부터" + toNum + "까지 더한결과: " + sumNum);
	}
	
	static int returnSum(int fromNum, int toNum) {
		int sumNum = 0;
		for(int i = fromNum; i <= toNum; i++) {
			sumNum += i;
		}
		return sumNum;
	}
		
}

