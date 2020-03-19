package sr.unasat.boot_keuring_systeem.dao.specifications.proxyImp;

import sr.unasat.boot_keuring_systeem.controllers.AuthController;
import sr.unasat.boot_keuring_systeem.dao.specifications.AbstractCrudDao;
import sr.unasat.boot_keuring_systeem.dao.standards.proxy.KeuringDao;
import sr.unasat.boot_keuring_systeem.entities.Keuring;

import javax.persistence.*;
import java.util.List;

public class KeuringDaoProxy extends AbstractCrudDao<Keuring> implements KeuringDao {
    private KeuringDao dao = KeuringDaoImp.getDao();
    private static KeuringDao proxyDao = KeuringDaoImp.getDao();

    private KeuringDaoProxy(){}

    public static KeuringDao getDao(){
        if(proxyDao == null){
            proxyDao = new KeuringDaoProxy();
        }

        return proxyDao;
    }

    @Override
    public List<Keuring> getAll(){
        if(AuthController.getAuthUserRank() == 3) {
            return dao.getAll();
        } else {
            return getAllProxy();
        }
    }

    private List<Keuring> getAllProxy(){
        entityManager.getTransaction().begin();

        String keuring_jpql = "select k from Keuring k WHERE k.controleur.id = :id";
        TypedQuery<Keuring> query = entityManager.createQuery(keuring_jpql, Keuring.class);
        query.setParameter("id", AuthController.getAuthUserId());
        List<Keuring> keuringList = query.getResultList();

        entityManager.getTransaction().commit();
        return keuringList;
    }

    @Override
    public Keuring getOne(Long id){
        if(AuthController.getAuthUserRank() == 3) {
            return dao.getOne(id);
        } else {
            return getOneProxy(id);
        }
    }

    private Keuring getOneProxy(Long id){
        entityManager.getTransaction().begin();

        String jpql = "select k from Keuring k WHERE k.id = :id AND k.controleur.id = :controleurId";
        TypedQuery<Keuring> query = entityManager.createQuery(jpql, Keuring.class);
        query.setParameter("id", id);
        query.setParameter("controleurId", AuthController.getAuthUserId());

        List<Keuring> list = query.getResultList();
        entityManager.getTransaction().commit();

        if(list.isEmpty()){
            return null;
        }

        return list.get(0);
    }

    @Override
    public boolean save(Keuring entity) {
        return false;
    }

    @Override
    public boolean update(Keuring keuring){
        if(keuring.getStatus() == 0){
            return dao.update(keuring);
        }else{
            return false;
        }
    }

    @Override
    public boolean delete(Keuring keuring){
        if(keuring.getStatus() == 0 && AuthController.getAuthUserRank() == 3){
            return dao.delete(keuring);
        }else{
            return false;
        }
    }

    @Override
    public List<Keuring> findKeuring(String keyword){
        if(AuthController.getAuthUserRank() == 3) {
            return dao.findKeuring(keyword);
        } else {
            return findProxyKeuring(keyword);
        }
    }

    private List<Keuring> findProxyKeuring(String keyword){
        entityManager.getTransaction().begin();

        String jpql = "SELECT k FROM Keuring k WHERE k.controleur.id = : id AND (k.boot.bootNaam LIKE :naam OR k.boot.shipCode LIKE :shipCode)";
        Query query = entityManager.createQuery(jpql, Keuring.class);
        query.setParameter("shipCode", "%" + keyword + "%");
        query.setParameter("naam", "%" + keyword + "%");
        query.setParameter("id", AuthController.getAuthUserId());
        List<Keuring> keuringList = query.getResultList();

        entityManager.getTransaction().commit();
        return keuringList;
    }
}
