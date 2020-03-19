let authUrl = "http://localhost:8080/boot_keuring_systeem/api/auth";
const logouthref = document.getElementById("logouthref");

if(logouthref){
    logouthref.addEventListener("click", logout);
}

function validateCredentials(){
    let gebruikersNaam = document.getElementById('gebruikersNaam').value;
    let wachtwoord = document.getElementById('wachtwoord').value;

    document.getElementById('usernameFormGroup').classList.remove('has-error');
    document.getElementById('wachtwoordFormGroup').classList.remove('has-error');

    document.getElementById("loginFeedback").style.display = "none";
    document.getElementById("gebruikersFeedback").style.display = "none";
    document.getElementById("wachtwoordFeedback").style.display = "none";

    if(gebruikersNaam.trim() === ""){
        document.getElementById("gebruikersFeedback").style.display = "block";
        document.getElementById('usernameFormGroup').classList.add('has-error');
    }

    if(wachtwoord.trim() === ""){
        document.getElementById("wachtwoordFeedback").style.display = "block";
        document.getElementById('wachtwoordFormGroup').classList.add('has-error');
    }

    if(gebruikersNaam.trim() !== "" && wachtwoord.trim() !== ""){
        authentication();
    }
}

function authentication() {
    let xhttp = new XMLHttpRequest();
    let credentials = {
        gebruikersNaam: document.getElementById('gebruikersNaam').value,
        wachtwoord: document.getElementById('wachtwoord').value
    };

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.response);

            if(response >= 0){
                if(response === 3){
                    window.location = "/boot_keuring_systeem/views/admin/keuringen/index.html";
                    return;
                }else{
                    window.location = "/boot_keuring_systeem/views/controleur/keuringen/index.html";
                    return;
                }
            }

            document.getElementById('usernameFormGroup').classList.add('has-error');
            document.getElementById('wachtwoordFormGroup').classList.add('has-error');
            document.getElementById("loginFeedback").style.display = "block";
        }
    };

    xhttp.open('POST', authUrl + '/login', true);
    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(credentials));
}

function logout(){
    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            window.location = "/boot_keuring_systeem";
        }
    };

    xhttp.open('GET', authUrl + '/logout', true);
    xhttp.send();
}