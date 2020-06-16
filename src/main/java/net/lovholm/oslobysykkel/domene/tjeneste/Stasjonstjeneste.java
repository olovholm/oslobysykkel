package net.lovholm.oslobysykkel.domene.tjeneste;

import net.lovholm.oslobysykkel.domene.modell.Posisjon;
import net.lovholm.oslobysykkel.domene.modell.Stasjon;
import net.lovholm.oslobysykkel.domene.modell.Status;
import net.lovholm.oslobysykkel.domene.repository.Stasjonsrepository;
import net.lovholm.oslobysykkel.integrasjon.oslobysykkel.gbfs_modell.StationInformation;
import net.lovholm.oslobysykkel.integrasjon.oslobysykkel.gbfs_modell.StationStatus;
import net.lovholm.oslobysykkel.integrasjon.oslobysykkel.klient.GBFSLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Stasjonstjeneste {

    private final GBFSLoadService gbfsLoadService;
    private final Stasjonsrepository stasjonsrepository;

    public Stasjonstjeneste(@Autowired GBFSLoadService gbfsLoadService,
                            @Autowired Stasjonsrepository stasjonsrepository){
        this.gbfsLoadService = gbfsLoadService;
        this.stasjonsrepository = stasjonsrepository;
    }

    public void hentOgLagreAlleStasjoner(){
        var stasjoner = gbfsLoadService.getStationInformation().stream()
                .map(this::mapStasjonFraStationInformation).collect(Collectors.toList());
        stasjonsrepository.settStasjoner(stasjoner);
    }

    public List<Stasjon> hentAlleStasjoner(){
        return stasjonsrepository.getStasjonerCopy();
    }

    public List<Stasjon> hentNærmesteStasjoner(Posisjon posisjon) {
        List<Stasjon> stasjoner = stasjonsrepository.getStasjonerCopy();
        stasjoner.sort(Comparator.comparingDouble(p-> p.getPosisjon().distanseFra(posisjon)));
        return stasjoner;
    }

    public void oppdaterStasjonerMedStatusInformasjon(){
        List<StationStatus> stasjonstatuser = gbfsLoadService.getStationStatus();
        for(StationStatus stationStatus : stasjonstatuser){
            var stasjon = stasjonsrepository.getStasjonById(stationStatus.getStationId()).orElseGet(Stasjon::new);
            stasjon.setStatus(new Status(stationStatus.getNumBikesAvailable(),stationStatus.getNumDocksAvailable()));
        }
    }



    private Stasjon mapStasjonFraStationInformation(StationInformation stationInformation){
        return new Stasjon(
                stationInformation.getStationId(),
                stationInformation.getName(),
                stationInformation.getAddress(),
                new Posisjon(
                    stationInformation.getLat(),
                    stationInformation.getLon()
                        ),
                stationInformation.getCapacity()
        );
    }





}
