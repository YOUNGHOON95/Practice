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
	
	border-collapse: collapse;
}

tr {
	border: solid 1px black;
	background-color: white;
	color: black;
}

td {
	border: solid 1px black;
}
</style>

<body>

	<jsp:include page="/common/Top2.jsp"></jsp:include>
	
    <section class="home">
    </section>
    <div style="height: 700px">
        <!-- just to make scrolling effect possible -->
					<div>
					<form action="NewFile4.jsp" method="post" name="loginForm" id="joinForm">
					<h1 style="text-align: center; ">Login Page</h1>
						<table
							style="width: 800px; height: 400px; margin-left: auto; margin-right: auto;">
							<tr>
								<th><h1>ID:</h1></th>
								<td><input type="text" name="id" id="id"></td>
							</tr>
							<tr>
								<th><h1>PWD:</h1></th>
								<td><input type="password" name="pwd" id="pwd"></td>
							</tr>
							<tr>
								<td colspan="2"><input type="submit" value="Login"> <input
									type="reset" value="Cancel"></td>
							</tr>
						</table>
					</form>
					</div>
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