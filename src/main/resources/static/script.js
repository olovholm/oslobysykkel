var minPosisjon;


function appendStationElement(parentElement, stasjon) {
    let tableRow = document.createElement("tr");
    let stasjonsNavn = document.createElement("th");
    stasjonsNavn.innerHTML = `<a href=http://maps.google.com/?q=${encodeURI(stasjon.adresse)}>${stasjon.navn}</a>`
    let avstand = document.createElement("td");
    avstand.innerHTML = stasjon.avstand;
    let antallLedigeLåser = document.createElement("td");
    antallLedigeLåser.innerHTML = stasjon.antallLedigeLåser;
    let antallLedigeSykler = document.createElement("td");
    antallLedigeSykler.innerHTML = stasjon.antallLedigeSykler;

    tableRow.append(stasjonsNavn, avstand, antallLedigeLåser, antallLedigeSykler);
    parentElement.append(tableRow);
}

var lastStasjonerUtenPosisjon = function () {
    $.ajax({
        type: 'GET',
        url: encodeURI(`http://localhost:8080/api/`),
        dataType: 'json',
        success: function (data) {
            $.each(data.stasjoner, function (index, element) {
                appendStationElement($("#stasjoner"), element);
            });
        }

    })
};

var lastStasjonerMedPosisjon = function (minPosisjon) {
    console.log("Last med posisjon");
    $.ajax({
        type: 'GET',
        url: encodeURI(`http://localhost:8080/api/?lon=${minPosisjon.lon}&lat=${minPosisjon.lat}&antall=20`),
        dataType: 'json',
        success: function (data) {
            $.each(data.stasjoner, function (index, element) {
                appendStationElement($("#stasjoner"), element);
            });
        }
    })
};


$(document).ready(function () {
    console.log("ready!");

    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
            function (position) {
                minPosisjon = {
                    lat: "" + position.coords.latitude,
                    lon: "" + position.coords.longitude
                };
                lastStasjonerMedPosisjon(minPosisjon);
            }, function () {
                console.log("Kunne ikke laste koordinater for posisjon");
                lastStasjonerUtenPosisjon();

            }
        );
    }
});


