function errorPopup() {
    window.alert("One or more fields are empty or the data entered is invalid!")
}

function submitStage() {
    window.alert("Stage added successfully!")
}

document.getElementById("submit-stage").addEventListener("click", submitStage)
document.getElementById("submit-stage").addEventListener("contextmenu", errorPopup)