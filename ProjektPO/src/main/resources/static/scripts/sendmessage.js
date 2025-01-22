document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("message-form");

    form.addEventListener("submit", function (event) {

        const messageContent = document.getElementById("message").value.trim();


        if (messageContent === "") {

            event.preventDefault();
            alert("Message cannot be empty. Please enter a message.");
            return;
        }

        alert("Message sent successfully!");
    });
});
