# Bysykkel

## Hva gjør applikasjonen
Applikasjonen henter åpne sanntidsdata fra Oslo Bysykkel og tilgjengeliggjør disse. 
Applikasjonen laster stasjonsdata hvert 15 minutt, og oppdaterer med stasjonsstatus (ledige låser og sykler) hver 30. sekund. 



## Oppstart i Docker
 - Forutsetninger for å bygge: Installert følgende versjoner eller høyere: Java 12, Maven 3.6 og Docker 19. 
 - Klon prosjektet til en lokal mappe med git
 - Kjør 'mvn clean install' i prosjektets rotmappe for å bygge prosjektet
 - Kjør deretter 'docker build -t local/oslobysykkel:latest .' i samme mappe for å opprette docker-image med applikasjonen
 - Start containeren med 'docker run -p 8080:8080 local/oslobysykkel:latest'
 - Applikasjonen er da tilgjengelig på localhost:8080

## Lisenser og gjenbruk av kode
Dataene som benyttes er lisensiert under [NLOD 2.0](https://oslobysykkel.no/apne-data/sanntid) og ligger tilgjengelig på [Oslo bysykkels sanntidsoversikt](https://oslobysykkel.no/apne-data/sanntid). 

 

