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
    <link rel="stylesheet" href="./css/register.css">
       <link rel="shortcut icon" href="./img/favicon.ico">
    <title>Register Shopee FAKE VietNam</title>
</head>

<body>
    <header>
        	<a href="home"><img src="./img/login/logo.png" alt="logo" width="162" height="50" alt="not found"> </a>
        <p>Đăng ký</p>
        <p>Bạn cần giúp đỡ</p>

    </header>
    <main>
        <div>
            <form action="register" method="post">
            <p>ĐĂNG KÝ</p>
            <input type="text" name="username" id="username" placeholder="Email/Số điện thoại/Tên đăng ký" required autocomplete="off">
            <input type="password" name="password" id="password" placeholder="Mật khẩu" autocomplete="off"> 
            <input type="submit" value="ĐĂNG KÝ">
        </form>
        </div>
       
    </main>
    <footer>
        <h3>SHOPEE - GÌ CŨNG CÓ, MUA HẾT Ở SHOPEE </h3>
        <p>Shopee - ứng dụng mua sắm trực tuyến thú vị, tin cậy, an toàn và miễn phí! Shopee là nền tảng giao dịch trực
            tuyến hàng đầu ở Đông Nam Á, có trụ sở chính ở Singapore, đã có mặt ở khắp các khu vực Singapore, Malaysia,
            Indonesia, Thái Lan, Philippines, Đài Loan, Brazil, México, Colombia, Chile, Poland, Spain, Argentina. Với
            sự đảm bảo của Shopee, bạn sẽ mua hàng trực tuyến an tâm và nhanh chóng hơn bao giờ hết!</p>
        <ul>
            <li>CHÍNH SÁCH BẢO MẬT</li>
            <li>QUY CHẾ HOẠT ĐỘNG</li>
            <li>CHÍNH SÁCH VẬN CHUYỂN</li>
            <li>CHÍNH SÁCH TRẢ HÀNG VÀ HOÀN TIỀN</li>
        </ul>
    </footer>
</body>

</html>