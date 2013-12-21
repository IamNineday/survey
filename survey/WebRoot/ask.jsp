<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ask.jsp' starting page</title>
    
	<script type="text/javascript">
		function validate()
		{
			var title = document.getElementsByName("title")[0];
			var option = document.getElementsByName("option");
			if(null == title.value || "" == title.value)
				{
					alert("题目不能为空！");
					return false;
				}
			if(null == option.value || option.length < 1)
				{
					alert("请按要求选择选项！");
					return false;
				}
			return true;		
		}
	</script>
  </head>
  
  <body>
    <form onsubmit="return validate()" action="UpdateServlet">
    
		题目：<%=request.getAttribute("title") %>(<%=request.getAttribute("type") %>)<br>
		<%
		String type = new String();
		if("单选".equals(request.getAttribute("type")))
			type = "radio";
		else if ("多选".equals(request.getAttribute("type")))
			type = "checkbox";
		List<String> answer = (List<String>)request.getAttribute("answer");
		for(int i=0; i < answer.size(); i++)
		{%>
		<input type="<%=type %>" name="option" value="<%=answer.get(i) %>"><%=answer.get(i) %><br>
		<%} %>
	
		<input type="hidden" name="title" value="<%=request.getAttribute("title") %>">
		<input type="submit" value="提   交">

	</form>
  </body>
</html>
