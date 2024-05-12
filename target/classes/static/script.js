let apiUrl = '/items';

async function getItems() {
    try {
        let response = await fetch(apiUrl);
        let data = await response.json();
        return data;
    } catch (error) {
        console.log(error);
    }
}

async function addItem() {
    let newItemName = document.getElementById("new_item_name").value;

    try {

        let response = await fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ name: newItemName })
        });

        if (!response.ok) {
            throw new Error(`Failed to add item with name=${newItemName}`);
        }

        getItems().then(data => {
            displayList(data);
        });

    } catch (error) {
        console.log(error);
    }
}

async function deleteItem(id) {
    try {
        let response = await fetch(apiUrl + '/' + encodeURIComponent(id), { method: 'DELETE' });

        if (!response.ok) {
            throw new Error(`Failed to delete item with id=${id}`);
        }

        getItems().then(data => {
            displayList(data);
        });

    } catch (error) {
        console.log(error);
    }
}

async function editItem(id) {
    try {
        let response = await fetch(apiUrl + '/' + encodeURIComponent(id), { method: 'PUT'});
        if (!response.ok) {
            throw new Error(`Failed to mark item with id=${id}`);
        }

        getItems().then(data => {
            displayList(data);
        });

    } catch (error) {
        console.log(error);
    }
}

function displayList(items) {
    let list = document.getElementById("list");
    list.innerHTML = "";

    for (let i = 0; i < items.length; i++) {
        let item = items[i];

        let li = document.createElement("li");

        let span = document.createElement("span");
        span.appendChild(document.createTextNode(item.name));
        li.appendChild(span);

        let deleteButton = document.createElement("button");
        deleteButton.appendChild(document.createTextNode("Delete"));
        deleteButton.onclick = function() {
            deleteItem(item.id);
        };
        li.appendChild(deleteButton);

        let checkbox = document.createElement("input");
        checkbox.type = "checkbox";

        checkbox.checked = item.isPurchased;
        checkbox.onclick = function() {
            editItem(item.id);
        };
        li.insertBefore(checkbox, li.firstChild);

        list.appendChild(li);
    }
}

getItems().then(data => {
    displayList(data);
});