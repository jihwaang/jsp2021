package com.newlecture.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.newlecture.web.entity.Notice;

public class NoticeService {
	public List<Notice>/* <Map<String, Object>> */ getList() {
		return getList(1, "title", "");
	}
	
	public List<Notice>/* <Map<String, Object>> */ getList(int page, String category, String keyword) {

		System.out.println(">> page : " + page);
		System.out.println(">> category : " + category);
		System.out.println(">> keyword : " + keyword);
		//List<Notice> list = new ArrayList<>();
		List<Notice>/* <Map<String, Object>> */ list = new ArrayList<>();
		
		int pageSize = 10;
		int startNumber = 1; //1+(page-1)*pageSize;
		int lastNumber = page * pageSize;
		startNumber = lastNumber - (pageSize-1);
		
		String url = "jdbc:oracle:thin:@hi.namoolab.com:1521/xepdb1";
		String _id = "NEWLEC";
		String pwd = "11111";
		String classPath = "oracle.jdbc.OracleDriver";
		//String sql = "SELECT ID, TITLE, WRITER_ID, CONTENT, TO_CHAR(REGDATE, 'YYYY-MM-DD') REGDATE, HIT, FILES FROM NOTICE";
		String sql = "SELECT ID, TITLE, WRITER_ID, CONTENT, TO_CHAR(REGDATE, 'YYYY-MM-DD') REGDATE, HIT, FILES FROM ("
		           + " SELECT ROWNUM NUM, N.* "
		           + " FROM ("
		           + "  SELECT * "
		           + "  FROM NOTICE" 
		           + "  WHERE "+category+" LIKE '%"+keyword+"%'" 
		           +"   ORDER BY REGDATE DESC" 
		           +"  ) N" 
		           + ")"
		           + "WHERE NUM BETWEEN "+startNumber+" AND "+lastNumber;
		System.out.println(">> SQL Executed : "+sql);
		
		try {
			Class.forName(classPath);
			Connection conn = DriverManager.getConnection(url, _id, pwd);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String writerId = rs.getString("writer_id");
				String content = rs.getString("content");
				Date regDate = rs.getDate("regDate");
				int hit = rs.getInt("hit");
				String files = rs.getString("files");
				
				Notice notice = new Notice();
				notice.setId(id);
				notice.setTitle(title);
				notice.setWriterId(writerId);
				notice.setContent(content);
				notice.setRegDate(regDate);
				notice.setHit(hit);
				notice.setFiles(files);
//				
//				System.out.println(notice.toString());
				
//				int columnCount = rsmd.getColumnCount();
//				Map<String, Object> row = new HashMap<>();
//				for(int i = 1; i <= columnCount; i++) {
//					row.put(rsmd.getColumnName(i), rs.getObject(i));
//				}
//				list.add(row);
				
				list.add(notice);
			}
			rs.close();
			st.close();
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		System.out.println(list);
		return list;
	}
	
	public Notice get(int id) {
		Notice notice = null;
		String url = "jdbc:oracle:thin:@hi.namoolab.com:1521/xepdb1";
		String user = "NEWLEC";
		String pw = "11111";
		String sql = "SELECT * FROM NOTICE WHERE ID = "+id;
		System.out.println(">>sql : "+sql);
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection conn = DriverManager.getConnection(url, user, pw);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				int _id = rs.getInt("id");
				String title = rs.getString("title");
				String writerId = rs.getString("writer_id");
				String content = rs.getString("content");
				Date regDate = rs.getDate("regDate");
				int hit = rs.getInt("hit");
				String files = rs.getString("files");
				
				notice = new Notice();
				notice.setId(_id);
				notice.setTitle(title);
				notice.setWriterId(writerId);
				notice.setContent(content);
				notice.setRegDate(regDate);
				notice.setHit(hit);
				notice.setFiles(files);
			}
			
			rs.close();
			st.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(notice.toString());
		return notice;
	}
	
	public Map<String, Object> getCount(String category, String keyword) {
		//int count = 0;
		Map<String, Object> count = new HashMap<>();;
		
		String url = "jdbc:oracle:thin:@hi.namoolab.com:1521/xepdb1";
		String id = "NEWLEC";
		String pwd = "11111";
		String classPath = "oracle.jdbc.OracleDriver";
		String sql = "SELECT COUNT(ID) COUNT FROM NOTICE WHERE "+category+" LIKE '%"+keyword+"%'";
		System.out.println(">> SQL Executed : "+sql);
		
		try {
			Class.forName(classPath);
			Connection conn = DriverManager.getConnection(url, id, pwd);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while(rs.next()) {
				for(int i = 1; i <= columnCount; i++) {
					count.put(rsmd.getColumnName(i), rs.getObject(i));
				}
				//count = rs.getInt("COUNT");
				System.out.println(">>count : " + count.get("COUNT"));				
			}
			
			rs.close();
			st.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return count;
	}

	public int update(Notice notice) {
		int result = 0;
		String url = "jdbc:oracle:thin:@hi.namoolab.com:1521/xepdb1";
		String id = "NEWLEC";
		String pwd = "11111";
		String classPath = "oracle.jdbc.OracleDriver";
		String sql = "UPDATE NOTICE SET TITLE = ?, CONTENT = ?, HIT = ?, FILES = ? WHERE ID = ?";
		
		try {
			Class.forName(classPath);
			Connection conn = DriverManager.getConnection(url, id, pwd);
			
			
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, notice.getTitle());
			st.setString(2, notice.getContent());
			st.setInt(3, notice.getHit());
			st.setString(4, notice.getFiles());
			st.setInt(5, notice.getId());
			
			result = st.executeUpdate();
			
			st.close();
			conn.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public int insert(Notice notice) {
		int result = 0;
		String url = "jdbc:oracle:thin:@hi.namoolab.com:1521/xepdb1";
		String user = "NEWLEC";
		String pwd = "11111";
		String classPath = "oracle.jdbc.OracleDriver";
		String sql = "INSERT INTO NOTICE(TITLE, WRITER_ID, CONTENT) VALUES (?, ?, ?)";
		System.out.println(">> SQL ==> " + sql);
		
		try {
			Class.forName(classPath);
			Connection conn = DriverManager.getConnection(url, user, pwd);
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, notice.getTitle());
			st.setString(2, notice.getWriterId());
			st.setString(3, notice.getContent());
			
			result = st.executeUpdate();
			
			st.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int delete(int id) {
		int result = 0;
		String url = "jdbc:oracle:thin:@hi.namoolab.com:1521/xepdb1";
		String user = "NEWLEC";
		String pwd = "11111";
		String classPath = "oracle.jdbc.OracleDriver";
		String sql = "DELETE FROM NOTICE WHERE ID = ?";
		System.out.println(">> SQL execute ==> " + sql);
		
		try {
			Class.forName(classPath);
			Connection conn = DriverManager.getConnection(url, user, pwd);
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);
			
			result = st.executeUpdate();
			
			st.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
}
