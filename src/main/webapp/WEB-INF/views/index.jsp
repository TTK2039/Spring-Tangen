<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body class="login_body">
	<div class="header">
		<h1 class="site_logo">商品管理システム</h1>
	</div>

	<div class="login_form">
		<img src="./images/logo.png" class="login_logo">
		<c:if test="${error == 1 }">
		<p class="error"><fmt:message key="form.lbl.loginError" /></p>
		</c:if>
		<form:form modelAttribute="login" method="post">
			<div class="cp_iptxt">
				<label><fmt:message key="form.lbl.loginId" /></label>
				<form:input path="loginId" />
				<form:errors path="loginId" cssStyle="color: red" />
			</div>
			<div>
			<label><fmt:message key="form.lbl.password" /></label>
			<form:input path="password" />
			<form:errors path="password" cssStyle="color: red" />
			</div>
			<form:button formaction="login">ログイン</form:button>
		</form:form>
		<%-- 		<form action="login" method="post"> --%>
		<!-- 			<fieldset> -->
		<!-- 				<div class="cp_iptxt"> -->
		<!-- 					<input class="base_input" type="text" name="loginId" -->
		<!-- 						placeholder="ID"> <i class="fa fa-user fa-lg fa-fw" -->
		<!-- 						aria-hidden="true"></i> -->
		<%-- 					<div class="error">${requestScope.idError}</div> --%>
		<!-- 				</div> -->

		<!-- 				<div> -->
		<!-- 					<input class="base_input" type="password" name="pass" -->
		<!-- 						placeholder="PASS"> -->
		<%-- 					<div class="error">${requestScope.passError}</div> --%>
		<!-- 				</div> -->
		<!-- 			</fieldset> -->
		<!-- 			<button class="logout_btn" type="submit" name="btn" value="login">ログイン</button> -->
		<%-- 		</form> --%>
	</div>
</body>
</html>
