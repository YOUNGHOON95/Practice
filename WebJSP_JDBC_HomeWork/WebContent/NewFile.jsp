<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="js/scripts.js"></script>
    <link rel="stylesheet" href="js/cs.css">
</head>
<body>

<jsp:include page="/common/Top2.jsp"></jsp:include>

    <section class="home">
    </section>
    <div style="height: 700px">
        <!-- just to make scrolling effect possible -->
			<td style="width: 700px">
				<!-- MAIN PAGE CONTENT  -->
				<%
					String id = null;
					id = (String)session.getAttribute("userid");
					
					if(id != null){
						//회원
						out.print("<h1>"+id + " 회원님 방가방가^^</h1><br>");
						if(id.equals("admin")){
							out.print("<a href='NewFile6.jsp'>회원관리</a>");
						}
					}else{
						//로그인 하지 않은 사용자
						//메인 페이지는 회원만 볼수 있어요 (강제 링크 추가)
						out.print("<h1>사이트 방문을 환영합니다 ^^ <br>회원가입 좀 하지 ...</h1>");
					}
				%>
			</td>
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