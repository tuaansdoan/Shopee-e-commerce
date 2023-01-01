<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
						</div></li>

				</c:if>
			</ul>
		</div>
	</section>
	<section id="searchbar">
		<div>
			<a href="home"><img src="./img/logo.png" alt="logo" width="162"
				height="50"> </a>
		</div>

		<div id="search">
			<form action="home">
				<input type="search" name="search" value="${requestScope.keyWord}"
					placeholder="QUÀ COLOSMULTI ĐẾN 60TR">
				<button type="submit">
					<i class="gg-search"></i>
				</button>
			</form>
			<ul>
				<li>Dép</li>
				<li>Áo Phông</li>
				<li>Balo</li>
				<li>Dép Nữ</li>
				<li>Áo Croptop</li>
				<li>Áo Khoác</li>
				<li>Túi Xách</li>
				<li>Váy</li>
			</ul>
		</div>
		<div id="cart-div">
			<a href="shopping-cart?cart-login=1">
				<p id="NumItem">
					<c:out value="${sessionScope.userCartList.size()}" />
				</p> <i class="gg-shopping-cart"> </i>

				<div id="shop-cart">

					<c:if test="${sessionScope.customerLogin == null}">
						<div class="noItemCart">
							<img src="./img/shopping-bag-circle.jpg" alt="shopping cart"
								width="80">
							<p>Chưa có sản phẩm</p>
						</div>

					</c:if>

					<c:if test="${sessionScope.customerLogin != null}">
						<div class="ItemCart">
							<p
								style="padding: 1em; font-size: 11pt; color: gray; border-bottom: 1px solid gray">
								Sản phẩm mới thêm</p>
							<c:forEach var="item" items="${userCartList}">
								<div class="itemList">
									<div>
										<img src="./img/product/${item.getImage()}">
									</div>
									<div style="height: 60px; overflow: hidden">
										<c:out value="${item.getProductName()}"></c:out>
									</div>
									<div style="color: #fb5330">
										đ
										<c:out value="${item.getPrice()}"></c:out>
										<span style="color: gray"> x <c:out
												value="${item.getQuantity()}" /></span>
									</div>
								</div>
							</c:forEach>
						</div>

					</c:if>

				</div>
			</a>
		</div>
	</section>
</header>