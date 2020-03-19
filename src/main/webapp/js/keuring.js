let url = "http://localhost:8080/boot_keuring_systeem/api/keuring";

function getAll(){
    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let eigenaren = JSON.parse(this.response);
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
    };

    xhttp.open('GET', url + '/get-all', true);
    xhttp.send();
}