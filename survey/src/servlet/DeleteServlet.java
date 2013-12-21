package servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class DeleteServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String title = new String(request.getParameter("title").getBytes("iso8859-1"), "utf-8");
		//判断传过来的字符串是否合法
		if(null == title|| "".equals(title))
		{
			//请选择你要删除的问题
			request.setAttribute("error", new String("请选择你要删除的问题"));
			request.getRequestDispatcher("deleteError.jsp").forward(request, response);
			return;
		}
		
		QuestionXML qsXML = new QuestionXML();
		Document doc = qsXML.getXML("contact.xml");
		Element questions = doc.getRootElement();
		List<Element> question = questions.getChildren();
		
		boolean flag = false;//标志是否查找成功
		for(int i=0; i < question.size(); i++)
		{	
			
			if(title.equals(question.get(i).getChild("title").getValue()))
			{
				question.remove(i);
				flag=true;
				break;
			}
		}
		if(!flag){
			request.setAttribute("error", new String("题库没有你要删除的问题"));
			request.getRequestDispatcher("deleteError.jsp").forward(request, response);
			return;
		}
		
		XMLOutputter output = new XMLOutputter(Format.getPrettyFormat()
				.setIndent("    "));
		output.output(doc, new FileOutputStream("contact.xml"));
		
		response.sendRedirect("surveyIndex.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

}
