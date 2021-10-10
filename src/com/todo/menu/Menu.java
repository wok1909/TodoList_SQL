//package com.todo.menu;
//public class Menu {
//
//    public static void displaymenu()
//    {
//        System.out.println();
//        System.out.println("새로운 항목을 추가하려면 add 입력");
//        System.out.println("기존의 항목을 삭제하려면 del 입력");
//        System.out.println("기존의 항목을 수정하려면 edit 입력");
//        System.out.println("현재의 항목을 조회하려면 ls 입력");
//        System.out.println("현재의 항목을 이름순으로 오름차순 정렬을 하려면 ls_name 입력");
//        System.out.println("현재의 항목을 이름순으로 내림차순 정렬을 하려면 ls_name_desc 입력");
//        System.out.println("현재의 항목을 날짜순으로 정렬을 하려면 ls_date 입력");
//        System.out.println("현재의 항목을 제목이나 설명으로 찾으려면 find 입력");
//        System.out.println("카테고리 검색기능을 사용하려면 find_cate 입력");
//        System.out.println("프로그램을 종료하려면 exit 입력");
//    }
//}
package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
    	System.out.println();
    	System.out.println("<ToDoList 관리 명령어 사용법>");
    	System.out.println("add - 항목 추가");
    	System.out.println("del - 항목 삭제");
    	System.out.println("edit - 항목 수정");
    	System.out.println("find <keyword> - 제목,내용내 키워드 검색");
    	System.out.println("find_cate <keyword> - 카테고리내 키워드 검색");
    	System.out.println("ls - 전체 목록");
    	System.out.println("ls_cate - 전체 목록의 카테고리 정렬");
    	System.out.println("ls_name_asc - 제목순 정렬");
    	System.out.println("ls_name_desc - 제목역순 정렬");
    	System.out.println("ls_date - 날짜순 정렬");
    	System.out.println("ls_date_desc - 날짜역순 정렬");
    	System.out.println("exit - 종료");
    }
    
    public static void prompt()
    {
    	System.out.println();
    	System.out.print("Command > "); 
    }
}
