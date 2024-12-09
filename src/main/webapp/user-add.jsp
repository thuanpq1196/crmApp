<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" type="image/png" sizes="16x16" href="plugins/images/favicon.png">
    <title>Pixel Admin</title>
    <!-- Bootstrap Core CSS -->
    <link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Menu CSS -->
    <link href="plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css" rel="stylesheet">
    <!-- animation CSS -->
    <link href="css/animate.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="css/style.css" rel="stylesheet">
    <!-- color CSS -->
    <link href="css/colors/blue-dark.css" id="theme" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>

<body>
    <!-- Preloader -->
    <div class="preloader">
        <div class="cssload-speeding-wheel"></div>
    </div>
    <div id="wrapper">
        <!-- Navigation -->
        <div id="loadnavbar"></div>
        <!-- Left navbar-header end -->
        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">${user.id!= null? "Chỉnh sửa thông tin thành viên" : "Thêm mới thành viên"}</h4>
                    </div>
                </div>
                <!-- /.row -->
                <!-- .row -->
                <div class="row">
                    <div class="col-md-2 col-12"></div>
                    <div class="col-md-8 col-xs-12">
                        <div class="white-box">
                            <form class="form-horizontal form-material" action="${user.id != null ? 'user-update' : 'user-add' }" method="post">
                            	<div class="form-group" hidden>
                                    <label class="col-md-12">ID</label>
                                    <div class="col-md-12">
                                        <input name="id" type="text" 
                                            class="form-control form-control-line" value="${user.id}"> </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">Full Name</label>
                                    <div class="col-md-12">
                                        <input name="fullname" type="text" 
                                            class="form-control form-control-line" value="${user.fullName}"> </div>
                                </div>
                                <div class="form-group">
                                    <label for="example-email" class="col-md-12">Email</label>
                                    <div class="col-md-12">
                                        <input  type="email" 
                                            class="form-control form-control-line" name="email" value ="${user.email}"
                                            id="example-email"> </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">Password</label>
                                    <div class="col-md-12">
                                        <input name="password" type="password" value="${user.password}" class="form-control form-control-line">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-12">Select role</label>
                                    <div class="col-sm-12">
                                        <select name = "role" class="form-control form-control-line">
                                        <c:forEach items="${roles}" var="item">
                                        	<c:choose>
    											<c:when test="${user.id != null && item.id == user.roleId}">
  													<option value="${item.id}" selected>${item.description}</option>
    											</c:when>    
											    <c:otherwise>
											    	<option value="${item.id}">${item.description}</option>
											    </c:otherwise>
											</c:choose>
                                        </c:forEach>
                                        
                                            <!-- <option>London</option>
                                            <option>India</option>
                                            <option>Usa</option>
                                            <option>Canada</option>
                                            <option>Thailand</option> -->
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="example-address" class="col-md-12">Address</label>
                                    <div class="col-md-12">
                                        <input type="text"
                                            class="form-control form-control-line" name="address" value ="${user.address}"
                                            id="example-address"> </div>
                                </div>
                                <div class="form-group">
                                    <label for="example-phone" class="col-md-12">Phone number</label>
                                    <div class="col-md-12">
                                        <input  type="tel" 
                                            class="form-control form-control-line" name="phoneNumber" value ="${user.phoneNumber}"
                                            id="example-phone" pattern="[0-9]{3}[0-9]{3}[0-9]{4}"> </div>
                                </div>
                                <div class="form-group" hidden>
                                    <label for="example-phone" class="col-md-12">Is Active</label>
                                    <div class="col-md-12">
                                        <input  type="text" 
                                            class="form-control form-control-line" name="isActive" value ="${user.isActive}"
                                            id="example-active" ></div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-12">
                                        <button type="submit" class="btn btn-success">${user.id != null ? 'Update User' : 'Add User' }</button>
                                        <a href="user-table.jsp" class="btn btn-primary">Quay lại</a>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="col-md-2 col-12"></div>
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->
            <footer class="footer text-center"> 2018 &copy; myclass.com </footer>
        </div>
        <!-- /#page-wrapper -->
    </div>
    <!-- /#wrapper -->
    <!-- jQuery -->
    <script src="plugins/bower_components/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- Menu Plugin JavaScript -->
    <script src="plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"></script>
    <!--slimscroll JavaScript -->
    <script src="js/jquery.slimscroll.js"></script>
    <!--Wave Effects -->
    <script src="js/waves.js"></script>
    <!-- Custom Theme JavaScript -->
    <script src="js/custom.min.js"></script>
    <script>
		$(function(){
		  $("#loadnavbar").load("nav.jsp");
		});
	</script>
</body>

</html>    