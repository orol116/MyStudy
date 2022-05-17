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