<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Task Management Application</title>
</head>
<body>
<jsp:include page="Menu.jsp" />
	<center>
		<h1>Task Management</h1>
		<h2>
			<a href="newtask">Add New Task</a> &nbsp;&nbsp;&nbsp; <a
				href="listtask">List All Task</a>
		</h2>
	</center>
	<div align="center">
		<c:if test="${task != null}">
			<form action="updatetask" method="post">
		</c:if>
		<c:if test="${task == null}">
			<form action="inserttask" method="post">
		</c:if>
		<table border="1" cellpadding="5">
			<caption>
				<h2>
					<c:if test="${task != null}">
            			Edit Task
            		</c:if>
					<c:if test="${task == null}">
            			Add New Task
            		</c:if>
				</h2>
			</caption>
			<c:if test="${task != null}">
				<input type="hidden" name="id" value="<c:out value='${task.id}' />" />
			</c:if>
			<tr>
				<th>Project:</th>
				<td><select name="projectid">
						<c:forEach items="${projectsList}" var="project">
							<option value="${project.id}"
    							<c:if test="${project.id eq selectedProject}">selected="selected"</c:if>>
    							${project.name}
						</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<th>Task Name:</th>
				<td><input type="text" name="name" size="45"
					value="<c:out value='${task.name}' />" /></td>
			</tr>
			<tr>
				<th>Task Title:</th>
				<td><input type="text" name="title" size="45"
					value="<c:out value='${task.title}' />" /></td>
			</tr>
			<tr>
				<th>Classtask:</th>
				<td>
					<select name="classtask">
						<c:forEach items="${classtasksList}" var="classtask">
							<option value="${classtask}"
    							<c:if test="${classtask eq selectedClasstask}">selected="selected"</c:if>>
    							${classtask}
						</option>
						</c:forEach>
				</select>
				</td>
			</tr>
			<tr>
				<th>Status:</th>
				<td>
				<select name="status">
						<c:forEach items="${statusesList}" var="status">
							<option value="${status}"
    							<c:if test="${status eq selectedStatus}">selected="selected"</c:if>>
    							${status}
						</option>
						</c:forEach>
				</select>
				</td>
			</tr>
			<tr>
				<th>Priority:</th>
				<td><select name="priority">
						<c:forEach items="${prioritesList}" var="priority">
							<option value="${priority}"
    							<c:if test="${priority eq selectedPriority}">selected="selected"</c:if>>
    							${priority}
						</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<th>User:</th>
				<td><select name="userid">
						<c:forEach items="${usersList}" var="user">
							<option value="${user.id}"
    							<c:if test="${user.id eq selectedUser}">selected="selected"</c:if>>
    							${user.name}
						</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<th>Task Description:</th>
				<td><textarea name="description"><c:out value='${task.description}' /></textarea></td>
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