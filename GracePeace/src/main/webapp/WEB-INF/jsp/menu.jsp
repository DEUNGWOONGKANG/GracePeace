<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="resources/css/menu.css" />
<script type="text/javascript">
var menuName = "<%=(String)session.getAttribute("menu")%>";
var adminid = "<%=session.getAttribute("adminid")%>";
$(function(){
	if(adminid != "admin"){
		$("#admin").css('display', 'none');
	}
	var menu = document.getElementById("menubar").getElementsByTagName("li");
	for(var i=0; i<menu.length; i++){
		if(menuName == menu[i].id){
			document.getElementById(menu[i].id).className = document.getElementById(menu[i].id).className + " expand";
		}
	}
	$(".zeta-menu>li:has(ul)>a").each( function() {
		$(this).html( $(this).html()+' &or;' );
	});
	$(".zeta-menu ul li:has(ul)")
		.find("a:first")
		.append("<p style='float:right;margin:-3px'>&#9656;</p>");

	$(".zeta-menu li>a").click(function(){
		var li = $(this).parent();
		$("#menu").val(li[0].id);
		var form = document.getElementById('form');
		form.submit();
	});
});
</script>

<title>메인</title>
</head>
<body>
	<form action="/menu" method="post" id="form">
		<input type="hidden" id="menu" name="menu"/>
    </form>
	<div class='zeta-menu-bar' id="menubar">
		<ul class="zeta-menu">
			<li id="main"><a>홈</a></li>
			<li id="finance"><a>헌금 관리</a></li>
			<li id="expend"><a>지출 관리</a></li>
			<li id="user"><a>성도 관리</a></li>
			<li id="admin"><a>관리자 관리</a></li>
		</ul>
	</div>
</body>
</html>