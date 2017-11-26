
public class Menu {
	public void showMenu(){
		System.out.println("**********은행 프로그램**********");
		System.out.println("원하는 작업을 선택하세요:");
		System.out.println("[0] 고객 인증");
		System.out.println("[1] 잔고 조희");
		System.out.println("[2] 입금");
		System.out.println("[3] 출금");
		System.out.println("[4] 거래 내역 조회");
		System.out.println("[5] 고객정보 조회");
		System.out.println("[6] 고객 등록");
		System.out.print("선택 (0, 1, 2, 3, 4, 5, 6) : ");
		/*원하는 작업을 선택하시오:
			[0] 고객 인증
			[1] 잔고 조회
			[2] 입금
			[3] 출금
			[4] 거래 내역 조회
			[5] 계좌 개설
			[6] 고객 등록
			[7] 종료*/
		
	}
	
	public void errorMessage(){
		System.out.println("잘못된 입력입니다.");
	}
}
