window.onload = function() {
	let bookImage = document.getElementById("bookimage");
	bookImage.onchange = function(evt) {
		let img = evt.target.files[0];
		let blobUrl = window.URL.createObjectURL(img);
		let preview = document.getElementById('imagepreview');
		preview.src = blobUrl;
	}
}