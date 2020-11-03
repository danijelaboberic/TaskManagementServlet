<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="/pages/header.jsp"%>
	<div class="container">
		<div class="row pt-5">
			<div class="col">
				<c:if test="${not empty loginFailed}">
					<strong class="text-danger">Username i password nisu tačni, pokušajte ponovo.</strong>
				</c:if>
			</div>

		</div>
		<div class="row pt-5">
			<div class="col-4"></div>
			<div class="col-4">
				<form class="text-center border border-light p-5" method="post"
					action="http://localhost:8080/TaskManagementServlet/LoginServlet?action=login">


					<input type="text" id="username" name="username"
						class="form-control mb-4" placeholder="Korisničko ime"> <input
						type="password" name="password" id="defaultLoginFormPassword"
						class="form-control mb-4" placeholder="Lozinka">
					<button class="btn btn-default btn-block my-4" type="submit">Prijavi
						se</button>
				</form>

			</div>
			<div class="col-4"></div>
		</div>
	</div>

</body>
</html>