package hellojpa;

import hellojpa.advanced_mapping.Movie;
import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent);
//            em.persist(child1);
//            em.persist(child2);



            em.flush();
            em.clear();
            Parent findParent = em.find(Parent.class, parent.getId());
            findParent.getChildList().remove(0);
            // 컬렉션에서 사라진 child 하나가 지워진다.  H2에서 확인하면 Child row하나가 사라져있다.

//            //=============q부모가 지워지면 전부지워진다. cascade = Remove와같다.
//            em.remove(findParent);



            tx.commit();
        } catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        } finally{
            em.close();
        }

       emf.close();
    }

}
