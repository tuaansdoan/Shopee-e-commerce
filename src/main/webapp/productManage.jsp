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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<link href='https://css.gg/close-o.css' rel='stylesheet'>
<link rel="stylesheet" href="./css/productManage.css">
   <link rel="shortcut icon" href="./img/favicon.ico">
<title>Product Management Shopee</title>
</head>

<body>
	<header>
		<a href="home"><img src="./img/login/logo.png" alt="logo"></a>
		<p>QUẢN LÝ SẢN PHẨM</p>
		<a href="#addnew" id="addbtn"> Add </a>
	</header>
	<main>
		<div id="table-header">
			<p class="ID">ID</p>
			<p class="productName">Thông Tin Sản Phẩm</p>
			<p class="price">Giá (VND)</p>
			<p class="sold">Đã Bán</p>
			<p class="choice">Thao Tác</p>
		</div>


		<c:forEach var="item" items="${productList}">

			<form action="product-manage?page=${pg}" method="post" class="product-list" enctype="multipart/form-data">
				<input name="eId" class="eID" type="text" value="${item.getId()}" id="ID" readonly></input> 
				
				<img class="image" src="./img/product/${item.getImage()}" alt="error image" width="100px">
				
				<div class="product-info"> 
					<textarea name="eProductName"	class="eproductName" style="border: none;"	
						 id="productName${item.getId()}"readonly > <c:out value="${item.getProductName()}"/> </textarea> 
						 
					<p style="font-style: italic; color: gray "> -<c:out value="${item.getcName()}" />-</p>
						 
					<input type="hidden" name="imageEdit" id="image${item.getId()}" >
				</div>

				<input name="ePrice" class="eprice"	style="border: none;" type="text" value="${item.getPrice()}" id="price${item.getId()}" readonly></input>
					 
				<input class="esold"
					type="text" value="${item.getSold()} " id="sold${item.getId()}"
					readonly></input>

				<div class="edit-delete-bttn">
					<input type="hidden" class="esave" id="save${item.getId()}"	name="save" value="Save"></input>

					<form>
						<input class="edit" type="button" id="${item.getId()}" name="edit" value="Edit"></input>
					</form>

					<form  action="product-manage?ID=${item.getId()}&page=${pg}" method="post">
						<input class="delete" type="submit" name="delete" value="Delete" style="border: 2px solid red"></input>
					</form>
				</div>
			</form>

			<script>
				document.getElementById('${item.getId()}').onclick = function() {
					document.getElementById("productName${item.getId()}")
							.removeAttribute("readonly");

					document
							.getElementById("productName${item.getId()}")
							.setAttribute("style",
									"border: 2px solid black; border-radius: 5px");
					document
							.getElementById("price${item.getId()}")
							.setAttribute("style",
									"border: 2px solid black; border-radius: 5px");

					document.getElementById("price${item.getId()}")
							.removeAttribute("readonly");

					document.getElementById("save${item.getId()}")
							.setAttribute("style",
									"background-color: greenyellow;");

					document.getElementById("save${item.getId()}")
							.setAttribute("type", "submit");
					
					document.getElementById("image${item.getId()}")
					.setAttribute("type", "file");
				}
			</script>
		</c:forEach>
		

		<div id="addnew">
			<div>
				<a href="#"><i class="gg-close-o"></i></a>
			</div>
			<form action="product-manage" method="post" enctype="multipart/form-data">
				<img src="./img/logo.png" alt=""> <label for="name-product">Tên
					Sản Phẩm</label> 
					
					<input type="text" name="name-product" id="name-product"
					required> <br> 
					
					<label for="price-addnew">Mức
					Giá</label> 
					
					<input type="text" name="price" id="price-addnew" required> <br>
					
					<label for="image"> Hình Ảnh </label> 
					<input type="file" name="image"> <br>
					
					<label> Danh Mục </label> 
					<select name="choose-cID" >
						<c:forEach var="item" items="${requestScope.categoryList}"> 
							<option value="${item.getcID()}" > <c:out value="${item.getcName()}"/> </option>
						</c:forEach>
					</select>
				<div>
					<input type="submit" value="Add"> 
					<input type="reset"	value="Cancel">
				</div>
			</form>
		</div>
		<% 
		int pg =  (Integer) request.getAttribute("pg");
		int totalPage = (Integer) request.getAttribute("totalPage");
			%>
			
		<div id="paging">
			<c:forEach var="item" begin= "1" end="${totalPage}" step="1" > 
				<a  href="product-manage?page=${item}" class=${item == pg ? "current" : ""} > <c:out value="${item}"/> </a>
			</c:forEach>
		</div>
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