package hellojpa;

import org.hibernate.Criteria;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{

            //
            Member member = new Member();
            member.setUsername("member1");
            em.persist(member);

            //flush -> commit, query생성시 내부에서 자동적으로 동작.
            List<Member> resultList = em.createNativeQuery("select MEMBER_ID, city, street, zipcode, USERNAME FROM MEMBER", Member.class)
                    .getResultList();

            for(Member member1 : resultList){
                System.out.println("member1 = " + member1); // member1의 값에 존재한다.
            }
            // 즉 flush()가 되고 쿼리를 생성한 것이다.

            //예시코드로 아래처럼 쓰면 결과가 0이다.
            // flush()가 되지않았기 떄문에 MEMBER TABLE에는 값이 없기 때문이다.
            // 이럴때는 수동적으로 em.flush() 해주어 강제적으로 테이블에 값을 넣고 Member객체에 값을 채워넣어놔야한다.
            // em.flush();
//            dbconn.executeQuery("select * from member");


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
