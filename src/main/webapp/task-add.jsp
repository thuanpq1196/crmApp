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
    <link href="css/modal.css" rel="stylesheet">
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
                        <h4 class="page-title">Thêm mới công việc</h4>
                    </div>
                </div>
                <!-- /.row -->
                <!-- .row -->
                <div class="row">
                    <div class="col-md-2 col-12"></div>
                    <div class="col-md-8 col-xs-12">
                        <div class="white-box">
                            <form class="form-horizontal form-material" action="${task.id != null ? 'task-update': 'task-add'}" method="post">
                            	 <div class="form-group" hidden>
                                    <label class="col-md-12">ID</label>
                                    <div class="col-md-12">
                                        <input type="text" placeholder="ID" readonly
                                            class="form-control form-control-line" name ="id" value ="${task.id}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">Dự án</label>
                                    <div class="col-md-12">
                                        <select class="form-control form-control-line" name ="jobID">
                                        <c:forEach items="${jobs}" var="job">
                                            <c:choose>
    											<c:when test="${task.id != null && task.jobId == job.id}">
  													<option value="${task.jobId}" selected>${task.jobName}</option>
    											</c:when>    
											    <c:otherwise>
											    	<option value="${job.id}">${job.name}</option>
											    </c:otherwise>
											</c:choose>
                                        </c:forEach>
                                        <%-- <c:if test="${task.id != null}">
                                        	<option value="${task.jobId}" selected>${task.jobName}</option>
                                        </c:if> --%>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">Tên công việc</label>
                                    <div class="col-md-12">
                                        <input type="text" placeholder="Tên công việc"
                                            class="form-control form-control-line" name ="name" value="${task.name}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">Mô tả</label>
                                    <div class="col-md-12">
                                        <input type="text" placeholder="Mô tả công việc"
                                            class="form-control form-control-line" name ="description" value ="${task.description}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">Người thực hiện</label>
                                    <div class="col-md-12">
                                        <select class="form-control form-control-line" name ="userID">
                                            <c:forEach items="${users}" var="user">
                                            	<c:choose>
	    											<c:when test="${task.id != null && task.userId == user.id}">
	  													<option value="${task.userId}" selected>${task.userName}</option>
	    											</c:when>    
												    <c:otherwise>
												    	<option value="${user.id}">${user.fullName}</option>
												    </c:otherwise>
												</c:choose>
                                        	</c:forEach>
                                        <%-- <c:if test="${task.id != null}">
                                        	<option value="${task.userId}" selected>${task.userName}</option>
                                        </c:if> --%>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">Ngày bắt đầu</label>
                                    <div class="col-md-12">
                                        <input type="text" placeholder="mm/dd/yyyy" onfocus="(this.type='date')"
                                            class="form-control form-control-line" name="startdate" value="${task.startDate}"> 
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">Ngày kết thúc</label>
                                    <div class="col-md-12">
                                        <input type="text" placeholder="mm/dd/yyyy" onfocus="(this.type='date')"
                                            class="form-control form-control-line" name="enddate" value="${task.endDate}"> 
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">Trạng thái</label>
                                    <div class="col-md-12">
                                        <select class="form-control form-control-line" name ="statusID">
                                            <c:forEach items="${status}" var="item">
                                            <c:choose>
    											<c:when test="${task.id != null && task.status == item.id}">
  													<option value="${task.status}" selected>${task.statusName}</option>
    											</c:when>    
											    <c:otherwise>
											    	<option value="${item.id}">${item.name}</option>
											    </c:otherwise>
											</c:choose>
                                        	</c:forEach>
                                        	<%-- <c:if test="${task.id != null}">
                                        	<option value="${task.status}" selected>${task.statusName}</option>
                                        	</c:if> --%>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-12">
                                        <button type="submit" class="btn btn-success">Lưu lại</button>
                                        <a href="task.html" class="btn btn-primary">Quay lại</a>
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
    <script src="js/modal.js"></script>
    <script>
		$(function(){
		  $("#loadnavbar").load("nav.jsp");
		});
	</script>
</body>

</html>