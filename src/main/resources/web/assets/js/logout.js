async function logout() {
    const options = {
        method: 'POST',
    };

    try {
        let response = await fetch("/logout", options);
        console.log(response.status);
        let status = await response.status;
        let body = await response.json();
        if (status === 200) {
            document.location.href = body.url;
        }
    } catch (e) {
        console.log("An error occured!");
    }
}

var logoutButton = document.getElementById('logoutButton');
logoutButton.addEventListener('click', logout);
