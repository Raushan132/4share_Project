$(document).ready(function() {
	$(".error").fadeOut(3000);
	$(".success").fadeOut(3000);



});


inputTypeFile = document.getElementById('inputTypeFile')
showFilesList = document.getElementById('ShowFilesList')

inputTypeFile.addEventListener('click', function() {

	document.getElementById('ShowFilesList').innerHTML = "";

});


// file name
inputTypeFile.addEventListener('change', function() {

	document.getElementById('ShowFilesList').innerHTML = "";

	if (this.files.length > 0) {
		for (var i = 0; i < this.files.length; i++) {
			ShowFilesList.innerHTML += "<p class='addedFile'><img src='assets/fileIcon/fileIcon.svg' alt='file image'>" + "<span class='fileName'>" + this.files.item(i).name + "</span>" + " <span class='close_button'>✖</span></p>"
		}
	}


	//close button
	close_button = document.getElementsByClassName('close_button')
	arrayFormCloseBtn = [...close_button];
	arrayFormCloseBtn.forEach((onebyone) => {
		onebyone.addEventListener('click', function(e) {
			var w = e.target.parentElement;
			e.target.parentElement.remove();

			var x = w.getElementsByClassName('fileName');

			const dt = new DataTransfer();
			for (var i = 0; i < inputTypeFile.files.length; i++) {

				var s1 = inputTypeFile.files.item(i).name;
				var s2 = x.item(0).innerText;


				if (s1 != s2) {
					dt.items.add(inputTypeFile.files.item(i));
				}
			}

			inputTypeFile.files = dt.files;

			if (ShowFilesList.firstChild == null) {
				alert('you cleared all files, please select again to upload the files!')
				inputTypeFile.value = '';
			}
		})
	})
})


