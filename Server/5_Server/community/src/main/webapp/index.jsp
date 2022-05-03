<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>커뮤니티 사이트</title>

    <link rel="stylesheet" href="resources/css/main-style.css">

    <script src="https://kit.fontawesome.com/a2e8ca0ae3.js" crossorigin="anonymous"></script>
</head>
<body>
    
    <main>
        <header>

            <!-- 클릭 시 메인페이지로 이동하는 로고 -->
            <section>
                <a href="#">
                    <img src="resources/images/logo.jpg" id="home-logo">
                </a>
            </section>

            <section>
                <article class="search-area">
                    <!-- form 내부 input 태그 값을 서버 또는 페이지로 전달 -->
                    <form action="#" name="search-form">
        
                        <!-- fieldset: form 내부에서 input을 종류별로 묶는 용도로 많이 사용 -->
                        <fieldset>

                            <!-- autocomplete="off" : HTML 기본 자동완성 사용 X -->
                            <input type="search" id="query" name="query" 
                                autocomplete="off" placeholder="검색어를 입력해주세요.">
        
                            <!-- 검색 버튼 -->
                            <button type="submit" id="search-btn" class="fa-solid fa-magnifying-glass"></button>  
                        </fieldset>
                    </form>
                </article>
            </section>

            
            <section></section>

        </header>


        <nav>
            <ul>
                <li><a href="#">공지사항</a></li>
                <li><a href="#">자유 게시판</a></li>
                <li><a href="#">질문 게시판</a></li>
                <li><a href="#">FAQ</a></li>
                <li><a href="#">1:1문의</a></li>
            </ul>
        </nav>



        <section class="content">
            <section class="content-1">
            	loginMember : ${sessionScope.loginMember}
            	<hr>
            	message : ${sessionScope.message}
            </section>

            <section class="content-2">
            
            	<%-- if - else --%>
            	<c:choose>
            		<%-- choose 내부에는 jsp 주석만 사용 --%>
            	
            		<%-- 로그인이 되어있지 않은 경우 --%>
            		<c:when test="${empty sessionScope.loginMember}">
            		
            			<!-- 절대경로 : /community/member/login -->
		            	<!-- 상대경로 (index.jsp) 기준 -->
		                <form action="member/login" method="POST" name="login-form">
		        
		                    <!-- 아이디(이메일)/비밀번호/로그인버튼 영역 -->
		                    <fieldset id="id-pw-area">
		        
		                        <section>
		                            <input type="text" name="inputEmail" placeholder="아이디(이메일)" value="${cookie.saveId.value}">
                                                                                             <!-- 현재 페이지 쿠키 중 "saveId"의 내용을 출력 -->
		                            <input type="password" name="inputPw" placeholder="비밀번호">
		                        </section>
		        
		                        <section>
		                            <!-- button의 type 기본값은 submit -->
		                            <button>로그인</button>
		                        </section>
		        
		                    </fieldset>
		
                            <%-- 쿠키에 saveId가 있는 경우 --%>
                            <c:if test="${cookie.saveId.value}">

                                <%-- chk 변수 생성(page scope) --%>
                                <c:set var="chk" value="checked"/>

                            </c:if>

		                    <label>
                                <!-- checked 속성 : radio/checkbox를 체크하는 속성 -->
		                        <input type="checkbox" name="saveId"${chk}>아이디 저장
		                    </label>
		        
		                    <!-- 회원가입 / ID/PW 찾기 영역 -->
		                    <article id="signup-find-area">


                                <!-- WEB-INF 폴더는 외부로부터 직접적으로 요청할 수 없는 폴더이다.
                                     왜? 중요한 코드(자바, sql, 설정관련)가 위치하는 폴더로써
                                         외부로부터의 접근을 차단하기 위해서이다.
                                        
                                     -> 대신 Servlet을 이용한 내부 접근(forward)은 가능하다.
                                -->
		                        <!-- <a href="/community/WEB-INF/views/member/signUp/jsp">회원가입</a>  -->
		                        <a href="/community/member/signUp">회원가입</a> 
		                        <span>|</span>
		                        <a href="#">ID/PW 찾기</a>
		                    </article>
		                </form>
		                
            		</c:when>
            		
            		<%-- 로그인이 되어있는 경우 --%>
            		<c:otherwise>
            		
            			<article class="login-area">
            			
            				<!-- 회원 프로필 이미지 -->
            				<a href="#">
            					<img src="/community/resources/images/user.png" id="member-profile">
            				</a>
            				
            				<!-- 회원 정보 + 로그아웃 버튼 -->
            				<div class="my-info">
                                
                                <div>
                                    <a href="#" id="nickname">${loginMember.memberNickname}</a>

                                    <a href="/community/member/logout" id="logout-btn">로그아웃</a>
                                </div>

                                <p>
                                    ${loginMember.memberEmail}
                                </p>

                            </div>
            				
            			</article>
            		
            		</c:otherwise>
            	</c:choose>
  	
            </section>
        </section>

    </main>

    <footer>
        <p>Copyright &copy; KH Information Educational Institute A-Class</p>

        <article>
            <a href="#">프로젝트 소개</a>
            <span>|</span>
            <a href="#">이용약관</a>
            <span>|</span>
            <a href="#">개인정보처리방침</a>
            <span>|</span>
            <a href="#">고객센터</a>
        </article>
    </footer>

</body>
</html>