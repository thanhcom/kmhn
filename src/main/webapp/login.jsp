<%-- 
    Document   : login
    Created on : Oct 22, 2024, 4:37:58 PM
    Author     : thanhcom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous"> 
        <link href="https://cdn.datatables.net/v/bs5/dt-2.1.5/datatables.min.css" rel="stylesheet">
    </head>
    <body>
        <section class="vh-100" style="background-color: #9A616D;">
            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col col-xl-10">
                        <div class="card" style="border-radius: 1rem;">
                            <div class="row g-0">
                                <div class="col-md-6 col-lg-5 d-none d-md-block">
                                    <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/img1.webp"
                                         alt="login form" class="img-fluid" style="border-radius: 1rem 0 0 1rem;" />
                                </div>
                                <div class="col-md-6 col-lg-7 d-flex align-items-center">
                                    <div class="card-body p-4 p-lg-5 text-black">

                                        <form action="dologin" method="post">

                                            <div class="d-flex align-items-center mb-3 pb-1">
                                                <i class="fas fa-cubes fa-2x me-3" style="color: #ff6219;"></i>
                                                <span class="h1 fw-bold mb-0">Kính Mắt Hà Nội </span>
                                            </div>

                                            <h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Đăng Nhập Vào Hệ Thống </h5>

                                            <div data-mdb-input-init class="form-outline mb-4">
                                                <input type="text" name="user" id="form2Example17" class="form-control form-control-lg" />
                                                <label class="form-label" for="form2Example17">Tài Khoản</label>
                                            </div>

                                            <div data-mdb-input-init class="form-outline mb-4">
                                                <input type="password" name="pass" id="form2Example27" class="form-control form-control-lg" />
                                                <label class="form-label" for="form2Example27">Mật Khẩu </label>
                                            </div>

                                            <div class="pt-1 mb-4">
                                                <button data-mdb-button-init data-mdb-ripple-init class="btn btn-dark btn-lg btn-block " type="submit">Đăng Nhập </button>
                                            </div>
                                            <p class="text-warning bg-dark">${requestScope.ERR}</p>
                                            <p class="mb-5 pb-lg-2" style="color: #393f81;">Chưa có tài khoản ? <a href="#!"
                                                                                                                      style="color: #393f81;">Liên Hệ Với ADMIN </a></p>
                                            <a href="#!" class="small text-muted">Terms of use.</a>
                                            <a href="#!" class="small text-muted">Privacy policy</a>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
