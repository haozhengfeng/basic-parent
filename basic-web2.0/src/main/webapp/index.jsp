<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%String s = session.getId(); //获取session ID号  %>  
<%=s%>  
<%=(String)session.getAttribute("clj1")%>  
<%session.setAttribute("clj1", "clj20150528");%>  
</body>
</html>