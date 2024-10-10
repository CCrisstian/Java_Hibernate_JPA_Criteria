<h1 align="center">Criteria API</h1>
<p>Criteria API es parte de JPA 2.0 y ofrece una manera orientada a objetos para construir consultas. En lugar de escribir una consulta JPQL o SQL directamente como una cadena de texto, Criteria API permite crear las consultas utilizando código Java, lo que hace que sean más dinámicas y seguras en cuanto a tipos.</p>
<h2>Criteria API se usa cuando:</h2>

- <b>Consultas dinámicas</b>: Es necesario construir consultas de manera programática en función de las condiciones del negocio. Por ejemplo, si se quiere agregar filtros a la consulta de forma condicional, Criteria API permite hacerlo de manera fluida.
- <b>Seguridad en tipos</b>: Al ser una API orientada a objetos, Criteria es completamente <b>tipada</b>. Esto significa que los errores de sintaxis o tipos de datos incorrectos se detectan en tiempo de compilación y no en tiempo de ejecución, lo que mejora la seguridad y reduce la posibilidad de errores.
- <b>Consultas complejas</b>: Permite crear consultas más complejas que podrían involucrar múltiples criterios, asociaciones entre entidades, agregaciones, y más.

<h2>Ventajas de Criteria API:</h2>

- <b>Dinamismo</b>: Se puede agregar filtros, ordenamientos y uniones de manera dinámica, en función de las necesidades del negocio.
- <b>Tipo seguro</b>: Al construir consultas con objetos Java, los nombres de los campos y las relaciones entre las entidades se verifican en tiempo de compilación, reduciendo los errores.
- <b>Compatibilidad con JPA</b>: Criteria API es parte de JPA, lo que significa que es compatible con cualquier implementación de JPA (como Hibernate, EclipseLink, etc.).
- <b>Construcción de consultas complejas</b>: Es muy útil cuando se necesitan consultas que involucren múltiples relaciones, agrupamientos o funciones de agregación.

<h1 align="center">CriteriaBuilder, CriteriaQuery <...>, Root <...></h1>

```java
public class HibernateCriteria {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        CriteriaBuilder criteria = em.getCriteriaBuilder();
        CriteriaQuery <Cliente> query = criteria.createQuery(Cliente.class);
        Root<Cliente> from = query.from(Cliente.class);
        query.select(from);

        List<Cliente> clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        em.close();
    }
}
```

<h3>Explicación paso a paso de cada método:</h3>

- `EntityManager em = JpaUtil.getEntityManager();`
Este método obtiene un <b>EntityManager</b>, que es el objeto principal para interactuar con la base de datos a través de JPA. El `EntityManager` permite realizar operaciones CRUD (crear, leer, actualizar, eliminar) sobre las entidades persistentes.
  - <b>EntityManager</b>: Administra el ciclo de vida de las entidades y actúa como un puente entre el código Java y la base de datos.

- `CriteriaBuilder criteria = em.getCriteriaBuilder();`
Este método obtiene un <b>CriteriaBuilder</b> del `EntityManager`. El `CriteriaBuilder` es el objeto principal que proporciona los métodos necesarios para construir consultas programáticamente con la API de Criteria.
  - <b>CriteriaBuilder</b>: Se utiliza para construir las diferentes partes de una consulta, como seleccionar, agregar condiciones, ordenar, agrupar, etc.
 
- `CriteriaQuery<Cliente> query = criteria.createQuery(Cliente.class);`
Este método crea una <b>CriteriaQuery</b> para una consulta específica de la entidad `Cliente`. El tipo genérico `<Cliente>` indica que la consulta va a devolver una lista de objetos de tipo `Cliente`.
  - <b>CriteriaQuery</b>: Representa la consulta que se va a construir. Define lo que se va a seleccionar y cómo se va a estructurar la consulta. En este caso, se va a seleccionar todos los objetos `Cliente`.

- `Root<Cliente> from = query.from(Cliente.class);`
Aquí se define la <b>raíz</b> (root) de la consulta. La raíz representa la tabla o entidad sobre la cual se realizará la consulta. En este caso, `Cliente` es la entidad raíz, lo que significa que se van a consultar los registros de la tabla `Cliente`.
  - <b>Root</b>: Especifica desde dónde comienzan las consultas. Representa una entidad principal en la consulta y se usa para referirse a sus atributos y generar las condiciones de la consulta.

- `query.select(from)`;
Este método indica que se va a seleccionar (es decir, se quiere recuperar) todo lo que está en la raíz `from`, que en este caso es la entidad `Cliente`. Es una instrucción equivalente a un `SELECT *` en SQL.
  - <b>select()</b>: Define qué columnas o atributos se van a devolver como resultado. En este caso, selecciona toda la entidad `Cliente`.

- `List<Cliente> clientes = em.createQuery(query).getResultList();`
  - `em.createQuery(query)` ejecuta la consulta creada y devuelve un objeto `TypedQuery<Cliente>`. Esto prepara la consulta para su ejecución.
  - `getResultList()` ejecuta la consulta y devuelve los resultados como una lista de objetos de tipo `Cliente`.

