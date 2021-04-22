package kr.or.bit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

//CRUD 함수 > ConnectionPool > 함수단위 연결 ,받환 
public class SecondHandsDAO {
	DataSource ds = null;
	
	public SecondHandsDAO() {
		Context context;
		try {
			context = new InitialContext();
			ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle"); 
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//로그인체크 함수 - stay
	public String getLoginOk(String email, String password) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		try {
			conn=ds.getConnection();
			String sql = "select storename from member where m_email=? and m_pwd=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {//참이면
				return rs.getString(1);
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();//반환하기
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return null;
	}
	//이메일 중복체크 - stay
	public boolean getEmailCheck(String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		try {
			conn=ds.getConnection();
			String sql = "select * from member where m_email=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {//참이면
	            System.out.println("이미 존재하는 이메일");
				return false;
			}
			else {
	            System.out.println("사용가능한 이메일");
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();//반환하기
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return true;
	}
	//storename 중복체크 - hyunjin
	public boolean getNameCheck(String storename) {
	    Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs=null;
	      try {
	          conn=ds.getConnection();
	          String sql = "select * from member where storename=?";
	          pstmt = conn.prepareStatement(sql);
	          pstmt.setString(1, storename);
	          rs=pstmt.executeQuery();
	          
	          if(rs.next()) {//참이면
	             System.out.println("닉네임존재");
	             return false;
	          }
	          else {
	             System.out.println("닉네임존재X");
	             return true;
	          }
	       } catch (SQLException e) {
	          // TODO Auto-generated catch block
	          e.printStackTrace();
	       }finally {
	          try {
	             rs.close();
	             pstmt.close();
	             conn.close();//반환하기
	          } catch (Exception e2) {
	             System.out.println(e2.getMessage());
	          }
	       }
	      return false;
  }
	// 회원가입 insert 하기 - 명환
			public int registerInsert(String email, String password, String name, String shopname, String phone) {
				Connection conn = null;
				PreparedStatement pstmt = null;
				int result = 0;
				try {
					conn = ds.getConnection();
					String sql ="insert into member(storename, m_name, m_phone, m_email, m_pwd, m_profile) values(?,?,?,?,?,null)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, shopname);
					pstmt.setString(2, name);
					pstmt.setString(3, phone);
					pstmt.setString(4, email);
					pstmt.setString(5, password);

					result = pstmt.executeUpdate(); //여기서 오류남
					System.out.println(result);
					
				} catch (Exception e) {
					System.out.println(e.getStackTrace());
				} finally {
					try {
						conn.close();
						pstmt.close();
					} catch (Exception e2) {
						System.out.println(e2.getStackTrace());
					}
				}

				return result;
			}

	//메인에 뿌려줄 상품 리스트 뽑기
	public JSONArray getProductList() { 
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs=null;
	      JSONArray arr = new JSONArray();
	      
	      System.out.println("리스트 함수 실행");
	      
	      try {
	         conn=ds.getConnection();
	         
	         //String sql = "select * from member";
	         
	         String sql = "select pi.pimg_name, p.p_subj,p.p_price, p.p_wr_time, p.p_num, p.storename "
	               +"from product p left join product_img pi "
	               +"on p.p_num=pi.p_num "
	               +"where pi.pimg_num=1";
	         
	         pstmt = conn.prepareStatement(sql);
	         
	         rs= pstmt.executeQuery();
	         System.out.println("re next : "+rs.next());
	         
	         while(rs.next()) {
	        	
	        	 SimpleDateFormat date = new SimpleDateFormat ( "yyyy.MM.dd");

	        	 
	            JSONObject obj = new JSONObject();
	            obj.put("pimg_name",rs.getString("pimg_name"));
	            obj.put("p_subj",rs.getString("p_subj"));
	            obj.put("p_price",rs.getInt("p_price"));
	            obj.put("p_num",rs.getInt("p_num"));
	            obj.put("storename",rs.getString("storename"));
	            //시간 자르려고 substring 사용
	            obj.put("p_wr_time", rs.getString("p_wr_time").substring(0, 11));
	            
	            System.out.println(obj);
	            arr.add(obj);
	            
	            System.out.println("배열 담겼나 : " +arr.get(0));
	         } 
	         System.out.println(arr);
	         
	      
	         
	      } catch (SQLException e) {
	         // TODO: handle exception
	         System.out.println("SQLException" + e.getMessage());
	      }catch(Exception e3) {
	         System.out.println(e3.getMessage());
	      }
	      finally {
	         try {
	            rs.close();
	            pstmt.close();
	            conn.close();//반환하기
	         } catch (Exception e2) {
	            System.out.println(e2.getMessage());
	         }
	      }
	      return arr;
	   }
	 //메인에 뿌려줄 상품 리스트 뽑기
	   public JSONArray getMyProductList(String storename) { 
	         Connection conn = null;
	         PreparedStatement pstmt = null;
	         ResultSet rs=null;
	         JSONArray arr = new JSONArray();
	         try {
	            conn=ds.getConnection();
	            
	            //String sql = "select * from member";
	            
	            String sql =  "select p.p_num, pi.pimg_name, p.p_subj, p.p_price, p.p_wr_time "
	            		 +"from product p left join product_img pi "
	            		 +"on p.p_num=pi.p_num "
	            		 +"where pi.pimg_num=1 and p.storename=?";
	            
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, storename);
	            
	            rs= pstmt.executeQuery();	            
	            while(rs.next()) {
	               SimpleDateFormat date = new SimpleDateFormat ( "yyyy.MM.dd");

	               
	               JSONObject obj = new JSONObject();
	               obj.put("pimg_name",rs.getString("pimg_name"));
	               obj.put("p_subj",rs.getString("p_subj"));
	               obj.put("p_price",rs.getInt("p_price"));
	               obj.put("p_num",rs.getInt("p_num"));
	               //시간 자르려고 substring 사용
	               obj.put("p_wr_time", rs.getString("p_wr_time").substring(0, 11));
	               
	               System.out.println(obj);
	               //상품하나씩 배열에 넣는다.
	               arr.add(obj);
	            } 
	            System.out.println(arr); 
	         } catch (SQLException e) {
	            // TODO: handle exception
	        	 e.printStackTrace();
	         }catch(Exception e3) {
	        	 e3.printStackTrace();
	         }
	         finally {
	            try {
	               rs.close();
	               pstmt.close();
	               conn.close();//반환하기
	            } catch (Exception e2) {
	            	e2.printStackTrace();
	            }
	         }
	         //내 상품들이 들어가있는 배열.
	         //상품들의 개수는 배열크기를 통해 알수 있다.
	         System.out.println("arr list = " + arr.size());
	         return arr;
	      }
	   //찜 리스트 출력
	   public JSONArray getMyLikeList(String storename) { 
	         Connection conn = null;
	         PreparedStatement pstmt = null;
	         ResultSet rs=null;
	         JSONArray arr = new JSONArray();
	         try {
	            conn=ds.getConnection();
	            
	            //String sql = "select * from member";
	            
	            String sql =  "select p.p_num, pi.pimg_name, p.p_subj, p.p_price, p.p_wr_time "
	            		+"from product p join "
	            		+"(select p_num, storename from likelist where storename=?) ll "
	            		+"on p.p_num = ll.p_num "
	            		+"join product_img pi "
	            		+"on ll.p_num=pi.p_num and pimg_num=1";
	            
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, storename);
	            
	            rs= pstmt.executeQuery();	            
	            while(rs.next()) {
	               SimpleDateFormat date = new SimpleDateFormat ( "yyyy.MM.dd");

	               
	               JSONObject obj = new JSONObject();
	               obj.put("pimg_name",rs.getString("pimg_name"));
	               obj.put("p_subj",rs.getString("p_subj"));
	               obj.put("p_price",rs.getInt("p_price"));
	               obj.put("p_num",rs.getInt("p_num"));
	               //시간 자르려고 substring 사용
	               obj.put("p_wr_time", rs.getString("p_wr_time").substring(0, 11));
	               
	               System.out.println(obj);
	               //상품하나씩 배열에 넣는다.
	               arr.add(obj);
	            } 
	            System.out.println(arr); 
	         } catch (SQLException e) {
	            // TODO: handle exception
	        	 e.printStackTrace();
	         }catch(Exception e3) {
	        	 e3.printStackTrace();
	         }
	         finally {
	            try {
	               rs.close();
	               pstmt.close();
	               conn.close();//반환하기
	            } catch (Exception e2) {
	            	e2.printStackTrace();
	            }
	         }
	         //내 상품들이 들어가있는 배열.
	         //상품들의 개수는 배열크기를 통해 알수 있다.
	         System.out.println("arr list = " + arr.size());
	         return arr;
	      }
	   //내상점의 리뷰 리스트 출력
	   public JSONArray getMyReviewList(String storename) { 
		   // [리뷰 작성자] , [별점] , [구매 상품 제목 ] , [ 글 내용 ]
	         Connection conn = null;
	         PreparedStatement pstmt = null;
	         ResultSet rs=null;
	         JSONArray arr = new JSONArray();
	         try {
	            conn=ds.getConnection();
	            
	            //String sql = "select * from member";
	            
	            String sql =  "select p.p_num, pi.pimg_name, p.p_subj, p.p_price, p.p_wr_time "
	            		+"from product p join "
	            		+"(select p_num, storename from likelist where storename=?) ll "
	            		+"on p.p_num = ll.p_num "
	            		+"join product_img pi "
	            		+"on ll.p_num=pi.p_num and pimg_num=1";
	            
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, storename);
	            
	            rs= pstmt.executeQuery();	            
	            while(rs.next()) {
	               SimpleDateFormat date = new SimpleDateFormat ( "yyyy.MM.dd");

	               
	               JSONObject obj = new JSONObject();
	               obj.put("pimg_name",rs.getString("pimg_name"));
	               obj.put("p_subj",rs.getString("p_subj"));
	               obj.put("p_price",rs.getInt("p_price"));
	               obj.put("p_num",rs.getInt("p_num"));
	               //시간 자르려고 substring 사용
	               obj.put("p_wr_time", rs.getString("p_wr_time").substring(0, 11));
	               
	               System.out.println(obj);
	               //상품하나씩 배열에 넣는다.
	               arr.add(obj);
	            } 
	            System.out.println(arr); 
	         } catch (SQLException e) {
	            // TODO: handle exception
	        	 e.printStackTrace();
	         }catch(Exception e3) {
	        	 e3.printStackTrace();
	         }
	         finally {
	            try {
	               rs.close();
	               pstmt.close();
	               conn.close();//반환하기
	            } catch (Exception e2) {
	            	e2.printStackTrace();
	            }
	         }
	         //내 상품들이 들어가있는 배열.
	         //상품들의 개수는 배열크기를 통해 알수 있다.
	         return arr;
	      }
	
	//연결 테스트 함수
	public void member() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		ResultSet rs=null;
		int count=0;
		try {
			conn = ds.getConnection();
			String sql="select * from member";
			pstmt = conn.prepareStatement(sql);
		
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				count+=1;
			}
			System.out.println("MEMBER COUNT : " + count);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				pstmt.close();
				conn.close();//반환하기
			} catch (Exception e2) {
			
			}
		}
		
	}
	
	//검색하는 함수 (가희)
		public JSONArray searchProduct(String keyword){
			System.out.println("k : " + keyword);
			 Connection conn = null;
		      PreparedStatement pstmt = null;
		      ResultSet rs=null;
		      JSONArray arr = new JSONArray();
		      
		      
		      try {
		         conn=ds.getConnection();
		         String sql = "select pi.pimg_name, p.p_subj,p.p_price, p.p_wr_time,p.p_num, p.storename "
		               +"from product p left join product_img pi "
		               +"on p.p_num=pi.p_num "
		               +"where p.p_subj Like '%" + keyword + "%' or p.p_content Like '%"+ keyword + "%' ";
		         pstmt = conn.prepareStatement(sql);
		         
		         rs = pstmt.executeQuery();
		         
		         while(rs.next()) {
		        	 
		            JSONObject obj = new JSONObject();
		            
		            obj.put("pimg_name",rs.getString("pimg_name"));
		            obj.put("p_subj",rs.getString("p_subj"));
		            obj.put("p_price",rs.getInt("p_price"));
		            obj.put("p_wr_time", rs.getString("p_wr_time").substring(0, 11));
		            obj.put("p_num",rs.getInt("p_num"));
		            obj.put("storename",rs.getString("storename"));
		            
		            System.out.println("제이슨 객체 : "+obj);
		            
		            arr.add(obj);
		         } 
		         System.out.println("배열 : " +arr);

		         
		      
		         
		      } catch (SQLException e) {
		         // TODO: handle exception
		         System.out.println(e.getMessage());
		      }catch(Exception e3) {
		         System.out.println(e3.getMessage());
		      }
		      finally {
		         try {
		            rs.close();
		            pstmt.close();
		            conn.close();//반환하기
		         } catch (Exception e2) {
		            System.out.println(e2.getMessage());
		         }
		      }
		      return arr;
		}
		
		//검색 후 최신순 정렬(가희)
		public JSONArray ProductOderByTime(String keyword){
			System.out.println("k : " + keyword);
			 Connection conn = null;
		      PreparedStatement pstmt = null;
		      ResultSet rs=null;
		      JSONArray arr = new JSONArray();
		      
		      
		      try {
		         conn=ds.getConnection();
		         String sql = "select pi.pimg_name, p.p_subj,p.p_price, p.p_wr_time, p.p_num, p.storename "
		               +"from product p left join product_img pi "
		               +"on p.p_num=pi.p_num "
		               +"where p.p_subj Like '%" + keyword + "%' or p.p_content Like '%"+ keyword + "%' "
		               +"order by p.p_wr_time";
		         pstmt = conn.prepareStatement(sql);
		         
		         rs = pstmt.executeQuery();
		         
		         while(rs.next()) {
		        	 
		            JSONObject obj = new JSONObject();
		            
		            obj.put("pimg_name",rs.getString("pimg_name"));
		            obj.put("p_subj",rs.getString("p_subj"));
		            obj.put("p_price",rs.getInt("p_price"));
		            obj.put("p_wr_time", rs.getString("p_wr_time").substring(0, 11));
		            obj.put("p_num",rs.getInt("p_num"));
		            obj.put("storename",rs.getString("storename"));
		            
		            System.out.println("제이슨 객체 : "+obj);
		            
		            arr.add(obj);
		         } 

		         
		      
		         
		      } catch (SQLException e) {
		         // TODO: handle exception
		         System.out.println(e.getMessage());
		      }catch(Exception e3) {
		         System.out.println(e3.getMessage());
		      }
		      finally {
		         try {
		            rs.close();
		            pstmt.close();
		            conn.close();//반환하기
		         } catch (Exception e2) {
		            System.out.println(e2.getMessage());
		         }
		      }
		      return arr;
		}
		
		//검색 후 가격순 정렬 가희
		public JSONArray ProductOderByPrice(String keyword){
			System.out.println("k : " + keyword);
			 Connection conn = null;
		      PreparedStatement pstmt = null;
		      ResultSet rs=null;
		      JSONArray arr = new JSONArray();
		      
		      
		      try {
		         conn=ds.getConnection();
		         String sql = "select pi.pimg_name, p.p_subj,p.p_price, p.p_wr_time, p.p_num, p.storename "
		               +"from product p left join product_img pi "
		               +"on p.p_num=pi.p_num "
		               +"where p.p_subj Like '%" + keyword + "%' or p.p_content Like '%"+ keyword + "%' "
		               +"order by p.p_price";
		         pstmt = conn.prepareStatement(sql);
		         
		         rs = pstmt.executeQuery();
		         
		         while(rs.next()) {
		        	 
		            JSONObject obj = new JSONObject();
		            
		            obj.put("pimg_name",rs.getString("pimg_name"));
		            obj.put("p_subj",rs.getString("p_subj"));
		            obj.put("p_price",rs.getInt("p_price"));
		            obj.put("p_wr_time", rs.getString("p_wr_time").substring(0, 11));
		            obj.put("p_num",rs.getInt("p_num"));
		            obj.put("storename",rs.getString("storename"));
		            
		            System.out.println("제이슨 객체 : "+obj);
		            
		            arr.add(obj);
		         } 
		         System.out.println("배열 : " +arr);

		         
		      
		         
		      } catch (SQLException e) {
		         // TODO: handle exception
		         System.out.println(e.getMessage());
		      }catch(Exception e3) {
		         System.out.println(e3.getMessage());
		      }
		      finally {
		         try {
		            rs.close();
		            pstmt.close();
		            conn.close();//반환하기
		         } catch (Exception e2) {
		            System.out.println(e2.getMessage());
		         }
		      }
		      return arr;
		}
	//storename의 프로플사진 파일이름을 넘겨준다. -태희- 
	public String MyShopProfile(String storename) {
		 Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs=null;
	      try {
	    	  	conn=ds.getConnection();
	    	  	// 프로필사진파일 이름 얻기
		         String sql = "select m_profile "
		         		+"from member "
		         		+"where storename=?";
		         pstmt = conn.prepareStatement(sql);
		         pstmt.setString(1, storename);
		           
		         rs = pstmt.executeQuery();
		         
		         if(rs.next()) {
			         return rs.getString("m_profile");
		         }
		        
		         //상품 전체개수 얻기
//		         sql="select count(*)as cnt "
//		         		+ "from product "
//		         		+ "group by storename having storename=?";
//		         pstmt = conn.prepareStatement(sql);
//		         pstmt.setString(1, (String)obj.get("storename"));
//		         
//		         
//		         rs=pstmt.executeQuery();
//		         
//		         if(rs.next()) {
//		        	 obj.put("p_count", rs.getString("cnt"));
//		         }
//
//		         System.out.println(obj.toString());
		         
		         
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	//카테고리 목록 불러오기(대)
		public JSONArray getCategory(){
			
			System.out.println("대 불러오기");
			 Connection conn = null;
		      PreparedStatement pstmt = null;
		      ResultSet rs=null;
		      JSONArray arr = new JSONArray();
		      
		      
		      try {
		         conn=ds.getConnection();
		         String sql = "select t_num, t_name "
		               +"from category_top "
		               +"order by t_num";
		         pstmt = conn.prepareStatement(sql);
		         
		         rs = pstmt.executeQuery();
		         
		         while(rs.next()) {
		        	 
		            JSONObject obj = new JSONObject();
		            
		            obj.put("t_num",rs.getString("t_num"));
		            obj.put("t_name",rs.getString("t_name"));
		            /*
		            System.out.println("제이슨 객체 : "+obj);
		            */
		            arr.add(obj);
		         } 
		         /*
		         System.out.println("배열 : " +arr);
	*/
		         
		      
		         
		      } catch (SQLException e) {
		         // TODO: handle exception
		         System.out.println(e.getMessage());
		      }catch(Exception e3) {
		         System.out.println(e3.getMessage());
		      }
		      finally {
		         try {
		            rs.close();
		            pstmt.close();
		            conn.close();//반환하기
		         } catch (Exception e2) {
		            System.out.println(e2.getMessage());
		         }
		      }
		      return arr;
		}
		
		
			//카테고리 목록 불러오기(중소)
			public JSONArray getCategory(String c_number){
				
				   
				  Connection conn = null;
			      PreparedStatement pstmt = null;
			      ResultSet rs=null;
			      JSONArray arr = new JSONArray();
		      
			      try {
			    	 
			    	 int c_num = Integer.parseInt(c_number);
			         conn=ds.getConnection();
			         
			         String sql="";
			         
			         //중분류
			         if(c_num % 100 == 0 ) {
			        	 
			        	sql += "select m_num, m_name "
					               +"from category_middle "
					               +"where t_num="+c_num
					               +"order by m_num";
			         //소분류
			         } else {
			        	 
			        	 sql += "select b_num, b_name "
					               +"from category_bottom "
					               +"where m_num="+c_num
					               +"order by b_num";
			        	 
			         }
			         
			         
			         pstmt = conn.prepareStatement(sql);
			         
			         rs = pstmt.executeQuery();
			         
			         while(rs.next()) {
			        	 
			            JSONObject obj = new JSONObject();
			            
			            if(c_num % 100 == 0 ) {
			            	
			            	obj.put("m_num",rs.getString("m_num"));
				            obj.put("m_name",rs.getString("m_name"));
				            
			            } else {
			            	
			            	System.out.println("소분류 else문을 타나요?");
			            	obj.put("b_num",rs.getString("b_num"));
				            obj.put("b_name",rs.getString("b_name"));
			            }
			            
			            
			            System.out.println("제이슨 객체 : "+obj);
			            
			            arr.add(obj);
			         } 
			         System.out.println("배열 : " +arr);

			         
			      
			         
			      } catch (SQLException e) {
			         // TODO: handle exception
			         System.out.println(e.getMessage());
			      }catch(Exception e3) {
			         System.out.println(e3.getMessage());
			      }
			      finally {
			         try {
			            rs.close();
			            pstmt.close();
			            conn.close();//반환하기
			         } catch (Exception e2) {
			            System.out.println(e2.getMessage());
			         }
			      }
			      return arr;
			}
			
			//카페고리에 맞는 상품 물러오기
			public JSONArray getSelectedProduct(String index) { 
				
				  
			      Connection conn = null;
			      PreparedStatement pstmt = null;
			      ResultSet rs=null;
			      JSONArray arr = new JSONArray();
			      
			      System.out.println("카테고리로 불러오기 함수 실행");
			      
			      try {
			         conn=ds.getConnection();
			         int c_num = Integer.parseInt(index);
			         //String sql = "select * from member";
			         
			         String sql = "select pi.pimg_name, p.p_subj,p.p_price, p.p_wr_time, p.p_num, p.storename "
			               +"from product p left join product_img pi "
			               +"on p.p_num=pi.p_num "
			               +"where p.b_num=" + c_num;
			         
			         pstmt = conn.prepareStatement(sql);
			         
			         rs= pstmt.executeQuery();
			         System.out.println("re next : "+rs.next());
			         
			         while(rs.next()) {
			        	 
			        	 
			            JSONObject obj = new JSONObject();
			            obj.put("pimg_name",rs.getString("pimg_name"));
			            obj.put("p_subj",rs.getString("p_subj"));
			            obj.put("p_price",rs.getInt("p_price"));
			            obj.put("p_wr_time", rs.getString("p_wr_time").substring(0, 11));
			            obj.put("p_num",rs.getInt("p_num"));
			            obj.put("storename",rs.getString("storename"));
			            
			            System.out.println(obj);
			            arr.add(obj);

			         } 
			         System.out.println(arr);
			         
			      
			         
			      } catch (SQLException e) {
			         // TODO: handle exception
			         System.out.println("SQLException" + e.getMessage());
			      }catch(Exception e3) {
			         System.out.println(e3.getMessage());
			      }
			      finally {
			         try {
			            rs.close();
			            pstmt.close();
			            conn.close();//반환하기
			         } catch (Exception e2) {
			            System.out.println(e2.getMessage());
			         }
			      }
			      return arr;
			   }
			
			
			
					//카페고리에 맞는 상품 물러와서 가격순 정렬하기
					public JSONArray C_ProductOder(String keyword, String type) { 
						
						  
					      Connection conn = null;
					      PreparedStatement pstmt = null;
					      ResultSet rs=null;
					      JSONArray arr = new JSONArray();
					      
					      System.out.println("카테고리로 불러오고 정렬하는 함수 실행");
					      
					      try {
					         conn=ds.getConnection();
					         int c_num = Integer.parseInt(keyword);
					         //String sql = "select * from member";
					         String sql = "";
					         
					         if(type.equals("time")) {
					        	 sql += "select pi.pimg_name, p.p_subj,p.p_price, p.p_wr_time, p.p_num, p.storename "
							               +"from product p left join product_img pi "
							               +"on p.p_num=pi.p_num "
							               +"where p.b_num=" + c_num
							               +"order by p.p_wr_time";
					         }else {
					        	 sql += "select pi.pimg_name, p.p_subj,p.p_price, p.p_wr_time, p.p_num, p.storename "
							               +"from product p left join product_img pi "
							               +"on p.p_num=pi.p_num "
							               +"where p.b_num=" + c_num
							               +"order by p.p_price";
					         }
					         
					         
					         pstmt = conn.prepareStatement(sql);
					         
					         rs= pstmt.executeQuery();
					         System.out.println("re next : "+rs.next());
					         
					         while(rs.next()) {
					        	 
					        	 
					            JSONObject obj = new JSONObject();
					            obj.put("pimg_name",rs.getString("pimg_name"));
					            obj.put("p_subj",rs.getString("p_subj"));
					            obj.put("p_price",rs.getInt("p_price"));
					            obj.put("p_wr_time", rs.getString("p_wr_time").substring(0, 11));
					            obj.put("p_num",rs.getInt("p_num"));
					            obj.put("storename",rs.getString("storename"));
					            System.out.println(obj);
					            arr.add(obj);

					         } 
					         System.out.println(arr);
					         
					      
					         
					      } catch (SQLException e) {
					         // TODO: handle exception
					         System.out.println("SQLException" + e.getMessage());
					      }catch(Exception e3) {
					         System.out.println(e3.getMessage());
					      }
					      finally {
					         try {
					            rs.close();
					            pstmt.close();
					            conn.close();//반환하기
					         } catch (Exception e2) {
					            System.out.println(e2.getMessage());
					         }
					      }
					      return arr;
					   }
					
					// 메인에서 선택한 상품번호에 해당하는 상품의 내용 가져오는 함수(가희)
					// 여기서 가져온 정보들을 상품상세페이지에 적당히 뿌려준다
					public JSONObject showProductDetail(int p_num) {
						
						Connection conn = null;
					      PreparedStatement pstmt = null;
					      PreparedStatement pstmt2 = null;
					      ResultSet rs=null;
					      ResultSet rs2=null;
					      JSONObject obj = new JSONObject();
					      System.out.println("상품 상세페이지 함수 실행");
					      
					      try {
					         conn=ds.getConnection();
		
					         //String sql = "select * from member";
					         String sql = "";
					         
					        	 sql += "select pi.pimg_name, p.p_subj,p.p_price, p.p_wr_time, p.p_content,p.storename "
							               +"from product p left join product_img pi "
							               +"on p.p_num=pi.p_num "
							               +"where p.p_num=" + p_num;
					        	 		


					         pstmt = conn.prepareStatement(sql);
					         
					         rs= pstmt.executeQuery();
					        		         
					         while(rs.next()) {				        	 
					            
					            obj.put("pimg_name",rs.getString("pimg_name"));
					            obj.put("p_subj",rs.getString("p_subj"));
					            obj.put("p_price",rs.getInt("p_price"));
					            obj.put("p_wr_time", rs.getString("p_wr_time"));
					            obj.put("p_content", rs.getString("p_content"));
					            obj.put("storename", rs.getString("storename"));
					           // obj.put("m_profile", rs2.getString("m_profile"));
					            System.out.println("들어오나?");
					            System.out.println("obj : "+obj);


					         } 
					         
					      
					         
					      } catch (SQLException e) {
					         // TODO: handle exception
					         System.out.println("SQLException" + e.getMessage());
					      }catch(Exception e3) {
					         System.out.println(e3.getMessage());
					      }
					      finally {
					         try {
					            rs.close();
					            pstmt.close();
					            conn.close();//반환하기
					         } catch (Exception e2) {
					            System.out.println(e2.getMessage());
					         }
					      }
					      return obj;
					}
					
					//상점 정보 구역에 들어갈 상점 프로필, 상품 갯수, 최근 상품 정보 구하기 위한 쿼리문
			         // 회원, 상품, 상품 이미지 세 개의 테이블 조인
					public Object showShopInfo(String storename) {
						Connection conn = null;
					      PreparedStatement pstmt = null;
					      ResultSet rs=null;
					      JSONArray arr = new JSONArray();
					      System.out.println("상품 상세페이지 함수 실행");
					      
					      try {
					         conn=ds.getConnection();
		
	        
					         
					         String sql = "";
					         	
				         	    sql += "select m.m_profile, p.p_price, pi.pimg_name, p.p_num from product p inner join"
				                        +"product_img pi on p.p_num = pi.p_num left outer join"
				                        +"member m on p.storename = m.storename"
				                        +"where p.storename="+storename
				                        +"order by p.p_wr_time desc";
				         	    
				         	   pstmt = conn.prepareStatement(sql);
						         
						       rs= pstmt.executeQuery();   
					         
						       
					         //while(rs.next()) {				        	 
					            
					        	 JSONObject obj = new JSONObject();
					        	 
					            obj.put("pimg_name",rs.getString("pimg_name"));
					            obj.put("p_price",rs.getInt("p_price"));
					            obj.put("storename", rs.getString("storename"));
					           obj.put("m_profile", rs.getString("m_profile"));
					          
					            
					            System.out.println("obj : "+obj);
					            
					            arr.add(obj);
					            System.out.println("함수에서 배열은?" + arr);

					         //} 
					         
					      
					         
					      } catch (SQLException e) {
					         // TODO: handle exception
					         System.out.println("SQLException" + e.getMessage());
					      }catch(Exception e3) {
					         System.out.println(e3.getMessage());
					      }
					      finally {
					         try {
					            rs.close();
					            pstmt.close();
					            conn.close();//반환하기
					         } catch (Exception e2) {
					            System.out.println(e2.getMessage());
					         }
					      }
					      return arr;
					}

	//상점이름 불러오기 -영훈-
		public void getProductName() {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			System.out.println("상점이름 불러오기");
			
			try {
				conn = ds.getConnection();
				String sql = "select storename from member";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}finally {
				try {
					rs.close();
					pstmt.close();
					
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}
			}

		}




}