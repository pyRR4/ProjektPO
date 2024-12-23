document.getElementById('message-form').addEventListener('submit', function (event) {
    // If the form passes HTML validation, this block will execute
    event.preventDefault(); // Prevent the default form submission behavior
    window.location.href = 'greetings.html'; // Redirect to greetings.html
});
