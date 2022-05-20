// 1단계

// [jQuery]

// addEventListener == on
$("#btn1").on("click", function() {

    // this : 이벤트가 발생한 요소 (#btn1 버튼) -> JS

    // $(this) : 이벤트가 발생한 요소를 jQuery 방식으로 선택
    //           -> jQuery의 메서드 사용 가능

    // console.log($(this).prev().prev());

    // 버튼 전전 요소(input)에 작성된 값 얻어오기
    const color = $(this).prev().prev().val();

    // 버튼 전전전 요소(div)에 배경색 변경
    $(this).prev().prev().prev().css("backgroundColor" , color);
});


// 2단계
$("#btn2").on("click", function() {

    const colorList = document.getElementsByClassName("div2");
    const inputList = document.getElementsByClassName("input2");

    for (let i = 0; i < colorList.length; i++) {
        color = $(inputList[i]).val();
        $(colorList[i]).css("backgroundColor", color);
    }
});

$(".input3").on("keyup", function() {
        $(this).prev().css("backgroundColor", $(this).val());
        $(this).css("border-color", $(this).val());
}); 