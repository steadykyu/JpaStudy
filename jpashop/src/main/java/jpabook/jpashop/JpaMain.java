package jpabook.jpashop;

import jpabook.jpashop.domian.Member;
import jpabook.jpashop.domian.Order;

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
            Order order = em.find(Order.class, 1L);
            Long memberId = order.getMemberId();
            Member member = em.find(Member.class,memberId);
            // 식별자 값을 그대로 가져온 경우 - 더이상 참조를 할 수 없음
            // 객체 지향적이지 않음
            // 객체 지향적이지 않은 관계형 DB에 맞춘 설계.
            Member findmember = order.getMember();
            //Order 에 Member 필드를 추가하자
            //객체를 가져와서 참조참조하는 객체지향적 모델

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally{
            em.close();
        }

        emf.close();
    }
}
