// dropdown toggle button js
let dropdown1 = document.querySelector('.dropdown1');
dropdown1.onclick = function () {
    dropdown1.classList.toggle('active');
}

let dropdown2 = document.querySelector('.dropdown2');
dropdown2.onclick = function () {
    dropdown2.classList.toggle('active');
}


// Carousel auto slide js
var counter = 1;
setInterval(function () {
    document.getElementById('radio' + counter).checked = true;
    counter++;
    if (counter > 3) {
        counter = 1;
    }
}, 5000);