<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品詳細</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>

	<div class="header">
		<h1 class="site_logo">
			<a href="menu.html">商品管理システム</a>
		</h1>
		<div class="user">
			<p class="user_name">${fn:escapeXml(user.name)}さん、こんにちは</p>
			<form class="logout_form" action="login" method="get">
				<button class="logout_btn" type="submit" name="btn" value="logout">
					<img src="images/ドアアイコン.png">ログアウト
				</button>
			</form>
		</div>
	</div>

	<hr>

	<div class="update">
		<div class="form_body">
			<div class="img_block">
				<img src="images/マッキー.png" class="product_img"><br>
			</div>
			<form action="TableServlet" method="get">
				<fieldset class="label-130 product_block">
					<p class="error">${error }</p>
					<div>
						<label>商品ID</label> <input type="text" name="pdId"
							value="${product.productId}" readonly class="base-text">
					</div>
					<div>
						<label>商品名</label> <input type="text" name="userName"
							value="${product.name}" readonly class="base-text">
					</div>
					<div>
						<label>単価</label> <input type="text" name="tel"
							value="${product.price}" readonly class="base-text">
					</div>
					<div>
						<label>カテゴリ</label> <input type="text" name="roleName"
							value="${product.categoryName}" readonly
							class="base-text">
					</div>
					<div>
						<label>商品説明</label>
						<input type="text" name="roleName"
							value="${product.description}" readonly
							class="base-text">
					</div>
				</fieldset>
				<div>
					<div class="btns">
<%-- 						<c:if test="${user.userId == 1}"> --%>
							<input type="button" onclick="openModal()" value="削除"
								class="basic_btn">
							<input type="button" onclick="location.href='./updateInput.jsp'"
								value="編集" class="basic_btn" name="">
<%-- 						</c:if> --%>
						<input type="button" onclick="location.href='menu'"
							value="戻る" class="cancel_btn">
					</div>
					<div id="modal">
						<p class="modal_message">削除しますか？</p>
						<div class="btns">
							<button type="submit" class="basic_btn" name="btn" value="delete">削除</button>
							<button type="button" onclick="closeModal()" class="cancel_btn">キャンセル</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div id="fadeLayer"></div>
</body>
</html>
<script src="./js/commons.js"></script>