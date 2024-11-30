console.log("Script called");


function addUser(event) {
    event.preventDefault();

    const name = document.getElementById("name").value.trim();
    const email = document.getElementById("email").value.trim();
    const phone = document.getElementById("phone").value.trim();
    const password = document.getElementById("password").value.trim();

    if (!name || !email || !phone || !password ) {
        alert("Please provide valid details.");
        return;
    }

    const data = JSON.stringify({ name, email, phone, password });

    const xhr = new XMLHttpRequest();
    xhr.open("POST", `http://localhost:8081/api/userDetails`, true);
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 201) {
                alert("User details added successfully.");
                const response = JSON.parse(xhr.responseText);
                console.log("User added:", response);
            } else {
                console.error("Error adding user:", xhr.status, xhr.statusText);
                alert("Failed to add user. Please try again.");
            }
        }
    };

    xhr.send(data);
}

