package org.CCristian.hibernateapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;
import org.CCristian.hibernateapp.entity.Cliente;
import org.CCristian.hibernateapp.util.JpaUtil;

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


        System.out.println("\n============= Listar WHERE EQUALS =============\n");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        ParameterExpression<String> nombreParam = criteria.parameter(String.class, "nombre");
        query.select(from).where(criteria.equal(from.get("nombre"), nombreParam));
        clientes = em
                .createQuery(query)
                .setParameter("nombre","Andres")
                .getResultList();
        clientes.forEach(System.out::println);

        em.close();
    }
}
