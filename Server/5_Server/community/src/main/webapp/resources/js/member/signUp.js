/* signUp.js */

// 전화번호 유효성 검사
const memberTel = document.getElementById("memberTel");
const telMessage = document.getElementById("telMessage");

// ** input 이벤트 **
// -> 입력과 관련된 모든 동작(key관련, mouse관련, 붙여넣기)
memberTel.addEventListener("input", function() {

    // 입력이 되지 않은 경우
    if(memberTel.value.length == 0) {
        telMessage.innerText = "전화번호를 입력해주세요.(- 제외)";

        // telMessage.classList.remove("confirm");
        // telMessage.classList.remove("error");
        telMessage.classList.remove("confirm", "error");     
        return;
    }

    // 전화번호 정규식
    const regExp = /^0(1[01679]|2|[3-6][1-5]|70)\d{3,4}\d{4}$/;

    // 유효한 경우
    if(regExp.test(memberTel.value)) {
        telMessage.innerText = "유효한 전화번호 형식입니다.";
        telMessage.classList.add("confirm");
        telMessage.classList.remove("error");
    } else { // 유효하지 않은 경우
        telMessage.innerText = "전화번호 형식이 올바르지 않습니다.";
        telMessage.classList.add("error");
        telMessage.classList.remove("confirm");
    }
});

// 이메일 유효성 검사
const memberEmail = document.getElementById("memberEmail");
const emailMessage = document.querySelector("#emailMessage");

memberEmail.addEventListener("input", function() {

    // 입력이 되지 않은 경우
    if(memberEmail.value.length == 0) {
        emailMessage.innerText = "메일을 받을 수 있는 이메일을 입력해주세요.";
        emailMessage.classList.remove("confirm", "error");
        return;
    }

    const regExp = /^[\w\-\_]{4,}@[\w\-\_]+(\.\w+){1,3}$/;

    if(regExp.test(memberEmail.value)){ // 유효한 경우
        emailMessage.innerText = "유효한 이메일 형식입니다.";
        emailMessage.classList.add("confirm");
        emailMessage.classList.remove("error");

        // ************** 이메일 중복 검사(ajax) 진행 예정 ***************

    } else { // 유효하지 않은 경우
        emailMessage.innerText = "이메일 형식이 올바르지 않습니다.";
        emailMessage.classList.add("error");
        emailMessage.classList.remove("confirm");
    }
});


// 닉네임 유효성 검사
const memberNickname = document.getElementById("memberNickname");
const nicknameMessage = document.getElementById("nicknameMessage");

memberNickname.addEventListener("input", function() {

    if(memberNickname.value.length == 0) {
        nicknameMessage.innerText = "영어/숫자/한글 2 ~ 10글자 사이로 작성해주세요.";
        nicknameMessage.classList.remove("confirm", "error");
        return;
    }

    const regExp = /^[a-zA-Z0-9가-힣]{2,10}$/;

    if (regExp.test(memberNickname.value)) {
        nicknameMessage.innerText = "유효한 닉네임 형식입니다.";
        nicknameMessage.classList.add("confirm");
        nicknameMessage.classList.remove("error");

    } else {
        nicknameMessage.innerText = "닉네임 형식이 올바르지 않습니다.";
        nicknameMessage.classList.add("error");
        nicknameMessage.classList.remove("confirm");
    }
});


// 비밀번호 유효성 검사
const memberPw = document.getElementById("memberPw");
const memberPwConfirm = document.getElementById("memberPwConfirm");
const pwMessage = document.getElementById("pwMessage");

memberPw.addEventListener("input", function() {

    if(memberPw.value.length == 0) {
        pwMessage.innerText = "영어/숫자/특수문자(!,@,#,-,_) 6 ~ 30글자 사이로 입력해주세요.";
        pwMessage.classList.remove("confirm", "error");
        return;
    }

    const regExp = /^[\w!@#_-]{6,30}$/;

    if (regExp.test(memberPw.value)) {

        if(memberPwConfirm.value.length == 0) {
            pwMessage.innerText = "유효한 비밀번호 형식입니다.";
            pwMessage.classList.add("confirm");
            pwMessage.classList.remove("error");
        } else {
            checkPw();
        }

    } else {
        pwMessage.innerText = "비밀번호 형식이 올바르지 않습니다.";
        pwMessage.classList.add("error");
        pwMessage.classList.remove("confirm");
    }
});

// 비밀번호 확인 유효성 검사

// 함수명() : 함수 호출 (수행)
// 함수명   : 함수에 작성된 코드 반환
memberPwConfirm.addEventListener("input", checkPw);

function checkPw() { // 비밀번호 확인 일치 검사

    // 비밀번호/비밀번호 확인이 같을 경우
    if(memberPw.value == memberPwConfirm.value) {
        pwMessage.innerText = "비밀번호가 일치합니다.";
        pwMessage.classList.add("confirm");
        pwMessage.classList.remove("error");
    } else {
        pwMessage.innerText = "비밀번호가 일치하지 않습니다.";
        pwMessage.classList.add("error");
        pwMessage.classList.remove("confirm");
    }
}