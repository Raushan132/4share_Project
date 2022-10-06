document.querySelector('.fileUpload_button').addEventListener('click', function (e) {
    document.querySelector('.box').classList.toggle('box-active');
    document.querySelector('.fileUpload_button').classList.toggle('fileUpload_button-active');
});