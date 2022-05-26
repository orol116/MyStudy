document.getElementById("newBtn").addEventListener("click", function(e){

    // 빙고 크기값 받아오기
    let bingoSize = document.getElementById("size").value;

    // 빙고 바깥쪽 div 변수로 얻어오기
    const bingoBoard = document.getElementById("bingoBoard");

    // 빙고 초기화
    bingoBoard.innerHTML = "";

    // 빙고 열과 행 변수로 선언
    let row = null;
    let col = null;
    
    // 빙고 크기가 3이상이라면 실행
    if (bingoSize >= 3) {

        // 빙고 행이 입력받은 크기까지 생성하고 class 이름을 "row"로 선언
        for (let i = 0; i < bingoSize; i++) {
            row = document.createElement("div");
            row.className = "row";

            // 빙고 열이 입력받은 크기까지 생성하고 class 이름을 "col"로 선언
            for (let j = 0; j < bingoSize; j++) {
                col = document.createElement("div");
                col.className = "col";

                // 열에 col의 div 추가
                row.append(col);
            }

            // 빙고판에 row의 div 추가
            bingoBoard.append(row);
        }
    }
});


