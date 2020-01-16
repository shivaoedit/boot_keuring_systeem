package sr.unasat.boot_keuring_systeem.factory;

import sr.unasat.boot_keuring_systeem.config.JPAConfiguration;
import sr.unasat.boot_keuring_systeem.entities.Controleur;
import sr.unasat.boot_keuring_systeem.entities.Type;

import javax.persistence.EntityManager;

public class TypeFactory {
    private static EntityManager entityManager = JPAConfiguration.getEntityManager();

    public static Type getType(String typeNaam){
        entityManager.getTransaction().begin();
        Type type;
        if(typeNaam.equalsIgnoreCase("waterscooter")){
            type = entityManager.find( Type.class, 1L);
        }else if(typeNaam.equalsIgnoreCase("passagiersboot")){
            type = entityManager.find( Type.class, 2L);
        }else if(typeNaam.equalsIgnoreCase("zeevissersvaartuig")){
            type = entityManager.find( Type.class, 3L);
        }else if(typeNaam.equalsIgnoreCase("veerboot")){
            type = entityManager.find( Type.class, 4L);
        }else if(typeNaam.equalsIgnoreCase("jacht")){
            type = entityManager.find( Type.class, 5L);
        }else if(typeNaam.equalsIgnoreCase("zeilboot")){
            type = entityManager.find( Type.class, 6L);
        }else if(typeNaam.equalsIgnoreCase("cruise")){
            type = entityManager.find( Type.class, 7L);
        }else if(typeNaam.equalsIgnoreCase("schip")){
            type = entityManager.find( Type.class, 8L);
        }else{
            type = null;
        }

        entityManager.getTransaction().commit();
        return type;
    }
}
