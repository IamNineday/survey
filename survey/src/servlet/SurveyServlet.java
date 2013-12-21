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

public class SurveyServlet extends HttpServlet {

	Document document = null;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		QuestionXML qsXML = new QuestionXML();
		qsXML.getXML("contact.xml");
		try {
			request.setAttribute("title", qsXML.getTitle());
		} catch (Exception e) {}
		
		request.getRequestDispatcher("questions.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

	
}
