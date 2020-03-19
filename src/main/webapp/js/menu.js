let baseUrl = "http://localhost:8080/boot_keuring_systeem/views/admin";

addActiveToMenuItem();
addLinkToMenuItem();

function addActiveToMenuItem(){
    document.getElementById('keuringMenu').classList.remove('active');
    document.getElementById('controleurMenu').classList.remove('active');
    document.getElementById('eigenaarMenu').classList.remove('active');
    document.getElementById('bootMenu').classList.remove('active');
    document.getElementById('rapportageMenu').classList.remove('active');

    if(window.location.href.indexOf('keuringen') > -1){
        document.getElementById('keuringMenu').classList.add('active');
    }else if(window.location.href.indexOf('controleurs') > -1){
        document.getElementById('controleurMenu').classList.add('active');
    }else if(window.location.href.indexOf('eigenaren') > -1){
        document.getElementById('eigenaarMenu').classList.add('active');
    }else if(window.location.href.indexOf('boten') > -1){
        document.getElementById('bootMenu').classList.add('active');
    }else if(window.location.href.indexOf('rapportages') > -1){
        document.getElementById('rapportageMenu').classList.add('active');
    }
}

function addLinkToMenuItem(){
    document.getElementById('keuringhref').href = baseUrl + '/keuringen/index.html';
    document.getElementById('controleurhref').href = baseUrl + '/controleurs/index.html';
    document.getElementById('eigenaarhref').href = baseUrl + '/eigenaren/index.html';
    document.getElementById('boothref').href = baseUrl + '/boten/index.html';
    document.getElementById('rapportagehref').href = baseUrl + '/rapportages/index.html';
}