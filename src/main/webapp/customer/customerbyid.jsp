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
        <script type="text/javascript">
            
           function  DeleteOther(cid,oid){
                if(confirm("Are You Sure To Delete ?"))
                {
                    window.location="otherdelete?cid="+cid+"&oid="+oid;
                    //alert("otherdelete?cid="+cid+"&oid="+oid);
                }
            }
            
            function  DeleteEyeService(cid,eid){
                if(confirm("Are You Sure To Delete ?"))
                {
                    window.location="eyedelete?cid="+cid+"&eid="+eid;
                    //alert("otherdelete?cid="+cid+"&oid="+oid);
                }
            }
            
            function  DeleteOder(cid,rid){
                if(confirm("Are You Sure To Delete ?"))
                {
                    window.location="oderdlete?cid="+cid+"&rid="+rid;
                    //alert("otherdelete?cid="+cid+"&oid="+oid);
                }
            }
        </script>
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
                                <li><a class="dropdown-item" href="#">Tạo Mới Khách Hàng</a></li> 
                                <li><a class="dropdown-item" href="#">Another action</a></li>
                                <li><hr class="dropdown-divider"></li>
                                <li><a class="dropdown-item" href="createother">Tạo Dịch Vụ Khác</a></li>
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
                                        <a class="btn btn-outline-primary" role="button" aria-disabled="true" href="receiptbycustomer_edit?cid=${customer.id}&rid=${item.id}">Sửa</a>
                                        <a class="btn btn-outline-danger ms-1" role="button" aria-disabled="true" onclick="DeleteOder(`${customer.id}`,`${item.id}`)">Xoá</a>
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
                                        <a class="btn btn-outline-primary" role="button" aria-disabled="true" href="receiptbycustomer_eyeonlyedit?cid=${customer.id}&eid=${item.eyeid}" >Sửa</a>
                                        <a class="btn btn-outline-danger ms-1" role="button" aria-disabled="true" onclick="DeleteEyeService(`${customer.id}`,`${item.eyeid}`)">Xoá</a>
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
                                        <a class="btn btn-outline-primary" href="otherbycustomer_edit?cid=${customer.id}&oid=${item.id}">Sửa</a>
                                        <a class="btn btn-outline-danger ms-1 "  onclick="DeleteOther(`${customer.id}`,${item.id})" >Xoá</a>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>

                    </tbody>                
                </table> 
            </div> 
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
