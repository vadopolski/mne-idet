const CLIENTS_NAME = 'clients';
var STATE = {
    [CLIENTS_NAME]: [],
    formName: 'form1',
};

function getDataFromLocalStorage(taskName) {
    var tmp = JSON.parse(localStorage.getItem(taskName));
    return tmp || [];
}

const initClientContainer = () => {
    var data = STATE[CLIENTS_NAME] = getDataFromLocalStorage(TASKS_NAME);
    var keysCount = data.length || 0;

    if(!keysCount) {
        coreInsertLiToList({task: "На сегодня новых клиентов нет."});
    } else {
        for(var i = 0; i < keysCount; i++) {
            coreInsertLiToList(data[i], i, true);
        }
    }
};

document.addEventListener("DOMContentLoaded", () => {
    initClientContainer();
});

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

function coreInsertLiToList(data, index, task = false) {

//    var list = document.getElementById('task_list');
//    var newListNode = document.createElement('li');
//    newListNode.classList.add('list-group-item');
    if (task) {
        newListNode.innerHTML = (data.check ? '<i class="text-danger fa fa-exclamation-triangle"></i> &nbsp ' : '') +
            data.task + '<br /><span class="text-muted"><small>' +
            data.reminder + '</small></span> ' +
            '<span data-id="' + index + '" class="delete_ico" onclick="deleteNode(event)"><i class="fa fa-times"></i></span>';
        newListNode.setAttribute('data-type', 'task');
    }
    else {
        newListNode.innerHTML = data.task;
        newListNode.setAttribute('data-type', 'empty');
    }
    list.appendChild(newListNode);
}

const coreInsertLiToList = (data, index, task = false) => {
    const list = document.querySelector('#clientList');
    const newListNode = document.createElement('li');
    newListNode.classList.add('list-group-item');
    // let emptyItem = findEmptyItemToList();
    //
    // if (emptyItem !== null){
    //     emptyAllList();
    // }

    if (task) {
        newListNode.innerHTML = (data.check ? '<span class="text-danger text-uppercase"> DANGER </span>>' : '')
            + data.clientIdVk + '<br / ><span class="text-muted"><small>' + data.clientAddingDate + '</span></small>' +
            '<span class="delete_ico" onclick="deleteNode(event)"><i class="fa fa-times"></i></span>>';

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

const getDataFromForm = () => {
    const clientIdVk = document.querySelector('#clientIdVk').value.trim();
    const clientAddingDate = document.querySelector('#clientAddingDate').value.trim();
    const check = document.querySelector('#isStatusVIP').checked;
    console.log(clientIdVk + ' ' + clientAddingDate + ' ' + check);
    const res = {clientIdVk, clientAddingDate, check};

    return res;
};


function insertItemToList() {
    const clientIdVk = document.querySelector('#clientIdVk').value.trim();
    const clientAddingDate = document.querySelector('#clientAddingDate').value.trim();
    const check = document.querySelector('#isStatusVIP').checked;
    var errCounter = 0;

    if( !clientIdVk.length ) {
//        printErrorHelper('taskData', "Это поле обязательно для ввода - введите имя клиента", true);
        errCounter++;
    }

    // var reminder = document.getElementById('reminderData').value;
    // reminder = reminder.trim();
    // if( !reminder.length ) {
    //     printErrorHelper('reminderData', "Дата задачи обязательна для ввода", true);
    //     errCounter++;
    // }

    if (errCounter) {
        return false;
    }
    console.log({ clientIdVk, clientAddingDate, check });
    STATE[CLIENTS_NAME].push({ clientIdVk, clientAddingDate, check });
    emptyAllList();
    return true;
}

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

const popupShow = () => {
    const popup = document.querySelector("#myPopup");
    popup.classList.toggle("show");
};