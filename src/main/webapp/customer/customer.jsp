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
                        <a class="nav-link" href="other">Other</a>
                        </li>

                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Chức Năng  
                            </a>
                            <ul class="dropdown-menu">
                               <li><a class="dropdown-item" href="#">Tạo Mới Khách Hàng</a></li> 
                                <li><a class="dropdown-item" href="#">Another action</a></li>
                                <li><hr class="dropdown-divider"></li>
                                <li><a class="dropdown-item" href="createother">Tạo Dịch Vụ Khác</a></li>
                                <li><a class="dropdown-item" href="cusnew">Tạo Hoá Đơn </a></li>
                            </ul>
                        </li>
                    </ul>
                    <form class="d-flex" role="search" action="searchcustomer">
                        <input name="username" class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-success" type="submit">Search</button>
                    </form>
                </div>
            </div>
        </nav>
        <div class="container-fluid">

            <h1 class="center_sun4">Kính Mắt Hà  Nội CS2 :<span class="badge rounded-pill text-bg-warning">Khách Hàng </span> </h1>

            <table id="example" class="table table-striped table-hover align-middle" style="width:100%">
                <thead>
                    <tr>
                        <th>Họ Tên</th>
                        <th>Số Điện Thoại </th>
                        <th>Tuổi</th>
                        <th>Giới Tính</th>
                        <th>Địa Chỉ </th>
                        <th>Hoá Đơn </th>
                        <th>Dịch Vụ #</th>
                    </tr>
                </thead>
                <tbody>

                    <c:set var="countck" value="0" scope="page" />
                    <c:set var="count" value="0" scope="page" />
                    <c:forEach var="item" items="${requestScope.list}">

                        <tr>
                            <td class="text-uppercase text-danger fw-bold">${item.getName()}</td>
                            <td >${item.getPhone()}</td>
                            <td>${item.getAge()}</td>
                            <c:if test="${item.getGender()!=0}">                                    
                                <td>Nam</td>                                   
                            </c:if> 
                            <c:if test="${item.getGender()==0}">                                    
                                <td >Nữ </td>                                      
                            </c:if>
                            <td>${item.getAddress()}</td>
                            <td>${item.getReceipt().size()}</td>
                            <td>${item.getOther().size()}</td>
                            <td>
                                <a class="btn btn-outline-warning" role="button" aria-disabled="true" href="customerdetail?id=${item.getId()}">Chi Tiết </a>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-outline-primary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                                        Action
                                    </button>
                                    <ul class="dropdown-menu">
                                        </li><a class="dropdown-item" aria-disabled="true" href="receiptbycustomer?id=${item.getId()}">Tạo Hoá Đơn</a></li>
                                        <li><a class="dropdown-item" aria-disabled="true" href="receiptbycustomer_eyeonly?id=${item.getId()}">Đo Mắt</a></li>
                                        <li><hr class="dropdown-divider"></li>
                                        <li><a class="dropdown-item" href="otherbycustomer?id=${item.getId()}">Dịch Vụ #</a></li>
                                    </ul>
                                </div>                 
                        </tr>
                    </c:forEach>
                </tbody>                
            </table>           
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <c:if test="${requestScope.pagecurren>1}">
                        <li class="page-item"><a class="page-link" href="customer?pagecurren=${requestScope.pagecurren-1}">Previous</a></li>
                        </c:if>
                    
                        <c:set var="pagecurr" value="${requestScope.pagecurren}"/>
                        <c:forEach begin="${1}" end="${requestScope.pagenumber}" var="i">                    

                        <c:if test="${i!=pagecurren}">
                            <li class="page-item"><a class="page-link" href="customer?pagecurren=${i}">${i}</a></li>
                            </c:if>
                            <c:if test="${i==pagecurren}">
                            <li class="page-item active"><a class="page-link" href="customer?pagecurren=${i}">${i}</a></li>
                            </c:if>
                        </c:forEach>
                        <c:if test="${requestScope.pagecurren<requestScope.pagenumber}">
                        <li class="page-item"><a class="page-link" href="customer?pagecurren=${requestScope.pagecurren+1}">Next</a></li>
                        </c:if>
                </ul>
            </nav>
        </div>
                        <div class="position-fixed bottom-0 end-0 mb-3 me-3">
            <!-- Example split danger button -->
            <div class="btn-group">
                <button type="button" class="btn btn-danger">${sessionScope.account.user}</button>
                <button type="button" class="btn btn-danger dropdown-toggle dropdown-toggle-split" data-bs-toggle="dropdown"
                        aria-expanded="false">
                    <span class="visually-hidden">Toggle Dropdown</span>
                </button>
                <ul class="dropdown-menu">
                    <li><a class="dropdown-item" href="#">Xin CHào ${sessionScope.account.fullname}</a></li>
                    <li>
                        <hr class="dropdown-divider">
                    </li>
                    <li><a class="dropdown-item" href="dologout">Logout</a></li>
                    <li><a type="button" class="dropdown-item" data-bs-toggle="modal" data-bs-target="#exampleModal" >Account Details</a></li>

                </ul>
            </div>
        </div>

        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="exampleModalLabel">Thông Tin User</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <p> Họ Và Tên : ${sessionScope.account.fullname}</p>
                        <p> Số Phone  : ${sessionScope.account.phone}</p>
                        <p> Số Email  : ${sessionScope.account.email}</p>
                        <c:if test="${sessionScope.account.role==1}">
                           <p> Quyền Hạn : ADMIN</p>   
                        </c:if>
                        <c:if test="${sessionScope.account.role==2}">
                            <p> Quyền Hạn : USER</p>
                        </c:if>

                        <p> Ngày Tạo  : ${sessionScope.account.joindate}</p>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary">Save changes</button>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <script src="https://cdn.datatables.net/v/bs5/dt-2.1.5/datatables.min.js"></script>
    </body>
</html>
