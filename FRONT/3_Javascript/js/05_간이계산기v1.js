function calculator(value){

    const num1 = Number(document.getElementById("num1").value);
    const num2 = Number(document.getElementById("num2").value);

    switch(value){
        case "+": document.getElementById("calc").innerHTML = num1 + num2; break;
        case '-': document.getElementById("calc").innerHTML = num1 - num2; break;
        case '*': document.getElementById("calc").innerHTML = num1 * num2; break;
        case '/': document.getElementById("calc").innerHTML = num1 / num2; break;
        case '%': document.getElementById("calc").innerHTML = num1 % num2; break;
    }
}