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
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Chức Năng  
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="#">Action</a></li>
                                <li><a class="dropdown-item" href="#">Another action</a></li>
                                <li><hr class="dropdown-divider"></li>
                                <li><a class="dropdown-item" href="cusnew">Tạo Hoá Đơn </a></li>
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

            <h1 class="center_sun4">Kính Mắt Hà  Nội CS2 :<span class="badge rounded-pill text-bg-warning"><span class="text-primary">${customer.getName()} - ${customer.getPhone()} </span></span> </h1>
            <div class="border border-danger"> 
                <h1> <p  class=".text-danger text-center mt-2">Phần Hoá Đơn</p> </h1>
                <table id="example" class="table table-striped table-hover align-middle" style="width:100%">
                    <thead>
                        <tr>
                            <th>Ngày Lập </th>
                            <th>Gọng Kính  </th>
                            <th>Tròng Kính </th>
                            <th>Số Tiền </th>
                            <th>Ghi Chú </th>
                            <th>Phương Thức Thanh Toán</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${customer.getReceipt()}">

                            <tr>
                                <td class="text-uppercase text-danger fw-bold">${item.getDate()}</td>
                                <td>${item.getGkName()}
                                    <c:if test="${item.getGkPrice()!=0}">
                                        <span class="badge text-bg-primary rounded-pill">
                                            <fmt:formatNumber value = "${item.getGkPrice()}" type = "number"/> 
                                        </span>
                                    </c:if> 
                                </td>
                                <td>${item.getTkName()}
                                    <c:if test="${item.getTkPrice()!=0}">
                                        <span class="badge text-bg-primary rounded-pill">
                                            <fmt:formatNumber value = "${item.getTkPrice()}" type = "number"/> 
                                        </span>
                                    </c:if> 
                                </td>
                                <c:if test="${item.getPaymentMethod()==1}">
                                    <c:set var="countck" value="${countck + (item.getGkPrice()+item.getTkPrice())}" scope="page"/>
                                    <td class="text-bg-warning"><fmt:formatNumber value = "${item.getGkPrice()+item.getTkPrice()}" type = "number"/></td>   
                                </c:if>
                                <c:if test="${item.getPaymentMethod()==0}">
                                    <c:set var="count" value="${count + (item.getGkPrice()+item.getTkPrice())}" scope="page"/>
                                    <td><fmt:formatNumber value = "${item.getGkPrice()+item.getTkPrice()}" type = "number"/></td>
                                </c:if>
                                <td>${item.getNote()}</td>
                                <c:if test="${item.getPaymentMethod()!=0}">                                    
                                    <td>Chuyển Khoản </td>                                   
                                </c:if> 
                                <c:if test="${item.getPaymentMethod()==0}">                                    
                                    <td >Tiền Mặt </td>                                      
                                </c:if>

                                 <c:if test="${account.role==1}"> 
                                    <td>
                                        <a class="btn btn-outline-primary" role="button" aria-disabled="true" href="#">Sửa</a>
                                        <a class="btn btn-outline-danger ms-1" role="button" aria-disabled="true" href="#">Xoá</a>
                                    </td>
                                </c:if>



                            </tr>
                        </c:forEach>

                    </tbody>                
                </table>      
            </div>
            <br/>    
            <div class="border border-primary"> 
                <h1> <p  class=".text-danger text-center mt-2">Phần Đo Mắt </p> </h1>
                <table id="example" class="table table-striped table-hover align-middle" style="width:100%">
                    <thead>
                        <tr>
                            <th>Ngày Đo  </th>
                            <th>Độ Kính   </th>
                            <th>Đáp Ứng  </th>
                            <th>PD </th>
                            <th>ADD </th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${customer.getEyeService()}">

                            <tr>
                                <td class="text-uppercase text-danger fw-bold">${item.getEyedatetime()}</td>
                                <td>
                                    <p>MP:<span class="badge text-bg-primary">${item.getEyesphr()}</span><span class="badge text-bg-info">${item.getEyecylr()==0?"":item.getEyecylr()}</span><span class="badge text-bg-danger">${item.getEyeaxr()==0?"":item.getEyeaxr()}</span></p>
                                    <p>MT:<span class="badge text-bg-primary">${item.getEyesphl()}</span><span class="badge text-bg-info">${item.getEyecyll()==0?"":item.getEyecyll()}</span><span class="badge text-bg-danger">${item.getEyeaxl()==0?"":item.getEyeaxl()}</span></p>
                                </td>
                                <td>${item.getEyeapproved()}</td>
                                <td>${item.getEyepd()}</td>
                                <td>${item.getEyeadd()}</td>
                                <c:if test="${account.role==1}"> 
                                    <td>
                                        <a class="btn btn-outline-primary" role="button" aria-disabled="true" href="#">Sửa</a>
                                        <a class="btn btn-outline-danger ms-1" role="button" aria-disabled="true" href="#">Xoá</a>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>

                    </tbody>                
                </table> 
            </div>  
            <br/>
            <div class="border border-primary"> 
                <h1> <p  class=".text-danger text-center mt-2">Phần Dịch Vụ Khác </p> </h1>
                <table id="example" class="table table-striped table-hover align-middle" style="width:100%">
                    <thead>
                        <tr>
                            <th>Ngày Tháng </th>
                            <th>Tên Dịch Vụ</th>
                            <th>Ghi Chú  </th>
                            <th>Số Tiền</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${customer.getOther()}">

                            <tr>
                                <td class="text-uppercase text-danger fw-bold">${item.getDate()}</td>

                                <td>${item.getServiceName()}</td>
                                <td>${item.note}</td>
                                <c:if test="${item.getPaymentmethod()==1}">

                                    <td class="text-bg-warning"><fmt:formatNumber value = "${item.getServicePrice()}" type = "number"/></td>   
                                </c:if>
                                <c:if test="${item.getPaymentmethod()==0}">

                                    <td><fmt:formatNumber value = "${item.getServicePrice()}" type = "number"/></td>
                                </c:if>
                                <c:if test="${account.role==1}"> 
                                    <td>
                                        <a class="btn btn-outline-primary" role="button" aria-disabled="true" href="#">Sửa</a>
                                        <a class="btn btn-outline-danger ms-1" role="button" aria-disabled="true" href="#">Xoá</a>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>

                    </tbody>                
                </table> 
            </div> 
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <script src="https://cdn.datatables.net/v/bs5/dt-2.1.5/datatables.min.js"></script>
    </body>
</html>
