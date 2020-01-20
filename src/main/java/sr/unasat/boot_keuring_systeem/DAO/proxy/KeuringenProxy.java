package sr.unasat.boot_keuring_systeem.DAO.proxy;

import sr.unasat.boot_keuring_systeem.entities.Keuring;
import sr.unasat.boot_keuring_systeem.services.LoginService;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class KeuringenProxy implements Keuringen{
    private KeuringenImp keuringenImp;

    public KeuringenProxy(){
        keuringenImp = new KeuringenImp();
    }

    @Override
    public List<Keuring> getAllKeuringen(){
        if(LoginService.getUserRank() == 3) {
            return keuringenImp.getAllKeuringen();
        } else {
            return getAllProxyKeuringen();
        }
    }

    @Override
    public List<Keuring> findKeuring(String keyword){
        if(LoginService.getUserRank() == 3) {
            return keuringenImp.findKeuring(keyword);
        } else {
            return findProxyKeuring(keyword);
        }
    }

    @Override
    public void updateKeuring(Keuring keuring){
        if(keuring.getStatus() == 0){
            System.out.println("Keuring voltooid.");
            keuringenImp.updateKeuring(keuring);
        }else{
            System.out.println("Niet toegestaan om keuring nogmaals uit te voeren.");
        }
    }

    @Override
    public void deleteKeuring(Keuring keuring){
        if(keuring.getStatus() == 0 && LoginService.getUserRank() == 3){
            keuringenImp.deleteKeuring(keuring);
        }else{
            System.out.println("Niet toegestaan om keuring te verwijderen.");
        }
    }

    private List<Keuring> getAllProxyKeuringen(){
        entityManager.getTransaction().begin();

        String keuring_jpql = "select k from Keuring k ORDER BY controleur WHERE k.controleur.id = :id;";
        TypedQuery<Keuring> query = entityManager.createQuery(keuring_jpql, Keuring.class);
        query.setParameter("id", LoginService.getUserId());
        List<Keuring> keuringList = query.getResultList();

        entityManager.getTransaction().commit();
        return keuringList;
    }

    private List<Keuring> findProxyKeuring(String keyword){
        entityManager.getTransaction().begin();

        String jpql = "SELECT k FROM Keuring k WHERE k.controleur.id = : id AND (WHERE k.boot.bootNaam LIKE :naam OR k.boot.shipCode LIKE :shipCode)";
        Query query = entityManager.createQuery(jpql, Keuring.class);
        query.setParameter("shipCode", "%" + keyword + "%");
        query.setParameter("naam", "%" + keyword + "%");
        query.setParameter("id", LoginService.getUserId());
        List<Keuring> keuringList = query.getResultList();

        entityManager.getTransaction().commit();
        return keuringList;
    }
}
