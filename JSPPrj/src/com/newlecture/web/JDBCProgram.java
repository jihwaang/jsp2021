package com.newlecture.web;

import java.util.List;
import java.util.Scanner;

import com.newlecture.web.entity.Member;
import com.newlecture.web.service.MemberService;

public class JDBCProgram {
	public static void main(String[] args) {

		/*
		 * nicname : _newlec -----[ 검색된 데이터 ] ------------------ id:1, nicname:newlec,
		 * .......
		 */
		System.out.print("nickname: ");
		Scanner scan = new Scanner(System.in);
		String nickname = scan.nextLine();

		System.out.println("-------[검색된 데이터]-------");

		// String sql = "SELECT * FROM MEMBER WHERE NICNAME=" + "'" + nickname +"'";
		String sql = String.format("SELECT * FROM MEMBER WHERE NICNAME='%s'", nickname);
		scan.close();
		
		MemberService memberService = new MemberService();
		List<Member> list = memberService.getList();
		
		

//		String classPath = "oracle.jdbc.OracleDriver";
//		String url = "jdbc:oracle:thin:@1.222.116.188:1521/xepdb1";
//		//String query = "SELECT * FROM MEMBER WHERE 1=1 AND ";
//		System.out.println(sql);
//		try {
//			Class.forName(classPath);
//			Connection conn = DriverManager.getConnection(url, "NEWLEC", "11111");
//			Statement st = conn.createStatement();
//			ResultSet rs = st.executeQuery(sql);
//
//			// 멤버 데이터
//			while (rs.next()) {
//				int id = rs.getInt("id");
//				String nicName = rs.getString("nicname");
//				String pwd = rs.getString("pwd");
//
//				System.out.printf("id:%d, nicname:%s, pwd:%s\n", id, nicName, pwd);
//			}
//
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
		for(int i = 0; i < list.size(); i++) {
			Member member = list.get(i);
			int id = member.getId();
			String nicName = member.getNicName();
			String pwd = member.getPwd();
			
			System.out.printf("id:%d, nicname:%s, pwd:%s\n", id, nicName, pwd);
		}
	}
}
