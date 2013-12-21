<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'add.jsp' starting page</title>
    
   	<script type="text/javascript">
   		function validate()
   		{
   			var title = document.getElementsByName("question")[0];
			var single = document.getElementById("single");
			var complex = document.getElementById("complex");
   			var a1 = document.getElementsByName("a1")[0];
   			var a2 = document.getElementsByName("a2")[0];
   			var a3 = document.getElementsByName("a3")[0];
   			var a4 = document.getElementsByName("a4")[0];
   			if(null == title.value || "" == title.value)
   			{
   				alert("标题不能为空！");
   				return false;
   			}
   			if(!single.checked && !complex.checked) 
   			{
   				alert("请选择题目类型");
   				return false;
   			}
   			
   			if(null == a1.value || "" == a1.value ||
   					null == a2.value || "" == a2.value ||
   					null == a3.value || "" == a3.value ||
   					null == a4.value || "" == a4.value )
   			{
   				alert("请输入选项！");
   				return false;
   			}	
   		return true;	
   			
   		}
   
   	</script>

  </head>
  
  <body>
  		<form onsubmit=" return validate()" action="InputServlet">
    	问题：<input type="text" name="question"><br>
    	类型:单选<input type="radio" name="type" id="single" value="单选">&nbsp;&nbsp;&nbsp;
    		多选<input type="radio" name="type" id="complex" value="多选"><br>
    	选项：<br>
    		1、<input type="text" name="a1"><br>
    		2、<input type="text" name="a2" ><br>
    		3、<input type="text" name="a3" ><br>
    		4、<input type="text" name="a4" ><br>
    		
    	<input type="submit" value="确认提交"> 
    	
    	</form>
    	
 
    	
  </body>
</html>
