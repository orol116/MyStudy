<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%--
     * JSP는 컴파일 시 Servlet으로 변환된다.
       -> JSP에서도 forward가 가능하다!!

     * webapp 폴더 바로 하위에 존재하는 index.jsp 파일은
       자동으로 welcome-file로 인식이 된다.
       -> 최상위 주소로 요청 시 index.jsp 화면이 보여지게 된다.

       지금까진 sevlet -> JSP로 forward (JSP 경로 작성)

       이번에는 JSP -> servlet으로 forward (Servlet의 요청 처리 주소 작성)

      * http://localhost:8080/comm/main 로 주소 요청
        단, forward이기 때문에 주소는 http://localhost:8080/comm 로 유지한다.
--%>

<jsp:forward page="main" />