function errorPopup() {
    window.alert("One or more fields are empty or the data entered is invalid!")
}

function signUp() {
    window.alert("Signed up successfully!")
}

document.getElementById("signup").addEventListener("click", signUp)
document.getElementById("signup").addEventListener("contextmenu", errorPopup)