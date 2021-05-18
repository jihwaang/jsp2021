package com.newlecture.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.newlecture.web.entity.Member;

public class MemberService {
	public List<Member> getList() {
		List<Member> list = new ArrayList<>();
		
		String classPath = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@1.222.116.188:1521/xepdb1";
//		String sql = String.format("SELECT * FROM MEMBER WHERE NICNAME='%s'", nickname);
		String sql = "SELECT * FROM MEMBER";
		//String query = "SELECT * FROM MEMBER WHERE 1=1 AND ";
		System.out.println(">> SQL Executed : "+sql);
		try {
			Class.forName(classPath);
			Connection conn = DriverManager.getConnection(url, "NEWLEC", "11111");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);

			// 멤버 데이터
			while (rs.next()) {
				int id = rs.getInt("id");
				String nicName = rs.getString("nicname");
				String pwd = rs.getString("pwd");
				
//				System.out.printf("id:%d, nicname:%s, pwd:%s\n", id, nicName, pwd);
				Member member = new Member();
				member.setId(id);
				member.setNicName(nicName);
				member.setPwd(pwd);
				
				list.add(member); 
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
