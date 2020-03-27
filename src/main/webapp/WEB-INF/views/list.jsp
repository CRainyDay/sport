<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2 align="center">员工信息列表</h2>
	<div align="center">
	<table border="1" style="width: 70%; cellspacing: 0px;">
		<tr align="center">
			<th>ID</th>
			<th>LastName</th>
			<th>Email</th>
			<th>Gender</th>
			<th>DeptName</th>
			<th>Operation</th>
		</tr>
		<c:forEach items="${emps}" var="emp">
			<tr align="center">
				<td>${emp.id }</td>
				<td>${emp.lastName }</td>
				<td>${emp.email }</td>
				<td>${emp.gender==0?"未知":emp.gender==1?"男":"女" }</td>
				<td>${emp.dept.departmentName }</td>
				<td>
					<a href="#">Edit</a>
					<a href="#">Delete</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	</div>
	<h2 align="center"><a href="#">Add New Employee</a></h2>
</body>
</html>