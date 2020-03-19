package sr.unasat.boot_keuring_systeem.dao.specifications;

import sr.unasat.boot_keuring_systeem.dao.standards.RapportageDao;
import sr.unasat.boot_keuring_systeem.entities.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RapportageDaoImp implements RapportageDao {
    private EntityManager entityManager;

    public RapportageDaoImp(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Keuring> aantalKeuringenPerPeriode(LocalDate startDatum, LocalDate endDatum){
        entityManager.getTransaction().begin();

        String jpql = "SELECT k FROM Keuring k WHERE k.keuringsDatum >= :startDatum AND k.keuringsDatum <= :endDatum";
        Query query = entityManager.createQuery(jpql, Keuring.class);
        query.setParameter("startDatum", startDatum);
        query.setParameter("endDatum", endDatum);

        List<Keuring> keuringList = query.getResultList();

        entityManager.getTransaction().commit();
        return keuringList;
    }

    @Override
    public Controleur meesteKeuringenControleur(LocalDate startDatum, LocalDate endDatum){
        entityManager.getTransaction().begin();

        String jpql = "SELECT k FROM Keuring k WHERE k.keuringsDatum >= :startDatum AND k.keuringsDatum <= :endDatum ORDER BY size(k.controleur.keuringList) DESC";
        Query query = entityManager.createQuery(jpql, Keuring.class);
        query.setParameter("startDatum", startDatum);
        query.setParameter("endDatum", endDatum);

        List<Keuring> keuringList = query.getResultList();

        entityManager.getTransaction().commit();

        if(keuringList.isEmpty()){
            return null;
        }

        return keuringList.get(0).getControleur();
    }

    @Override
    public List<List<Keuring>> uitEenZettingPerKwartaal(int jaar){
        LocalDate eersteKwartaal = LocalDate.of(jaar, 1, 1);
        LocalDate tweedeKwartaal = LocalDate.of(jaar, 5, 1);
        LocalDate derdeKwartaal = LocalDate.of(jaar, 9, 1);
        LocalDate einde = LocalDate.of(jaar, 12, 31);

        String jpql = "SELECT k FROM Keuring k WHERE k.keuringsDatum >= :startDatum AND k.keuringsDatum < :endDatum";
        Query eersteKwartaalQuery = entityManager.createQuery(jpql, Keuring.class);
        eersteKwartaalQuery.setParameter("startDatum", eersteKwartaal);
        eersteKwartaalQuery.setParameter("endDatum", tweedeKwartaal);

        List<Keuring> keuringListEersteKwartaal = eersteKwartaalQuery.getResultList();

        Query tweedeKwartaalQuery = entityManager.createQuery(jpql, Keuring.class);
        tweedeKwartaalQuery.setParameter("startDatum", tweedeKwartaal);
        tweedeKwartaalQuery.setParameter("endDatum", derdeKwartaal);

        List<Keuring> keuringListTweedeKwartaal = tweedeKwartaalQuery.getResultList();

        Query derdeKwartaalQuery = entityManager.createQuery(jpql, Keuring.class);
        derdeKwartaalQuery.setParameter("startDatum", derdeKwartaal);
        derdeKwartaalQuery.setParameter("endDatum", einde);

        List<Keuring> keuringListDerdeKwartaal = tweedeKwartaalQuery.getResultList();

        List<List<Keuring>> kwartaalList = new ArrayList<>();
        kwartaalList.add(keuringListEersteKwartaal);
        kwartaalList.add(keuringListTweedeKwartaal);
        kwartaalList.add(keuringListDerdeKwartaal);

        return kwartaalList;
    }
}
