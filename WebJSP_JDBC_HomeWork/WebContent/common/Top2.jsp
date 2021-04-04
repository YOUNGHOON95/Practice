<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="nav">
        <div class="container">
            <div class="logo">
                <a href="NewFile.jsp">메인 페이지</a>
            </div>
            <div id="mainListDiv" class="main_list">
                <ul class="navlinks">
                    <li><a href="NewFile2.jsp">회원가입</a></li>
                    <li><a href="NewFile1.jsp">로그인</a></li>
                    <li><a href="#">Intro</a></li>
                    <li><a href="#">Intro</a></li>
					<li>
					<%
					if (session.getAttribute("userid") != null) {
						out.print("<a href='#'><b>[ " + session.getAttribute("userid") + " ]</b> 로그인 상태</a>");
						out.print("<a href='NewFile5.jsp'>[ 로그아웃 ]</a>");
					} else {
						out.print("<a href='#'><b>[로그인 하지 않으셨네요]</b></a>");
						out.print("<a href='NewFile.jsp'>[ 로그인 ]</a>");
					}
					%>
					</li>
				</ul>
            </div>
            <span class="navTrigger">
                <i></i>
                <i></i>
                <i></i>
            </span>
        </div>
    </nav>

