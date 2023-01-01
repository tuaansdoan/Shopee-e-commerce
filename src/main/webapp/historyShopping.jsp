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
<link href='https://css.gg/search.css' rel='stylesheet'>

<link rel="stylesheet" href="./css/history.css">
<link rel="shortcut icon" href="./img/favicon.ico">
<title>History Shopping Cart Shopee</title>
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
					</div></li>

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
							<a href="history"> Lịch sử đơn hàng</a>
							<form action="home" method="post">
								<input type="submit" value="Đăng xuất">
							</form>
						</div></li>

				</c:if>


			</ul>
		</div>
	</section>

		<section id="search-bar">
			<a href="home"><img src="./img/login/logo.png" alt="logo"></a>
			<p>LỊCH SỬ MUA HÀNG</p>
			<form action="#" method="get">
				<input type="search" name="search">
				<button type="submit">
					<i class="gg-search"></i>
				</button>
			</form>

		</section>

	</header>
	<main>
	<c:if test="${!historyList.isEmpty()}">
		<section id="product-detail">
		<div class="don-hang head">
				<p style="flex-basis: 40%">Sản Phẩm</p>
				<p style="flex-basis: 10%">Đơn Giá</p>
				<p style="flex-basis: 10%">Số Lượng</p>
				<p style="flex-basis: 10%">Tiền</p>
		</div>
		<c:forEach var="item" items="${historyList}">
			<div class="don-hang totalhead">
				<p>	Đơn hàng số: <span style="color: #fb5330; font-weight: 500; "> <c:out value="${item.getInvoice()}" /> </span> </p>
				<i> Ngày chốt đơn: <c:out value="${item.getOrderDate()}" /></i>
				<p>
					Tổng thanh toán (
					<c:out value="${item.getShoppingCart().size()}" />
					Sản Phẩm): <span id="total-amount" style="color: #fb5330; font-weight: 500; " > đ ${item.totalPayment()} </span>
				</p>
			</div>
			<div >
			
				<c:forEach var="cart" items="${item.getShoppingCart()}">
				<div class="donhanginfo" >
								<div style="display: flex; flex-basis: 40%; align-items: center; ">
									<img src="./img/product/${cart.getImage()}" alt="not found"
										width="100px">
									<p style="margin-left: 10px" >${cart.getProductName()}</p>
								</div>
								<p style="flex-basis: 10%">đ<c:out value="${cart.getPrice()}" /></p>
								<p style="flex-basis: 10%"><c:out value="${cart.getQuantity()}" /></p>
								<p style="flex-basis: 10%; font-weight: 500">đ ${cart.getPrice()*cart.getQuantity()}</p>
							</div>
					
				</c:forEach>
				
			</div>
			</c:forEach>
		</section>
		</c:if>
		
		<c:if test="${historyList.isEmpty()}">
			<section class="noItemInCart">

				<img src="./img/shopping-bag-circle.jpg" alt="shopping cart">
				<p>Bạn chưa có đơn hàng!</p>
				<a href="home"> Tiếp Tục Mua Sắm </a>


			</section>
		</c:if>
		
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
	<footer>
		<h3>SHOPEE - GÌ CŨNG CÓ, MUA HẾT Ở SHOPEE</h3>
		<p>Shopee - ứng dụng mua sắm trực tuyến thú vị, tin cậy, an toàn
			và miễn phí! Shopee là nền tảng giao dịch trực tuyến hàng đầu ở Đông
			Nam Á, có trụ sở chính ở Singapore, đã có mặt ở khắp các khu vực
			Singapore, Malaysia, Indonesia, Thái Lan, Philippines, Đài Loan,
			Brazil, México, Colombia, Chile, Poland, Spain, Argentina. Với sự đảm
			bảo của Shopee, bạn sẽ mua hàng trực tuyến an tâm và nhanh chóng hơn
			bao giờ hết!</p>
		<ul>
			<li>CHÍNH SÁCH BẢO MẬT</li>
			<li>QUY CHẾ HOẠT ĐỘNG</li>
			<li>CHÍNH SÁCH VẬN CHUYỂN</li>
			<li>CHÍNH SÁCH TRẢ HÀNG VÀ HOÀN TIỀN</li>
		</ul>
	</footer>
</body>

</html>