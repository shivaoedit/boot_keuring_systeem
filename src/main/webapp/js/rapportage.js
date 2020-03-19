let url = "http://localhost:8080/boot_keuring_systeem/api/rapportage";

function kwartaalRapportage(){
    let jaar = document.getElementById('jaartal').value;
    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let keuringen = JSON.parse(this.response);
            let tableBody = document.getElementById('kwartaalTable');
            let bodyContent = '';
            tableBody.innerHTML = bodyContent;

            keuringen.forEach( item => {
                bodyContent += `<tr>
                        <td>` + item.type.toUpperCase() + `</td>
                        <td>` + item.eersteKwartaal + `</td>
                        <td>` + item.tweedeKwartaal + `</td>
                        <td>` + item.derdeKwartaal + `</td>
                        <td>` + item.vierdeKwartaal + `</td>
                    </tr>`;
            });

            tableBody.innerHTML = bodyContent;
        }
    };

    xhttp.open('POST', url + '/kwartaal-rapportage', true);
    xhttp.send(jaar);
}

function aantalKeuringen(){
    let xhttp = new XMLHttpRequest();
    let periode = {
        start: document.getElementById('start').value.toString(),
        end: document.getElementById('eind').value.toString()
    };

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let keuringen = JSON.parse(this.response);
            let aantal = document.getElementById('aantal');
            aantal.innerHTML = keuringen.length;
        }
    };

    xhttp.open('POST', url + '/aantal-keuringen-rapportage', true);
    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(periode));
}

function meesteKeuringen(){
    let xhttp = new XMLHttpRequest();
    let periode = {
        start: document.getElementById('start2').value,
        end: document.getElementById('eind2').value
    };

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let keuring = JSON.parse(this.response);
            let tableBody = document.getElementById('meesteKeuringen');
            tableBody.innerHTML = `<tr>
                <td>` + keuring.naam + `</td>
                <td>` + keuring.voorNaam + `</td>
                <td>` + keuring.aantalKeuringen + `</td>
            </tr>`;
        }
    };

    xhttp.open('POST', url + '/meeste-keuringen-rapportage', true);
    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(periode));
}