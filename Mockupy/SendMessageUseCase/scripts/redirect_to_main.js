function errorPopup() {
    window.alert("One or more fields are empty or the data entered is invalid!")
}

function submitTravel() {
    window.alert("Message sent successfully!")
}

document.getElementById("submit-message").addEventListener("click", submitTravel)
document.getElementById("submit-message").addEventListener("contextmenu", errorPopup)

document.getElementById('message-form').addEventListener('submit', function (event) {
    event.preventDefault(); 
    window.location.href = 'greetings.html'; 
});
