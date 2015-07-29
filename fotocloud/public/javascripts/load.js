$(document).ready(function(){
	var counter = 0;
	var i = 0;
	var imgs = ["stock0.jpg", "stock1.jpg", "stock2.jpg", "stock3.jpg", "stock4.jpg", "stock5.jpg", "stock6.jpg"];

	var timer = setInterval(function(){
		counter++;
		// change background image every 5 seconds
		if(counter % 5 == 0){
			i++;
			document.getElementById("background").style.backgroundImage = "url(public/images/" + imgs[i % imgs.length] + ")";

		}
	}, 1000);

	$('#welcome, #down-button').hide();
	$('#welcome, #down-button').fadeIn(1200);
});

var login = function(){
	$('#welcome, #down-button').fadeOut(800);
	$('#welcome, #down-button').hide();
	$('#signin').animate({width: "toggle"}, 1200);
};

var register = function(){
	$('#welcome, #down-button').fadeOut(800);
	$('#welcome, #down-button').hide();
	$('#signup').animate({width: "toggle"}, 1200);
};

var cancel = function(){
	$('#signup').hide();
	$('#signin').hide();
	$('#welcome, #down-button').fadeIn(1200);
};