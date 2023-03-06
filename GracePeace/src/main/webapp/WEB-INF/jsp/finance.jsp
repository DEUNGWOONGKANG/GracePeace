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
                url: 'finance/select',
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
						label: 'userid',
                        name: 'user.id',
                        width: 0,
						hidden: true
                    },
                    {
						label : '성명',
                        name: 'user.username',
                        width: 100,
                        editable: false,
                        align:'center'
                    },
                    {
						label : '직분',
                        name: 'user.gradename',
                        width: 100,
                        editable: false,
                        align:'center'
                    },
                    {
						label: '헌금종류',
                        name: 'kind',
                        width: 100,
                        editable: true,
                        align:'center'
                    },
                    {
						label: '월',
                        name: 'month',
                        width: 50,
                        editable: true,
                        cellEdit: true,
                        edittype: 'select', 
                        formatter: 'select',
                        editoptions:{value: getMonthList()},
                        align:'center'
                    },
                    {
						label: '주차',
                        name: 'week',
                        width: 50,
                        editable: true,
                        align:'center'
                    },
                    {
						label : '금액',
                        name: 'price',
                        width: 100,
                        editable: true,
                        formatter:'integer',
                        formatoptions:{thousandsSeparator:","},
                        align:'center'
                    },
                    {
						label: '메모',
                        name: 'memo',
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
                footerrow: true, 
                userDataOnFooter : true,
                gridComplete : function(){
    		    	let sum = $("#jqGrid").jqGrid('getCol','price', false, 'sum'); 
    				$('#jqGrid').jqGrid('footerData', 'set', {'user.username':'합계', price: sum});
    				
    				$('table.ui-jqgrid-ftable td:eq(0)').hide();
    				$('table.ui-jqgrid-ftable td:eq(1)').hide();
    				$('table.ui-jqgrid-ftable td:eq(3)').hide();
    				$('table.ui-jqgrid-ftable td:eq(4)').hide();
    				$('table.ui-jqgrid-ftable td:eq(5)').hide();
    				$('table.ui-jqgrid-ftable td:eq(6)').hide();
    				$('table.ui-jqgrid-ftable td:eq(8)').hide();
    		    },
    		    onCellSelect : function(rowId, iCol, content, e){
    		    	let classCheck = $("#jqGrid_iladd").attr("class");
    		    	if(iCol == 2 && classCheck.includes("ui-state-disabled")){
    		    		$('#myModal').modal('show');
    		    		
    		    		$("#userGrid").jqGrid({
    		    			url: "user/select",
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
    		                        editable: false,
    		                        align: 'center'
    		                    },
    		                    {
    								label: '직분',
    		                        name: 'gradename',
    		                        width: 50,
    		                        editable: false,
    		                        align: 'center'
    		                    },
    		                    {
    								label : '생년월일',
    		                        name: 'birthday',
    		                        width: 50,
    		                        editable: false,
    		                        align:'center'
    		                    },
    		                    {
    								label: '전화번호',
    		                        name: 'phone',
    		                        width: 100,
    		                        editable: false,
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
    						width: 580,
    		                autoheight: true,
    		                rowNum: 50,
    		    		    ondblClickRow : function(rowId, iRow, iCol){
    		    		    	let rowData = $("#userGrid").jqGrid("getRowData", rowId);
    		    		    	let selrow = $("#jqGrid").getGridParam("selrow");
    		    		    	
    		    		    	$('#jqGrid').jqGrid('setCell', selrow, 'user.id', rowData.id);
    		    		    	$('#jqGrid').jqGrid('setCell', selrow, 'user.username', rowData.username);
    		    		    	$('#jqGrid').jqGrid('setCell', selrow, 'user.gradename', rowData.gradename);
    		    		    }
    		            });
    		    	}
    		    }
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
            
        	$("#startDate").datepicker();
        	$("#startDate").datepicker( "option", "dateFormat", "yy-mm-dd" );
        	$("#endDate").datepicker();
        	$("#endDate").datepicker( "option", "dateFormat", "yy-mm-dd" );
        	
        	//저장 버튼 클릭시 db에 저장
        	$("#jqGrid_ilsave").on("click", function(){
        		let rowId = $('#jqGrid').getGridParam("selrow");
        		let rowData = $("#jqGrid").jqGrid("getRowData", rowId);
        		let idVal = rowId;
        		if(idVal.includes("jqg")) idVal = null;
        		
        		let postData = {"id": idVal, 
            		"userid": rowData["user.id"], 
					"kind": rowData["kind"],
					"price": rowData["price"],
					"memo": rowData["memo"],
            		"month": rowData["month"],
            		"week": rowData["week"]
            	};
        		
        		$.ajax({
	                type : "POST",
	                url : "/finance/save",
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
        	
        	//모달팝업 검색버튼
        	$("#modalSearch").on("click", function(){
        		$("#userGrid").clearGridData();
        		$("#userGrid").jqGrid("setGridParam",
        			{
        			url: "user/select",
        			datatype: "JSON",
        			mtype:"POST",
        			postData:{
        				username: $("#modal_username").val(),
        				birthday: $("#modal_birthday").val()
        			},
        			success: function(data) {}
        		}).trigger("reloadGrid");
        	});
        	
        	//메인 검색버튼
        	$("#searchBtn").on("click", function(){
        		$("#jqGrid").clearGridData();
        		console.log($("#username").val());
        		$("#jqGrid").jqGrid("setGridParam",
        			{
        			url: "finance/select",
        			datatype: "JSON",
        			mtype:"POST",
        			postData:{
        				username: $("#username").val(),
        				birthday: $("#birthday").val(),
        				kind: $("#kind").val(),
        				startmonth: $("#startmonth").val(),
        				endmonth: $("#endmonth").val(),
        				startweek: $("#startweek").val(),
        				endweek: $("#endweek").val()
        			},
        			success: function(data) {}
        		}).trigger("reloadGrid");
        	});
        });
        
        function getMonthList() {
        	let kind = { '1': '1'
        				,'2': '2'
        				,'3': '3'
        				,'4': '4'
        				,'5': '5'
        				,'6': '6'
        				,'7': '7'
        				,'8': '8'
        				,'9': '9'
        				,'10': '10'
        				,'11': '11'
        				,'12': '12'}
        	return kind;
        }
        
        function deleteRow() {
        	let rowId = $('#jqGrid').getGridParam( "selrow" );
        	if(rowId != null){
	        	let result = confirm('선택한 데이터를 삭제하시겠습니까?');
	        	if(result){
		        	let selRowData = $("#jqGrid").jqGrid("getRowData", rowId);
		        	
		        	$.ajax({
		                type : "POST",
		                url : "/finance/delete",
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
        		alert("삭제할 데이터를 선택해 주세요.");
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
        	<input type="text" placeholder="헌금종류" class="form-control" name="kind" id="kind" style="width:150px; float:left; margin-left:10px;">
        	<input type="text" placeholder="시작 월" class="form-control" name="startmonth" id="startmonth" style="width:100px; float:left; margin-left:10px;">
        	<input type="text" placeholder="시작 주차" class="form-control" name="startweek" id="startweek" style="width:120px; float:left; margin-left:10px;">
        	<span style="float:left; font-size: 16pt; padding: 10px 10px;">~</span>
        	<input type="text" placeholder="종료 월" class="form-control" name="endmonth" id="endmonth" style="width:100px; float:left;">
        	<input type="text" placeholder="종료 주차" class="form-control" name="endweek" id="endweek" style="width:120px; float:left; margin-left:10px;">
        </div>
    	<button id="searchBtn" class="btn" style="margin-left:20px;">검색</button>
    </div>
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content" style="height:500px;">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
	        <h4 class="modal-title" id="myModalLabel">성도 찾기</h4>
	      </div>
	      <div class="modal-body" style="height:365px;width:100%;">
	        <table id="userGrid"></table>
	      </div>
	      <div class="modal-footer">
	      	<input type="text" placeholder="성명" class="form-control" id="modal_username" style="width:100px; float:left; margin-left:5px;">
	      	<input type="text" placeholder="생년월일" class="form-control" id="modal_birthday" style="width:150px; float:left; margin-left:10px;">
		    <button type="button" class="btn btn-primary" id="modalSearch">찾기</button>
	        <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
	      </div>
	    </div>
	  </div>
	</div>
</body>
</html>