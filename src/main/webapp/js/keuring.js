let url = "http://localhost:8080/boot_keuring_systeem/api/keuring";
let bootUrl = "http://localhost:8080/boot_keuring_systeem/api/boot";
let allBoten = [];

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

function save(){
    let xhttp = new XMLHttpRequest();
    let boot = document.getElementById('boot').value;

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4) {
            if(this.status === 200){
                let controleur = JSON.parse(this.response);
                alert('Keuring toegewezen aan ' + controleur.naam + ' ' + controleur.voorNaam);
            }else if(this.status === 204){
                alert('Keuring niet toegewezen aan een controleur');
            }

            window.location = 'index.html';
        }
    };

    xhttp.open('POST', url + '/assign-keuring', true);
    xhttp.send(boot);
}

function getBootData(){
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let boten = JSON.parse(this.response);
            allBoten = boten;
            let boot = document.getElementById('boot');

            boten.forEach( item => {
                let opt = document.createElement("option");
                opt.innerHTML = item.bootNaam;
                opt.value = item.id;

                boot.appendChild(opt);
            });
        }
    };

    xhttp.open('GET', bootUrl + '/get-all', true);
    xhttp.send();
}

function changeEigenaar(){
    let boot = document.getElementById('boot').value;
    allBoten.forEach(function (item, index) {
        if (item.id === parseInt(boot)) {
            document.getElementById('eigenaar').value = item.eigenaar.naam + ' ' + item.eigenaar.voorNaam;
        }
    });
}

function search(){
    let keyword = document.getElementById('keyword').value;
    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fillTable(this.response);
        }
    };

    xhttp.open('POST', url + '/search', true);
    xhttp.send(keyword);
}

function fillTable(response){
    let eigenaren = JSON.parse(response);
    let tableBody = document.getElementById('keuringTable');
    let bodyContent = '';

    eigenaren.forEach( item => {
        bodyContent += `<tr>
            <td>` +
                item.keuringsDatum.dayOfMonth + `-` + item.keuringsDatum.month + `-` + item.keuringsDatum.year +
            `</td>
            <td>` +
                item.controleur.naam + ` ` + item.controleur.voorNaam +
            `</td>
            <td>` + item.boot.shipCode + `</td>
            <td>` +
                item.boot.eigenaar.naam + ` ` + item.boot.eigenaar.voorNaam +
            `</td>
            <td>` + item.status + `</td>
        </tr>`;
    });

    tableBody.innerHTML = bodyContent;
}