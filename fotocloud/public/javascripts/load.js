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

	$('#welcome').hide();
	$('#welcome').fadeIn(1200);
});

var login = function(){
	$('#welcome').fadeOut(800);
	$('#welcome').hide();
	$('#signin').fadeIn(1200);
};

var register = function(){
	$('#welcome').fadeOut(800);
	$('#welcome').hide();
	$('#signup').fadeIn(1200);
};

var cancel = function(){
	$('#signup').hide();
	$('#signin').hide();
	$('#welcome, #down-button').fadeIn(1200);
};

var validateRegistration = function(){
	var requiredItems = document.getElementsByClassName("required-registration");
	var allIsGood = true;

	for(var i=0; i<requiredItems.length;i++){
		if(requiredItems[i].value.length == 0){
		//	var errorLabel = requiredItems[i].name + "-error";
		//	document.getElementById(errorLabel).parentNode.style.display = "block";
		//	document.getElementById(errorLabel).style.display = "inline-block";
			allIsGood = false;
		}
	}

	if(requiredItems['password'].value == requiredItems['password-confirm'].value){
		//document.getElementById("password-confirm-error").style.display = "none";
	}else{
		//document.getElementById("password-confirm-error").style.display = "inline-block";
		allIsGood = false;
	}

	if(requiredItems['agree'].checked != true){
		//document.getElementById("agree-error").parentNode.style.display = "block";
		//document.getElementById("agree-error").style.display = "inline-block";
		allIsGood = false;
	}
	return allIsGood;
};

var validateLogin = function(){

};