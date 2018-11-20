const deleteNode = (event) => {
    let tmp = event.target;
    let nodeToDelete = tmp.parentNode.parentNode;
    nodeToDelete.remove();

    const list = document.querySelector('#clientList').childNodes;
    if(!list.length){
        coreInsertLiToList({task: "Claims No"});
    }
};

const emptyAllList = () => {
    const list = document.querySelector('#clientList');

    while (list.firstChild) {
        list.removeChild(list.firstChild);
    }

    document.createElement('li');
};


const deleteAllNode = () => {
    const clientList = document.querySelector('#clientList');

    clientList.innerHTML = '';

    const emptyElement = document.createElement('li');
    emptyElement.classList.add('list-group-item');
    emptyElement.classList.add('disabled');

    emptyElement.innerHTML = 'No task';
    emptyElement.id = 'emptyElement';
    clientList.appendChild(emptyElement);

};

const claims = [];

const clearLeftForm = () => {
    document.querySelector('#leftForm').reset();
};


const handleClearAll = () => {
    emptyAllList();
    coreInsertLiToList({task: "На сегодня активных клаймов нет"});
};

const go = () => {
    const data = getDataFromForm();
    const insert = insertDataToList(data);
//    if( res )
};

const getDataFromForm = () => {
    const clientIdVk = document.querySelector('#clientIdVk').value.trim();
    const clientAddingDate = document.querySelector('#clientAddingDate').value.trim();
    const check = document.querySelector('#isStatusVIP').checked;
    console.log(clientIdVk + ' ' + clientAddingDate + ' ' + check);
    const res = {clientIdVk, clientAddingDate, check};

    return res;
};

document.addEventListener("DOMContentLoaded", function () {
   coreInsertLiToList({task: "На сегодня нет активных задач"})
});

const insertDataToList = (data) => {
    if (!data.clientIdVk.length || !data.clientAddingDate.length) {
        return -1;
    }

    coreInsertLiToList(data, true);

};

const findEmptyItemToList = () => {
    alert('test');
    const list = document.querySelector('#clientList').childNodes;

    for (let i = 0; i < list.length; i++){
        if(typeof list[i].getAttribute === "function" && list[i].getAttribute('data-type') == "empty"){
            return list[i];
        }
    }
    return null;
};

const coreInsertLiToList = (data, task = false) => {
    const list = document.querySelector('#clientList');
    const newListNode = document.createElement('li');
    newListNode.classList.add('list-group-item');
    let emptyItem = findEmptyItemToList();

    if (emptyItem !== null){
        emptyAllList();
    }

    if (task) {
        newListNode.innerHTML = (data.check ? '<span class="text-danger text-uppercase"> DANGER </span>>' : '')
            + data.clientIdVk + '<br / ><span class="text-muted"><small>' + data.clientAddingDate + '</span></small>' +
            '<span class="delete_ico" onclick="deleteNode(event)"><i class="fa fa-times"></i></span>>';
        list.appendChild(newListNode);

    } else {
        newListNode.innerHTML = data.task;
        newListNode.setAttribute('data-type', 'empty');

    }

    list.appendChild(newListNode);
};

const makeUL = (array) => {
    const list = document.createElement('ul');

    list.id = 'clientList';
    list.classList.add('list-group');

    for (let i = 0; i < array.length; i++) {
        const item = document.createElement('li');

        item.classList.add('list-group-item');
        item.appendChild(document.createTextNode(array[i].name + ' ' + array[i].client + ' ' + array[i].date));
        list.appendChild(item);
    }

    return list;
};

const saveData = () => {
    const name = document.querySelector("#claimData").value;
    const client = document.querySelector("#clientName").value;
    const date = document.querySelector("#clientAddingDate").value;

    const claim = {name: name, client: client, date: date};

    claims.push(claim);

    const element = document.querySelector('#clientList');
    const parent = element.parentNode;
    const newElement = makeUL(claims);

    parent.insertBefore(newElement, element);
    parent.removeChild(element);
};

function myFunction() {
    var popup = document.getElementById("myPopup");
    popup.classList.toggle("show");
}