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

            if(window.location.href.includes('?id=')){
                getOne();
            }

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

function getOne(){
    let id = new URL(window.location.href).searchParams.get('id');
    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            document.getElementById("additionalInfo").style.display = "block";
            getKeuringBewijzen();

            let boot = JSON.parse(this.response);
            let eigenaar = document.getElementById('eigenaar');
            let type = document.getElementById('type');
            let shipCode = document.getElementById('shipCode');
            let bootNaam = document.getElementById('bootNaam');
            let bouwjaar = document.getElementById('bouwjaar');
            let kleur = document.getElementById('kleur');
            let lengte = document.getElementById('lengte');
            let breedte = document.getElementById('breedte');
            let mast = document.getElementById('mast');
            let spanten = document.getElementById('spanten');
            let motorMerk = document.getElementById('motorMerk');
            let brandstof = document.getElementById('brandstof');

            eigenaar.value = boot.eigenaar.id;
            type.value = boot.type.id;
            shipCode.value = boot.shipCode;
            bootNaam.value = boot.bootNaam;
            bouwjaar.value = boot.bouwjaar;
            kleur.value = boot.kleur;
            lengte.value = boot.lengte;
            breedte.value = boot.breedte;
            mast.value = boot.mast;
            spanten.value = boot.spanten;
            motorMerk.value = boot.motorMerk;
            brandstof.value = boot.brandstof;

            eigenaar.disabled = true;
            type.disabled = true;
            shipCode.disabled = true;
            bootNaam.disabled = true;
            bouwjaar.disabled = true;
            kleur.disabled = true;
            lengte.disabled = true;
            breedte.disabled = true;
            mast.disabled = true;
            spanten.disabled = true;
            motorMerk.disabled = true;
            brandstof.disabled = true;

            let saveButton = document.getElementById('saveButton')
            saveButton.innerHTML = '<button class="btn btn-warning waves-effect" onclick="goback()">Terug</button>'
        }
    };

    xhttp.open('GET', url + '/get-one/' + id, true);
    xhttp.send();
}

function getKeuringBewijzen(){
    let id = new URL(window.location.href).searchParams.get('id');
    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            getEigenschappen();

            let bewijzen = JSON.parse(this.response);
            let tableBody = document.getElementById('keuringsBewijsTable');
            let bodyContent = '';

            bewijzen.forEach( item => {
                bodyContent += `<tr>
                        <td>` + item.id + `</td>
                        <td>` + item.keuringsDatum + `</td>
                        <td>` + item.vervalDatum + `</td>
                    </tr>`;
            });

            tableBody.innerHTML = bodyContent;
        }
    };

    xhttp.open('GET', url + '/get-keuring-bewijzen/' + id, true);
    xhttp.send();
}

function getEigenschappen(){
    let id = new URL(window.location.href).searchParams.get('id');
    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let eigenschappen = JSON.parse(this.response);
            let tableBody = document.getElementById('eigenschapTable');
            let bodyContent = '';

            eigenschappen.forEach( item => {
                bodyContent += `<tr>
                        <td>` + item.eigenschap + `</td>
                    </tr>`;
            });

            tableBody.innerHTML = bodyContent;
        }
    };

    xhttp.open('GET', url + '/get-boot-eigenschappen/' + id, true);
    xhttp.send();
}

function goback(){
    window.location = 'index.html';
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
            window.location = 'index.html';
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
                        <td>
                            <a href="edit.html?id=` + item.id + `">
                                <button type="button" class="btn btn-icon command-edit waves-effect waves-circle">
                                    <span class="zmdi zmdi-eye"></span>
                                </button>
                            </a>
                        </td>
                    </tr>`;
    });

    tableBody.innerHTML = bodyContent;
}