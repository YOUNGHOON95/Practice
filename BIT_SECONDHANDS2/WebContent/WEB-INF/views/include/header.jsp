<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
        <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css?family=Muli:300,400,500,600,700,800,900&display=swap" rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="css/themify-icons.css" type="text/css">
    <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="css/style.css" type="text/css">

    <!--j쿼리 cdn-->
    <script  src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> 
    <!-- Page Preloder 
    <div id="preloder">
        <div class="loader"></div>
    </div>-->
    <!-- Header Section Begin -->
    <header class="header-section">
        <div class="container">

            <div class="inner-header">
            <div id="rightmenu">
            <ul id="loginmenu">
            	<c:choose>
            	<c:when test="${sessionScope.storename eq null}">
            	<li><a href="login.do">로그인</a></li>
            	</c:when>
            	<c:otherwise>
            	<li onclick="logout(${request})">로그아웃</li>
            	</c:otherwise>
            	</c:choose>
            	<li><a href="register.do">회원가입</a></li>
            </ul>
            </div>
                <div class="row">
                    <div class="col-lg-2 col-md-2">
                        <div class="logo">
                            <a href="/index.jsp">
                                <img src="img/logo.png" alt="">
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-7 col-md-7">
                       
                        <div class="advanced-search">

                        <!--  <form class="search"> -->
                            <div class="input-group" id="header_search">
                                <input type="text" id="keyword">
                                <button type="submit" name="clickbtn" onclick="search()"><i class="ti-search"></i></button>
                            </div>
                        <!-- </form> -->

                        <!-- advanced-search end 헤더 검색창-->
                        </div>
                    </div>
                    <div class="col-lg-3 text-right col-md-3">
                    <!--  -->
                        <ul class="nav-right">
                            <li class="heart-icon">
                                <c:choose>
                            <c:when test="${sessionScope.storename eq null}">
                            <a href="login.do">
                                    <img src="img/sell.gif">
                                </a>
                                </c:when>
                             <c:otherwise>
                             <a href="#?storename="${sessionScope.storename}>
                             <img src="img/sell.gif">
                                </a>
                             </c:otherwise>
                             </c:choose>
                            </li>
                            <li class="cart-icon">
                            <c:choose>
                            <c:when test="${sessionScope.storename eq null}">
                            <a href="login.do">
                                    <img src="img/myshop.gif">
							</a>
                             </c:when>
                             <c:otherwise>
                             <a href="myshop.jsp?storename="${sessionScope.storename}>
                                    <img src="img/myshop.gif">
                                </a>
                             </c:otherwise>
                             </c:choose>
                            </li>
                            
                        </ul>
                    </div>
                </div>
            </div>
        <script>
        //로그아웃 함수
        function logout(request){
        	
        	request.getSession().invalidate();
        	request.getSession(true);
        }
        
      //검색하기 함수
        function search(){
    	  
        	$("#bodywrap").empty();
        	$("#bodywrap").append(
        	"<div id='content'><div id='ordermenu'><p>오늘의 추천</p><ul id='defaultorder'>"+
		"<li><input type='button' value='최신순' onclick='orderbytime()' id='timebtn' class='unclickedbtn'></li>"+
		"<li><input type='button' value='가격순' onclick='orderbyprice()' id='pricebtn' class='unclickedbtn'></li></ul>"+
	
		"<ul id='categoryorder'>"+
		"<li><input type='button' value='최신순' onclick='c_orderbytime()' id='timebtn' class='unclickedbtn'></li>"+
		"<li><input type='button' value='가격순' onclick='c_orderbyprice()' id='pricebtn' class='unclickedbtn'></li>"+
		"</ul></div><ul class='productlist'></ul>");
    	  
        
        	$("#defaultorder").css("display","flex");
        	$("#categoryorder").css("display","none");
        	
        	console.log("검색");
        	
			$.ajax(
        			
        			{	
        				url:"SearchProductOk.ajax",
        				//url:"maincontent.ajax",
        				type:"post",
        				dataType:"json",
        				data: { keyword : $("#keyword").val()},
        				success:function(responsedata){
        					console.log(responsedata);
        						$(".productlist").empty();
        					       					
$.each(responsedata, function(index, obj){
          							
        							$(".productlist").append("<li><a href='productdetail.do?p_num="+obj.p_num+"&storename="+obj.storename+"'><div class='thumnail'>"+obj.pimg_name+
        									"</div><div class=title>"+obj.p_subj+"</div><div class='imginfo'><p calss='price'>"+obj.p_price+"</p>"+
        									"<p class='wrtime'>"+obj.p_wr_time+"</p></div></a></li>");
        						});
								
								//$("#keyword").val("");
        						
        					
        				},
        				error:function(xhr){
        					console.log(xhr);
        				}
        			}
        			
        		);        	
        	
        }

        function getcategorytop(){
        	
        	console.log("카테고리 대");
        	
        	$.ajax(
        			{
        		
        				url:"GetCategoryOk.ajax",
        				type:"get",
        				dataType:"json",

        				success:function(responsedata){
        					
        					console.log("이제 대분류카테고리를 뿌려준다");
        					console.log(responsedata);
        				
        					$("#top").append("<option id='defualt'>대분류</option>");
        					
        					$.each(responsedata,function(index,obj){
        						
        						$("#top").append(
                						"<option id='category_t"+obj.t_num+"' value="+obj.t_num+">"+obj.t_name + "</option>"	
                				);
        						
        					});
        					
        					
   					
        					
        				},
        				error:function(xhr){
        					console.log(xhr);
        				}
        				
        		}
        	);
        	
        	
        }
        
        window.onload = function(){
        	getcategorytop();
        }
      
        function getcategorymiddle(){
        	
      		
        	console.log("카테고리 중");
        	
        	let index = $("#top option:selected").val();
        	console.log(index);
        	
        	$.ajax(
        			{
        		
        				url:"GetCategoryMBOk.ajax",
        				type:"get",
        				dataType:"json",
        				data:{ c_number : index},
        				success:function(responsedata){
        					
        					console.log("이제 중분류카테고리를 뿌려준다");
        					console.log(responsedata);
        					
        					$("#middle").empty();
        					$("#middle").append("<option id='defualt'>중분류</option>");
        				        					
        					$.each(responsedata,function(index,obj){
        						
        						$("#middle").append(
                						"<option id='category_n"+obj.n_num+"' value="+obj.m_num+">"+obj.m_name + "</option>"	
                				);
        					
        					});
   					
        					
        				},
        				error:function(xhr){
        					console.log(xhr);
        				}
        				
        		}
        	);
        	
        	
        }
      	
      	
        function getcategorybottom(){

        	console.log("카테고리 소");
        	
        	let index = $("#middle option:selected").val();
        	console.log(index);
        	
        	$.ajax(
        			{
        		
        				url:"GetCategoryMBOk.ajax",
        				type:"get",
        				dataType:"json",
        				data:{ c_number : index},
        				success:function(responsedata){
        					
        					
        					
        					console.log("이제 소분류카테고리를 뿌려준다");
        					console.log(responsedata);
        					
        					$("#bottom").empty();
        					$("#bottom").append("<option id='defualt'>소분류</option>");
        				        					
        					$.each(responsedata,function(index,obj){
        						
        						$("#bottom").append(
                						"<option id='category_n"+obj.b_num+"' value="+obj.b_num+">"+obj.b_name + "</option>"	
                				);
        					
        					});
        					
        				},
        				error:function(xhr){
        					console.log(xhr);
        				}
        				
        		}
        	);
        	
        	
        }
        
        //소분류 정하면 맞는 상품 불러오기
        function getselectedproduct(){
        	
        	/*
        	getcategorytop();
        	getcategorymiddle();
        	getcategorybottom();
        	
        	location.href='index.jsp';
        	*/
        	
        	$("#bodywrap").empty();
        	$("#bodywrap").append(
        	"<div id='content'><div id='ordermenu'><p>오늘의 추천</p><ul id='defaultorder'>"+
		"<li><input type='button' value='최신순' onclick='orderbytime()' id='timebtn' class='unclickedbtn'></li>"+
		"<li><input type='button' value='가격순' onclick='orderbyprice()' id='pricebtn' class='unclickedbtn'></li></ul>"+
	
		"<ul id='categoryorder'>"+
		"<li><input type='button' value='최신순' onclick='c_orderbytime()' id='timebtn' class='unclickedbtn'></li>"+
		"<li><input type='button' value='가격순' onclick='c_orderbyprice()' id='pricebtn' class='unclickedbtn'></li>"+
		"</ul></div><ul class='productlist'></ul>");
        	
        	console.log("분류에 맞는 상품을 불러옵니다");
        	let index = $("#bottom option:selected").val();
        	console.log(index);
        	
        	$("#defaultorder").css("display","none");
        	$("#categoryorder").css("display","flex");
        	
        	$.ajax(
        			{
        		
        				url:"GetSelectedProductOk.ajax",
        				type:"get",
        				dataType:"json",
        				data:{ c_number : index},
        				success:function(responsedata){
        					
        					console.log("이제 소분류카테고리를 뿌려준다 여긴 다른페이지");
        					console.log(responsedata);
        					
        					$(".productlist").empty();
        					
        					$.each(responsedata, function(index, obj){
      							
    							$(".productlist").append("<li><a href='productdetail.do?p_num="+obj.p_num+"&storename="+obj.storename+"'><div class='thumnail'>"+obj.pimg_name+
    									"</div><div class=title>"+obj.p_subj+"</div><div class='imginfo'><p calss='price'>"+obj.p_price+"</p>"+
    									"<p class='wrtime'>"+obj.p_wr_time+"</p></div></a></li>");
    						});
   					
        					
        				},
        				error:function(xhr){
        					console.log(xhr);
        				}
        				
        		}
        	);
        	
        }
        
        
        
        
    //헤더 둘로 나누기 전 원래 index.jsp에 있었던 함수들

        
        $(function() {    //화면 다 뜨면 시작

        	console.log("함수실행");
        	
        	$("#categoryorder").css("display","none");
        		//상품 이미지 리스트 불러오기
        	$.ajax(
        			
        			{	
        				url:"ProductListOk.ajax",
        				//url:"maincontent.ajax",
        				type:"get",
        				dataType:"json",
        				success:function(responsedata){
        					console.log(responsedata);
        					
        					
        					$.each(responsedata, function(index, obj){
      							
    							$(".productlist").append("<li><a href='productdetail.do?p_num="+obj.p_num+"&storename="+obj.storename+"'><div class='thumnail'>"+obj.pimg_name+
    									"</div><div class=title>"+obj.p_subj+"</div><div class='imginfo'><p calss='price'>"+obj.p_price+"</p>"+
    									"<p class='wrtime'>"+obj.p_wr_time+"</p></div></a></li>");
    						});
        						
        					
        				},

        				error:function(xhr){
        					console.log(xhr);
        				}
        			}
        			
        			
        		);
        		
        	
        	
        	
            
        });
        
        
        //최신순 정렬
        function orderbytime(){
        	
        	$("#timebtn").addClass("clickedbtn");
        	$("#timebtn").removeClass("unclickedbtn");
        	
        	$("#pricebtn").removeClass("clickedbtn");
        	$("#pricebtn").addClass("unclickedbtn");
        	
			console.log("최신순정렬");
        	
			$.ajax(
        			
        			{	
        				url:"ProductOrderByTime.ajax",
        				type:"post",
        				dataType:"json",
        				data: { keyword : $("#keyword").val()},
        				success:function(responsedata){
        					console.log(responsedata);
        						$(".productlist").empty();
        					       					
								$.each(responsedata, function(index, obj){
          							
        							$(".productlist").append("<li><a href='productdetail.do?p_num="+obj.p_num+"&storename="+obj.storename+"'><div class='thumnail'>"+obj.pimg_name+
        									"</div><div class=title>"+obj.p_subj+"</div><div class='imginfo'><p calss='price'>"+obj.p_price+"</p>"+
        									"<p class='wrtime'>"+obj.p_wr_time+"</p></div></a></li>");
        						});		
								
        					
        				},
        				error:function(xhr){
        					console.log(xhr);
        				}
        			}
        			
        		);
        	
        }
        
        //가격순 정렬
        function orderbyprice(){
        	
        	$("#pricebtn").addClass("clickedbtn");
        	$("#pricebtn").removeClass("unclickedbtn");
        	
        	$("#timebtn").removeClass("clickedbtn");
        	$("#timebtn").addClass("unclickedbtn"); 
        	
			console.log("가격순정렬");
        	
			$.ajax(
        			
        			{	
        				url:"ProductOrderByPrice.ajax",
        				type:"post",
        				dataType:"json",
        				data: { keyword : $("#keyword").val()},
        				success:function(responsedata){
        					console.log(responsedata);
        						$(".productlist").empty();
        					       					
$.each(responsedata, function(index, obj){
          							
        							$(".productlist").append("<li><a href='productdetail.do?p_num="+obj.p_num+"&storename="+obj.storename+"'><div class='thumnail'>"+obj.pimg_name+
        									"</div><div class=title>"+obj.p_subj+"</div><div class='imginfo'><p calss='price'>"+obj.p_price+"</p>"+
        									"<p class='wrtime'>"+obj.p_wr_time+"</p></div></a></li>");
        						});        						
        					
        				},
        				error:function(xhr){
        					console.log(xhr);
        				}
        			}
        			
        		);
			
        }
        
        
        
        //카테고리별 최신순 정렬
        function c_orderbytime(){
        	
        	$("#timebtn").addClass("clickedbtn");
        	$("#timebtn").removeClass("unclickedbtn");
        	
        	$("#pricebtn").removeClass("clickedbtn");
        	$("#pricebtn").addClass("unclickedbtn");
        	
			console.log("최신순정렬");
        	
			$.ajax(
        			
        			{	
        				url:"C_ProductOrderOk.ajax",
        				type:"post",
        				dataType:"json",
        				data: { keyword : $("#bottom option:selected").val(),
        						type: "time"},
        				success:function(responsedata){
        					console.log(responsedata);
        						$(".productlist").empty();
        					       					
$.each(responsedata, function(index, obj){
          							
        							$(".productlist").append("<li><a href='productdetail.do?p_num="+obj.p_num+"&storename="+obj.storename+"'><div class='thumnail'>"+obj.pimg_name+
        									"</div><div class=title>"+obj.p_subj+"</div><div class='imginfo'><p calss='price'>"+obj.p_price+"</p>"+
        									"<p class='wrtime'>"+obj.p_wr_time+"</p></div></a></li>");
        						});
								
        					
        				},
        				error:function(xhr){
        					console.log(xhr);
        				}
        			}
        			
        		);
        	
        }
        
        //카테고리별 가격순 정렬
        function c_orderbyprice(){
        	
        	$("#pricebtn").addClass("clickedbtn");
        	$("#pricebtn").removeClass("unclickedbtn");
        	
        	$("#timebtn").removeClass("clickedbtn");
        	$("#timebtn").addClass("unclickedbtn"); 
        	
			console.log("가격순정렬");
        	
			$.ajax(
        			
        			{	
        				url:"C_ProductOrderOk.ajax",
        				type:"post",
        				dataType:"json",
        				data: { keyword :$("#bottom option:selected").val(),
        					type:"price"},
        				success:function(responsedata){
        					console.log(responsedata);
        						$(".productlist").empty();
        					       					
$.each(responsedata, function(index, obj){
          							
        							$(".productlist").append("<li><a href='productdetail.do?p_num="+obj.p_num+"&storename="+obj.storename+"'><div class='thumnail'>"+obj.pimg_name+
        									"</div><div class=title>"+obj.p_subj+"</div><div class='imginfo'><p calss='price'>"+obj.p_price+"</p>"+
        									"<p class='wrtime'>"+obj.p_wr_time+"</p></div></a></li>");
        						});       						
        					
        				},
        				error:function(xhr){
        					console.log(xhr);
        				}
        			}
        			
        		);
			
        }
      	
        </script>
        <div class="nav-item">
            <div class="container">
                <div class="nav-depart">

                        <div id="selectionarea">

                        <select id="top" onchange="getcategorymiddle()" class="c_selection">
							
                        </select>
                        
                        <select id="middle" onchange="getcategorybottom()" class="c_selection">
							<option>중분류</option>
                        </select>
                        
                        <select id="bottom" onchange="getselectedproduct()" class="c_selection">
							<option>소분류</option>
                        </select>
                        </div>

                </div>
               

                <div id="mobile-menu-wrap"></div>
            </div>
        </div>
       </div>
    </header>
    <!-- Header End -->
