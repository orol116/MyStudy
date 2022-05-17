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