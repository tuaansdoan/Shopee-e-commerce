<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.List"%>

<%@page import="entity.Product"%>
<%@page import="entity.Category"%>
<%@page import="entity.CustomerInfo"%>
<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href='https://css.gg/boy.css' rel='stylesheet'>
<link href='https://css.gg/facebook.css' rel='stylesheet'>
<link href='https://css.gg/instagram.css' rel='stylesheet'>
<link href='https://css.gg/bell.css' rel='stylesheet'>
<link href='https://css.gg/search.css' rel='stylesheet'>
<link href="https://css.gg/css?=|shopping-cart" rel="stylesheet">
<link href='https://css.gg/globe-alt.css' rel='stylesheet'>
<link href='https://css.gg/chevron-down-o.css' rel='stylesheet'>
<link href='https://css.gg/arrow-left.css' rel='stylesheet'>
<link href='https://css.gg/arrow-right.css' rel='stylesheet'>

<link rel="stylesheet" href="./css/header.css">
<link rel="stylesheet" href="./css/index.css">

<title>Shopee fake VietNam</title>
<link rel="shortcut icon" href="./img/favicon.ico">
</head>
<body>
<c:import url="/header.jsp" />

	<main>
		<section id="banner-1">
			<div>
				<img src="./img/Banner 1280x720(2).png" alt="banner"> <img
					src="./img/banner 1.png" alt="banner"> <img
					src="./img/shopee-o-nha-khong-kho.jpg" alt="banner">
			</div>
			<div id="nav-menu">
				<ul>
					<li><img src="./img/shoppee header.png" alt="">
						<p>Khung Giờ Săn Sale</p></li>
					<li><img src="./img/shoppee header.png" alt="">
						<p>Gì Cũng Rẻ Mua Là FreeShip</p></li>
					<li><img src="./img/shoppee header.png" alt="">
						<p>Miễn Phí Vận Chuyển</p></li>
					<li><img src="./img/shoppee header.png" alt="">
						<p>Bắt Trend - Giá Sốc</p></li>
					<li><img src="./img/shoppee header.png" alt="">
						<p>Hoàn Xu 6% - Lên Đến 200k</p></li>
					<li><img src="./img/shoppee header.png" alt="">
						<p>Hàng Hiệu Giá Tốt</p></li>
					<li><img src="./img/shoppee header.png" alt="">
						<p>Hàng Quốc Tế - Thương Hiệu</p></li>
					<li><img src="./img/shoppee header.png" alt="">
						<p>Nạp Thẻ, Hóa Đơn, Phim</p></li>
					<li><img src="./img/shoppee header.png" alt="">
						<p>Deal Sốc Từ 1K</p></li>
					<li><img src="./img/shoppee header.png" alt="">
						<p>Chọn 6 Số Trúng Tiền Triệu</p></li>
				</ul>
			</div>
		</section>

		<section id="banner-2">
			<img src="./img/banner tặng quà.png" alt="tặng quà không đồng">
		</section>

		<%-- 	<% List<Category> categoryList = (List<Category>) request.getAttribute("categoryList"); %> --%>

		<section id="danhmuc">
			<p>DANH MỤC</p>
			<ul>
				<c:forEach var="item" items="${categoryList}">
					<li><a href="home?category=${item.getcID()}"> <img
							src="./img/category/${item.getcImage()}" alt="not found">
							<p style="color: black">${item.getcName()}</p>
					</a></li>
				</c:forEach>


			</ul>
		</section>


		<section id="goi-y">
			<p>GỢI Ý HÔM NAY</p>
			<ul>
				<%-- <%
				List<Product> productList = (List<Product>) request.getAttribute("productList");
				%> --%>

				<c:forEach items="${productList}" var="item">
					<li><a href="home?ID=${item.getId()}"> <img
							src="./img/product/${item.getImage()}" alt="error image">
							<p>${item.getProductName()}</p>
							<p>đ ${item.getPrice()}</p>
							<p>Đã bán ${item.getSold()}
							<p>Tìm sản phầm tương tự</p>
					</a></li>
				</c:forEach>



			</ul>

			<%
			int pg = (Integer) request.getAttribute("pg");
			int totalPage = (Integer) request.getAttribute("totalPage");
			%>

			<div id="paging">
				<c:forEach var="item" begin="1" end="${totalPage}" step="1">
					<a href="home?page=${item}" class=${ item == pg ? "current" : ""}>${item}</a>
				</c:forEach>
			</div>
		</section>
	</main>
<c:import url="/footer.jsp"/>