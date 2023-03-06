<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <script type="text/javascript" src="resources/js/jquery.min.js"></script> 
    <script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="resources/js/jquery.jqGrid.min.js"></script>
    <script type="text/javascript" src="resources/js/grid.locale-kr.js"></script>
    <script type="text/javascript" src="resources/js/jquery-ui.js"></script>
    <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="resources/css/form-elements.css">
    <link rel="stylesheet" type="text/css" href="resources/css/jquery-ui.css" />
    <link rel="stylesheet" type="text/css" href="resources/css/ui.jqgrid.css" />
	<!-- Favicon and touch icons -->
	<link rel="shortcut icon" href="resources/images/favicon.png">
	<style>
		table { font-size:11pt;}
	</style>
	<script type="text/javascript">
        $(document).ready(function () {
            $("#jqGrid").jqGrid({
                url: 'user/select',
                datatype: "JSON",
    			mtype:"POST",
                colModel: [
                    {
						label: 'id',
                        name: 'id',
                        width: 0,
						key: true,
						hidden: true
                    },
                    {
						label: '성명',
                        name: 'username',
                        width: 50,
                        editable: true,
                        align: 'center'
                    },
                    {
						label: '직분',
                        name: 'gradename',
                        width: 50,
                        editable: true,
                        align: 'center'
                    },
                    {
						label : '주민등록번호',
                        name: 'regnumber',
                        width: 100,
                        editable: true,
                        align:'center'
                    },
                    {
						label: '전화번호',
                        name: 'phone',
                        width: 100,
                        editable: true,
                        align:'center'
                    },
                    {
						label: '주소',
                        name: 'address',
                        width: 150,
                        editable: true,
                        align:'center'
                    }
                ],
				sortname: 'id',
				loadonce: true,
				autowidth: true,
                height: 500,
                rowNum: 10,
                pager: "#jqGridPager",
            });
            
            $('#jqGrid').navGrid("#jqGridPager", {edit: false, add: false, del: false, refresh: false, view: false, search: false});
            $("#jqGrid").navButtonAdd("#jqGridPager", {
                caption: "",
                title: "",
                buttonicon: "ui-icon-minusthick",  //버튼 이미지( + )
                onClickButton: deleteRow,        //버튼 클릭시 실행될 function
                id:"gridDelBtn"           //버튼 id
   			});
           $('#jqGrid').inlineNav('#jqGridPager',{ 
                edit: true, 
                search: false,
                save: true,
                add: true, 
                del: false, 
                cancel: true
            });
            
        	//저장 버튼 클릭시 db에 저장
        	$("#jqGrid_ilsave").on("click", function(){
        		let rowId = $('#jqGrid').getGridParam("selrow");
        		let rowData = $("#jqGrid").jqGrid("getRowData", rowId);
        		let idVal = rowId;
        		if(idVal.includes("jqg")) idVal = null;
        		
        		let postData = {"id": idVal, 
            		"username": rowData["username"], 
					"gradename": rowData["gradename"],
					"regnumber": rowData["regnumber"],
					"phone": rowData["phone"],
            		"address": rowData["address"]
            	};
        		
        		$.ajax({
	                type : "POST",
	                url : "/user/save",
	                contentType: 'application/json',
	                data : JSON.stringify(postData),
	                async : false,
	                success : function(data){
	                },
	                error : function(XMLHttpRequest, textStatus, errorThrown){ // 비동기 통신이 실패할경우 error 콜백으로 들어옵니다.
	                    alert("통신 실패.")
	                }
	            });
        	});
        	
        	//메인 검색버튼
        	$("#searchBtn").on("click", function(){
        		$("#jqGrid").clearGridData();
        		$("#jqGrid").jqGrid("setGridParam",
        			{
        			url: "user/select",
        			datatype: "JSON",
        			mtype:"POST",
        			postData:{
        				username: $("#username").val(),
        				regnumber: $("#regnumber").val(),
        				phone: $("#phone").val(),
        				address: $("#address").val()
        			},
        			success: function(data) {}
        		}).trigger("reloadGrid");
        	});
        });
        
        function deleteRow() {
        	let rowId = $('#jqGrid').getGridParam( "selrow" );
        	if(rowId != null){
	        	let result = confirm('선택한 데이터를 삭제하시겠습니까?');
	        	if(result){
		        	let selRowData = $("#jqGrid").jqGrid("getRowData", rowId);
		        	
		        	$.ajax({
		                type : "POST",
		                url : "/user/delete",
		                data : 'id='+selRowData.id,
		                async : false,
		                success : function(data){
		                	if(data.result == "Success"){
		                		$("#jqGrid").jqGrid("delRowData", rowId); // 행 삭제
		                	}
		                },
		                error : function(XMLHttpRequest, textStatus, errorThrown){ // 비동기 통신이 실패할경우 error 콜백으로 들어옵니다.
		                    alert("통신 실패.")
		                }
		            });
	        	}
        	}else{
        		alert("삭제할 성도를 선택해 주세요.");
        	}
        }
    </script>

<title>메인</title>
</head>
<body>
	<jsp:include page="menu.jsp"/>
	<table id="jqGrid"></table>
    <div id="jqGridPager"></div>
    <div id="searchForm">
    	<div class="form-group">
        	<input type="text" placeholder="성명" class="form-control" name="username" id="username" style="width:100px; float:left; margin-left:20px;">
        	<input type="text" placeholder="주민등록번호" class="form-control" name="regnumber" id="regnumber" style="width:150px; float:left; margin-left:10px;">
        	<input type="text" placeholder="전화번호" class="form-control" name="phone" id="phone" style="width:200px; float:left; margin-left:10px;">
        	<input type="text" placeholder="주소" class="form-control" name="address" id="address" style="width:200px; float:left; margin-left:10px;">
        </div>
    	<button id="searchBtn" class="btn" style="margin-left:20px;">검색</button>
    </div>
</body>
</html>