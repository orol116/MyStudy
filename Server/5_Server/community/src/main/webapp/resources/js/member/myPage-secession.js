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

    if (!confirm("정말 탈퇴 하시겠습니까?")) return false;
}