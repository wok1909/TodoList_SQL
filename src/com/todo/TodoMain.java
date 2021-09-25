package com.todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	private static TodoList l;
	
	public static void load() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("todolist.txt"));
			
			l = new TodoList();
			int count = 0;
			String oneline;
			while((oneline = br.readLine()) != null) {
//				System.out.println(oneline);
				StringTokenizer st = new StringTokenizer(oneline, "##");
				
				String title = st.nextToken();
//				title = title.substring(1,title.length()-1);
				String category = st.nextToken();
				String desc = st.nextToken();
				String date = st.nextToken();				

				TodoUtil.createItemAtBeginning(l, title, category, desc, date);
				count++;
			}
			br.close();
			System.out.printf("%d개의 정보 로딩 완료!\n", count);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void save() throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter("todolist.txt"));
		for (TodoItem item : l.getList()) {
//			bw.write("[" + item.getTitle() + "]##" + item.getDesc() + "##" + item.getCurrent_date());
			bw.write(item.toSaveString());
			bw.newLine();
		}
		bw.flush();
		bw.close();
		
		System.out.printf("%d개 정보 저장 완료!\n", l.getList().size());
	}
	
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		
		if(l == null)
			l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		
		Menu.displaymenu();
		do {
//			Menu.displaymenu();
			Menu.prompt();
			isList = false;
			String choice = sc.next();
			
			if(choice.equals("help"))
			{
				Menu.displaymenu();
				continue;
			}
			
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
			case "find":
				String key = sc.nextLine().trim();
				TodoUtil.find(l, key);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;

			case "ls_name_asc":
				l.sortByName();
				
				System.out.println("제목순으로 정렬하였습니다.");

				isList = true;
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				
				System.out.println("제목순으로 정렬하였습니다.");

				isList = true;
				break;
				
			case "ls_date":
				l.sortByDate();

				System.out.println("날짜순으로 정렬하였습니다.");

				isList = true;
				break;

			case "exit":
				quit = true;
				break;

			default:
				System.out.println("정확한 명령어를 입력하세요. (도움말 - help)");
				break;
			}
			
			if(isList) l.listAll();
		} while (!quit);
	}
}
