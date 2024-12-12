
console.log("Saved Address Script called");

// Function to fetch saved addresses via AJAX call

function getSavedAddress() {
    return new Promise((resolve, reject) => {
        const xhr = new XMLHttpRequest();
        const url = "http://localhost:8081/api/saved_addresses"; // Update with actual API endpoint
        xhr.open("GET", url, true);
        xhr.setRequestHeader("Content-Type", "application/json");

        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    resolve(JSON.parse(xhr.responseText));
                    console.log("getSavedAddress is called, response:", xhr.responseText);
                } else {
                    reject("Error: " + xhr.status + " - " + xhr.statusText);
                }
            }
        };

        xhr.send();
    });
}

// Function to display saved addresses
function displaySavedAddress(addresses) {

    if (!addresses || !Array.isArray(addresses) || addresses.length === 0) {
        console.error("Invalid or empty data format:", addresses);
        document.getElementById("savedAddresses").innerHTML = "No data available.";
        return;
    }
    

    let html = `
        <table style="border-collapse: collapse; width: 100%;">
        <thead>
            <tr>
                <th>SId</th>
                <th>Address Nickname</th>
                <th>Latitude</th>
                <th>Longitude</th>
                <th>Pincode</th>
                <th>City</th>
                <th>State</th>
                <th>Nation</th>
                <th>UserId</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody id="addressTableBody">
    `;

    addresses.forEach(address => {

        html += `
    <tr id="row-${address.sid}">
           <td><span class="Sid">${address.sid || "N/A"}</td>
           <td><span class="AddressNickname">${address.addressNickName || "N/A"}</td>
           <td><span class="Latitude">${address.latitude || "N/A"}</td>
           <td><span class="Longitude">${address.longitude || "N/A"}</td>
           <td><span class="Pincode">${address.pincode || "N/A"}</td>
           <td><span class="City">${address.city || "N/A"}</td>
           <td><span class="State">${address.state || "N/A"}</td>
           <td><span class="Nation">${address.nation || "N/A"}</td>
           <td><span class="UserId">${address.userId || "N/A"}</td>
        <td>
            <button class="editRow" onclick="editAddress(${address.sid})">Edit</button>
            <button class="deleteRow" onclick="deleteAddress(${address.sid})">Delete</button>
        </td>
    </tr>`;

    });

    html += "</tbody></table>";
    document.getElementById("savedAddresses").innerHTML = html;
}


// Fetch and display data on page load
window.onload = function () {
    console.log("windows loaded!")
    getSavedAddress()
        .then(data => {
            console.log("Saved address fetched successfully:", data);
            displaySavedAddress(data);
        })
        .catch(error => {
            console.error("Error fetching Saved address:", error);
            document.getElementById("savedAddresses").innerHTML = `<p>${error}</p>`;
        });
    };

    function addAddress(event){
        const row = document.getElementById(`row-${address.sid}`);
        let addressNickName = row.querySelector(".AddressNickname").value;
        let latitude = row.querySelector(".Latitude").value;
        let longitude  = row.querySelector(".Longitude").value;
        let pincode= row.querySelector(".Pincode").value;
        let city = row.querySelector(".City").value;
        let state = row.querySelector(".State").value;
        let nation= row.querySelector(".Nation").value;
        let userId= row.querySelector(".UserId").value;

        
        if (!addressNickName|| !latitude || !longitude || !pincode || !city || !state || !nation || !userId) {
            alert("Please fill in all fields!");
            return;
        }
    
         // Log input values
        console.log("address added:", { addressNickName, latitude, longitude , pincode, city, state, nation, userId});

        // Prepare request payload
        const AddressData = JSON.stringify({ addressNickName, latitude, longitude , pincode, city, state, nation, userId });

        // Send the request to the backend
        const xhr = new XMLHttpRequest();
        xhr.open("POST", "http://localhost:8081/api/saved_addresses", true);
        xhr.setRequestHeader("Content-Type", "application/json");
    
        xhr.onreadystatechange = function () {
            console.log("ReadyState:", xhr.readyState, "Status:", xhr.status);
    
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 201) {
                    // Parse the response JSON
                    let newAddress = JSON.parse(xhr.responseText);
    
                    console.log("Address added successfully:", newAddress);


                    // Dynamically add the new row to the table    onclick="addEdit(${newCar.id})"
                    const tableBody = document.getElementById("addressTableBody");
                    const newRow = document.createElement("tr");
                    newRow.id = `row-${newAddress.sid}`;
                    newRow.innerHTML = `
        
                         <td><span class="Sid">${newAddress.sid}</span></td>
                         <td><span class="AddressNickname">${newAddress.addressNickName}</span></td>
                         <td><span class="Latitude">${newAddress.latitude}</span></td>
                         <td><span class="Longitude">${newAddress.longitude }</span></td>
                         <td><span class="Pincode">${newAddress.pincode}</span></td>
                         <td><span class="City">${newAddress.city}</span></td>
                         <td><span class="State">${newAddress.state}</span></td>
                         <td><span class="Nation">${newAddress.nation}</span></td>
                         <td><span class="UserId">${newAddress.userId}</span></td>
        <td>
            <button class="editRow" onclick="editAddress(${newAddress.sid})">Edit</button>
            <button class="deleteRow" onclick="deleteAddress(${newAddress.sid})">Delete</button>
        </td>`;
                    tableBody.appendChild(newRow);
    
                    // Reset input fields
                    addressNickName.value = "";
                   

                    // alert("Address added successfully!");
                } else {
                    // Log error if the request fails
                    console.error("Failed to add car:", xhr.statusText);
                    alert("Error adding car. Please try again.");
                }
            }
        };
    
        xhr.onerror = function () {
            console.error("Network error occurred while adding car.");
            alert("Network error. Please check your server.");
        };
    
        xhr.send(carData); // Send the request
    
    }