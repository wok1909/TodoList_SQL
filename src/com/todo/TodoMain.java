package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.DbConnect;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
			
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean quit = false;
		l.importData("todolist.txt");
		do {
			Menu.prompt();
			String choice = sc.next();
			
			switch (choice) {
				case "add":
					TodoUtil.createItem(l);
					break;
				
				case "del":
					TodoUtil.deleteItem(l);
					break;
					
				case "edit":
					TodoUtil.updateItem(l);
					break;
					
				case "ls":
					TodoUtil.listAll(l);
					break;
	
				case "ls_name":
					System.out.println("제목순으로 정렬하였습니다.");
					TodoUtil.listAlls(l, "title", 1);
					break;
	
				case "ls_name_desc":
					System.out.println("제목역순으로 정렬하였습니다.");
					TodoUtil.listAlls(l, "title", 0);
					break;
					
				case "ls_date":
					System.out.println("날짜순으로 정렬하였습니다.");
					TodoUtil.listAlls(l, "due_date", 1);
					break;
				
				case "ls_date_desc":
					System.out.println("날짜역순으로 정렬하였습니다.");
					TodoUtil.listAlls(l, "due_date", 0);
					break;
					
				case "ls_cate":
					TodoUtil.listCateAll(l);
					break;
					
				case "find":
					sc.nextLine();
					String keyword = sc.nextLine().trim();
					TodoUtil.findList(l, keyword);
					break;
					
				case "find_cate":
					sc.nextLine();
					String cate = sc.nextLine().trim();
					TodoUtil.findCateList(l, cate);
					break;
				
				case "comp":
					TodoUtil.setCompleteness(l);
					break;
					
				case "done":
					TodoUtil.setIsDone(l);
					break;
					
					
				case "help":
					Menu.displaymenu();
					break;			
				
				case "exit":
					quit = true;
					break;
					
				default:
//					System.out.println("정확한 명령어를 입력하세요. (도움말 - help)");
					wrongInput(choice, l);
					break;
			}
		} while (!quit);
		
		TodoUtil.saveList(l, "todolist.txt");
		
		DbConnect.getConnection();
	}
	
	public static void wrongInput(String input, TodoList l) {
		Scanner sc = new Scanner(System.in);
		
		switch(input) {
		case "a":
		case "ad": 
			{
				System.out.print("입력이 잘못되었습니다. add 옵션을 원하셨다면 Y를 입력해주세요 >");
				String choice = sc.next();
				
				if(choice.equals("Y") || choice.equals("y"))
					TodoUtil.createItem(l);
				break;
			}
		case "d":
		case "de":
			{
				System.out.print("입력이 잘못되었습니다. del 옵션을 원하셨다면 Y를 입력해주세요 > ");
				String choice = sc.next();
				
				if(choice.equals("Y") || choice.equals("y"))
					TodoUtil.deleteItem(l);
				break;
			}
		case "e":
		case "ed":
		case "edi":
			{
				System.out.print("입력이 잘못되었습니다. edit 옵션을 원하셨다면 Y를 입력해주세요 >");
				String choice = sc.next();
				
				if(choice.equals("Y") || choice.equals("y"))
					TodoUtil.updateItem(l);
				break;
			}
		case "l":
			{
				System.out.print("입력이 잘못되었습니다. ls 옵션을 원하셨다면 Y를 입력해주세요 > ");
				String choice = sc.next();
				
				if(choice.equals("Y") || choice.equals("y"))
					TodoUtil.listAll(l);
				break;
			}
		case "ls_n":
		case "ls_na":
		case "ls_nam": 
			{
				System.out.print("입력이 잘못되었습니다. ls_name 옵션을 원하셨다면 Y를 입력해주세요 > ");
				String choice = sc.next();
				
				if(choice.equals("Y") || choice.equals("y")) {
					System.out.println("제목순으로 정렬하였습니다.");
					TodoUtil.listAlls(l, "title", 1);
				}
				break;
			}
		case "ls_name_":
		case "ls_name_d":
		case "ls_name_de":
		case "ls_name_des":
			{
				System.out.print("입력이 잘못되었습니다. ls_name_desc 옵션을 원하셨다면 Y를 입력해주세요 > ");
				String choice = sc.next();
				
				if(choice.equals("Y") || choice.equals("y")) {
					System.out.println("제목역순으로 정렬하였습니다.");
					TodoUtil.listAlls(l, "title", 0);
				}
				break;
			}
		case "ls_d":
		case "ls_da":
		case "ls_dat":
			{
				System.out.print("입력이 잘못되었습니다. ls_date 옵션을 원하셨다면 Y를 입력해주세요 > ");
				String choice = sc.next();
				
				if(choice.equals("Y") || choice.equals("y")) {
					System.out.println("날짜순으로 정렬하였습니다.");
					TodoUtil.listAlls(l, "due_date", 1);
				}
				break;
			}
		case "ls_date_":
		case "ls_date_d":
		case "ls_date_de":
		case "ls_date_des":
			{
				System.out.print("입력이 잘못되었습니다. ls_date_desc 옵션을 원하셨다면 Y를 입력해주세요 > ");
				String choice = sc.next();
				
				if(choice.equals("Y") || choice.equals("y")) {
					System.out.println("날짜역순으로 정렬하였습니다.");
					TodoUtil.listAlls(l, "due_date", 0);
				}
				break;
			}
		case "ls_c":
		case "ls_ca":
		case "ls_cat":
			{
				System.out.print("입력이 잘못되었습니다. ls_cate 옵션을 원하셨다면 Y를 입력해주세요 > ");
				String choice = sc.next();
				
				if(choice.equals("Y") || choice.equals("y")) 
					TodoUtil.listCateAll(l);
				break;
			}
		case "f":
		case "fi":
		case "fin":
			{
				System.out.print("입력이 잘못되었습니다. find 옵션을 원하셨다면 Y를 입력해주세요 > ");
				String choice = sc.next();
				sc.nextLine();
				if(choice.equals("Y") || choice.equals("y")) {
					System.out.print("찾고자 하는 키워드를 입력해주세요 > ");
					String keyword = sc.nextLine().trim();
					TodoUtil.findList(l, keyword);
				}
				break;
			}
		case "find_":
		case "find_c":
		case "find_ca":
		case "find_cat":
			{
				System.out.print("입력이 잘못되었습니다. find_cate 옵션을 원하셨다면 Y를 입력해주세요 > ");
				String choice = sc.next();
				sc.nextLine();
				
				if(choice.equals("Y") || choice.equals("y")) {
					System.out.print("찾고자 하는 키워드를 입력해주세요 > ");
					String keyword = sc.nextLine().trim();
					TodoUtil.findList(l, keyword);
				}
				break;
			}
		case "c":
		case "co":
		case "com":
			{
				System.out.print("입력이 잘못되었습니다. comp 옵션을 원하셨다면 Y를 입력해주세요 > ");
				String choice = sc.next();
				
				if(choice.equals("Y") || choice.equals("y")) 
					TodoUtil.setCompleteness(l);
				break;
			}
		case "do":
		case "don":
			{
				System.out.print("입력이 잘못되었습니다. done 옵션을 원하셨다면 Y를 입력해주세요 > ");
				String choice = sc.next();
				
				if(choice.equals("Y") || choice.equals("y")) 
					TodoUtil.setIsDone(l);
				break;
			}
		case "h":
		case "he":
		case "hel":
			{
				System.out.print("입력이 잘못되었습니다. help 옵션을 원하셨다면 Y를 입력해주세요 > ");
				String choice = sc.next();
				
				if(choice.equals("Y") || choice.equals("y")) 
					Menu.displaymenu();
				break;
			}
		case "ex":
		case "exi":
			{
				System.out.print("입력이 잘못되었습니다. exit 옵션을 원하셨다면 Y를 입력해주세요 > ");
				String choice = sc.next();
				
				if(choice.equals("Y") || choice.equals("y")) {
					TodoUtil.saveList(l, "todolist.txt");
					
					DbConnect.getConnection();
					
					return;
				}
			}
		default: System.out.println("정확한 명령어를 입력하세요. (도움말 - help)"); break;

		}
	}
}
