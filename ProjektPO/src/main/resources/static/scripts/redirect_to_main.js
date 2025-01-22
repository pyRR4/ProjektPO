document.getElementById("message-form").addEventListener("submit", async function (event) {
    event.preventDefault();
    const messageContent = document.getElementById("message").value;
    const country = document.getElementById("country").value;

    const payload = {
        content: messageContent,
        country: country
    };

    try {
        const response = await fetch('/sendmessage', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)
        });

        if (response.ok) {
            alert("Message sent successfully!");
        } else {
            alert("Failed to send message.");
        }
    } catch (error) {
        alert("Error: " + error.message);
    }
});
