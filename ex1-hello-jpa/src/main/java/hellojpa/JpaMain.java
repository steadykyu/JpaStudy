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
//            //비영속
//            Member member = new Member();
//            member.setId(101L);
//            member.setName("HelloJPA");
//
//            //영속
//            System.out.println("===Before===");
//            em.persist(member);
////            회원 엔티티를 영속성 컨텍스트에서 분리(준영속상태)
////            em.detach(member);
//            System.out.println("===After===");
//            // before , After 사이가 아닌 이후에 DB에 쿼리를 집어넣음
//            // 즉 commit할때 DB를 집어 넣는 것이다.
//
////              위에서 생성 후에 돌리면, 쿼리없이 캐시에서 값을 가져온다.
//            Member findMember = em.find(Member.class, 101L);
//            System.out.println("findMember.getID() = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());
//            tx.commit();
////          DB에서 값을 가져온 것이 아니라, 1차캐시에서 가져와 조회하기 때문에 쿼리가 생성되지 않았다.

//// ----------------1차캐시 동작방식 확인----------
////          em를 새로 만든후, Member 객체 생성없이 돌린경우 (단 DB에는 값 존재)
//            Member findMember1 = em.find(Member.class, 101L);
//            Member findMember2 = em.find(Member.class, 101L);
////          결과 쿼리가 한개이다.-> findMember1은 DB에서 쿼리에서 통해 값을 찾고, findMember2는 영속 컨텍스트 안의 캐시에서 값을 찾음
//
//            //영속성 엔티티의 동일성 보장
//            System.out.println("result = " + (findMember1 == findMember2));
//            tx.commit();

//            //----------------------------엔티티 등록 - 쓰기지연 ----------
//            Member member1 = new Member(150L,"A");
//            Member member2 = new Member(160L,"B");
//
//            em.persist(member1);
//            em.persist(member2);
//            System.out.println("=========================");
//            tx.commit();
            //쓰기지연 sql에 한번에 모아서 커밋할때 쿼리를 보낸다.

//            //--------------------------------엔티티 수정 ----------
//            Member member = em.find(Member.class,150L);
//            member.setName("ZZZZZZ");
//
//            tx.commit();
////          commit전에 Persist로 컬렉션에 넣어주지 않아도, 알아서 update쿼리를 날려준다.(자바 컬렉션(ex) List)이라고 생각해보자.)
//
////             컨테이너에서 member 객체를 지움.
//            em.remove(member);

////--------------------------------플러시하는법 ----------
//            Member member = new Member(200L,"member200");
//            em.persist(member);
//
//            em.flush();
//
//            System.out.println("===================");
//            tx.commit();
//             커밋 이전에 쿼리를 바로 DB에 반영해버린다. 강제 호출!

            //-------------------------------------준영속 상태
            Member member = em.find(Member.class,150L);
            member.setName("AAAAA");
//            ---------------------------준영속상태로만들기
//            em.detach(member);
            // JPA에서 관리를 하지않는다.
            // 엔티티값을 변경했는데 update문이 날라가지 않는다.(준영속상태가 되어, 영속성컨테이너가 관리하지 않기 때문이다.)
//            ----------------------------- 영속성 컨테이너 다 지우기
            em.clear();
            Member member2 = em.find(Member.class,150L);
            //member2를 1차캐시에서 찾아오는 것이 아니라, DB에서 값을 빼온후 다시 영속성 컨테이너에 등록한다.
//            ----------------------------- 영속성 컨테이너 닫기
//            em.close();
//            System.out.println("===================");
//            tx.commit();

        } catch (Exception e){
            tx.rollback();
        } finally{
            em.close();
        }

       emf.close();
    }
}
