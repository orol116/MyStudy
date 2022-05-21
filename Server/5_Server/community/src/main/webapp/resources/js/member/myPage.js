// 내 정보 수정 함수
function myPageCheck() {

    const memberNickname = document.getElementById("memberNickname");
    const memberTel = document.getElementById("memberTel");

    const regExpNickname = /^[a-zA-Z0-9가-힣]{2,10}$/;
    const regExpTel = /^0(1[01679]|2|[3-6][1-5]|70)\d{3,4}\d{4}$/;

    if (memberNickname.value.length == 0) {
        alert("닉네임을 입력해주세요.");
        memberNickname.focus();
        return false;

    } else if (!regExpNickname.test(memberNickname.value)) {
        alert("닉네임은 영어/숫자/한글 2~10 글자 사이로 작성해주세요.");
        memberNickname.focus();
        return false;

    } else if (memberTel.value.length == 0) {
        alert("전화번호를 입력해주세요.(- 제외)");
        memberTel.focus();
        return false;

    } else if (!regExpTel.test(memberTel.value)) {
        alert("전화번호 형식이 올바르지 않습니다.");
        memberTel.focus();
        return false;
    }
}

// 회원 탈퇴 함수
function myPage_secession() {

    const memberPw = document.getElementById("memberPw");
    const agree = document.getElementById("agree");

    if (memberPw.value.length == 0) {
        alert("비밀번호를 입력해주세요.");
        memberPw.focus();
        return false;

    } else if (!agree.checked) {
        alert("약관 동의 후 탈퇴 버튼을 클릭해주세요.");
        return false;
    }

    // - window.confirm("내용") : 대화 상자에 확인 / 취소 버튼 생성
    //                            확인 클릭 시 true / 취소 클릭 시 false
    //                            앞의 window는 생략이 가능하다.
    if (!confirm("정말 탈퇴 하시겠습니까?")) return false;
}

// 내 비밀번호 변경 함수
function myPage_changePw() {

    const currentPw = document.getElementById("currentPw");
    const newPw = document.getElementById("newPw");
    const newPwConfirm = document.getElementById("newPwConfirm");

    const regExp = /^[\w!@#_-]{6,30}$/;

    if (currentPw.value.length == 0) {
        alert("현재 비밀번호를 입력해주세요.");
        currentPw.focus();
        return false;

    } else if (newPw.value.length == 0) {
        alert("새 비밀번호를 입력해주세요.");
        newPw.focus();
        return false;

    } else if (!regExp.test(newPw.value)) {
        alert("영어, 숫자, 특수문자(!,@,#,-,_) 6~30 글자 사이로 작성해주세요.");
        newPw.focus();
        return false;

    } else if (newPwConfirm.value.length == 0) {
        alert("새 비밀번호 확인을 입력해주세요.");
        newPwConfirm.focus();
        return false;
    } else if (newPw.value != newPwConfirm.value) {
        alert("새 비밀번호가 일치하지 않습니다.");
        newPwConfirm.focus();
        return false;
    }
}