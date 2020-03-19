package sr.unasat.boot_keuring_systeem.dao.specifications;

import sr.unasat.boot_keuring_systeem.config.JPAConfiguration;
import sr.unasat.boot_keuring_systeem.dao.standards.RapportageDao;
import sr.unasat.boot_keuring_systeem.entities.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

public class RapportageDaoImp implements RapportageDao {
    private EntityManager entityManager;
    private static RapportageDao dao;

    private RapportageDaoImp(){
        this.entityManager = JPAConfiguration.getEntityManager();
    }

    public static RapportageDao getDao(){
        if(dao == null){
            dao = new RapportageDaoImp();
        }

        return dao;
    }

    @Override
    public List<List<Keuring>> uitEenZettingPerKwartaal(int jaar){
        LocalDate eersteKwartaal = LocalDate.of(jaar, 1, 1);
        LocalDate tweedeKwartaal = LocalDate.of(jaar, 4, 1);
        LocalDate derdeKwartaal = LocalDate.of(jaar, 7, 1);
        LocalDate vierdeKwartaal = LocalDate.of(jaar, 10, 1);
        LocalDate einde = LocalDate.of(jaar, 12, 31);

        // eerste kwartaal
        String jpql = "SELECT k FROM Keuring k WHERE k.keuringsDatum >= :startDatum AND k.keuringsDatum < :endDatum";
        TypedQuery<Keuring> eersteKwartaalQuery = entityManager.createQuery(jpql, Keuring.class);
        eersteKwartaalQuery.setParameter("startDatum", eersteKwartaal);
        eersteKwartaalQuery.setParameter("endDatum", tweedeKwartaal);
        List<Keuring> keuringListEersteKwartaal = eersteKwartaalQuery.getResultList();

        // tweede kwartaal
        TypedQuery<Keuring> tweedeKwartaalQuery = entityManager.createQuery(jpql, Keuring.class);
        tweedeKwartaalQuery.setParameter("startDatum", tweedeKwartaal);
        tweedeKwartaalQuery.setParameter("endDatum", derdeKwartaal);
        List<Keuring> keuringListTweedeKwartaal = tweedeKwartaalQuery.getResultList();

        // derde kwartaal
        TypedQuery<Keuring> derdeKwartaalQuery = entityManager.createQuery(jpql, Keuring.class);
        derdeKwartaalQuery.setParameter("startDatum", derdeKwartaal);
        derdeKwartaalQuery.setParameter("endDatum", vierdeKwartaal);
        List<Keuring> keuringListDerdeKwartaal = derdeKwartaalQuery.getResultList();

        // vierde kwartaal
        TypedQuery<Keuring> vierdeKwartaalQuery = entityManager.createQuery(jpql, Keuring.class);
        vierdeKwartaalQuery.setParameter("startDatum", vierdeKwartaal);
        vierdeKwartaalQuery.setParameter("endDatum", einde);
        List<Keuring> keuringListvierdeKwartaal = vierdeKwartaalQuery.getResultList();

        List<List<Keuring>> kwartaalList = new ArrayList<>();
        kwartaalList.add(keuringListEersteKwartaal);
        kwartaalList.add(keuringListTweedeKwartaal);
        kwartaalList.add(keuringListDerdeKwartaal);
        kwartaalList.add(keuringListvierdeKwartaal);

        return kwartaalList;
    }

    @Override
    public List<Keuring> aantalKeuringenPerPeriode(LocalDate startDatum, LocalDate endDatum){
        entityManager.getTransaction().begin();

        String jpql = "SELECT k FROM Keuring k WHERE k.keuringsDatum >= :startDatum AND k.keuringsDatum <= :endDatum";
        TypedQuery<Keuring> query = entityManager.createQuery(jpql, Keuring.class);
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
}
