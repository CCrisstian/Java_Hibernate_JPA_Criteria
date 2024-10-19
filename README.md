<h1 align="center">Criteria API</h1>
<p>Criteria API es parte de JPA 2.0 y ofrece una manera orientada a objetos para construir consultas. En lugar de escribir una consulta JPQL o SQL directamente como una cadena de texto, Criteria API permite crear las consultas utilizando código Java, lo que hace que sean más dinámicas y seguras en cuanto a tipos.</p>
<h2>Criteria API se usa cuando:</h2>

- <b>Consultas dinámicas</b>: Es necesario construir consultas de manera programática en función de las condiciones del negocio.
- <b>Seguridad en tipos</b>: Al ser una API orientada a objetos, Criteria es completamente <b>tipada</b>. Esto significa que los errores de sintaxis o tipos de datos incorrectos se detectan en tiempo de compilación y no en tiempo de ejecución, lo que mejora la seguridad y reduce la posibilidad de errores.
- <b>Consultas complejas</b>: Permite crear consultas más complejas que podrían involucrar múltiples criterios, asociaciones entre entidades, agregaciones, y más.

<h2>Ventajas de Criteria API:</h2>

- <b>Dinamismo</b>: Se puede agregar filtros, ordenamientos y uniones de manera dinámica, en función de las necesidades del negocio.
- <b>Tipo seguro</b>: Al construir consultas con objetos Java, los nombres de los campos y las relaciones entre las entidades se verifican en tiempo de compilación, reduciendo los errores.
- <b>Compatibilidad con JPA</b>: Criteria API es parte de JPA, lo que significa que es compatible con cualquier implementación de JPA (como Hibernate, EclipseLink, etc.).
- <b>Construcción de consultas complejas</b>: Es muy útil cuando se necesitan consultas que involucren múltiples relaciones, agrupamientos o funciones de agregación.

<h1 align="center">Criteria y EntityManager</h1>

```java
public class HibernateCriteria {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        CriteriaBuilder criteria = em.getCriteriaBuilder();

        System.out.println("\n============= Listar=============\n");
        CriteriaQuery <Cliente> query = criteria.createQuery(Cliente.class);
        Root<Cliente> from = query.from(Cliente.class);
        query.select(from);

        List<Cliente> clientes = em.createQuery(query).getResultList();
  }
}
```

- `EntityManager em = JpaUtil.getEntityManager();`
  -  `EntityManager`: Es una interfaz de JPA (Java Persistence API) que se utiliza para interactuar con el contexto de persistencia. Proporciona métodos para crear, leer, actualizar y eliminar entidades en la base de datos.
  - `JpaUtil.getEntityManager()`: Aquí, se está llamando a un método `getEntityManager()` de la clase `JpaUtil`, que se supone que devuelve una instancia de `EntityManager`. Normalmente, este método configura el `EntityManager` usando una `EntityManagerFactory` para administrar el ciclo de vida de las entidades.
 
- `CriteriaBuilder criteria = em.getCriteriaBuilder();`
  - `CriteriaBuilder`: Es una interfaz que se utiliza para crear consultas de Criteria en JPA. Proporciona métodos para construir de manera programática diferentes componentes de una consulta, como selecciones, predicados, órdenes, etc.
  - `em.getCriteriaBuilder()`: Obtiene una instancia de `CriteriaBuilder` del `EntityManager`, que se usará para construir la consulta.

-  `CriteriaQuery<Cliente> query = criteria.createQuery(Cliente.class);`
    -  `CriteriaQuery<Cliente>`: Especifica el tipo de objeto que se devolverá en el resultado de la consulta. En este caso, es del tipo `Cliente`.
    -  `criteria.createQuery(Cliente.class)`: Crea una instancia de CriteriaQuery para la entidad `Cliente`, lo que permite definir una consulta específica para recuperar instancias de la clase `Cliente`.
  
- `Root<Cliente> from = query.from(Cliente.class);`
  - `Root<Cliente>`: Es una instancia que representa una de las entidades principales desde la cual se realizará la consulta. Es el "punto de partida" de la consulta.
  - `query.from(Cliente.class)`: Indica que la consulta se realizará sobre la entidad `Cliente`. Es similar a la cláusula `FROM` en SQL.
 
- `query.select(from);`
  - `query.select(from)`: Configura la parte `SELECT` de la consulta, indicando que se desea seleccionar la entidad completa (`Cliente`) definida por el objeto `from`.
 
- `List<Cliente> clientes = em.createQuery(query).getResultList()`;
  - `em.createQuery(query)`: Crea una consulta de tipo `TypedQuery<Cliente>` basada en la `CriteriaQuery` previamente construida.
  - `.getResultList()`: Ejecuta la consulta y devuelve una lista de resultados del tipo especificado (`Cliente`). En este caso, se devuelve una lista con todas las instancias de `Cliente` que cumplen con los criterios definidos en la consulta.
