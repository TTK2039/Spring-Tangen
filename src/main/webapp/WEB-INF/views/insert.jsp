<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<c:if test="${empty user}">
	<meta http-equiv="refresh" content="0;URL=index">
</c:if>
<meta charset="UTF-8">
<title>商品登録</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>

	<div class="header">
		<h1 class="site_logo">
			<a href="menu.html">商品管理システム</a>
		</h1>
		<div class="user">
			<p class="user_name">${fn:escapeXml(user.name)}さん、こんにちは</p>
			<form class="logout_form" action="logout">
				<button class="logout_button">
					<img src="images/ドアアイコン.png">ログアウト
				</button>
			</form>
		</div>
	</div>

	<hr>

	<div class="insert">
		<div class="discription">
			<p>
				登録情報を入力してください（<span class="required"></span>は必須です）
			</p>
		</div>

		<div class="form_body">
			<p class="error">${error }</p>
			<p>${msg }</p>
			<form:form modelAttribute="insert" method="get">
				<fieldset class="label-130">
					<div>
						<label class="required"><fmt:message
								key="form.lbl.productId" /></label>
						<form:input type="text" path="pdId" class="base-text" />
						<form:errors path="pdId" cssStyle="color: red" />
					</div>
					<div>
						<label class="required"><fmt:message
								key="form.lbl.productName" /></label>
						<form:input type="text" path="pdName" class="base-text" />
						<form:errors path="pdName" cssStyle="color: red" />
					</div>
					<div>
						<label class="required"><fmt:message key="form.lbl.price" /></label>
						<form:input type="number" path="price" class="base-text" />
						<form:errors path="price" cssStyle="color: red" />
					</div>
					<div class="select_block">
						<label class="required"><fmt:message
								key="form.lbl.category" /></label>
						<form:select path="roleId" class="base-text">
							<c:forEach var="cd" items="${cdList}">
								<option value="${cd.id}">${cd.name}</option>
							</c:forEach>
						</form:select>
					</div>
					<div>
						<label><fmt:message key="form.lbl.description" /></label>
						<form:textarea path="description" class="base-text"></form:textarea>
					</div>
					<div>
						<label><fmt:message key="form.lbl.images" /></label>
						<form:input type="file" path="images" accept="image/*" />
						<span class="error">${errorImage}</span>
					</div>
				</fieldset>
				<div class="btns">
					<button type="button" onclick="openModal()" class="basic_btn">
						<fmt:message key="form.lbl.register" />
					</button>
					<input type="button" onclick="location.href='menu'" value="戻る"
						class="cancel_btn">
				</div>
				<div id="modal">
					<p class="modal_message">
						<fmt:message key="form.lbl.register" />
						しますか？
					</p>
					<div class="btns">
						<form:button type="submit" formaction="register" class="basic_btn">
							<fmt:message key="form.lbl.register" />
						</form:button>
						<button type="button" onclick="closeModal()" class="cancel_btn">
							<fmt:message key="form.lbl.cancel" />
						</button>
					</div>
				</div>
			</form:form>
		</div>
	</div>
	<div id="fadeLayer"></div>
</body>
</html>

<script src="./js/commons.js"></script>
