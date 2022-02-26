package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        // 관리자를 새로 만들기.(아래 코드입장에서는 계속 em과 트랜잭션이 새로 만들어 지는 것이다.)
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
        Member member1 = new Member();

        em.persist(member1);
        tx.commit();

        } catch (Exception e){
            tx.rollback();
        } finally{
            em.close();
        }

       emf.close();
    }
}
