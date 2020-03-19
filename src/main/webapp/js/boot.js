let url = "http://localhost:8080/boot_keuring_systeem/api/boot";
let eigenaarUrl = "http://localhost:8080/boot_keuring_systeem/api/eigenaar";

function getAll(){
    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fillTable(this.response);
        }
    };

    xhttp.open('GET', url + '/get-all', true);
    xhttp.send();
}

function getEigenaarData(){
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            getTypeData();

            let eigenaren = JSON.parse(this.response);
            let eigenaar = document.getElementById('eigenaar');

            eigenaren.forEach( item => {
                let opt = document.createElement("option");
                opt.innerHTML = item.naam + ' ' + item.voorNaam;
                opt.value = item.id;

                eigenaar.appendChild(opt);
            });
        }
    };

    xhttp.open('GET', eigenaarUrl + '/get-all', true);
    xhttp.send();
}

function getTypeData(){
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let types = JSON.parse(this.response);
            let type = document.getElementById('type');

            types.forEach( item => {
                let opt = document.createElement("option");
                opt.innerHTML = item.type;
                opt.value = item.id;

                type.appendChild(opt);
            });
        }
    };

    xhttp.open('GET', url + '/get-all-types', true);
    xhttp.send();
}

function save(){
    let xhttp = new XMLHttpRequest();
    let boot = {
        eigenaar: {
            id: document.getElementById('eigenaar').value
        },
        type: {
            id: document.getElementById('type').value
        },
        shipCode: document.getElementById('shipCode').value,
        bootNaam: document.getElementById('bootNaam').value,
        bouwjaar: document.getElementById('bouwjaar').value,
        kleur: document.getElementById('kleur').value,
        lengte: document.getElementById('lengte').value,
        breedte: document.getElementById('breedte').value,
        mast: document.getElementById('mast').value,
        spanten: document.getElementById('spanten').value,
        motorMerk: document.getElementById('motorMerk').value,
        brandstof: document.getElementById('brandstof').value,
    };

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4) {
            window.location = 'index.html'
        }
    };

    xhttp.open('POST', url + '/save', true);
    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(boot));
}

function search(){
    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fillTable(this.response);
        }
    };

    let keyword = document.getElementById('search').value;
    xhttp.open('POST', url + '/search', true);
    xhttp.send(keyword);
}

function fillTable(response){
    let eigenaren = JSON.parse(response);
    let tableBody = document.getElementById('bootTable');
    let bodyContent = '';

    eigenaren.forEach( item => {
        bodyContent += `<tr>
                        <td>` + item.shipCode + `</td>
                        <td>` +
            item.eigenaar.naam + ` ` + item.eigenaar.voorNaam +
            `</td>
                        <td>` + item.type.type + `</td>
                        <td>` + item.bootNaam + `</td>
                        <td>` + item.bouwjaar + `</td>
                    </tr>`;
    });

    tableBody.innerHTML = bodyContent;
}