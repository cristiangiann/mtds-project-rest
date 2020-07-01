async function signUp() {
    const user = {
        id: document.getElementById('inputId').value,
        name: document.getElementById('inputName').value,
        password: document.getElementById('inputPassword').value
    };
    const options = {
        method: 'POST',
        body: JSON.stringify(user)
    };

    try {
        let response = await fetch("/api/users", options);
        console.log(response.status);
        let status = await response.status;
        let body = await response.json();
        if (status === 201) {
            document.location.href = body.url;
        }
    } catch (e) {
        console.log("An error occured!");
    }
}

var signUpButton = document.getElementById('signUpButton');
signUpButton.addEventListener('click', signUp);
