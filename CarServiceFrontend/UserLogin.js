console.log("User Details Script called");
// Function to fetch user details via AJAX call
function getUserDetails() {
    return new Promise((resolve, reject) => {
        var xhr = new XMLHttpRequest();
        var url = "http://localhost:8081/api/userDetails"; // Replace with your actual API endpoint
        xhr.open("GET", url, true);
        xhr.setRequestHeader("Content-Type", "application/json");
        
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    resolve(JSON.parse(xhr.responseText));
                    console.log("getUserDetails is called");
                } else {
                    reject("Error: " + xhr.status + " - " + xhr.statusText);
                }
            }
        };

        xhr.send();
    });
}


// Function to display user details
function displayUserDetails(users) {
    if (!Array.isArray(users)) {
        console.error("Invalid data format:", users);
        document.getElementById("userDetailsList").innerHTML = "Invalid data received!";
        return;
    }
   
    let html = `
        <table style="border-collapse: collapse; width: 100%;">
        <thead>
            <tr>
                <th>User ID</th>
                <th>Name</th>
                <th>Phone Number</th>
                <th>Email</th>
                <th>Password</th>
                <th>Action</th>
            </tr>
             </thead>
             <tbody id="userTableBody">
    `;

    // Iterate through the users and add rows
    users.forEach(user => {
        console.log(user);
        const userId = user.userId || "N/A";
        const name = user.name || "N/A";
        const phoneNo = user.phoneNo || "N/A";
        const email = user.email || "N/A";
        const password = user.password || "N/A";

        html += `
        <tr id="row-${userId}">
        <td>${userId}</td>
        <td><span class="name">${name}</span></td>
        <td><span class="phoneNo">${phoneNo}</span></td>
        <td><span class="email">${email}</span></td>
        <td><span class="password">${password}</span></td>
        <td><button style ="width:60px; background-color: white;" class="editRow" onclick="addEdit(${userId})">Edit</button> 
        <button style="width: 60px; background-color:red; color: white;" class="deleteRow" onclick="addDelete(${userId})">delete</button></td></tr> `;
        
    });

    html += "</tbody></table>";

    // Inject the table into the DOM
    document.getElementById("userDetailsList").innerHTML = html;
}

    // Fetch and display user details on page load
document.addEventListener("DOMContentLoaded", () => {
    getUserDetails()
        .then(users => {
            displayUserDetails(users);
        })
        .catch(error => {
            console.error(error);
            document.getElementById("userDetailsList").innerHTML = `<p>Error fetching user details!</p>`;
   });
});


function addEdit(userId){
    let row = document.getElementById(`row-${userId}`);
    let name = row.querySelector(".name").innerText;
    let phoneNo = row.querySelector(".phoneNo").innerHTML;
    let email = row.querySelector(".email").innerText;
    let password = row.querySelector(".password").innerText;

    row.innerHTML = `
<td>${userId}</td>
<td><input type="text" style="width: 100px;height: 30px; font-size:18px;" id="editUserName-${userId}" value="${name}" /></td>
<td><input type="tel"  style="width: 100px;height: 30px; font-size:18px;"  id="editUserPhoneNo-${userId}" value="${phoneNo}" 
oninput="validateNumberInput(this)"/></td>
<td><input type="text" style="width: 100px;height: 30px; font-size:18px;" id="editUserEmail-${userId}" value="${email}" /></td>
<td><input type="text" style="width: 100px;height: 30px; font-size:18px;" id="editUserPassword-${userId}" value="${password}" /></td>
<td>
    <button style ="width:70px; background-color: white;" class="editRow" onclick="saveRow(${userId})">Save</button>
    <button style ="width:70px; background-color: white;" class="deleteRow" onclick="cancelEdit('${userId}', '${name}', '${phoneNo}','${email}','${password}')">Cancel</button>
</td>
`;
console.log("Name Element:", row.querySelector(".name"));
console.log("phoneNo Element:", row.querySelector(".phoneNo"));
console.log(row.innerHTML);
}


function cancelEdit(userId ,name ,phoneNo ,email ,password) {
    const row = document.getElementById(`row-${userId}`);
    
    if (!row) {
        console.log(`Row with id row-${userId} not found`);
    }

    // Restore the original values passed as arguments
    row.innerHTML = `
    <td>${userId}</td>
    <td class="name">${name}</td>
    <td class="phoneNo">${phoneNo}</td>
    <td class="email">${email}</td>
    <td class="password">${password}</td>
    <td>
        <button style="width:70px; background-color: white;" class="editRow" onclick="addEdit(${userId})">Edit</button>
          <button  style="width: 60px; background-color:red; color: white;" class="deleteRow" onclick="addDelete(${userId})">delete</button></td>
    </td>
    `;
  
    console.log("Edit canceled");
}

function validateNumberInput(input){
    const sanitizedValue = input.value.replace(/\D/g, ""); // Allow digits only
    if (input.value !== sanitizedValue) {
        alert("Invalid input! Please enter numerical values only.");
        input.value = sanitizedValue;
    }
}

function saveRow(userId) {
    let name = document.getElementById(`editUserName-${userId}`).value.trim();
    let phoneNo = document.getElementById(`editUserPhoneNo-${userId}`).value.trim();
    let email = document.getElementById(`editUserEmail-${userId}`).value.trim();
    let password = document.getElementById(`editUserPassword-${userId}`).value.trim();

    const data = JSON.stringify({ name, phoneNo, email, password });

    const xhr = new XMLHttpRequest();
    xhr.open("PUT", `http://localhost:8081/api/userDetails/${userId}`, true);
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                alert("User updated successfully!");
                const response = JSON.parse(xhr.responseText);

                // Update the row tr id="row-${userId}"
                const row = document.getElementById(`row-${userId}`);
                row.innerHTML = `
                    <td>${response.userId}</td>
                    <td><span class="name">${response.name}</span></td>
                    <td><span class="phoneNo">${response.phoneNo}</span></td>
                    <td><span class="email">${response.email}</span></td>
                    <td><span class="password">${response.password}</span></td>
                    <td>
                        <button style ="width:60px; background-color: white;" class="editRow" onclick="addEdit(${response.userId})">Edit</button>
                        <button style="width: 60px; background-color:red; color: white;" class="deleteRow" onclick="addDelete(${response.userId})">Delete</button>
                    </td>
                `;
            } else {
                console.error("Error updating user:", xhr.responseText);
                alert("Failed to update user.");
            }
        }
    };

    xhr.send(data);
}


// Fetch and display data on page load
window.onload = function () {
    getUserDetails()
    .then(data => {
        console.log("Response from API:", data); // Log the data
        displayUserDetails(data);
    })
    .catch(error => {
        console.error("Error fetching user details:", error);
        document.getElementById("userDetailsList").innerHTML = `<p>${error}</p>`;
    });

    };

  function editAdd(userId){
    const row = document.getElementById(`row-${userId}`);
    const name = row.querySelector(".name").innerText;
    const phoneNo = row.querySelector(".phoneNo").innerText;
    const email = row.querySelector(".email").innerText;
    const password = row.querySelector(".password").innerText;

    row.innerHTML = `
    <td>${userId}</td>
    <td><input type="text" style="width: 100px;height: 30px; font-size:18px;" id="editUserName-${userId}" value="${name}" /></td>
    <td><input type="tel"  style="width: 100px;height: 30px; font-size:18px;"  id="editUserPhone-${userId}" value="${phoneNo}" 
    oninput="validateNumberInput(this)"/></td>
    <td><input type="text" style="width: 100px;height: 30px; font-size:18px;" id="editUserEmail-${userId}" value="${email}" /></td>
    <td><input type="text" style="width: 100px;height: 30px; font-size:18px;" id="editUserPassword-${userId}" value="${password}" /></td>
    <td>
        <button style ="width:70px; background-color: white;" class="editRow" onclick="saveRow(${userId})">Save</button>
        <button style ="width:70px; background-color: white;" class="editRow" onclick="cancelEdit(${userId}, '${name}', ${phoneNo},${email},${password})">Cancel</button>
    </td>
    `;
    console.log(row.innerHTML);

  }

    // posting new value to table
    function addUser(event) {
        event.preventDefault(); // Prevent form submission from reloading the page
        
         let row = document.getElementById(`row-${userId}`);
         let name = row.querySelector(".name").innerText;
         let phoneNo = row.querySelector(".phoneNo").innerText;
         let email= row.querySelector(".email").innerText;
         let password = row.querySelector(".password").innerText;
        
        if (!name || !phoneNo || !email || !password) {
            alert("Please fill in all fields!");
            return;
        }
    
        // Log input values
        console.log("Adding userDetails:", { name, phoneNo, email, password });
        
        // Prepare request payload
        const userData = JSON.stringify({ name, phoneNo, email, password });
        
        // Send the request to the backend
        const xhr = new XMLHttpRequest();
        xhr.open("POST", "http://localhost:8081/api/userDetails", true);
        xhr.setRequestHeader("Content-Type", "application/json");
    
        xhr.onreadystatechange = function () {
            console.log("ReadyState:", xhr.readyState, "Status:", xhr.status);
        
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 201) {
                    // Parse the response JSON
                    const newUser = JSON.parse(xhr.responseText);
                    console.log("User added successfully:", newUser);
    
                    // Dynamically add the new row to the table
                    const tableBody = document.getElementById("userTableBody");
                    const newRow = document.createElement("tr");
                    newRow.id = `row-${newUser.userId}`;
                    newRow.innerHTML = `
                        <td>${newUser.userId}</td>
                        <td>${newUser.name}</td>
                        <td>${newUser.phoneNo}</td>
                        <td>${newUser.email}</td>
                        <td>
                            <button class="editRow" onclick="addEdit(${newUser.userId})">Edit</button>
                            <button class="deleteRow" style="background-color:red;color:white;" onclick="addDelete(${newUser.userId})">Delete</button>
                        </td>
                    `;
                    tableBody.appendChild(newRow);
    
                    // Reset input fields
                    document.getElementById("nameInput").value = "";
                    document.getElementById("phoneInput").value = "";
                    document.getElementById("emailInput").value = "";
                    document.getElementById("passwordInput").value = "";
    
                    alert("User added successfully!");
                } else {
                    console.error("Failed to add user:", xhr.statusText);
                    alert("Error adding user. Please try again.");
                }
            }
        };
        
        xhr.onerror = function () {
            console.error("Network error occurred while adding user.");
            alert("Network error. Please check your server.");
        };
    
        xhr.send(userData);
    }
    
    // function create(){
    //     document.querySelector(".create_form").Style.display="block";
    //     document.querySelector(".add_div").Style.display="none";
    // }

     // Function to handle delete action
function addDelete(userId) {

    // Make an AJAX request to delete the item
    fetch(`http://localhost:8081/api/userDetails/${userId}`, {
      method: "DELETE",
      headers: {
          "Content-Type": "application/json",
      },
  })
      .then((response) => {
          if (response.ok) {
              // Remove the row from the table
              const row = document.getElementById(`row-${userId}`);
              if (row) {
                  row.remove();
                  console.log(`Item with ID ${userId} deleted successfully.`);
              }
          } else {
              console.error(`Failed to delete item with ID ${userId}.`);
          }
      })
      .catch((error) => {
          console.error("Error:", error);
      });
}
