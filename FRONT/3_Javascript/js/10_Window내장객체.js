// window.setTimeout(함수, 지연시간(ms))
document.getElementById("btn1").addEventListener("click", function() {

    setTimeout(function(){
        alert("3초후 출력 확인!");
    }, 3000);
});

let interval;

function clockFn(){
    const clock = document.getElementById("clock");
    clock.innerText = currentTime();

    interval = setInterval(function(){
        clock.innerText = currentTime();
    }, 1000);
}

function currentTime(){
    const now = new Date();

    return now.getHours() + " : " + now.getMinutes() + " : " + now.getSeconds();
}

clockFn();

// clearInterval
document.getElementById("stop").addEventListener("click", function() {
    clearInterval(interval);
});