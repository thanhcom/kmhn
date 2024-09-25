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
        <title>Kính mắt Hà Nội <%=java.time.LocalDate.now()%></title> 
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous"> 
        <link href="https://cdn.datatables.net/v/bs5/dt-2.1.5/datatables.min.css" rel="stylesheet">
    </head>
    <body>
        <%

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String str = formatter.format(date);
            // List<Customer> list = request.getAttribute("listCustomer")
        %>
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
                            <a class="nav-link" href="other">Other</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
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

            <h1 class="center_sun4">Kính Mắt Hà  Nội CS2 :<span class="badge rounded-pill text-bg-warning">${requestScope.str} --> <%=str%></span> </h1>

            <table id="example" class="table table-striped table-hover align-middle" style="width:100%">
                <thead>
                    <tr>
                        <th>Họ Tên</th>
                        <th>Số Điện Thoại </th>
                        <th>Gọng Kính </th>
                        <th>Tròng Kính </th>
                        <th>Độ Kính </th>
                        <th>Thành Tiền </th>
                        <th>Thời Gian  </th>
                    </tr>
                </thead>
                <tbody>

                    <c:set var="countck" value="0" scope="page" />
                    <c:set var="count" value="0" scope="page" />
                    <c:forEach var="item" items="${requestScope.listCustomer}">

                        <tr>
                            <td class="text-uppercase text-danger fw-bold">${item.getCustomer().getName()}</td>
                            <td class="text-uppercase text-danger fw-bold">${item.getCustomer().getPhone()}</td>
                            <td class="text-uppercase text-danger fw-bold">${item.getGkName()}</td>
                            <td class="text-uppercase text-danger fw-bold">${item.getTkName()}</td>
                            <td class="text-uppercase text-danger fw-bold">${item.getCustomer().getEyeService().getLast().getEyesphr()} | ${item.getCustomer().getEyeService().getLast().getEyesphl()}</td>
                            <td class="text-uppercase text-danger fw-bold">${item.getGkPrice()+item.getTkPrice()}</td>
                            <td class="text-uppercase text-danger fw-bold">${item.getDate()}</td>
                        </tr>
                    </c:forEach>
                </tbody>
                <tfoot>
                    <tr> 
                        <th>Chuyển Khoản</th>
                        
                    </tr>
                </tfoot>
            </table>           
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                    <li class="page-item"><a class="page-link" href="#">1</a></li>
                    <li class="page-item active"><a class="page-link" href="#">2</a></li>
                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                    <li class="page-item"><a class="page-link" href="#">Next</a></li>
                </ul>
            </nav>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <script src="https://cdn.datatables.net/v/bs5/dt-2.1.5/datatables.min.js"></script>
    </body>
</html>
