package com.newlecture.web.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;

@WebServlet("/api/notice/list")
public class NoticeList extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		PrintWriter out;
		try {
			out = resp.getWriter();
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html; charset=UTF-8");
			String p = req.getParameter("p");
			String f = req.getParameter("f");
			String q = req.getParameter("q");

			int page = 1;
			String field = "title";
			String query = "";

			if (p != null && !p.equals("")) {
				page = Integer.parseInt(p);
			}

			if (f != null && !f.equals("")) {
				field = f;
			}

			if (q != null && !q.equals("")) {
				query = q;
			}
			NoticeService noticeService = new NoticeService();
//		List<Map<String, Object>> list = noticeService.getList(1, "title","");
			List<Notice> list = noticeService.getList(page, field, query);

			Gson gson = new Gson();

			String json = gson.toJson(list);

			out.println(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
