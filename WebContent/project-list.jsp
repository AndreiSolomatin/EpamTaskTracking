<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Project Management Application</title>
</head>
<body>
<jsp:include page="Menu.jsp" />
	<center>
		<h1>Project Management</h1>
        <h2>
        	<a href="newproject">Add New Project</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="listproject">List All Projects</a>
        	
        </h2>
	</center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Projects</h2></caption>
            <tr>
                <th>ID</th>
                <th>Name</th>
            </tr>
            <c:forEach var="project" items="${listProject}">
                <tr>
                    <td><c:out value="${project.id}" /></td>
                    <td><c:out value="${project.name}" /></td>
                    <td>
                    	<a href="editproject?id=<c:out value='${project.id}' />">Edit</a>
                    	&nbsp;&nbsp;&nbsp;&nbsp;
                    	<a href="deleteproject?id=<c:out value='${project.id}' />">Delete</a>                    	
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>	
</body>
</html>