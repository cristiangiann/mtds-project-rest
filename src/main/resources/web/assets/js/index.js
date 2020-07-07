async function login() {
    const user = {
        id: document.getElementById('inputId').value,
        password: document.getElementById('inputPassword').value
    };
    const options = {
        method: 'POST',
        body: JSON.stringify(user)
    };

    try {
        let response = await fetch("/login", options);
        console.log(response.status);
        let status = response.status;
        let body = await response.json();
        if (status === 200) {
            document.location.href = body._links.redirect_to;
        }
    } catch (e) {
        console.log("An error occured!");
    }
}

var loginButton = document.getElementById('loginButton');
loginButton.addEventListener('click', login);
