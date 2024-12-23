
function submitTravel() {
    window.alert("Message sent successfully!")
}

document.getElementById("submit-message").addEventListener("click", submitTravel)

document.getElementById('message-form').addEventListener('submit', function (event) {
    event.preventDefault(); 
    window.location.href = 'greetings.html'; 
});
