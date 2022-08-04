import models.Car;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpaProject");
        EntityManager manager = factory.createEntityManager();

//        manager.getTransaction().begin();
//        manager.persist(new Car("BMW","328"));
//        manager.persist(new Car("BMW","528"));
//        manager.getTransaction().commit();

        int id = 2;
        TypedQuery<Car> query = manager.createQuery("select c from Car c where c.id = :id", Car.class);
        query.setParameter("id",id);
        List<Car> resultList = query.getResultList();

        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Car> criteria = criteriaBuilder.createQuery(Car.class);
        Root<Car> from = criteria.from(Car.class);
        criteria.select(from);
        criteria.where();
        Car singleResult = manager.createQuery(criteria).getSingleResult();


        resultList.forEach(System.out::println);

        manager.close();
        factory.close();
    }
}
