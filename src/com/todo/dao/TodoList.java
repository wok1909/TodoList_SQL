package com.todo.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.todo.service.DbConnect;
import com.todo.service.TodoSortByDate;
import com.todo.service.TodoSortByName;

public class TodoList {
	Connection conn;

	private List<TodoItem> list;

	public TodoList() {
		this.conn = DbConnect.getConnection();
		this.list = new ArrayList<TodoItem>();
	}
	
	public int getCount() {
    	Statement stmt;
    	int count = 0;
    	try {
    		stmt = conn.createStatement();
    		String sql = "SELECT count(id) FROM list;";
    		ResultSet rs = stmt.executeQuery(sql);
    		rs.next();
    		count = rs.getInt("count(id)");
    		stmt.close();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return count;
    }

	public int addItem(TodoItem t) {
		String sql = "insert into list (title, memo, importance, category, current_date, due_date, completeness, is_done)" + " values (?,?,?,?,?,?,?,?);";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getTitle());
			pstmt.setString(2, t.getDesc());
			pstmt.setInt(3, t.getImportance());
			pstmt.setString(4, t.getCategory());
			pstmt.setString(5, t.getCurrent_date());
			pstmt.setString(6, t.getDue_date());
			pstmt.setInt(7, t.getCompleteness());
			pstmt.setBoolean(8, t.getIs_done());
			count = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public int deleteItem(int index) {
		String sql = "delete from list where id=?;";
		PreparedStatement pstmt;
		int count =0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, index);
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public int updateItem(TodoItem t) {
		String sql = "update list set title=?, memo=?, importance=?, category=?, current_date=?, due_date=?, completeness=?, is_done=?" + " where id = ?;";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getTitle());
			pstmt.setString(2, t.getDesc());
			pstmt.setInt(3, t.getImportance());
			pstmt.setString(4, t.getCategory());
			pstmt.setString(5, t.getCurrent_date());
			pstmt.setString(6, t.getDue_date());
			pstmt.setInt(7, t.getCompleteness());
			pstmt.setBoolean(8, t.getIs_done());
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public ArrayList<TodoItem> getList() {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM list";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("memo");
				int importance = rs.getInt("importance");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_Date");
				int completeness = rs.getInt("completeness");
				boolean is_done = rs.getBoolean("is_done");
				TodoItem t = new TodoItem(id, title, description, importance, category, due_date, completeness, is_done);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<TodoItem> getLists(String keyword) {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		keyword = "%"+keyword+"%";
		try {
			String sql = "SELECT * FROM list WHERE title like ? or memo like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2, keyword);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("memo");
				int importance = rs.getInt("importance");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_Date");
				int completeness = rs.getInt("completeness");
				boolean is_done = rs.getBoolean("is_done");
				TodoItem t = new TodoItem(id, title, description, importance, category, due_date, completeness, is_done);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void sortByName() {
		Collections.sort(list, new TodoSortByName());

	}

	public void listAll() {
		System.out.println("\n"
				+ "inside list_All method\n");
		for (TodoItem myitem : list) {
			System.out.println(myitem.getTitle() + myitem.getDesc());
		}
	}
	
	public void reverseList() {
		Collections.reverse(list);
	}

	public void sortByDate() {
		Collections.sort(list, new TodoSortByDate());
	}

	public int indexOf(TodoItem t) {
		return list.indexOf(t);
	}

	public Boolean isDuplicate(String title) {
		for (TodoItem item : list) {
			if (title.equals(item.getTitle())) return true;
		}
		return false;
	}
	public void importData(String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;
			String sql = "insert into list (title, memo, importance, category, current_date, due_date, completeness, is_done)" + " values (?,?,?,?,?,?,?,?);";
			int records = 0;
			while((line = br.readLine())!= null) {
				StringTokenizer st = new StringTokenizer(line, "##");
				String category = st.nextToken();
				int importance = Integer.parseInt(st.nextToken());
				String title = st.nextToken();
				String description = st.nextToken();
				String current_date = st.nextToken();
				String due_date = st.nextToken();
				int completeness = Integer.parseInt(st.nextToken());
				boolean is_done = Boolean.parseBoolean(st.nextToken());
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, title);
				pstmt.setString(2, description);
				pstmt.setInt(3, importance);
				pstmt.setString(4, category);
				pstmt.setString(5, current_date);
				pstmt.setString(6, due_date);
				pstmt.setInt(7, completeness);
				pstmt.setBoolean(8, is_done);
				int count = pstmt.executeUpdate();
				if (count > 0) records++;
				pstmt.close();
			}
			System.out.println(records + " records read!!");
		} catch (Exception e) {
			e.printStackTrace();		
		}
	}
	
	public ArrayList<String> getCategories(){
		ArrayList<String> list = new ArrayList<String>();
		Statement stmt;
		try {
			int i = 0;
			stmt = conn.createStatement();
			String sql = "SELECT DISTINCT category FROM list";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				i++;
				String category = rs.getString("category");
				System.out.println(category);
				
			}
			System.out.printf("\n총 %d개의 카테고리가 등록되어 있습니다. \n", i);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<TodoItem> getListCategory(String keyword){
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		try {
			String sql = "SELECT * FROM list WHERE category = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("memo");
				int importance = rs.getInt("importance");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_Date");
				int completeness = rs.getInt("completeness");
				Boolean is_done = rs.getBoolean("is_done");
				TodoItem t = new TodoItem(id, title, description, importance, category, due_date, completeness, is_done);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<TodoItem> getOrderedList(String orderby, int ordering) {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM list ORDER BY " + orderby;
			if (ordering==0)
				sql += " desc";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("memo");
				int importance = rs.getInt("importance");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_Date");
				int completeness = rs.getInt("Completeness");
				boolean is_done = rs.getBoolean("is_done");
				TodoItem t = new TodoItem(id, title, description, importance, category, due_date, completeness, is_done);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int completenessItem(int index, int completeness) {
		String sql = "update list set completeness = ?" + " where id = ?;";
		PreparedStatement pstmt;
		int sign = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, completeness);
			pstmt.setInt(2, index);
			sign = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sign;
	}
	
	
	public int isDoneItem(int index, int is_done) {
		String sql = "update list set is_done = ?" + " where id = ?;";
		PreparedStatement pstmt;
		int sign = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, is_done);
			pstmt.setInt(2, index);
			sign = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sign;
	}
	
}
