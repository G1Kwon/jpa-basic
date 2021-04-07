package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        //Entity Manager Factory 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        //Entity Manager 생성
        EntityManager em = emf.createEntityManager();
        //Transaction 획득
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 회원등록
            //Member member = new Member();
            //member.setId(3L);
            //member.setName("HelloC");
            //em.persist(member);
            // 회원수정
            //Member findMember = em.find(Member.class, 1L);
            //System.out.println("findMember.getId() = " + findMember.getId());
            //System.out.println("findMember.getName() = " + findMember.getName());
            //findMember.setName("HelloJPA");

            // 전체회원 조회
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(3)
                    .getResultList();
            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
