/*
	backend for FotoCloud to interact with Firebase API
*/
var firebase = require("firebase");
var fs = require("fs");
var express = require("express");
var swig = require("swig");
var path = require("path");

var myFirebase = new firebase("https://popping-torch-9091.firebaseio.com/");
var myExpress = new express();

myExpress.use(express.static(path.join(__dirname, "fotocloud")));
myExpress.engine("html", swig.renderFile);
myExpress.set("view engine", "html");
myExpress.set("views", __dirname + "/fotocloud/views");

// set view cache to true when site deployed in production
myExpress.set("view cache", false);
swig.setDefaults({cache : false});

myExpress.get("/", function(req, res){
	res.render('index');
});

myExpress.post("/register", function(req, res){

});

myExpress.post("/", function(req, res){

});

myServer = myExpress.listen(3000, function(){
	var hostaddr = myServer.address().address;
	var port = myServer.address().port;
	console.log("Server listening at http://%s:%s", hostaddr, port);	
});