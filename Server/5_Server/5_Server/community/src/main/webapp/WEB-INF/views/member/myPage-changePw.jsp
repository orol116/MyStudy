<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>My Page - changePw</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">

    <link rel="stylesheet" href="${contextPath}/resources/css/myPage-style.css">
    <script src="https://kit.fontawesome.com/a2e8ca0ae3.js" crossorigin="anonymous"></script>
</head>


<body>
    <main>

       <jsp:include page="/WEB-INF/views/common/header.jsp"/>

        <section class="myPage-content">

            <jsp:include page="/WEB-INF/views/member/sideMenu.jsp"/>

            <section class="myPage-main">

                <h1 class="myPage-title">비밀번호 변경</h1>
                <span class="myPage-explanation">현재 비밀번호가 일치하는 경우 새 비밀번호로 변경할 수 있습니다.</span>

                <form action="changePw" method="post" name="myPage-form">

                    <div class="myPage-row">
                        <label>현재 비밀번호</label>
                        <input type="password" name="currentPw" maxlength="30">              
                    </div>

                    <div class="myPage-row">
                        <label>새 비밀번호</label>
                        <input type="password" name="newPw" maxlength="30">              
                    </div>

                    <div class="myPage-row">
                        <label>새 비밀번호 확인</label>
                        <input type="password" name="newPwConfirm" maxlength="30">              
                    </div>

                    <button id="info-update-btn">변경하기</button>

                </form>


            </section>


        </section>

    </main>

    
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>

</body>
</html>