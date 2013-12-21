package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom2.Document;
import org.jdom2.Element;

public class ResultToServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = new String(request.getParameter("title").getBytes("iso8859-1"), "utf-8");
		//判断传过来的字符串是否合法
		if(null == title|| "".equals(title))
		{
			//请选择你要回答的问题
			request.setAttribute("error", new String("请选择你要查看的问题"));
			request.getRequestDispatcher("resultToError.jsp").forward(request, response);
			return;
		}
		QuestionXML qsXML = new QuestionXML();
		Document doc = qsXML.getXML("contact.xml");
		Element questions = doc.getRootElement();
		List<Element> question = questions.getChildren();
		
		for(int i=0; i < question.size(); i++)
		{
			if(title.equals(question.get(i).getChild("title").getValue()))
			{	
				request.setAttribute("title", title);
				List<Element> result = question.get(i).getChild("result").getChildren();
				request.setAttribute("a", result.get(0).getValue());
				request.setAttribute("b", result.get(1).getValue());
				request.setAttribute("c", result.get(2).getValue());
				request.setAttribute("d", result.get(3).getValue());
				request.getRequestDispatcher("result.jsp").forward(request, response);
				return;
			}
			
		}
			//请选择你要回答的问题
			request.setAttribute("error", new String("题库中没有你要查看的问题！"));
			request.getRequestDispatcher("resultToError.jsp").forward(request, response);
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
