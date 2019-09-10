<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Menu page</title>
<style>
div.scrollmenu {
  background-color: #2d5986;
  overflow: auto;
  white-space: nowrap;
}

div.scrollmenu a {
  display: inline-block;
  color: white;
  text-align: center;
  padding: 14px;
  text-decoration: none;
}

div.scrollmenu a:hover {
  background-color: #204060;
}
</style>
</head>
<body>
	<div class="scrollmenu">
		<a href="tasks">Tasks</a>
		<a href="users">Users</a>
		<a href="projects">Projects</a>
	</div>
</body>
</html>