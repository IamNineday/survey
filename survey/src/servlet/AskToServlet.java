package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom2.Document;
import org.jdom2.Element;

public class AskToServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String title = new String(request.getParameter("title").getBytes("iso8859-1"), "utf-8");
		//判断传过来的字符串是否合法
		if(null == title|| "".equals(title))
		{
			//请选择你要回答的问题
			request.setAttribute("error", new String("请选择你要回答的问题"));
			request.getRequestDispatcher("askError.jsp").forward(request, response);
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
				//进入该题答题界面
				List<Element> ans = question.get(i).getChild("answer").getChildren();
				List<String> answer = new ArrayList<String>();
				for(Iterator<Element> it = ans.iterator();it.hasNext();)
				{//获取选项值
					answer.add(it.next().getValue());
				}
				request.setAttribute("title", title);
				request.setAttribute("type", question.get(i).getChild("type").getValue());
				request.setAttribute("answer", answer);
				request.getRequestDispatcher("ask.jsp").forward(request, response);
				return;
			}
		}
		
		//没有在题库中找到题目
		request.setAttribute("error", new String("没有在题库中找到题目"));
		request.getRequestDispatcher("askError.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
