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
<link rel="stylesheet" href="./css/payment.css">

<title>Shopee fake VietNam</title>
<link rel="shortcut icon" href="./img/favicon.ico">
</head>

<body>

<c:import url="/header.jsp" />
	
	<main>
		<section id="payment">
			<div>
				<img src="./img/shipping.jpg" alt="not found" width="200px">
				<p>
					Quý khách hàng <span style="color: #fb5330"><c:out
							value="${sessionScope.customerLogin.getUsername()}" /> </span> đã mua
					hàng thành công! <br> Đơn hàng số <span style="color: #fb5330">
						<c:out value="${sessionScope.invoice}" />
					</span> sẽ được giao đến Quý khách dự kiến trong 2 ngày kể từ ngày mua
					hàng <br> Xin cảm ơn Quý khách đã lựa chọn Shopee!
				</p>
			</div>
		</section>

		<section id="goi-y">
			<p>GỢI Ý HÔM NAY</p>
			<ul>
				<c:forEach items="${productSession}" var="item">
					<li><a href="home?ID=${item.getId()}"> <img
							src="./img/product/${item.getImage()}" alt="not found">
							<p>${item.getProductName()}</p>
							<p>đ ${item.getPrice()}</p>
							<p>Đã bán ${item.getSold()}
							<p>Tìm sản phầm tương tự</p>
					</a></li>
				</c:forEach>
		</section>


	</main>
<c:import url="/footer.jsp"/>