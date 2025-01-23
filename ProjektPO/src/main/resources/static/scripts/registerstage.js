document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector("form");

    form.addEventListener("submit", function (event) {
        const startDate = document.getElementById("startDate").value;
        const endDate = document.getElementById("endDate").value;
        const country = document.getElementById("country").value.trim();
        const city = document.getElementById("city").value.trim();
        const address = document.getElementById("address").value.trim();
        const organizer = document.getElementById("organizer").value.trim();

        if (!startDate || !endDate || !country || !city || !address || !organizer) {
            event.preventDefault();
            alert("One or more fields are empty or the data entered is invalid!");
            return;
        }

        if (new Date(startDate) > new Date(endDate)) {
            event.preventDefault();
            alert("Start Date cannot be later than End Date!");
            return;
        }

        alert("Stage added successfully!");
    });
});
