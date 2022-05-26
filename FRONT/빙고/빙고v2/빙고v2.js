// 빙고v1.html의 모양을 JS로 만들어서 #bingoBoard에 추가

// document.createElement("태그명") : 요소 생성
// 요소.append(변수명) : 요소의 마지막 자식으로 추가

const bingoBoard = document.getElementById("bingoBoard");

bingoBoard.style.padding = "50px";

let row = null;
let col = null;

for (let i = 0; i < 5; i++) {

    row = document.createElement("div");
    row.className = "row";

    row.style.display = "flex";

    for (let j = 1; j < 6; j++) {
        col = document.createElement("div");
        col.className = "col";

        col.innerText = j;

        col.style.border = "1px solid black";
        col.style.margin = "2px";
        col.style.fontSize = "20px";
        col.style.width = "60px";
        col.style.height = "60px";

        col.style.display = "flex";
        col.style.justifyContent = "center";
        col.style.alignItems = "center";

        col.addEventListener("mouseenter", function() {
            this.style.backgroundColor = "yellow";
        })
        col.addEventListener("mouseleave", function() {
            this.style.backgroundColor = "white";
        })
        col.addEventListener("click", function() {
            alert(this.innerText);
        })

        row.append(col);
    }

    bingoBoard.append(row);
}