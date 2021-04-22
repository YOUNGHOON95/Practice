<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
 <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css?family=Muli:300,400,500,600,700,800,900&display=swap" rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="css/myshop.css" type="text/css">
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="css/themify-icons.css" type="text/css">
    <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="css/style.css" type="text/css">
    
    <!-- 제이쿼리 문 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    
    <!--  탭 부트스트랩  -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>

	

	<div id="fullwrap">
	<jsp:include page="WEB-INF/views/include/header.jsp"></jsp:include>

<div id="productdetailName">
<h4>상품정보</h4>

</div>
<hr>
<div id="ProductTotal">
<div id="ProductPhoto">



<!-- 
<form method="POST" enctype="multipart/form-data" id="uploadForm">	
    <input type="file" name="profile" />
    <button id='btnUpload'>확인</button>
</form>
-->
<!-- 
<div class="box" style="background: #BDBDBD;">
<form name="signform" method="POST" ENCTYPE="multipart/form-data" action="myshop.jsp">
    <input type="file" id="file" name="file" style="display:none;" onchange="changeValue(this)">
    <input type="hidden" name = "target_url">
</form>
<img id="target_img" src="image/profile.png">
</div> 
-->

<!--  
<img src="img/insta-2.jpg" id = "target_img">

<form name="signform" method="POST" ENCTYPE="multipart/form-data" action="myshop.jsp">
    <input type="file" id="file" name="file" style="display:none;" onchange="changeValue(this)">
    <input type="hidden" name = "target_url">
</form>
-->

</div>
<div id="Storename">
	
<%
        session = request.getSession();
        out.print(""+ session.getAttribute("storename") + "<br>");
        //session = request.getSession();
        //out.print(""+session.getAttribute("isLogined"));	
%>
</div>

	<c:choose>
		<c:when test="${session.isLogined}">
			<input id="editBtn" type="button" value="수정하기">
			<input type="button" value="내 상점 관리" id="myshopeditBtn" name="">
			
		</c:when>
		<c:otherwise>
			<input id="editBtn" type="button" value="수정하기" style="display:none;">
			<input type="button" value="내 상점 관리" id="myshopeditBtn" name="" style="display:none;">
		</c:otherwise>
	</c:choose>
</div>
	
<div style='width:100%;margin:0 auto;margin-top:50px;'>
	<ul class="nav nav-tabs">
		<li class='active'><a href="#tabmenu_01" data-toggle="tab">탭메뉴1</a></li>
		<li><a href="#tabmenu_02" data-toggle="tab">탭메뉴2</a></li>
		<li><a href="#tabmenu_03" data-toggle="tab">탭메뉴3</a></li>
	</ul>
	<div class="tab-content">
		<div class="tab-pane fade in active" id="tabmenu_01"><p>탭메뉴1 내용</p></div>
		<div class="tab-pane fade" id="tabmenu_02"><p>탭메뉴2 내용</p></div>
		<div class="tab-pane fade" id="tabmenu_03"><p>탭메뉴3 내용</p></div>
	</div>
</div>
<!-- Footer Section Begin -->
    <jsp:include page="WEB-INF/views/include/footer.jsp"></jsp:include>
<!-- Footer Section End -->
</div>
</body>
<script type="text/javascript">



/*
$('#target_img').click(function (e) {
    document.signform.target_url.value = document.getElementById( 'target_img' ).src;
    e.preventDefault();
    $('#file').click();
});    

function changeValue(obj){
	   document.signform.submit();

	}
*/
/*
$('#btnUpload').on('click', function(event) {
    event.preventDefault();
    
    var form = $('#uploadForm')[0]
    var data = new FormData(form);
    
    $('#btnUpload').prop('disabled', true);
	
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "upload",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {
        	$('#btnUpload').prop('disabled', false);
        	alert('success')
        },
        error: function (e) {
            $('#btnUpload').prop('disabled', false);
            alert('fail');
        }
    });
})
*/
</script>


</html>