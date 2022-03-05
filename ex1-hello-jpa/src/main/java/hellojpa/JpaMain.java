package hellojpa;

import hellojpa.advanced_mapping.Movie;

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

            Movie movie = new Movie();
            movie.setDirector("규하");
            movie.setActor("박보영");

            //이름과 price는 Item에 getter setter가 있어야 메서드 호출이 가능능
           movie.setName("바람과 함께 사라지다.");
            movie.setPrice(10000);

            em.persist(movie);
            
            em.flush();
            em.clear();

            Movie findMovie = em.find(Movie.class, movie.getId());
            System.out.println("findMovie = " + findMovie);
            // movie table이 item table과 inner join해서 값을 찾아내는 모습을 확인할수 있다.

            tx.commit();

        } catch (Exception e){
            tx.rollback();
        } finally{
            em.close();
        }

       emf.close();
    }
}
