document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector("form");

    form.addEventListener("submit", function (event) {
        const firstName = document.getElementById("first_name").value.trim();
        const lastName = document.getElementById("last_name").value.trim();
        const pesel = document.getElementById("pesel").value.trim();
        const city = document.getElementById("city").value.trim();
        const address = document.getElementById("address").value.trim();
        const country = document.getElementById("country").value.trim();
        const email = document.getElementById("email").value.trim();
        const phone = document.getElementById("phone").value.trim();

        if (
            !firstName || !lastName || !pesel || !city || !address ||
            !country || !email || !phone || !/^\d{11}$/.test(pesel)
        ) {
            event.preventDefault();
            alert("One or more fields are empty or the data entered is invalid!");
            return;
        }

        alert("Travel submitted successfully!");
    });
});
