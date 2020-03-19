let url = "http://localhost:8080/boot_keuring_systeem/api/eigenaar";

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
    let eigenaar = {
        naam: document.getElementById('naam').value,
        voorNaam: document.getElementById('voorNaam').value,
        geboorteDatumString: document.getElementById('geboorteDatum').value,
        paspoort: {
            paspoortNummer: document.getElementById('paspoortNummer').value,
            landCode: document.getElementById('landCode').value
        },
    };

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4) {
            window.location = 'index.html'
        }
    };

    xhttp.open('POST', url + '/save', true);
    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(eigenaar));
}

function remove(id){
    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            if(this.response === 'false'){
                alert('Eigenaar wordt al gebruikt in het systeem. Niet toegestaan om eigenaar te verwijderen.');
                return;
            }

            getAll();
        }
    };

    xhttp.open("DELETE", url+'/remove/' + id, true);
    xhttp.send();
}

function getOne(){
    let id = new URL(window.location.href).searchParams.get('id');
    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let eigenaar = JSON.parse(this.response);
            let naam = document.getElementById('naam');
            let voorNaam = document.getElementById('voorNaam');
            let geboorteDatum = document.getElementById('geboorteDatum');
            let paspoortNummer = document.getElementById('paspoortNummer');
            let landCode = document.getElementById('landCode');

            naam.value = eigenaar.naam;
            voorNaam.value = eigenaar.voorNaam;
            geboorteDatum.value = eigenaar.geboorteDatum;
            paspoortNummer.value = eigenaar.paspoort.paspoortNummer;
            landCode.value = eigenaar.paspoort.landCode;

            naam.disabled = true;
            voorNaam.disabled = true;
            geboorteDatum.disabled = true;
            paspoortNummer.disabled = true;
            landCode.disabled = true;

            let saveButton = document.getElementById('saveButton')
            saveButton.innerHTML = '<button class="btn btn-warning waves-effect" onclick="goback()">Go back</button>'
        }
    };

    xhttp.open('GET', url + '/get-one/' + id, true);
    xhttp.send();
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

function getOneData(){
    if(window.location.href.includes('?id=')){
        getOne();
    }
}

function goback(){
    window.location = 'index.html';
}

function fillTable(response){
    let eigenaren = JSON.parse(response);
    let tableBody = document.getElementById('eigenaarTable');
    let bodyContent = '';
    tableBody.innerHTML = bodyContent;

    eigenaren.forEach( item => {
        bodyContent += `<tr>
                        <td>` + item.naam + `</td>
                        <td>` + item.voorNaam + `</td>
                        <td>` +
                            item.geboorteDatum.dayOfMonth + `-` + item.geboorteDatum.month + `-` + item.geboorteDatum.year +
                        `</td>
                        <td>
                            <a href="edit.html?id=` + item.id + `">
                                <button type="button" class="btn btn-icon command-edit waves-effect waves-circle">
                                    <span class="zmdi zmdi-eye"></span>
                                </button>
                            </a>
                            <button type="button" class="btn btn-icon command-delete waves-effect waves-circle" onclick="remove(` + item.id + `)">
                                <span class="zmdi zmdi-delete"></span>
                            </button>
                        </td>   
                    </tr>`;
    });

    tableBody.innerHTML = bodyContent;
}