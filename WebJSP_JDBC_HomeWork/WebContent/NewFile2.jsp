<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
   <!-- Jquery needed -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="js/scripts.js"></script>
    <link rel="stylesheet" href="js/cs.css">
</head>
<style type="text/css">
table {
	border: solid 2px black;
	border-collapse: collapse;
}

tr {
	border: solid 1px blue;
	background-color: white;
	color: black;
}

td {
	border: solid 1px red;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script type="text/javascript">
 //jquery 로 간단하게 유효성 check 하기
 $(function() {
  	$('#joinForm').submit(function() {
	   //alert("가입");
	if ($('#id').val() == "") { // 아이디 검사
    	alert('ID를 입력해 주세요.');
    	$('#id').focus();
    return false;
   } else if ($('#pwd').val() == "") { // 비밀번호 검사
    alert('PWD를 입력해 주세요.');
    $('#pwd').focus();
    return false;
   }else if ($('#mname').val() == "") { // 이름 검사
    alert('mname를 입력해 주세요.');
    $('#mname').focus();
    return false;
   }else if ($('#age').val() == "") { // 나이 검사
    alert('age를 입력해 주세요.');
    $('#age').focus();
    return false;
   }else if ($('#email').val() == "") { // 우편번호
    alert('email를 입력해 주세요.');
    $('#email').focus();
    return false;
   }
   
  });
 });
</script>

<body>

	<jsp:include page="/common/Top2.jsp"></jsp:include>
	
    <section class="home">
    </section>
    <div style="height: 700px">
        <!-- just to make scrolling effect possible -->
		<tr>
			<td style="width: 700px">
				<form action="NewFile3.jsp" method="post" name="joinForm" id="joinForm">
					<h3 style="text-align: center;">회원가입</h3>
					<div>
						<table
							style="width: 800px; height: 400px; margin-left: auto; margin-right: auto;">
							<tr>
								<th>ID:</th>
								<td><input type="text" name="id" id="id"></td>
							</tr>
							<tr>
								<th>PWD:</th>
								<td><input type="password" name="pwd" id="pwd"></td>
							</tr>
							<tr>
								<th>Name:</th>
								<td><input type="text" name="mname" id="mname"></td>
							</tr>
							<tr>
								<th>age:</th>
								<td><input type="text" name="age" id="age" maxlength="3"></td>
							</tr>
							<tr>
								<th>Gender:</th>
								<td><input type="radio" name="gender" id="gender" value="여"
									checked>여자 <input type="radio" name="gender"
									id="gender" value="남">남자</td>
							</tr>
							<tr>
								<th>Email:</th>
								<td><input type="text" name="email" id="email"></td>
							</tr>
							<tr>
								<td colspan="2">
									<input type="submit" value="회원가입">
									<input type="reset" value="취소"></td>
							</tr>
						</table>

					</div>
				</form>
			</td>
		</tr>
    </div>



<!-- Function used to shrink nav bar removing paddings and adding black background -->
    <script>
        $(window).scroll(function() {
            if ($(document).scrollTop() > 50) {
                $('.nav').addClass('affix');
                console.log("OK");
            } else {
                $('.nav').removeClass('affix');
            }
        });
    </script>
    
</body>
</html>