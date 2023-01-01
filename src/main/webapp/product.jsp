<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.List"%>
<%@page import="entity.Product"%>
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

<link rel="stylesheet" href="./css/header.css">
<link rel="stylesheet" href="./css/product-style.css">
<link rel="shortcut icon" href="favicon.ico">
<title>Product Detail Shopee VietNam</title>
<link rel="shortcut icon" href="./img/favicon.ico">
</head>
<body>
<c:import url="/header.jsp" />	
	<%
	Product productDetail = (Product) request.getAttribute("productDetail");
	%>
	<main>
		<section id="product-detail">
			<div id="product-photo">
				<img src="./img/product/${productDetail.getImage()}"
					alt="error image"> <img
					src="./img/product/${productDetail.getImage()}" alt="error image">
				<img src="./img/product/${productDetail.getImage()}"
					alt="error image">
			</div>

			<div id="product-content">
				<div id="product-content-head">
					<p>
						<span>Yêu thích</span>
						<%=productDetail.getProductName()%>
					</p>
					<ul>
						<li>4.1</li>
						<li>609 Đánh giá</li>
						<li><%=productDetail.getSold()%> đã bán</li>
					</ul>
				</div>

				<div id="product-discount">
					<ul>
						<li>đ 40.000</li>
						<li>đ <%=productDetail.getPrice()%></li>
						<li>13% giảm</li>
					</ul>
					<p>giá tốt nhất so với các sản phẩm cùng loại</p>
				</div>
				<div id="discount-code" class="detail">
					<p>Mã Giảm Giá Của Shop</p>
					<p>8% giảm giá</p>
				</div>

				<div id="product-color" class="detail">
					<p>Màu Sắc</p>
					<ul>
						<li>Trắng</li>
						<li>Tím</li>
						<li>Đen</li>
					</ul>
				</div>

				<div class="detail" id="product-sise">
					<p>Kích thước</p>
					<p>Freesize</p>
				</div>

				<div style="display: flex">
					<form action="shopping-cart?ID=${productDetail.getId()}"
						method="post">
						<button type="submit" id="add-to-cart">
							<i class="gg-shopping-cart"></i> <span> Thêm Vào Giỏ Hàng
							</span>
						</button>
					</form>

					<form action="shopping-cart?muaID=${productDetail.getId()}"
						method="post">
						<button type="submit" id="mua-ngay">Mua Ngay</button>
					</form>
				</div>

			</div>

		</section>
		<section id="product-info">
			<div>MÔ TẢ SẢN PHẨM</div>
			<div>
				Áo hoodie Anchored thời trang đẹp rẻ <br> Chất liệu: Nỉ bông <br>
				Màu sắc: Trắng, Tím, Đen <br> Sản phẩm nữ Free size dưới 1m62,
				40-52kg tùy chiều cao<br> 🎁Thời Trang Nữ Hari Queen – FB:
				Xưởng May Xuân Hùng<br> 🎀 Chuyên sản xuất và cung cấp “SỈ LẺ”
				các loại quần áo thời trang 4 mùa.<br> 🎀 Hàng “GIÁ XƯỞNG”
				không qua trung gian.<br> 🎀 Cam kết “GIÁ RẺ NHẤT”.<br> 🎀
				Mẫu mã đa dạng ,luôn sẵn SLL.<br> 🎀 Nhận đặt may theo yêu cầu.<br>
				🎁 Sản phẩm bên shop đa số đã đăng “ẢNH THẬT’ các bạn chú ý đọc
				Thông tin sản phẩm trước khi đặt hàng.<br> 🎁Shop chỉ chuyển
				đơn hàng theo đúng phân loại khách chọn trong đơn .Ngoài ra khách có
				thay đổi màu sắc hay phân loại,...vui lòng hủy đơn và đặt lại giúp
				Shop nhé .Shop không hỗ trợ phí đổi trả trong trường hợp này.<br>
				Cảm ơn khách hàng đã ủng hộ và tin dùng sản phẩm của Thời Trang Nữ
				Hari Queen! 🎁 🎁 🎁<br> #Hariqueenfashion #aomu #quanaonu
				#thoitrangnu #giare #dep <br>#quankakinu #xinh #dethuong
				#hottrend #aosominu #quanbaggy <br>#aothuntaylo #shopee
				#giamgia #aocroptop #aosweater #aohoodie
				https://shopee.vn/hariqueenfashion <br>
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
	