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
        let status = response.status;
        if (status === 200) {
            document.location.href = "/pages/gallery.html";
        }
    } catch (e) {
        console.log("An error occured!");
    }
}

var loginButton = document.getElementById('loginButton');
loginButton.addEventListener('click', login);
