const col = document.getElementsByClassName("col");
const row = document.getElementsByClassName("row");

// bingoBoard.style.border = "1px solid black";
bingoBoard.style.padding = "50px";

for (let i of row){
    i.style.display = "flex";
}

for(let i of col) {
   
    i.style.border = "1px solid black";
    i.style.margin = "2px";
    i.style.fontSize = "20px";
    i.style.width = "60px";
    i.style.height = "60px";

    i.style.display = "flex";
    i.style.justifyContent = "center";
    i.style.alignItems = "center";

    i.addEventListener("mouseenter", function() {
        this.style.backgroundColor = "yellow";
    })
    i.addEventListener("mouseleave", function() {
        this.style.backgroundColor = "#ffffff";
    })
    i.addEventListener("click", function() {
        alert(this.innerText);
    })
}
