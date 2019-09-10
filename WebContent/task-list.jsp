<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>User Management Application</title>
</head>
<body>
	<jsp:include page="Menu.jsp" />
	<center>
		<h1>Task Management</h1>
		<h2>
			<a href="newtask">Add New Task</a> &nbsp;&nbsp;&nbsp; <a
				href="listtask">List All Tasks</a>

		</h2>
	</center>
	<div align="center">
		<table border="1" cellpadding="5">
			<caption>
				<h2>List of Tasks</h2>
			</caption>
			<div align="center">
				<form action="filtertask">
					User Filter <select name="userfilterdid">
						<c:forEach items="${userfilterdList}" var="user">
							<option value="${user.id}"
								    							<c:if test="${user.id eq selectedUserFilter}">selected="selected"</c:if>>
								     ${user.name}</option>
						</c:forEach>
					</select> Project Filter <select name="projectfilterid">
						<c:forEach items="${projectfilterList}" var="project">
							<option value="${project.id}"
								    							<c:if test="${project.id eq selectedProjectFilter}">selected="selected"</c:if>>
								     ${project.name}</option>
						</c:forEach>
					</select> <input type="submit" value="Filter"> <input type="submit"
						value="Clean" onclick="form.action='cleanfiltertask';">
				</form>
			</div>
			<tr>
				<th>ID</th>
				<th>Project</th>
				<th>Task Name</th>
				<th>Task Title</th>
				<th>Classtask</th>
				<th>Status</th>
				<th>Priority</th>
				<th>User</th>
				<th>Task Description</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="task" items="${listTask}">
				<tr>
					<td><c:out value="${task.id}" /></td>
					<td><c:out value="${task.project.name}" /></td>
					<td><c:out value="${task.name}" /></td>
					<td><c:out value="${task.title}" /></td>
					<td><c:out value="${task.classtask}" /></td>
					<td><c:out value="${task.status}" /></td>
					<td><c:out value="${task.priority}" /></td>
					<td><c:out value="${task.user.name}" /></td>
					<td><c:out value="${task.description}" /></td>

					<td><a href="edittask?id=<c:out value='${task.id}' />">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="deletetask?id=<c:out value='${task.id}' />">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>