async function logout() {
    const options = {
        method: 'POST',
    };

    try {
        let response = await fetch("/logout", options);
        let status = response.status;
        if (status === 200) {
            document.location.href = "/";
        }
    } catch (e) {
        console.log("An error occured!");
    }
}

var logoutButton = document.getElementById('logoutButton');
logoutButton.addEventListener('click', logout);
