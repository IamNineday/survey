package xml;

import java.io.FileWriter;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import servlet.SurveyServlet;

public class Test
{
	public static void main(String[] args) throws Exception
	{
		Document document = new Document();

		Element root = new Element("��ϵ���б�").setAttribute(new Attribute("��˾",
				"A����"));

		document.addContent(root);
		
		Element contactPerson = new Element("��ϵ��");
		
		root.addContent(contactPerson);

		contactPerson
				.addContent(new Element("����").setText("����"))
				.addContent(new Element("��˾").setText("A��˾"))
				.addContent(new Element("�绰").setText("021-55556666"))
				.addContent(
						new Element("��ַ")
								.addContent(new Element("�ֵ�").setText("5��"))
								.addContent(new Element("����").setText("�Ϻ�"))
								.addContent(new Element("ʡ��").setText("�Ϻ���")));

		XMLOutputter output = new XMLOutputter(Format.getPrettyFormat()
				.setIndent("    ").setEncoding("gbk"));

		output.output(document, new FileWriter("contact.xml"));
		
		
	}
}
