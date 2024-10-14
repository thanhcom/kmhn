<%-- 
    Document   : demo
    Created on : Aug 27, 2024, 3:03:13 PM
    Author     : thanhcom
--%>

<%@page import="org.hibernate.Session"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kính mắt Hà Nội</title> 
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous"> 
        <link href="https://cdn.datatables.net/v/bs5/dt-2.1.5/datatables.min.css" rel="stylesheet">
    </head>
    <body>

        <nav class="navbar bg-primary navbar-expand-lg bg-body-tertiary" data-bs-theme="dark"> 
            <div class="container-fluid">
                <a class="navbar-brand" href="home">Kính Mắt Hà  Nội</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll" aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarScroll">
                    <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px;">
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="today">Hôm Nay </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="yesterday">Hôm Qua</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="currentmonth">Tháng Này</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="yestermonth">Tháng Trước</a>
                        </li>
                        <li class="nav-item">
                            <li class="nav-item">
                            <a class="nav-link active" href="customer">Khách Hàng</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="other">Other</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle " href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Chức Năng  
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="#">Action</a></li>
                                <li><a class="dropdown-item" href="#">Another action</a></li>
                                <li><hr class="dropdown-divider"></li>
                                <li><a class="dropdown-item" href="cusnew.jsp">Tạo Hoá Đơn </a></li>
                            </ul>
                        </li>
                    </ul>
                    <form class="d-flex" role="search">
                        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-success" type="submit">Search</button>
                    </form>
                </div>
            </div>
        </nav>
        <div class="container-fluid">
            <h1 class="center_sun4">Kính Mắt Hà  Nội CS2 :<span class="badge rounded-pill text-bg-warning">Dịch Vụ Khác - ${customer.getName()}</span> </h1>
            <div class="row">
                <div class="col"></div>
                <div class="col">
                    <form action="otherbycustomersave" method="post">
                        <div class="form-group row">
                            <input type="hidden" name="cid" value="${customer.getId()}"/>
                            <label for="inputEmail3" class="col-sm-4 col-form-label" >Họ Tên</label>
                            <div class="col-sm-8">
                                <input type="text" name="name" class="form-control" value="${customer.getName()}" disabled required>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="inputPassword3" class="col-sm-4 col-form-label">Điện Thoại </label>
                            <div class="col-sm-8">
                                <input type="number" name="phone" class="form-control" value="${customer.getPhone()}" disabled>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="inputPassword3" class="col-sm-4 col-form-label">Địa Chỉ</label>
                            <div class="col-sm-8">
                                <input type="text" name="address" class="form-control" value="${customer.getAddress()}" disabled>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="inputPassword3" class="col-sm-4 col-form-label">Tuổi </label>
                            <div class="col-sm-8">
                                <input type="number" name="age" class="form-control"  value="${customer.getAge()}" disabled required>
                            </div>
                        </div>
                        <select name="gender" class="form-select form-select-sm" aria-label="Small select example" disabled required>
                            <c:if test="${customer.getGender()==0}">
                                <option value="0" selected >Nữ</option>
                            <option value="1">Nam </option>
                            </c:if>
                             <c:if test="${customer.getGender()==1}">
                                <option value="0"  >Nữ</option>
                            <option value="1" selected>Nam </option>
                            </c:if>
                        </select>
                        <hr/>
                        <div class="form-group row">                            
                            <label for="inputEmail3" class="col-sm-4 col-form-label" >Tên Dịch Vụ </label> 
                            <div class="col-sm-8">
                                <input type="text" name="serviceName" class="form-control" placeholder="Nhập Tên Dịch Vụ #" required>
                            </div>
                        </div>
                        <div class="form-group row">                            
                            <label for="inputEmail3" class="col-sm-4 col-form-label" >Thành Tiền</label> 
                            <div class="col-sm-8">
                                <input type="numbern" name="servicePrice" class="form-control" placeholder="Nhập Số Tiền " required>
                            </div>
                        </div>
                        
                        <div class="input-group mb-4">
                            <input type="text" name="note" class="form-control" placeholder="Ghi Chú " aria-label="Username" aria-describedby="basic-addon1">
                            
                            <select name="paymentmethod" class="form-select form-select-sm" aria-label="Small select example" id="validationDefault04" required>
                            <option disabled selected value="">Thanh Toán </option>
                            <option value="0">Tiền Mặt</option>
                            <option value="1">Chuyển Khoản</option>
                        </select>
                        </div>
                        
                        <hr/>
                        <div class="form-group row">
                            <div class="col-sm-10">
                                <button type="submit" class="btn btn-primary">LƯU HỆ THỐNG </button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col"></div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <script src="https://cdn.datatables.net/v/bs5/dt-2.1.5/datatables.min.js"></script>
    </body>
</html>
