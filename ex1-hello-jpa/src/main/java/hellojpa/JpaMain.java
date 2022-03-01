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
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            Team team = new Team();
            team.setName("TeamA");
//            team.getMembers().add(member);      // members에 member추가
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);                   // 주인에다 값을 넣어보자.
//            member.changeTeam(team);
            em.persist(member);

//            team.addMember(member);
//            team.getMembers().add(member);      // 둘다 넣는다면?
//            em.flush();
//            em.clear();

            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();
            System.out.println("==========");
            for(Member m : members){
                System.out.println("m = "+ m.getUsername());
            }
//            System.out.println("findTeam = " + findTeam);// 무한루프 발생
            System.out.println("==========");
//
//            Member findMember = em.find(Member.class, member.getId());
//            List<Member> members = findMember.getTeam().getMembers();
//            for(Member m : members){
//                System.out.println("m = "+ m.getUsername());
//            }

            tx.commit();

        } catch (Exception e){
            tx.rollback();
        } finally{
            em.close();
        }

       emf.close();
    }
}
