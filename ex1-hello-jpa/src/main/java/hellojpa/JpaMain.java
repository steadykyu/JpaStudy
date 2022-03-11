package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("homeCity","street","10000"));    // 생성자로 값 주입

            em.persist(member);


            Member findMember = em.find(Member.class, member.getId());
            findMember.getAddressHistory().add(new AddressEntity("old1","street","10000"));
            findMember.getAddressHistory().add(new AddressEntity("old2","street","10000"));

            findMember.getAddressHistory().remove(new AddressEntity("old1","street","10000"));
            findMember.getAddressHistory().add(new AddressEntity("new1","street","10000"));
            em.flush();
            em.clear();

            // ================================================값타입 수정
            System.out.println("===============START=========================");




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
