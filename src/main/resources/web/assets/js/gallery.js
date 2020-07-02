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
    div.id = 'container';
    div.className = "col-lg-3 col-md-4 col-6";
    div.innerHTML = "<a href=\"#\" class=\"d-block mb-4 h-100\">\n" +
        "<img class=\"img-fluid img-thumbnail\" src=\"" + previewUrl + "\" alt=\"\">\n" +
        "</a>";
    const url = item.url;
    const imageName = item.name;

    let newModal = function() {
        openModal(url, imageName)
    };

    div.onclick = newModal;
    document.getElementById("imageContainer").appendChild(div);
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
