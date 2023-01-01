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
<link href='https://css.gg/close-o.css' rel='stylesheet'>
<link href='https://css.gg/boy.css' rel='stylesheet'>
<link href='https://css.gg/facebook.css' rel='stylesheet'>
<link href='https://css.gg/instagram.css' rel='stylesheet'>
<link href='https://css.gg/bell.css' rel='stylesheet'>
<link href='https://css.gg/search.css' rel='stylesheet'>
<link href="https://css.gg/css?=|shopping-cart" rel="stylesheet">
<link href='https://css.gg/globe-alt.css' rel='stylesheet'>
<link href='https://css.gg/chevron-down-o.css' rel='stylesheet'>
<link href='https://css.gg/search.css' rel='stylesheet'>
<link rel="stylesheet" href="./css/user.css">
<link rel="shortcut icon" href="./img/favicon.ico">
<title>User Account Management Shopee</title>
</head>

<body>
	<header>
		<section id="navbar">
			<div>
				<ul>
					<li><a href="product-manage"> Kênh Người Bán</a></li>
					<li><a href="#"> Trở Thành Người Bán Shopee </a></li>
					<li id="app-barcode"><a href="#"> Tải Ứng Dụng <img
							src="./img/barcode.png" alt="barcode">
					</a></li>
					<p>&nbsp; Kết Nối</p>
					<a href="#"><i class="gg-facebook"></i> </a>
					<a href="#"><i class="gg-instagram"></i></a>

				</ul>
			</div>
			<div id="left-navbar">
				<ul>
					<li><i class="gg-bell"></i> &nbsp; Thông Báo
						<div id="thongbao">
						<c:if test="${sessionScope.customerLogin == null}">
							<div class="xemthongbao">
								<img src="./img/user.webp" alt="user" width="80">
								<p>Đăng nhập để xem thông báo</p>
							</div>
							<div class="notifi-bttn">
								<a href="register"> <input type="button" value="Đăng Ký"></a>
								<a href="login"> <input type="button" value="Đăng Nhập">
								</a>
							</div>
						</c:if>

						<c:if test="${sessionScope.customerLogin != null}">
							<div id="notifyList">
								<c:forEach var="item" items="${sessionScope.notifyList}">
									<div>
										<p style="color: gray">
											<c:out value="${item.getOrderDate()}" />
										</p>
										<p>
											Bạn đã đặt hàng thành công đơn hàng số <i
												style="color: #fb5330"> <c:out
													value="${item.getInvoice()}" />
											</i>
										</p>
									</div>
								</c:forEach>
							</div>

						</c:if>
					</div>
						
						</li>

					<li><i class="gg-boy"></i> &nbsp; Hỗ Trợ</li>

					<li><i class="gg-globe-alt"></i> &nbsp; Tiếng Việt &nbsp; <i
						class="gg-chevron-down-o"></i>
						<div id="language">
							<a href="#">Tiếng Việt</a> <a href="#">English</a>
						</div></li>
					<c:if test="${sessionScope.customerLogin == null}">

						<li><a href="./register.jsp">Đăng Ký</a></li>
						<li><a href="login"> Đăng Nhập</a></li>

					</c:if>

					<c:if test="${sessionScope.customerLogin != null}">
						<li id="dangnhap"><img alt="user" src="./img/user.webp">
							<a> ${sessionScope.customerLogin.username} </a>
							<div id="login-menu">
								<a href="#">Tài khoản của tôi</a> <a href="shopping-cart">Đơn
									mua</a>

								<c:if test="${sessionScope.customerLogin.isAdmin()}">
									<a href="user-account"> Quản lý tài khoản</a>
								</c:if>

								<a href="history"> Lịch sử đơn hàng</a>
								<form action="home" method="post">
									<input type="submit" value="Đăng xuất">
								</form>
							</div>
						</li>
					</c:if>
				</ul>
			</div>
		</section>

		<section id="search-bar">
			<a href="home"><img src="./img/login/logo.png" alt="logo"></a>
			<p>USER ACCOUNT</p>
			<form action="#" method="get">
				<input type="search" name="search">
				<button type="submit">
					<i class="gg-search"></i>
				</button>
			</form>

		</section>

	</header>
	<main>
		<div id="table-header">
			<p style="flex-basis: 40%">Username</p>
			<p style="flex-basis: 15%">Role</p>
			<p style="flex-basis: 15%">Status</p>
			<p style="flex-basis: 15%">Action</p>
		</div>

		<div>
			<c:forEach var="item" items="${userList}">
				<form action="" method="post" class="userinfo">
					<p style="flex-basis: 40%">
						<input type="text" name="username" value="${item.getUsername()}" readonly/>
					</p>
					<c:if test="${item.isAdmin() == true}">
						<p  style="flex-basis: 15%" >Admin</p>
					</c:if>
					<c:if test="${item.isAdmin() == false}">
						<p  style="flex-basis: 15%" >User</p>
					</c:if>

					<c:if test="${item.isStatus() == true}">
						<p  style="flex-basis: 15%; color: green" >Active</p>
						<div class="bttn">
						<button type="submit" name="deactivate" value="0"  style="border: 2px solid #fb5330"> Deactivate </button>
						</div>
						
					</c:if>

					<c:if test="${item.isStatus() == false}">
						<p  style="flex-basis: 15%; color: red">Inactive</p>
						<div class="bttn">
						<button type="submit" name="activate" value="1"  style="border: 2px solid green"> Activate </button>
						</div>
						
					</c:if>

				</form>

			</c:forEach>
		</div>
	</main>

</body>

</html>