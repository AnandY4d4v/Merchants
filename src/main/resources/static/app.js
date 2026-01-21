const BASE_URL = "https://merchants-ggd5.onrender.com/merchants";
let selectedMerchantId = null;

document.addEventListener("DOMContentLoaded", loadMerchants);

// LOAD ALL
function loadMerchants() {
    fetch(BASE_URL)
        .then(res => res.json())
        .then(data => {
            const table = document.getElementById("merchantTable");
            table.innerHTML = "";

            data.forEach(m => {
                table.innerHTML += `
                    <tr>
                        <td>${m.merchantName}</td>
                        <td>${m.email}</td>
                        <td>${m.status}</td>
                        <td>
                            <button class="action-btn edit" onclick="editMerchant(${m.id})">✏️</button>
                            <button class="action-btn delete" onclick="deleteMerchant(${m.id})">🗑️</button>
                        </td>
                    </tr>
                `;
            });
        });
}


function addMerchant() {

    const nameInput = document.getElementById("name");
    const emailInput = document.getElementById("email");


    if (!nameInput.checkValidity() || !emailInput.checkValidity()) {
        nameInput.reportValidity();
        emailInput.reportValidity();
        return;
    }

    const merchant = {
        merchantName: nameInput.value,
        email: emailInput.value,
        status: document.getElementById("status").checked
    };

    fetch(BASE_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(merchant)
    })
    .then(() => {
        resetForm();
        loadMerchants();
    });
}



function editMerchant(id) {
    fetch(`${BASE_URL}/${id}`)
        .then(res => res.json())
        .then(m => {
            document.getElementById("name").value = m.merchantName;
            document.getElementById("email").value = m.email;
            document.getElementById("status").checked = m.status;

            selectedMerchantId = id;

            document.getElementById("addBtn").style.display = "none";
            document.getElementById("updateBtn").style.display = "block";
        });
}


function updateMerchant() {

    const nameInput = document.getElementById("name");
    const emailInput = document.getElementById("email");

    if (!nameInput.checkValidity() || !emailInput.checkValidity()) {
        nameInput.reportValidity();
        emailInput.reportValidity();
        return;
    }

    const merchant = {
        merchantName: nameInput.value,
        email: emailInput.value,
        status: document.getElementById("status").checked
    };

    fetch(`${BASE_URL}/${selectedMerchantId}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(merchant)
    })
    .then(() => {
        resetForm();
        loadMerchants();
    });
}


function deleteMerchant(id) {
    if (!confirm("Are you sure?")) return;

    fetch(`${BASE_URL}/${id}`, {
        method: "DELETE"
    })
    .then(() => {
        resetForm();
        loadMerchants();
    });
}


// RESET FORM
function resetForm() {
    document.getElementById("name").value = "";
    document.getElementById("email").value = "";
    document.getElementById("status").checked = false;

    document.getElementById("addBtn").style.display = "block";
    document.getElementById("updateBtn").style.display = "none";

    selectedMerchantId = null;
}
