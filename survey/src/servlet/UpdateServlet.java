package servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class UpdateServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = new String(request.getParameter("title").getBytes("iso8859-1"), "utf-8");
		String[] results = request.getParameterValues("option");
		if(null ==title || "".equals(title))
		{
			//标题不能为空
			request.setAttribute("error", "标题不能为空");
			request.getRequestDispatcher("updateError.jsp").forward(request, response);
			return;
		}
		if(null == results || "".equals(results))
		{
			//选项不能为空
			request.setAttribute("error", "选项不能为空");
			request.getRequestDispatcher("updateError.jsp").forward(request, response);
			return;
		}
		//转换字符编码
		List<String> transResult = new ArrayList<String>();
		for(int i = 0; i < results.length; i++)
		{
			transResult.add(new String(results[i].getBytes("iso8859-1"), "utf-8"));
		}
		QuestionXML qsXML = new QuestionXML();
		Document doc = qsXML.getXML("contact.xml");
		Element questions = doc.getRootElement();
		List<Element> question = questions.getChildren();
		
		for(int i=0;i<question.size();i++)
		{
			if(title.equals(question.get(i).getChild("title").getValue()))
			{
				//更新题库信息
				List<Element> answer = question.get(i).getChild("answer").getChildren();
				List<Element> result = question.get(i).getChild("result").getChildren();
				for(int ti=0; ti < transResult.size(); ti++)
					for(int ai=0; ai < answer.size(); ai++)
					{
						if(transResult.get(ti).equals(answer.get(ai).getValue()))
						{
							Integer time = Integer.parseInt(result.get(ai).getValue());
							time++;//统计结果加1
							result.get(ai).setText(time.toString());
						}
					}
			}
		}
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
