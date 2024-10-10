package org.CCristian.hibernateapp;

import jakarta.persistence.EntityManager;
import org.CCristian.hibernateapp.util.JpaUtil;

public class HibernateCriteria {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        em.close();
    }
}
