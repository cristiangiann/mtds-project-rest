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
        let status = response.status;
        if (status === 201) {
            document.location.href = "/";
        }
    } catch (e) {
        console.log("An error occured!");
    }
}

let signUpButton = document.getElementById('signUpButton');
signUpButton.addEventListener('click', signUp);
