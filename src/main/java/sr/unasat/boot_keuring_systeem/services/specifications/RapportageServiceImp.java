package sr.unasat.boot_keuring_systeem.services.specifications;

import sr.unasat.boot_keuring_systeem.dao.specifications.RapportageDaoImp;
import sr.unasat.boot_keuring_systeem.dao.standards.RapportageDao;
import sr.unasat.boot_keuring_systeem.dto.RapportageDto;
import sr.unasat.boot_keuring_systeem.entities.Controleur;
import sr.unasat.boot_keuring_systeem.entities.Keuring;
import sr.unasat.boot_keuring_systeem.services.standards.RapportageService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RapportageServiceImp implements RapportageService {
    private static RapportageService service;
    private RapportageDao dao;

    private RapportageServiceImp(RapportageDao dao) {
        this.dao = dao;
    }

    public static RapportageService getService(){
        if(service == null){
            service = new RapportageServiceImp(RapportageDaoImp.getDao());
        }

        return service;
    }

    @Override
    public List<Keuring> aantalKeuringenPerPeriode(LocalDate startDatum, LocalDate endDatum) {
        return dao.aantalKeuringenPerPeriode(startDatum, endDatum);
    }

    @Override
    public Controleur meesteKeuringenControleur(LocalDate startDatum, LocalDate endDatum) {
        return dao.meesteKeuringenControleur(startDatum, endDatum);
    }

    @Override
    public List<RapportageDto> uitEenZettingPerKwartaal(int jaar) {
        List<List<Keuring>> kwartaalEntityList = dao.uitEenZettingPerKwartaal(jaar);
        List<RapportageDto> rapportageDtoList = new ArrayList<>();

        rapportageDtoList.add(new RapportageDto());
        rapportageDtoList.add(new RapportageDto());
        rapportageDtoList.add(new RapportageDto());
        rapportageDtoList.add(new RapportageDto());
        rapportageDtoList.add(new RapportageDto());
        rapportageDtoList.add(new RapportageDto());
        rapportageDtoList.add(new RapportageDto());
        rapportageDtoList.add(new RapportageDto());

        rapportageDtoList.get(0).setType("waterscooter");
        rapportageDtoList.get(1).setType("passagiersboot");
        rapportageDtoList.get(2).setType("zeevissersvaartuig");
        rapportageDtoList.get(3).setType("veerboot");
        rapportageDtoList.get(4).setType("jacht");
        rapportageDtoList.get(5).setType("zeilboot");
        rapportageDtoList.get(6).setType("cruise");
        rapportageDtoList.get(7).setType("schip");

        int a,b,c,d,e,f,g,h;

        int kwartaal = 1;

        for (List<Keuring> keuringList : kwartaalEntityList) {
            a=b=c=d=e=f=g=h=0;

            for (Keuring keuring : keuringList) {
                if(keuring.getBoot().getType().getType().equalsIgnoreCase("waterscooter")){
                    a++;
                }else if(keuring.getBoot().getType().getType().equalsIgnoreCase("passagiersboot")){
                    b++;
                }else if(keuring.getBoot().getType().getType().equalsIgnoreCase("zeevissersvaartuig")){
                    c++;
                }else if(keuring.getBoot().getType().getType().equalsIgnoreCase("veerboot")){
                    d++;
                }else if(keuring.getBoot().getType().getType().equalsIgnoreCase("jacht")){
                    e++;
                }else if(keuring.getBoot().getType().getType().equalsIgnoreCase("zeilboot")){
                    f++;
                }else if(keuring.getBoot().getType().getType().equalsIgnoreCase("cruise")){
                    g++;
                }else if(keuring.getBoot().getType().getType().equalsIgnoreCase("schip")){
                    h++;
                }
            }

            if(kwartaal == 1){
                rapportageDtoList.get(0).setEersteKwartaal(a);
                rapportageDtoList.get(1).setEersteKwartaal(b);
                rapportageDtoList.get(2).setEersteKwartaal(c);
                rapportageDtoList.get(3).setEersteKwartaal(d);
                rapportageDtoList.get(4).setEersteKwartaal(e);
                rapportageDtoList.get(5).setEersteKwartaal(f);
                rapportageDtoList.get(6).setEersteKwartaal(g);
                rapportageDtoList.get(7).setEersteKwartaal(h);
            } else if(kwartaal == 2){
                rapportageDtoList.get(0).setTweedeKwartaal(a);
                rapportageDtoList.get(1).setTweedeKwartaal(b);
                rapportageDtoList.get(2).setTweedeKwartaal(c);
                rapportageDtoList.get(3).setTweedeKwartaal(d);
                rapportageDtoList.get(4).setTweedeKwartaal(e);
                rapportageDtoList.get(5).setTweedeKwartaal(f);
                rapportageDtoList.get(6).setTweedeKwartaal(g);
                rapportageDtoList.get(7).setTweedeKwartaal(h);
            } else if(kwartaal == 3){
                rapportageDtoList.get(0).setDerdeKwartaal(a);
                rapportageDtoList.get(1).setDerdeKwartaal(b);
                rapportageDtoList.get(2).setDerdeKwartaal(c);
                rapportageDtoList.get(3).setDerdeKwartaal(d);
                rapportageDtoList.get(4).setDerdeKwartaal(e);
                rapportageDtoList.get(5).setDerdeKwartaal(f);
                rapportageDtoList.get(6).setDerdeKwartaal(g);
                rapportageDtoList.get(7).setDerdeKwartaal(h);
            } else if(kwartaal == 4){
                rapportageDtoList.get(0).setVierdeKwartaal(a);
                rapportageDtoList.get(1).setVierdeKwartaal(b);
                rapportageDtoList.get(2).setVierdeKwartaal(c);
                rapportageDtoList.get(3).setVierdeKwartaal(d);
                rapportageDtoList.get(4).setVierdeKwartaal(e);
                rapportageDtoList.get(5).setVierdeKwartaal(f);
                rapportageDtoList.get(6).setVierdeKwartaal(g);
                rapportageDtoList.get(7).setVierdeKwartaal(h);
            }

            kwartaal++;
        }

        return rapportageDtoList;
    }
}
