package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class QuestionXML {
	
	 Document document  = null;
	
	/*
	 * �÷������ڹ��챣����ݵ�XML�ļ�
	 * ����ֵΪ�Ѿ������õ�Document�ļ�
	 */
	public Document constructXML() throws Exception 
	{
		if(document !=null)
			return document;
		document = new Document();
		
		Element root = new Element("questions");
		
		document.addContent(root);
		
		XMLOutputter output = new XMLOutputter(Format.getPrettyFormat()
				.setIndent("    ").setEncoding("utf-8"));
		output.output(document, new FileWriter("contact.xml"));
		
		return document;
	}
	
	/*
	 * �÷������ڶ�ȡ���������Ĵ洢�������⼰�𰸵�XML�ļ�
	 * �ɹ��򷵻ع���XML�ļ���Document����
	 * ʧ���򷵻�null
	 */
	
	public Document getXML(String fileName) {
		
		try {
			FileInputStream in = new FileInputStream(new File(fileName));  
			Reader read = new InputStreamReader(in,"utf-8");  
			return document = new SAXBuilder().build(read);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
	/*
	 * �÷������ڻ�ȡ��������������Ŀ
	 * ����������������Ŀ
	 */
	public List<String> getTitle() throws Exception {
		
		if(this.document == null)
			constructXML();

		Element questions = document.getRootElement();
		
		List<String> result = new ArrayList<String>();
		List<Element> question = questions.getChildren();
		for(Iterator<Element> ite = question.iterator();ite.hasNext(); )
		{
			Element q = ite.next();
			result.add(q.getChild("title").getValue());
		}
		return result;
	}
	/*
	 * �÷������ڻ�ȡ�����������Ĵ�
	 * ���ҳɹ����ذ��ͳ�ƽ���List����,
	 * ���򷵻�null
	 */
	public List<String> getResult(String title) throws Exception{
		
		constructXML();

		Element questions = document.getRootElement();
		
		List<String> result = new ArrayList<String>();
		
		List<Element> question = questions.getChildren();
		
		for(Iterator<Element> ite = question.iterator();ite.hasNext();)
		{
			Element q = ite.next();
			if(title.equals(q.getChild("title").getValue()))
			{
				return result;
			}
		}
		return null;	
	}
	
}
