import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Scanner;


public class Bank implements Serializable{
	private Scanner input;
	private Customer[] customers;
	private String userID;
	private Customer user;
	private AccountLog accountLog;
	private FileWriter fw;
	private ObjectOutputStream writeInfo;
	
	Bank() throws IOException, ClassNotFoundException{
		input = new Scanner(System.in);
		customers = new Customer[500];   //500명의 고객이 있다.
		for(int i = 0; i < 500; i++){
			customers[i] = new Customer();
		}
		userID = new String();
		user = new Customer();
		loadCustomerInfo();
		accountLog = new AccountLog();
		//FileWriter fw = new FileWriter("CustomerInfo.data");    //파일 먼저 만들기
		writeInfo = new ObjectOutputStream (new FileOutputStream ("CustomerInfo.data"));
	}


	
	private void loadCustomerInfo() throws IOException, ClassNotFoundException{
		try {
			ObjectInputStream readInfo = new ObjectInputStream(new FileInputStream("CustomerInfo.data"));
			for(int i = 0; i < 500; i++){
				customers[i] = (Customer) readInfo.readObject();
			}
			readInfo.close();
		} catch (FileNotFoundException e) {
				System.out.println("고객 데이터 파일이 없습니다.");
		} catch (Exception eOFException) {
			
		}
	}
	
	public Boolean certifyCustomer() throws ClassNotFoundException, IOException{
		loadCustomerInfo();
		System.out.print("아이디를 입력하세요 : ");
		String customerID = input.nextLine();
		System.out.print("비밀번호를 입력하세요 : ");
		String password = input.nextLine();
		try {
			for(int i = 0; i < 500; i++){
				if(customers[i].id.equals(customerID)){
					if(customers[i].password.equals(password)){
						userID = customerID;
						user = customers[i];
						System.out.println("고객 인증에 성공하였습니다.");
						return true;
					}
					break;
				}
			}
			System.out.println("고객 인증에 실패하였습니다.");
			return false;
		} catch (Exception nullpointerException) {
			return false;
		}
	}
	
	public void searchBalance() throws ClassNotFoundException, IOException{
		loadCustomerInfo();
		System.out.println("잔고는 " + user.remain +" 입니다.");
	}
	
	public void deposit() throws ClassNotFoundException, IOException{
		loadCustomerInfo();
		System.out.print("입금 할 금액을 입력 : ");
		int deposit = input.nextInt();
		user.remain += deposit;
		System.out.println("입금을 완료하였습니다.");
		
		AccountLog newAccountLog = new AccountLog();
		newAccountLog = getTime();
		newAccountLog.type = "입금";
		newAccountLog.amount = deposit;
		ObjectOutputStream writeInfo;
		File file = new File("AccountLog.txt");
		try {
			FileWriter write = new FileWriter(file,true);
			write.write(Integer.toString(newAccountLog.year) + "/" + Integer.toString(newAccountLog.month) + "/" + Integer.toString(newAccountLog.day) +
			"/" + newAccountLog.type + "/" + Integer.toString(newAccountLog.amount) + "\n");
			write.flush();
			write.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("입금 정보를 기록할 수 없습니다.");
		}
	}
	
	public void withdraw(){
		System.out.print("출금 할 금액을 입력 : ");
		int withdraw = input.nextInt();
		if(user.remain - withdraw > 0){
			user.remain -= withdraw;
			System.out.println("출금을 완료하였습니다.");
			
			AccountLog newAccountLog = new AccountLog();
			newAccountLog = getTime();
			newAccountLog.type = "출금";
			newAccountLog.amount = withdraw;
			ObjectOutputStream writeInfo;
			File file = new File("AccountLog.txt");
			try {
				FileWriter write = new FileWriter(file,true);
				write.write(Integer.toString(newAccountLog.year) + "/" + Integer.toString(newAccountLog.month) + "/" + Integer.toString(newAccountLog.day) +
				"/" + newAccountLog.type + "/" + Integer.toString(newAccountLog.amount) + "\n");
				write.flush();
				write.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("출금 정보를 기록할 수 없습니다.");
			}
			
		}
		else{
			System.out.println("잔액이 부족합니다.");
		}
	}
	
	public void creditInformation(){
		String readLine = new String();
		File file = new File("AccountLog.txt");
		try {
			Scanner read = new Scanner(file);
			do{
				readLine = read.nextLine();
				System.out.println(readLine);
			}while(read.hasNext());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void showAccountInfo(){
		System.out.println("고객님의 이름은 " + user.name + " 입니다.");
		System.out.println("고객님의 주민번호는 " + user.residentNumber + " 입니다.");
		System.out.println("고객님의 전화번호는 " + user.telephone + " 입니다.");
		System.out.println("고객님의 주소는 " + user.address + " 입니다.");
		System.out.println("고객님의 아이디는 " + user.id + " 입니다.");
		System.out.println("고객님의 비밀번호는 " + user.password + " 입니다.");
	}
	
	public void registerCustomer() throws ClassNotFoundException, IOException{  
		Customer newCustomer = new Customer();
		System.out.print("이름을 입력하세요 : ");
		newCustomer.name = input.nextLine();
		System.out.print("주민등록번호를 입력하세요 : ");
		newCustomer.residentNumber = input.nextLine();
		System.out.print("전화번호를 입력하세요 : ");
		newCustomer.telephone = input.nextLine();
		System.out.print("주소를 입력하세요 : ");
		newCustomer.address = input.nextLine();
		System.out.print("아이디를 입력하세요 : ");
		newCustomer.id = input.nextLine();
		for(int i = 0; i < 500; i++){
			if(newCustomer.id.equals(customers[i].id)){
				System.out.println("중복된 id값입니다.");
				return;
			}
		}
		System.out.print("패스워드를 입력하세요 : ");
		newCustomer.password = input.nextLine();
		
		
		try {
			writeInfo.writeObject(newCustomer);
			writeInfo.flush();
			//writeInfo.close();
		} catch (FileNotFoundException e) {
			System.out.println("고객 데이터 파일이 없습니다.");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loadCustomerInfo();
	}
	
	public void exit(){
		System.out.println("은행 프로그램을 종료합니다.");
		System.exit(0);
	}
	
	private AccountLog getTime(){
		Calendar cal = Calendar.getInstance();
		AccountLog time = new AccountLog();
		time.year = cal.get(Calendar.YEAR);
		time.month = cal.get(Calendar.MONTH);
		time.day = cal.get(Calendar.DAY_OF_MONTH);
		return time;
	}

}
