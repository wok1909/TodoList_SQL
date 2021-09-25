package com.todo.service;

import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc, category, due;
		Scanner sc = new Scanner(System.in);
		
//		System.out.println("\n"
//				+ "========== Create item Section\n"
//				+ "enter the title\n");
		
//		title = sc.next();
		
		System.out.print("\n"
				+ "[항목 추가]\n"
				+ "제목 > ");
		
		title = sc.next().trim();
		if (list.isDuplicate(title)) {
//			System.out.printf("title can't be duplicate");
			System.out.println("제목이 중복됩니다!");
			return;
		}
//		System.out.println("enter the description");
//		desc = sc.next();
		
		sc.nextLine();	// flush buffer
		
		System.out.print("카테고리 > ");
		category = sc.nextLine().trim();
		
		System.out.print("내용 > ");
		desc = sc.nextLine().trim();
		
		System.out.print("마감일자 (yyyy/mm/dd) > ");
		due = sc.nextLine().trim();
		
		
		TodoItem t = new TodoItem(title, category, desc, due);
		list.addItem(t);
	}
	
public static void createItemAtBeginning(TodoList list, String title, String category, String desc, String date) {
		TodoItem t = new TodoItem(title, category, desc);
		
		if(date.length() > 19)
		{
			t.setCurrent_date(date.substring(0,10) + date.substring(23));
			t.setDue_date(date.substring(13, 23));
		}
		else
			t.setCurrent_date(date);
		
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
//		System.out.println("\n"
//				+ "========== Delete Item Section\n"
//				+ "enter the title of item to remove\n"
//				+ "\n");
		
		System.out.print("\n"
				+ "[항목 삭제]\n"
				+ "삭제할 항목의 번호를 입력하시오 > ");
		
		int index = sc.nextInt();
		
		System.out.println(index + ". " + getLine(l, index-1));
		
		l.deleteItem(l.getList().get(index-1));
		
//		for (TodoItem item : l.getList()) {
//			if (title.equals(item.getTitle())) {
//				l.deleteItem(item);
//				break;
//			}
//		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
//		System.out.println("\n"
//				+ "========== Edit Item Section\n"
//				+ "enter the title of the item you want to update\n"
//				+ "\n");
//		String title = sc.next().trim();
		
		System.out.print("\n"
				+ "[항목 수정]\n"
				+ "수정할 항목의 번호를 입력하시오 > ");
		
		int index = sc.nextInt();
		
		if(l.getList().size() < index) {
			System.out.println("없는 번호입니다!");
			return;
		}
		else
			System.out.println(index + ". " + getLine(l, index-1));

		System.out.print("새 제목 > ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("제목이 중복됩니다!");
			return;
		}
		
		sc.nextLine();	// flush buffer
		
		System.out.print("새 카테고리 > ");
		String new_category = sc.nextLine().trim();
		
		System.out.print("새 내용 > ");
		String new_description = sc.nextLine().trim();
		
		System.out.print("새 마감일자 > ");
		String new_duedate = sc.nextLine().trim();
		
		TodoItem item = l.getList().get(index-1);
		item.setTitle(new_title);
		item.setDesc(new_description);
		item.setCategory(new_category);
		item.setDue_date(new_duedate);
		
		System.out.println("정보 수정 완료!");

	}
	
	public static void find(TodoList l, String key) {
//		String key;
//		Scanner sc = new Scanner(System.in);
		
//		key = sc.nextLine().trim();
		
		int count = 0;
		for(int i=0; i<l.getList().size(); i++) {
			TodoItem item = l.getList().get(i);
			if(item.getTitle().contains(key) || item.getDesc().contains(key)) {
				System.out.println(i+1 + ". " + getLine(l, i));
				count++;
			}
		}
		
		System.out.printf("총 %d개의 항목을 찾았습니다.\n", count);
	}

	public static void listAll(TodoList l) {
		System.out.printf("[전체 목록, 총 %d개]\n", l.getList().size());
		int i=1;
		for (TodoItem item : l.getList()) {
//			System.out.println("Item Title: " + item.getTitle() + "  Item Description:  " + item.getDesc());
			System.out.print(i++ + ". ");
			System.out.println("[" + item.getCategory() + "] " + item.getTitle() + " " + item.getDesc() + " - " + item.getCurrent_date().substring(0,10) + " - " + item.getDue_date());
		}
	}
	
	public static String getLine(TodoList l, int index) {
		TodoItem item = l.getList().get(index);
		
		return "[" + item.getCategory() + "] " + item.getTitle() + " " + item.getDesc() + " - " + item.getCurrent_date().substring(0,10) + " - " + item.getDue_date();
		
	}
}
