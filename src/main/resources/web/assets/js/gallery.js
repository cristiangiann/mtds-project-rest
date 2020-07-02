document.addEventListener('DOMContentLoaded', async function() {
    const options = { method: 'GET' };
    let response = await fetch("/api/images", options);
    let images = await response.json();
    let status = response.status;
    if (status === 200) {
        images.forEach(addImage);
    }
}, false);

function addImage(item){
    const previewUrl = item.previewUrl;
    let div = document.createElement('div');
    div.id = 'container' + item.id;
    div.className = "col-lg-3 col-md-4 col-6";
    div.innerHTML = "<a href='#' class='d-block mb-4 h-100\'>\n" +
        "<img class='img-fluid img-thumbnail preview-image' src='" + previewUrl + "' alt=''>\n" +
        "</a>\n";

    document.getElementById("imageContainer").appendChild(div);
    addShowButton(item);
    addDeleteButton(item);
}

function addShowButton(item){
    const url = item.url;
    const imageName = item.name;

    let showButton = document.createElement('button');
    showButton.id = 'showButton';
    showButton.className = 'btn btn-primary btn-sm';
    showButton.innerHTML = '<img src="../assets/img/icon/show.svg"> Show';

    let showModal = function() {
        openModal(url, imageName);
    };
    showButton.onclick = showModal;
    document.getElementById("container" + item.id).appendChild(showButton);
}

function addDeleteButton(item){
    const url = item.url;

    let deleteButton = document.createElement('button');
    deleteButton.id = 'deleteButton';
    deleteButton.className = 'btn btn-danger btn-sm';
    deleteButton.innerHTML = '<img src="../assets/img/icon/bin.svg"> Delete';

    let deleteImageOnClick = function() {
        deleteImage(item);
    };
    deleteButton.onclick = deleteImageOnClick;

    document.getElementById("container" + item.id).appendChild(deleteButton);
}

function openModal(url, name){
    let modal = document.getElementById("galleryModal");
    let modalImg = document.getElementById("modalImg");
    modalImg.src = url;

    // Get the image and insert it inside the modal - use its "alt" text as a caption
    let captionText = document.getElementById("caption");
    modal.style.display = "block";
    modalImg.src = url;
    captionText.innerHTML = name;

    // Get the <span> element that closes the modal
    let span = document.getElementsByClassName("close")[0];

    // When the user clicks on <span> (x), close the modal
    span.onclick = function() {
        modal.style.display = "none";
    }
}

async function deleteImage(item) {
    const options = {
        method: 'DELETE',
    };
    try {
        let response = await fetch("/api/images/" + item.id, options);
        console.log(response.status);
        let status = response.status;
        if (status === 200) {
            location.reload();
        }
    } catch (e) {
        console.log("An error occured!");
    }
}
