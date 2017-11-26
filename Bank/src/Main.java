import java.io.IOException;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException, ClassNotFoundException{
		Menu menu = new Menu();
		int userSelection;
		Scanner input = new Scanner(System.in);
		Boolean certified = false;
		Bank bank = new Bank();	
		while(true){
			menu.showMenu();
			userSelection = input.nextInt();
			if(userSelection == 0){
				certified = bank.certifyCustomer();
			}
			else if(userSelection == 1){
				if(certified == false){
					System.out.println("먼저 고객 인증을 해주십시오.");
				}
				else{
					bank.searchBalance();
				}
			}
			else if(userSelection == 2){
				if(certified == false){
					System.out.println("먼저 고객 인증을 해주십시오.");
				}
				else{
					bank.deposit();
				}
			}
			else if(userSelection == 3){
				if(certified == false){
					System.out.println("먼저 고객 인증을 해주십시오.");
				}
				else{
					bank.withdraw();
				}
			}
			else if(userSelection == 4){
				if(certified == false){
					System.out.println("먼저 고객 인증을 해주십시오.");
				}
				else{
					bank.creditInformation();
				}
			}
			else if(userSelection == 5){
				if(certified == false){
					System.out.println("먼저 고객 인증을 해주십시오.");
				}
				else{
					bank.showAccountInfo();
				}
				
			}
			else if(userSelection == 6){
				bank.registerCustomer();
			}
			else{
				menu.errorMessage();
			}
		}
	}
}
