$(document).ready(function(){
	var counter = 0;
	var i = 0;
	var imgs = ["stock0.jpg", "stock1.jpg", "stock2.jpg", "stock3.jpg", "stock4.jpg"];

	var timer = setInterval(function(){
		counter++;
		// change background image every 5 seconds
		if(counter % 5 == 0){
			i++;
			document.getElementById("background").style.backgroundImage = "url(public/images/" + imgs[i % imgs.length] + ")";

		}
	}, 1000);

});