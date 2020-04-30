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

async function newUserApi() {
	const user = {
		name: getUser()
	};
	const options = {
		method: 'POST',
		body: JSON.stringify(user)
	};

	try {
		await fetch("http://localhost:4567/api/users/new", options)
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
	const image = {
		userId: getUser(),
		name: getImage()
	};
	const options = {
		method: 'POST',
		body: JSON.stringify(image)
	};

	try {
		await fetch("http://localhost:4567/api/images/new", options)
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
