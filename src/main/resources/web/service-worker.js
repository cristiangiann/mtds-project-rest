async function getUsersApi() {
	try {
		await fetch("/api/users")
			.then(function(response) {
				return response.json();
			})
			.then(function(json){
				console.log(json);
				const responseParagraph = document.getElementById('response');
				responseParagraph.textContent = JSON.stringify(json);
			})
	} catch (e) {
		console.log("An error occured!");
	}
}

async function newUserApi() {
	try {
		await fetch("http://localhost:4567/api/users/new?" + "name=" + getUser())
			.then(function(response) {
				return response.json();
			})
			.then(function(json){
				console.log(json);
				const responseParagraph = document.getElementById('response');
				responseParagraph.textContent = JSON.stringify(json);
			})
	} catch (e) {
		console.log("An error occured!");
	}
}

async function getImagesApi() {
	try {
		await fetch("http://localhost:4567/api/images?" + "user=" + getUser())
			.then(function(response) {
				return response.json();
			})
			.then(function(json){
				console.log(json);
				const responseParagraph = document.getElementById('response');
				responseParagraph.textContent = JSON.stringify(json);
			})
	} catch (e) {
		console.log("An error occured!");
	}
}

async function getImageApi() {
	try {
		await fetch("http://localhost:4567/api/images/get?" + "user=" + getUser() + "&id=" + getImage())
			.then(function(response) {
				return response.json();
			})
			.then(function(json){
				console.log(json);
				const responseParagraph = document.getElementById('response');
				responseParagraph.textContent = JSON.stringify(json);
			})
	} catch (e) {
		console.log("An error occured!");
	}
}

async function newImageApi() {
	try {
		await fetch("http://localhost:4567/api/images/new?" + "user=" + getUser() + "&id=" + getImage())
			.then(function(response) {
				return response.json();
			})
			.then(function(json){
				console.log(json);
				const responseParagraph = document.getElementById('response');
				responseParagraph.textContent = JSON.stringify(json);
			})
	} catch (e) {
		console.log("An error occured!");
	}
}

async function deleteImageApi() {
	try {
		await fetch("http://localhost:4567/api/images/delete?" + "user=" + getUser() + "&id=" + getImage())
			.then(function(response) {
				return response.json();
			})
			.then(function(json){
				console.log(json);
				const responseParagraph = document.getElementById('response');
				responseParagraph.textContent = JSON.stringify(json);
			})
	} catch (e) {
		console.log("An error occured!");
	}
}

function getUser(){
	return document.getElementById('userInput').value;
}

function getImage(){
	return document.getElementById('imgInput').value;
}

var getUsersButton = document.getElementById('getUsersButton');
getUsersButton.addEventListener('click', getUsersApi);

var addUserButton = document.getElementById('addUserButton');
addUserButton.addEventListener('click', newUserApi);

var getImagesButton = document.getElementById('getImagesButton');
getImagesButton.addEventListener('click', getImagesApi);

var addImageButton = document.getElementById('addImageButton');
addImageButton.addEventListener('click', newImageApi);

var getImageButton	 = document.getElementById('getImageButton');
getImageButton.addEventListener('click', getImageApi);

var deleteImageButton = document.getElementById('deleteImageButton');
deleteImageButton.addEventListener('click', deleteImageApi);
