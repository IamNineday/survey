package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class InputServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		if(null == request.getParameter("question") || "".equals(request.getParameter("question")))
		{
			request.setAttribute("error", "题目不能为空");
			request.getRequestDispatcher("addError.jsp").forward(request, response);
			return;
		}
		if(null == request.getParameter("type") || "".equals(request.getParameter("type")))
		{
			request.setAttribute("error", "请选择题目类型");
			request.getRequestDispatcher("addError.jsp").forward(request, response);
			return;
		}
		if(null == request.getParameter("a1") || "".equals(request.getParameter("a1"))||
		   null == request.getParameter("a2") || "".equals(request.getParameter("a2"))||
		   null == request.getParameter("a3") || "".equals(request.getParameter("a3"))||
		   null == request.getParameter("a4") || "".equals(request.getParameter("a4")))
		{
			request.setAttribute("error", "选项不能为空");
			request.getRequestDispatcher("addError.jsp").forward(request, response);
			return;
		}
		QuestionXML qsXML = new QuestionXML();
		if(!new File("contact.xml").exists())
			try {
				qsXML.constructXML();
			} catch (Exception e) {
				e.printStackTrace();
			}
		String newQuestion =new String(request.getParameter("question").getBytes("iso8859-1"),"utf-8");
		String type = new String(request.getParameter("type").getBytes("iso8859-1"),"utf-8");
		String option1 = new String(request.getParameter("a1").getBytes("iso8859-1"),"utf-8");
		String option2 = new String(request.getParameter("a2").getBytes("iso8859-1"),"utf-8");
		String option3 = new String(request.getParameter("a3").getBytes("iso8859-1"),"utf-8");
		String option4 = new String(request.getParameter("a4").getBytes("iso8859-1"),"utf-8");
			
		Document doc = qsXML.getXML("contact.xml");
		Element questions = doc.getRootElement();
		List<Element> question = questions.getChildren();
		
		for(int i=0;i<question.size();i++)
		{
			if(newQuestion.equals(question.get(i).getChild("title").getValue()))
			{
				//问题重复
				request.setAttribute("error", "该问题已存在！");
				request.getRequestDispatcher("addError.jsp").forward(request, response);
				return;
			}
		}
		questions.addContent(new Element("question").setName("q1")
				 		.addContent(new Element("title").setText(newQuestion))
				 		.addContent(new Element("type").setText(type))
				 		.addContent(new Element("answer")
						 		.addContent(new Element("a").setText(option1))
						 		.addContent(new Element("b").setText(option2))
						 		.addContent(new Element("c").setText(option3))
						 		.addContent(new Element("d").setText(option4)))
						.addContent(new Element("result")
								.addContent(new Element("a").setText("0"))
								.addContent(new Element("b").setText("0"))
								.addContent(new Element("c").setText("0"))
								.addContent(new Element("d").setText("0"))
						));
		XMLOutputter output = new XMLOutputter(Format.getPrettyFormat()
			.setIndent("    "));
	output.output(doc, new FileOutputStream("contact.xml"));
	response.sendRedirect("surveyIndex.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		this.doGet(request, response);
	}
	
	

}
