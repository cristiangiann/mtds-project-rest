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
		await fetch("http://localhost:4567/api/images?" + "user=" + getUserID())
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
		await fetch("http://localhost:4567/api/images/" + getImage() + "?user=" + getUserID())
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
		id: getUserID(),
		name: getUserName()
	};
	const options = {
		method: 'POST',
		body: JSON.stringify(user)
	};

	try {
		await fetch("http://localhost:4567/api/users", options)
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
		userId: getUserID(),
		name: getImage()
	};
	const options = {
		method: 'POST',
		body: JSON.stringify(image)
	};

	try {
		await fetch("http://localhost:4567/api/images", options)
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
	const image = {
		userId: getUserID(),
		name: getImage()
	};
	const options = {
		method: 'DELETE',
		body: JSON.stringify(image)
	};

	try {
		await fetch("http://localhost:4567/api/images/" + getImage(), options)
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

function getUserID(){
	return document.getElementById('userID').value;
}

function getUserName(){
	return document.getElementById('userName').value;
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
