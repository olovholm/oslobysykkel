



function appendStationElement(parentElement, stasjon){
    let tableRow = document.createElement("tr");
    let tableHeader = document.createElement("th");
    tableHeader.innerHTML = stasjon.stasjonsId;
    let stasjonId = document.createElement("td");
    stasjonId.innerHTML = stasjon.navn;
    let kapasitet = document.createElement("td");
    kapasitet.innerHTML = stasjon.kapasitet;
    let antallLedigeLåser = document.createElement("td");
    antallLedigeLåser.innerHTML = stasjon.antallLedigeLåser;
    let antallLedigeSykler = document.createElement("td");
    antallLedigeSykler.innerHTML = stasjon.antallLedigeSykler;

    tableRow.append(tableHeader,stasjonId,kapasitet,antallLedigeLåser,antallLedigeSykler);
    parentElement.append(tableRow);
}

lastStasjoner =  $.ajax({
    type: 'GET',
    url: 'http://localhost:8080/api/',
    dataType: 'json',
    success: function (data) {
        $.each(data.stasjoner, function(index, element) {
            appendStationElement($("#stasjoner"),element);
        });
    }
});



$( document ).ready(function() {
    console.log( "ready!" );
    lastStasjoner;
});


