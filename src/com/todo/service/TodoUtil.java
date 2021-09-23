package com.todo.service;

import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
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
		
		System.out.print("내용 > ");
		desc = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
	}
	
public static void createItemAtBeginning(TodoList list, String title, String desc, String date) {
		TodoItem t = new TodoItem(title, desc);
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
				+ "삭제할 항목의 제목을 입력하시오 > ");
		
		String title = sc.next().trim();
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				break;
			}
		}
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
				+ "수정할 항목의 제목을 입력하시오 > ");
		
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
//			System.out.println("title doesn't exist");
			System.out.println("없는 제목입니다!");
			return;
		}

//		System.out.println("enter the new title of the item");
//		String new_title = sc.next().trim();
		System.out.print("수정할 항목의 새로운 제목을 입력하시오 > ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
//			System.out.println("title can't be duplicate");
			System.out.println("제목이 중복됩니다!");
			return;
		}
		
		sc.nextLine();	// flush buffer

		
//		System.out.println("enter the new description ");
//		String new_description = sc.next().trim();
		System.out.print("수정할 항목의 새로운 내용을 입력하시오 > ");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
//				System.out.println("item updated");
				System.out.println("정보 수정 완료!");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("[전체 목록]");
		for (TodoItem item : l.getList()) {
//			System.out.println("Item Title: " + item.getTitle() + "  Item Description:  " + item.getDesc());
			System.out.println("[" + item.getTitle() + "] " + item.getDesc() + " - " + item.getCurrent_date());
		}
	}
}
