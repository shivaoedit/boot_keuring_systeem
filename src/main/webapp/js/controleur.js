let url = "http://localhost:8080/boot_keuring_systeem/api/controleur";

function getAll(){
    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let eigenaren = JSON.parse(this.response);
            let tableBody = document.getElementById('controleurTable');
            let bodyContent = '';

            eigenaren.forEach( item => {
                bodyContent += `<tr>
                        <td>` + item.controleurNummer.controleurNummer + `</td>
                        <td>` + item.gebruikersNaam + `</td>
                        <td>` + item.naam + `</td>
                        <td>` + item.voorNaam + `</td>
                        <td>` + item.rank.naam + `</td>
                    </tr>`;
            });

            tableBody.innerHTML = bodyContent;
        }
    };

    xhttp.open('GET', url + '/get-all', true);
    xhttp.send();
}

function getData(){
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let ranks = JSON.parse(this.response);
            let rank = document.getElementById('rank');

            ranks.forEach( item => {
                let opt = document.createElement("option");
                opt.innerHTML = item.naam;
                opt.value = item.id;

                rank.appendChild(opt);
            });
        }
    };

    xhttp.open('GET', url + '/get-all-ranks', true);
    xhttp.send();
}

function save(){
    let xhttp = new XMLHttpRequest();
    let controleur = {
        naam: document.getElementById('naam').value,
        voorNaam: document.getElementById('voorNaam').value,
        rank: {
            id: document.getElementById('rank').value
        },
        gebruikersNaam: document.getElementById('gebruikersNaam').value,
        wachtwoord: document.getElementById('wachtwoord').value,
    };

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4) {
            window.location = 'index.html'
        }
    };

    xhttp.open('POST', url + '/save', true);
    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(controleur));
}