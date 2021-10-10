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
					
				case "help":
					Menu.displaymenu();
					break;			
				
				case "exit":
					quit = true;
					break;
					
				default:
					System.out.println("정확한 명령어를 입력하세요. (도움말 - help)");
					break;
			}
		} while (!quit);
		DbConnect.getConnection();
	}
}
