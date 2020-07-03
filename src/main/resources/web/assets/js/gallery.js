document.addEventListener('DOMContentLoaded', async function() {
    const options = { method: 'GET' };
    let response = await fetch("/api/images", options);
    let images = await response.json();
    let status = response.status;
    if (status === 200) {
        images.forEach(addImagePreview);
    }
}, false);

function addImagePreview(item){
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

    let captionText = document.getElementById("caption");
    modal.style.display = "block";
    modalImg.src = url;
    captionText.innerHTML = name;

    let span = document.getElementsByClassName("close")[0];
    span.onclick = function() {
        modal.style.display = "none";
    }
}

async function deleteImage(item) {
    const options = {
        method: 'DELETE',
    };
    try {
        let response = await fetch(item.url, options);
        let status = response.status;
        if (status === 200) {
            location.reload();
        }
    } catch (e) {
        console.log("An error occured!");
    }
}

async function uploadImage() {
    const imageProperties = {
        name: document.getElementById('inputImageName').value
    };
    const formData = new FormData();
    formData.append('image_properties', JSON.stringify(imageProperties));
    formData.append('uploaded_image', document.getElementById('inputImageFile').files[0]);
    const options = {
        method: 'POST',
        body: formData
    };

    try {
        let response = await fetch("/api/images", options);
        let status = response.status;
        if (status === 201) {
            location.reload();
        }
    } catch (e) {
        console.log("An error occured!");
    }
}

function newImageButtonClick(){
    let modal = document.getElementById("uploadImageModal");
    modal.style.display = "block";

    let span = document.getElementById("closeNewImageModal");
    span.onclick = function() {
        modal.style.display = "none";
    };
}

document.querySelector('.custom-file-input').addEventListener('change',function(e){
    let fileName = document.getElementById("inputImageFile").files[0].name;
    let label = e.target.nextElementSibling;
    label.innerText = fileName;
});

let uploadImageButton = document.getElementById('uploadImageButton');
uploadImageButton.addEventListener('click', uploadImage);

let addImageButton = document.getElementById("addImageButton");
addImageButton.addEventListener('click', newImageButtonClick);
