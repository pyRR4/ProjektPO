function errorPopup() {
    window.alert("One or more fields are empty or the data entered is invalid!")
}

function submitTravel() {
    window.alert("Travel submitted successfully!")
}


document.getElementById("declare-next-stage").addEventListener("contextmenu", errorPopup)

document.getElementById("submit-travel").addEventListener("click", submitTravel)
document.getElementById("submit-travel").addEventListener("contextmenu", errorPopup)


