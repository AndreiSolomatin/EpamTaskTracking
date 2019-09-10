<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Project Management Application</title>
</head>
<body>
<jsp:include page="Menu.jsp" />
	<center>
		<h1>Project Management</h1>
		<h2>
			<a href="newproject">Add New Project</a> &nbsp;&nbsp;&nbsp; <a href="listproject">List All Projects</a>
		</h2>
	</center>
	<div align="center">
		<c:if test="${project != null}">
			<form action="updateproject" method="post">
		</c:if>
		<c:if test="${project == null}">
			<form action="insertproject" method="post">
		</c:if>
		<table border="1" cellpadding="5">
			<caption>
				<h2>
					<c:if test="${project != null}">
            			Edit Project
            		</c:if>
					<c:if test="${project == null}">
            			Add New Project
            		</c:if>
				</h2>
			</caption>
				<c:if test="${project != null}">
					<input type="hidden" name="id"
						value="<c:out value='${project.id}' />" />
				</c:if>
			<tr>
				<th>Project Name:</th>
				<td><input type="text" name="name" size="45"
					value="<c:out value='${project.name}' />" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Save" /></td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>