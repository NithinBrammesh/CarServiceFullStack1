
console.log("Script called");

function getCarDetails() {
    return new Promise((resolve, reject) => {
        var xhr = new XMLHttpRequest();
        var url = "http://localhost:9002/api/cars";
        xhr.open("GET", url, true); // Use GET if your endpoint expects it
        xhr.setRequestHeader("Content-Type", "application/json");
        
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    resolve(JSON.parse(xhr.responseText));
                } else {
                    reject("Error: " + xhr.status + " - " + xhr.statusText);
                }
            }
        }
        
        xhr.send();
    });
}

function displayCarCategories(categories) {
    if (!Array.isArray(categories)) {
        console.error("Invalid data format:", categories);
        document.getElementById("carCategoryList").innerHTML = "Invalid data received!";
        return;
    }

    // Create the table header
    let html = `
        <table border='1' style="border-collapse: collapse;">
        <thead>
            <tr>
                <th>ID</th>
                <th>Car Category</th>
                <th>Price</th>
                <th>Action</th>
            </tr>
        </thead>
   <tbody id="carTableBody">
    `;

    // Iterate through the categories and add rows
    categories.forEach(category => {
        console.log(category);
        const id = category.id || "N/A";
        const carType = category.carType || "N/A";
        const price = category.price || "N/A";

         html += `
    <tr id="row-${id}">
         <td>${id}</td>
         <td><span class="carType">${carType}</span></td>
         <td><span class="price">${price}</span></td>
         <td><button style ="width:60px; background-color: white;" class="editRow" onclick="addEdit(${id})">Edit</button>
         <button  style="width: 60px; background-color:red; color: white;" class="deleteRow" onclick="addDelete(${id})">delete</button></td></tr>`;
     });
     html += "</tbody></table>";

    // Inject the table into the DOM
    document.getElementById("carCategoryList").innerHTML = html;
}


function addEdit(id){
let row = document.getElementById(`row-${id}`);
let carType = row.querySelector(".carType").innerText;
let price = row.querySelector(".price").innerText;

row.innerHTML = `
<td>${id}</td>
<td><input type="text" style="width: 100px;height: 30px; font-size:18px;" id="editCarType-${id}" value="${carType}" /></td>
<td><input type="text" style="width: 100px;height: 30px; font-size:18px;"  id="editPrice-${id}" value="${price}" 
oninput="validateNumberInput(this)"/></td>
<td>
    <button style ="width:70px; background-color: white;" class="editRow" onclick="saveRow(${id})">Save</button>
    <button style ="width:70px; background-color: white;" class="editRow" onclick="cancelEdit(${id}, '${carType}', ${price})">Cancel</button>
</td>
`;

console.log(row.innerHTML);

}

function validateNumberInput(num){

    const Values = num.value;

    if(!Values.match(/^\d*$/)){
        alert("Invalid input! please enter numerical values");
        console.log("Enter a valid number");
    }
    num.value = Values.replace(/[^\d]/g, "");  // Remove non-numeric characters
}

function cancelEdit(id, originalCarType, originalPrice) {
    const row = document.getElementById(`row-${id}`);
    
    if (!row) {
        console.log(`Row with id row-${id} not found`);
    }

    // Restore the original values passed as arguments
    row.innerHTML = `
    <td>${id}</td>
    <td class="carType">${originalCarType}</td>
    <td class="price">${originalPrice}</td>
    <td>
        <button style="width:70px; background-color: white;" class="editRow" onclick="addEdit(${id})">Edit</button>
          <button  style="width: 60px; background-color:red; color: white;" class="deleteRow" onclick="addDelete(${id})">delete</button></td>
    </td>
    `;
  
    console.log("Edit canceled");
}

function saveRow(id) {
    let carType = document.getElementById(`editCarType-${id}`).value.trim();
    let price = document.getElementById(`editPrice-${id}`).value.trim();


    const data = JSON.stringify({ id, carType, price });


    const xhr = new XMLHttpRequest();
    xhr.open("PUT", `http://localhost:9002/api/cars/${id}`, true); // Assuming PUT endpoint
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                alert("Car updated successfully!");
                const response = JSON.parse(xhr.responseText);

                // Update the row in the table
                const row = document.getElementById(`row-${id}`);
                //addEdit() on edit button
                row.innerHTML = `
                    <td>${response.id}</td>
                    <td><span class="carType">${response.carType}</span></td>
                    <td><span class="price">${response.price}</span></td>
                    <td>
                        <button style ="width:60px; background-color: white;" class="editRow" onclick="addEdit(${id})">Edit</button> 
                        <button  style="width: 60px; background-color:red; color: white;" class="deleteRow" onclick="deleteRow(${response.id})" style="background-color: red; color: white;">Delete</button>
                    </td>
                `;
            } 
        
            else {
                console.error("Error updating car:", xhr.status, xhr.statusText, xhr.responseText);
                alert("Failed to update car. Please try again.");
            }
        }
    };

    xhr.send(data);
}


// Fetch and display data on page load
window.onload = function () {
    console.log("windows loaded!")
    getCarDetails()
        .then(data => {
            console.log("Car categories fetched successfully:", data);
            displayCarCategories(data);
        })
        .catch(error => {
            console.error("Error fetching car categories:", error);
            document.getElementById("carCategoryList").innerHTML = `<p>${error}</p>`;
        });
    };

    // posting new value to table
    function addCar(event) {
        event.preventDefault(); // Prevent form submission from reloading the page
    
        // Get values from the input fields
        let row = document.getElementById(`row-${id}`);
        let carType = row.querySelector(".carType").value; 
        let price = row.querySelector(".price").value;
    
        if (!carType || !price) {
            alert("Please fill in all fields!");
            return;
        }
    
        // Log input values
        console.log("Adding car:", { carType, price });
    
        // Prepare request payload
        const carData = JSON.stringify({ carType, price });
    
        // Send the request to the backend
        const xhr = new XMLHttpRequest();
        xhr.open("POST", "http://localhost:9002/api/cars", true);
        xhr.setRequestHeader("Content-Type", "application/json");
    
        xhr.onreadystatechange = function () {
            console.log("ReadyState:", xhr.readyState, "Status:", xhr.status);
    
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 201) {
                    // Parse the response JSON
                    let newCar = JSON.parse(xhr.responseText);
    
                    console.log("Car added successfully:", newCar);
    
                    // Dynamically add the new row to the table    onclick="addEdit(${newCar.id})"
                    const tableBody = document.getElementById("carTableBody");
                    const newRow = document.createElement("tr");
                    newRow.id = `row-${newCar.id}`;
                    newRow.innerHTML = `
                        <td>${newCar.id}</td>
                        <td><span class="carType">${newCar.carType}</span></td>
                        <td><span class="price">${newCar.price}</span></td>

                        <td>
                        <button style="width: 60px; background-color: white;" class="editRow" onclick="addEdit(${newCar.id})">Edit</button>
                        <button style="width: 60px; background-color: red; color: white;" class="deleteRow" onclick="addDelete(${newCar.id})">Delete</button>
                        </td>
                    `;
                    tableBody.appendChild(newRow);
    
                    // Reset input fields
                    carNameInput.value = "";
                    carPriceInput.value = "";

                    // alert("Car added successfully!");
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
    

    function create(){
        document.querySelector(".create_form").Style.display="block";
        document.querySelector(".add_div").Style.display="none";
    }

   // Function to handle delete action
function addDelete(id) {

      // Make an AJAX request to delete the item
      fetch(`http://localhost:9002/api/cars/${id}`, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then((response) => {
            if (response.ok) {
                // Remove the row from the table
                const row = document.getElementById(`row-${id}`);
                if (row) {
                    row.remove();
                    console.log(`Item with ID ${id} deleted successfully.`);
                }
            } else {
                console.error(`Failed to delete item with ID ${id}.`);
            }
        })
        .catch((error) => {
            console.error("Error:", error);
        });
}
