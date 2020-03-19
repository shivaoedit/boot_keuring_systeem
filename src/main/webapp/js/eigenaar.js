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
                            <button type="button" class="btn btn-icon command-delete waves-effect waves-circle" onclick="remove(` + item.id + `)">
                                <span class="zmdi zmdi-delete"></span>
                            </button>
                        </td>   
                    </tr>`;
    });

    tableBody.innerHTML = bodyContent;
}