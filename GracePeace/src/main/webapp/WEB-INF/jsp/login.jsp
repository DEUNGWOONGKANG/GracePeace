<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- JS -->
<script src="resources/js/jquery-1.11.1.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/jquery.backstretch.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
    	$.backstretch("resources/images/bg.jpg");
    });
</script>
<!-- CSS -->
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/form-elements.css">
<link rel="stylesheet" href="resources/css/gpcStyle.css">
<!-- Favicon and touch icons -->
<link rel="shortcut icon" href="resources/images/favicon.png">

<title>Login Page</title>
</head>
<body>
	<!-- Top content -->
    <div class="top-content">
        <div class="inner-bg">
            <div class="container">
                <div class="row">
                    <div class="col-sm-8 col-sm-offset-2 text">
                        <h1>재정관리</h1>
                        <div class="description">
                        	<p>
                         	우리 교회 재정관리를 위한 페이지
                        	</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-5" style="width:100%">
                    	<div class="form-box">
	                     	<div class="form-top">
	                     		<div class="form-top-left">
	                     			<h3>Login to our site</h3>
	                         		<p>Enter username and password to log on:</p>
	                     		</div>
	                     		<div class="form-top-right">
	                     			<i class="fa fa-key"></i>
	                     		</div>
	                        </div>
	                        <div class="form-bottom">
			                   <form role="form" action="/login" method="post" class="login-form">
			                    	<div class="form-group">
			                    		<label class="sr-only" for="form-username">id</label>
			                        	<input type="text" placeholder="Username..." class="form-username form-control" name="loginid">
			                        </div>
			                        <div class="form-group">
			                        	<label class="sr-only" for="form-password">password</label>
			                        	<input type="password" placeholder="Password..." class="form-password form-control" name="password">
			                        </div>
			                        <button type="submit" class="btn">로그인</button>
			                    </form>
			            	</div>
                  		</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Footer -->
    <footer>
    	<div class="container">
    		<div class="row">
    		</div>
    	</div>
    </footer>
</body>
</html>