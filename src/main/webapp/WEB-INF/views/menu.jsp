<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー</title>
<link href="css/commons.css" rel="stylesheet">
<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">

</head>
<body>
	<div id="app">
		<header>
			<div class="header">
				<h1 class="site_logo">
					<a href="menu.jsp">商品管理システム</a>
				</h1>
				<div class="user">
					<p class="user_name">${user.name}さん、こんにちは</p>
					<form class="logout_form" action="logout">
						<button>
							<img src="images/ドアアイコン.png">ログアウト
						</button>
					</form>
				</div>
			</div>
		</header>
	</div>
	<hr>
	<c:if test="${user.id == 1}">
		<div class="btn">
			<a class="basic_btn regist" href="insert">新規登録</a>
		</div>
	</c:if>
	<span class="error">${errorSelect}</span>
	<form method="get" action="TableServlet" class="search_container ">
		<input type="text" size="25" placeholder="キーワード検索" name="keyword"
			id="keyword"> <input type="submit" value="&#xf002"
			name="find">
	</form>

	<div class="order">
		<select
			onChange="location.href='TableServlet?sort=' + value + '&find=&keyword=' + document.getElementById('keyword').value">
			<option>並び替え</option>
			<option value="sortId">商品ID</option>
			<option value="sortCate">カテゴリ</option>
			<option value="sortPriceLow">単価：安い順</option>
			<option value="sortPriceHigh">単価：高い順</option>
			<option value="sortDayOld">登録日：古い順</option>
			<option value="sortDayNew">登録日：新しい順</option>
		</select>
	</div>
	<p>${msg }</p>
	<%-- 	<c:if test="${!(empty count) }"> --%>
	<div class="caption">
		<p>
			検索結果：${count }件<br>${resultSort }</p>
	</div>
	<table border="1">
		<thead>
			<tr>
				<th><fmt:message key="form.lbl.productId" /></th>
				<th><fmt:message key="form.lbl.productName" /></th>
				<th><fmt:message key="form.lbl.price" /></th>
				<th><fmt:message key="form.lbl.category" /></th>
				<th><fmt:message key="form.lbl.detail" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="products" items="${list}">
				<tr>
					<td>${products.productId}</td>
					<td>${products.name}</td>
					<td>${products.price}</td>
					<td>${products.categoryName}</td>
					<td><a class="detail_btn"
					href="./detail?id=${products.id}"><fmt:message key="form.lbl.detail" /></a></td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
	<%-- 	</c:if> --%>
	<footer></footer>

</body>
</html>
