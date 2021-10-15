package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;
import java.util.stream.Collectors;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList l) {
		
		String title, desc, category, due;
		int importance;
		boolean is_done;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[항목 추가]\n"
				+ "제목 > ");
		
		title = sc.nextLine();
		if (l.isDuplicate(title)) {
			System.out.printf("중복되는 이름이 있습니다");
			return;
		}
		
		System.out.print("중요도 > ");
		importance = sc.nextInt();
		
		sc.nextLine();
		
		System.out.print("카테고리 > ");
		category = sc.nextLine().trim();
					
		System.out.print("내용 > ");
		desc = sc.nextLine().trim();
		
		System.out.print("마감일자 (yyyy/mm/dd) > ");
		due = sc.nextLine().trim();		
		
		TodoItem t = new TodoItem(title, desc, importance, category, due);
		if(l.addItem(t)>0)
			System.out.println("항목이 추가되었습니다");
	}

	public static void deleteItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[항목 삭제]\n"
				+ "삭제할 항목의 번호를 입력하시오 (복수 선택 가능) > ");
		
		String indexStr = sc.nextLine();
		
		String[]index = indexStr.split(" ");
		
		for(int i=0; i<index.length; i++) {
			l.deleteItem(Integer.parseInt(index[i]));
		}
		System.out.println("삭제되었습니다.");
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[항목 수정]\n"
				+ "수정할 항목의 번호를 입력하시오 > ");
		int index = sc.nextInt();
		
		System.out.print("새 제목 > ");
		String new_title = sc.next().trim();
		
		sc.nextLine();		// fflush
		
		System.out.print("새 중요도 > ");
		int new_importance = sc.nextInt();
		
		sc.nextLine();
		
		System.out.print("새 카테고리 > ");
		String new_category = sc.nextLine().trim();
		
		System.out.print("새 내용 > ");
		String new_desc = sc.nextLine().trim();
		
		System.out.print("새 마감일자 > ");
		String new_duedate = sc.nextLine().trim();
		
		System.out.print("새 진행률 > ");
		int new_completeness = sc.nextInt();
		
		System.out.print("새 완료 여부 > ");
		boolean new_is_done = sc.nextBoolean();
		
		TodoItem t = new TodoItem(new_title, new_desc, new_importance, new_category, new_duedate, new_completeness, new_is_done);
		t.setId(index);
		
		if(l.updateItem(t)>0)
			System.out.println("정보 수정 완료!");

	}

	public static void listAll(TodoList l) {
		System.out.printf("[전체 목록, 총 %d개]\n", l.getCount()+1);
		for(TodoItem item : l.getList()) {
			System.out.print(item.getId() + " : ");
			System.out.println(item.toString());
		}
	}
	
	public static void saveList(TodoList l, String filename) {
		
		try {
			Writer w = new FileWriter(filename);
			for (TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();
			System.out.println("데이터 저장 완료!");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	public static void loadList(TodoList l, String filename) {
//		
//		try {
//			BufferedReader br = new BufferedReader(new FileReader("todolist.txt"));
//			String oneline;
//			while((oneline = br.readLine()) != null) {
//				StringTokenizer st = new StringTokenizer(oneline, "##");
//				String category = st.nextToken();
//				String title = st.nextToken();
//				String desc = st.nextToken();
//				int importance = Integer.parseInt(st.nextToken());
//				String date = st.nextToken();
//				String due_date = st.nextToken();
//				int completeness = Integer.parseInt(st.nextToken());
//				boolean is_done = Boolean.parseBoolean(st.nextToken());
//				TodoItem a = new TodoItem(title, desc, importance, category, due_date, completeness, is_done);
//				a.setCurrent_date(date);
//				l.addItem(a);
//				
//			}
//		} catch (FileNotFoundException e) {
//			System.out.println("파일 없음.");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//	}
	
	public static void findList(TodoList l, String keyword) {
		int count=0;
		for (TodoItem item : l.getLists(keyword)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("총 %d개의 항목을 찾았습니다.\n", count);
	}

	public static void findItem(TodoList l) {
		int i = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("찾는 제목 또는 내용을 입력하세요");
		String content = sc.nextLine();
		for (TodoItem item : l.getList()) {
			i++;
			if (item.getTitle().contains(content) || item.getDesc().contains(content)) {
				System.out.println(i + ". " + "카테고리 " + item.getCategory() + " 제목 : " + item.getTitle() + 
						" 설명 : " + item.getDesc() + " 중요도 : " + item.getImportance() + " 생성된 시간 : " +item.getCurrent_date() + 
						" 마감일자 :" + item.getDue_date() + " 진행률 : " + item.getCompleteness() + " 완료 여부 : " + item.getIs_done());
			}
		}

		
	}
	
	public static void find_cate(TodoList l) {
		System.out.println("키를 입력하세요");
		Scanner sc = new Scanner(System.in);
		String key = sc.nextLine();
		int gaesu = 0;
		for(int i=0; i<l.getList().size(); i++) {
			TodoItem item = l.getList().get(i);
			if(item.getCategory().contains(key)) {
				System.out.println(i + ". " + "카테고리 " + item.getCategory() + " 제목 : " + item.getTitle() + 
						" 설명 : " + item.getDesc() + " 중요도 : " + item.getImportance() + " 생성된 시간 : " +item.getCurrent_date() + 
						" 마감일자 :" + item.getDue_date() + " 진행률 : " + item.getCompleteness() + " 완료 여부 : " + item.getIs_done());
				gaesu++;
			}
		}
		
		System.out.printf("%d개의 항목을 로드하였습니다.\n", gaesu);
	}
	
	public static void setCompleteness(TodoList l) {
		int idx;
		int i = 0;

		
		System.out.print("진행율 정보를 바꿀 일정 리스트의 번호를 입력하세요 > ");
		Scanner sc = new Scanner(System.in);
		
		idx = sc.nextInt();
		
		System.out.print("진행율을 얼마로 설정하시겠습니까? > ");
		int set = sc.nextInt();
		
		l.completenessItem(idx, set);
		
		if(set == 100)
			l.isDoneItem(idx, 1);
	}
	
	public static void setIsDone(TodoList l) {
		
		System.out.print("완료로 바꿀 정보의 번호를 입력하시오 (복수 선택 가능) > ");
		Scanner sc = new Scanner(System.in);
		
		String indexStr = sc.nextLine();
		
		String[]index = indexStr.split(" ");
		
		System.out.print("완료로 바꾸시려면 1번, 미완료로 바꾸시려면 0번을 입력하세요 > ");
		int set = sc.nextInt();
		for(int i=0; i<index.length; i++) {
			l.isDoneItem(i, Integer.parseInt(index[i]));
		};
		
		System.out.println("저장되었습니다.");
	}
	
	public static void ls_cate(TodoList l) {
		List<String> list = new ArrayList<>();
		int count = 0;
		for(TodoItem item : l.getList()) {
			list.add(item.getCategory());
			count++;
		}
		
		List<String> cate = list.stream().distinct().collect(Collectors.toList());
		
		for(int i=0; i<cate.size(); i++) {
			System.out.print(cate.get(i));
			if(i != cate.size()-1)
				System.out.print(" \\ ");
		}
		System.out.printf("\n총 %d개의 카테고리가 등록되어 있습니다.\n", cate.size());
	}
	
	public static void listCateAll(TodoList l) {
		int count = 0;
		for (String item : l.getCategories()) {
			System.out.print(item + " ");
			count++;
		}
	}
	
	public static void findCateList(TodoList l, String cate) {
		int count=0;
		for(TodoItem item : l.getListCategory(cate)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("\n총 %d개의 항목을 찾았습니다.\n", count);
	}
	
	public static void listAlls(TodoList l, String orderby, int ordering) {
		System.out.printf("[전체 목록, 총 %d개]\n", l.getCount()+1);
		for (TodoItem item : l.getOrderedList(orderby, ordering)) {
			System.out.println(item.toString());
		}
	}
	
//	public static String getLine(TodoList l, int index) {
//		TodoItem item = l.getList().get(index);
//		
//		return "[" + item.getCategory() + "] " + item.getTitle() + " " + item.getDesc() + " - " + item.getCurrent_date().substring(0,10) + " - " + item.getDue_date();
//		
//	}
}
