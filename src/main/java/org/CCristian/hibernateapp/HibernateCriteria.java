package org.CCristian.hibernateapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;
import org.CCristian.hibernateapp.entity.Cliente;
import org.CCristian.hibernateapp.util.JpaUtil;

import java.util.Arrays;
import java.util.List;

public class HibernateCriteria {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        CriteriaBuilder criteria = em.getCriteriaBuilder();

        System.out.println("\n============= Listar=============\n");
        CriteriaQuery <Cliente> query = criteria.createQuery(Cliente.class);
        Root<Cliente> from = query.from(Cliente.class);
        query.select(from);

        List<Cliente> clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);


        System.out.println("\n============= WHERE EQUALS =============\n");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        ParameterExpression<String> nombreParam = criteria.parameter(String.class, "nombre");
        query.select(from)
                .where(criteria.equal(from.get("nombre"), nombreParam));
        clientes = em.createQuery(query)
                .setParameter("nombre","Andres").getResultList();
        clientes.forEach(System.out::println);


        System.out.println("\n============= WHERE LIKE =============\n");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        query.select(from)
                .where(criteria.like(from.get("nombre"), "%and%"));

        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);


        System.out.println("\n============= WHERE LIKE 2 =============\n");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        ParameterExpression<String> nombreParamLike = criteria.parameter(String.class, "nombreParam");
        query.select(from)
                .where(criteria.like(criteria.upper(from.get("nombre")), criteria.upper(nombreParamLike)));

        clientes = em.createQuery(query)
                .setParameter("nombreParam", "%and%").getResultList();
        clientes.forEach(System.out::println);


        System.out.println("\n============= WHERE BETWEEN =============\n");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        query.select(from)
                .where(criteria.between(from.get("id"),2L, 6L));

        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);


        System.out.println("\n============= WHERE IN =============\n");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        query.select(from)
                .where(from.get("nombre").in(Arrays.asList("Andres", "John", "Lou")));

        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);


        System.out.println("\n============= WHERE IN 2 =============\n");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        ParameterExpression<List> listParam = criteria.parameter(List.class, "nombres");

        query.select(from)
                .where(from.get("nombre").in(listParam));

        clientes = em.createQuery(query)
                    .setParameter("nombres", Arrays.asList("Andres", "John", "Lou"))
                    .getResultList();

        clientes.forEach(System.out::println);


        System.out.println("\n============= Filtrar usando predicados Mayor Igual que =============\n");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        query.select(from).where(criteria.ge(from.get("id"), 3L));

        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);


        System.out.println("\n============= Filtrar usando predicados Mayor que =============\n");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        query.select(from).where(criteria.gt(criteria.length(from.get("nombre")), 5L));

        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        em.close();
    }
}
