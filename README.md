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

<h1 align="center">public class HibernateCriteria</h1>

```java
EntityManager em = JpaUtil.getEntityManager();

CriteriaBuilder criteria = em.getCriteriaBuilder();

System.out.println("\n============= Listar=============\n");

CriteriaQuery <Cliente> query = criteria.createQuery(Cliente.class);
Root<Cliente> from = query.from(Cliente.class);
query.select(from);

List<Cliente> clientes = em.createQuery(query).getResultList();

em.close();
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

- `em.close();`: Cierra el `EntityManager` para liberar los recursos y finalizar la conexión con la base de datos.

```java
System.out.println("\n============= WHERE EQUALS =============\n");

ParameterExpression<String> nombreParam = criteria.parameter(String.class, "nombre");
query.select(from).where(criteria.equal(from.get("nombre"), nombreParam));

clientes = em.createQuery(query).setParameter("nombre","Andres").getResultList();
```

- `ParameterExpression<String> nombreParam = criteria.parameter(String.class, "nombre");`
    - `ParameterExpression<String>`: Especifica un parámetro de la consulta de tipo `String`. Este parámetro se utilizará en la condición `WHERE` para establecer un valor dinámico en la consulta.
    - `criteria.parameter(String.class, "nombre")`: Crea una expresión de parámetro para el tipo `String` y le asigna un nombre ("nombre"). Este parámetro se usará más adelante para establecer el valor del nombre del cliente que queremos filtrar.

- `query.select(from).where(criteria.equal(from.get("nombre"), nombreParam));`
    - `query.select(from)`: Selecciona la entidad completa (Cliente) en la consulta, similar a la parte `SELECT` en SQL.
    - `.where(criteria.equal(from.get("nombre"), nombreParam))`: Configura la cláusula `WHERE` de la consulta para filtrar los resultados. Vamos a desglosar esta parte:
        - `criteria.equal(from.get("nombre"), nombreParam)`: Crea una condición de igualdad, donde el valor de la propiedad `nombre` de la entidad `Cliente` debe ser igual al valor del parámetro `nombreParam`. Es similar a `WHERE nombre = ?` en SQL, donde el `?` será reemplazado por un valor específico más adelante.
        - `from.get("nombre")`: Hace referencia a la propiedad nombre de la entidad `Cliente`. Es como acceder al campo `nombre` en una tabla.

- `clientes = em.createQuery(query).setParameter("nombre", "Andres").getResultList();`
    - `em.createQuery(query)`: Crea una consulta de tipo `TypedQuery<Cliente>` basada en la `CriteriaQuery` configurada con la condición `WHERE`.
    - `.setParameter("nombre", "Andres")`: Asigna el valor "Andres" al parámetro llamado `nombre`. Esto significa que el filtro `WHERE` buscará clientes cuyo nombre sea igual a "Andres".
    - `.getResultList()`: Ejecuta la consulta y devuelve una lista con los resultados que cumplen con la condición especificada (clientes cuyo nombre es "Andres").

```java
System.out.println("\n============= WHERE LIKE =============\n");

query = criteria.createQuery(Cliente.class);
from = query.from(Cliente.class);

query.select(from).where(criteria.like(from.get("nombre"), "%and%"));

clientes = em.createQuery(query).getResultList();
```

- `query.select(from).where(criteria.like(from.get("nombre"), "%and%"));`
    - `query.select(from)`: Selecciona toda la entidad `Cliente` como resultado de la consulta, similar a un `SELECT *` en SQL.
    - `.where(criteria.like(from.get("nombre"), "%and%"))`: Agrega una cláusula `WHERE` a la consulta, usando la condición `LIKE` para realizar coincidencias parciales.
        - `criteria.like(from.get("nombre"), "%and%")`: Crea una condición `LIKE` para el campo `nombre` de la entidad `Cliente`. El valor `"%and%"`.
        - `from.get("nombre")`: Hace referencia a la propiedad `nombre` de la entidad `Cliente`, como acceder al campo `nombre` en una tabla SQL.

```java
System.out.println("\n============= WHERE LIKE 2 =============\n");

query = criteria.createQuery(Cliente.class);
from = query.from(Cliente.class);

ParameterExpression<String> nombreParamLike = criteria.parameter(String.class, "nombreParam");

query.select(from).where(criteria.like(criteria.upper(from.get("nombre")), criteria.upper(nombreParamLike)));

clientes = em.createQuery(query).setParameter("nombreParam", "%and%").getResultList();
```

- `ParameterExpression<String> nombreParamLike = criteria.parameter(String.class, "nombreParam");`
    - Crea un parámetro de la consulta de tipo `String`, llamado `"nombreParam"`. Este parámetro se utilizará para pasar dinámicamente un valor a la consulta.

- `query.select(from).where(criteria.like(criteria.upper(from.get("nombre")), criteria.upper(nombreParamLike)));`
    - `query.select(from)`: Selecciona la entidad completa (`Cliente`) en la consulta.
    - `.where(...)`: Añade la condición `WHERE` a la consulta.
    - `criteria.like(criteria.upper(from.get("nombre")), criteria.upper(nombreParamLike))`: Crea una condición `LIKE` para comparar el campo `nombre` con el valor del parámetro, ignorando diferencias de mayúsculas y minúsculas:
        - `criteria.upper(from.get("nombre"))`: Convierte el valor del campo `nombre` a mayúsculas antes de compararlo. Esto permite realizar una búsqueda sin distinguir entre mayúsculas y minúsculas.
        - `criteria.upper(nombreParamLike)`: Convierte el valor del parámetro `nombreParam` a mayúsculas. Así, la comparación se realiza con ambos valores convertidos a mayúsculas.
    - De esta forma, la comparación `LIKE` se realiza en mayúsculas, lo que la hace insensible a diferencias de capitalización en los nombres.

- `clientes = em.createQuery(query).setParameter("nombreParam", "%and%").getResultList();`
    - `em.createQuery(query)`: Crea una consulta de tipo `TypedQuery<Cliente>` basada en la `CriteriaQuery` configurada con la condición `LIKE`.
    - `.setParameter("nombreParam", "%and%")`: Asigna el valor `"%and%"` al parámetro `"nombreParam"`.
    - `.getResultList()`: Ejecuta la consulta y devuelve una lista de resultados que cumplen con la condición especificada.
 
 ```java
System.out.println("\n============= WHERE BETWEEN =============\n");

query = criteria.createQuery(Cliente.class);
from = query.from(Cliente.class);
query.select(from).where(criteria.between(from.get("id"),2L, 6L));

clientes = em.createQuery(query).getResultList();
clientes.forEach(System.out::println);
```

- `query.select(from).where(criteria.between(from.get("id"), 2L, 6L));`
    - `query.select(from)`: Selecciona la entidad completa (`Cliente`) para ser devuelta por la consulta.
    - `.where(criteria.between(from.get("id"), 2L, 6L))`: Añade la condición `WHERE` con la cláusula `BETWEEN` para filtrar los resultados:
        - `criteria.between(from.get("id"), 2L, 6L)`: Indica que el valor del campo `id` de la entidad `Cliente` debe estar entre `2L` y `6L`, ambos inclusive. En SQL, esto sería equivalente a `WHERE id BETWEEN 2 AND 6`.
        - `from.get("id")`: Hace referencia al campo `id` de la entidad `Cliente`.
 
```java
System.out.println("\n============= WHERE IN =============\n");

query = criteria.createQuery(Cliente.class);
from = query.from(Cliente.class);

query.select(from).where(from.get("nombre").in(Arrays.asList("Andres", "John", "Lou")));

clientes = em.createQuery(query).getResultList();
clientes.forEach(System.out::println);
```

- `query.select(from).where(from.get("nombre").in(Arrays.asList("Andres", "John", "Lou")));`
    - `query.select(from)`: Selecciona la entidad completa (`Cliente`) para ser devuelta por la consulta.
    - `.where(from.get("nombre").in(Arrays.asList("Andres", "John", "Lou")))`: Establece una condición `IN`, que busca clientes cuyo nombre esté en la lista especificada. Es equivalente a la cláusula `WHERE nombre IN ('Andres', 'John', 'Lou')` en SQL.
        - `.where(...)`: Añade la condición `WHERE` a la consulta.
        - `from.get("nombre")`: Hace referencia al campo `nombre` de la entidad `Cliente`.
        - `.in(Arrays.asList("Andres", "John", "Lou"))`: Establece una condición `IN`, especificando una lista de valores con los que se comparará el campo `nombre`. En lugar de pasar directamente los valores, se utiliza `Arrays.asList(...)` para crear una lista con los nombres "Andres", "John", y "Lou".

```java
System.out.println("\n============= Filtrar usando predicados Mayor Igual que =============\n");

query = criteria.createQuery(Cliente.class);
from = query.from(Cliente.class);

query.select(from).where(criteria.ge(from.get("id"), 3L));

clientes = em.createQuery(query).getResultList();
clientes.forEach(System.out::println);
```
- `query.select(from).where(criteria.ge(from.get("id"), 3L));`
    - `query.select(from)`: Selecciona la entidad completa (`Cliente`) para ser devuelta.
    - `.where(...)`: Añade una condición `WHERE`.
    - `criteria.ge(from.get("id"), 3L)`: Crea un predicado para filtrar los resultados, especificando que el campo `id` debe ser mayor o igual a 3. Equivale a `WHERE id >= 3` en SQL.


```java
System.out.println("\n============= Filtrar usando predicados Mayor que =============\n");

query = criteria.createQuery(Cliente.class);
from = query.from(Cliente.class);
        query.select(from).where(criteria.gt(criteria.length(from.get("nombre")), 5L));

clientes = em.createQuery(query).getResultList();
clientes.forEach(System.out::println);
```

- `query.select(from).where(criteria.gt(criteria.length(from.get("nombre")), 5L));`
    - `query.select(from)`: Selecciona la entidad completa (`Cliente`) para ser devuelta por la consulta.
    - `.where(...)`: Añade la condición `WHERE`.
    - `criteria.gt(criteria.length(from.get("nombre")), 5L)`: Crea un predicado que filtra los resultados, especificando que la longitud del campo `nombre` debe ser mayor a 5. Este predicado equivale a `WHERE LENGTH(nombre) > 5` en SQL.

```java
System.out.println("\n============= Filtrar usando predicados Mayor que =============\n");

query = criteria.createQuery(Cliente.class);
from = query.from(Cliente.class);
        query.select(from).where(criteria.gt(criteria.length(from.get("nombre")), 5L));

clientes = em.createQuery(query).getResultList();
clientes.forEach(System.out::println);
```

- `query.select(from).where(criteria.and(p3, criteria.or(porNombre3, porFormaPago3)));`
    - `query.select(from)`: Selecciona la entidad completa (`Cliente`) para ser devuelta.
    - `.where(...)`: Añade la condición `WHERE`.
    - `criteria.and(...)`: Combina los predicados utilizando la conjunción lógica `AND`. En este caso, requiere que p3 (id mayor o igual a 4) sea verdadero.
    - `criteria.or(porNombre3, porFormaPago3)`: Aplica la disyunción lógica `OR`, que verifica si el `nombre` es "Andres" o la `formaPago` es "debito".
    - En conjunto, la condición equivale a `WHERE id >= 4 AND (nombre = 'Andres' OR formaPago = 'debito')` en SQL.

```java
System.out.println("\n============= Consultas con ORDER BY ASC | DESC =============\n");

query = criteria.createQuery(Cliente.class);
from = query.from(Cliente.class);

query.select(from).orderBy(criteria.asc(from.get("nombre")), criteria.desc(from.get("apellido")));

clientes = em.createQuery(query).getResultList();
clientes.forEach(System.out::println);
```

- `query.select(from).orderBy(criteria.asc(from.get("nombre")), criteria.desc(from.get("apellido")));`
    - `query.select(from)`: Selecciona la entidad completa (`Cliente`) para ser devuelta por la consulta.
    - `.orderBy(...)`: Especifica el orden en el que se devolverán los resultados.
    - `criteria.asc(from.get("nombre"))`: Ordena los resultados en orden ascendente (`ASC`) por el campo `nombre`.
    - `criteria.desc(from.get("apellido"))`: Ordena los resultados en orden descendente (`DESC`) por el campo `apellido`.
 
```java
System.out.println("\n============= Consultas por Id =============\n");

query = criteria.createQuery(Cliente.class);
from = query.from(Cliente.class);

ParameterExpression<Long> idParam = criteria.parameter(Long.class, "id");

query.select(from).where(criteria.equal(from.get("id"), idParam));

Cliente cliente = em.createQuery(query).setParameter("id",1L).getSingleResult();

System.out.println(cliente);
```

- `ParameterExpression<Long> idParam = criteria.parameter(Long.class, "id");`: Crea un parámetro de tipo `Long` llamado `id` que se utilizará en la condición de la consulta.
- `query.select(from).where(criteria.equal(from.get("id"), idParam));`
    - `query.select(from)`: Selecciona la entidad `Cliente` completa.
    - `.where(criteria.equal(from.get("id"), idParam))`: Añade una condición `WHERE` que filtra los resultados para que el campo `id` sea igual al valor del parámetro `idParam`.
- `Cliente cliente = em.createQuery(query).setParameter("id", 1L).getSingleResult();`
    - `em.createQuery(query)`: Crea la consulta con el `EntityManager`.
    - `.setParameter("id", 1L)`: Asigna el valor `1L` al parámetro `id`.
    - `.getSingleResult()`: Ejecuta la consulta y devuelve un único resultado (`Cliente`), dado que se espera un registro único con ese `id`.

```java
System.out.println("\n============= Consultas DISTINCT por Nombre de Cliente =============\n");

queryString = criteria.createQuery(String.class);
from = queryString.from(Cliente.class);

queryString.select(criteria.upper(from.get("nombre"))).distinct(true);

nombres = em.createQuery(queryString).getResultList();
nombres.forEach(System.out::println);
```

- `queryString.select(criteria.upper(from.get("nombre"))).distinct(true);`
    - `queryString.select(criteria.upper(from.get("nombre")))`: Selecciona el campo `nombre` convertido a mayúsculas utilizando la función `upper()`.
    - `.distinct(true)`: Aplica la cláusula `DISTINCT` para eliminar duplicados, asegurando que solo se devuelvan nombres únicos.

```java
System.out.println("\n============= Consultas por Campos Personalizados del Entity Cliente usando WHERE =============\n");

CriteriaQuery<Object[]> queryObject = criteria.createQuery(Object[].class);
from = queryObject.from(Cliente.class);

queryObject.multiselect(from.get("id"),from.get("nombre"), from.get("apellido")).where(criteria.like(from.get("nombre"), "%lu%"));

List<Object[]> registros = em.createQuery(queryObject).getResultList();

registros.forEach(reg -> {
    Long id = (Long) reg[0];
    String nombre = (String) reg[1];
    String apellido = (String) reg[2];
    System.out.println("id = "+id+", nombre = "+nombre+", apellido = "+apellido);
});
```

- `CriteriaQuery<Object[]> queryObject = criteria.createQuery(Object[].class);`
- `from = queryObject.from(Cliente.class);`
    - Se crea una instancia de `CriteriaQuery` para devolver un arreglo de `Object[]`, ya que se van a seleccionar múltiples campos específicos (no toda la entidad `Cliente`).
    - `from` establece la entidad `Cliente` como origen de la consulta, equivalente a la cláusula `FROM` en SQL.
- `queryObject.multiselect(from.get("id"), from.get("nombre"), from.get("apellido")).where(criteria.like(from.get("nombre"), "%lu%"));`
    - `multiselect(from.get("id"), from.get("nombre"), from.get("apellido"))`: Selecciona los campos `id`, `nombre` y `apellido` del objeto `Cliente`.
    - `.where(criteria.like(from.get("nombre"), "%lu%"))`: Aplica un filtro `LIKE` en el campo `nombre`, buscando nombres que contengan la subcadena "lu".
- `List<Object[]> registros = em.createQuery(queryObject).getResultList();`
    - Ejecuta la consulta y devuelve una lista de arreglos de objetos (`Object[]`), donde cada arreglo contiene los valores de `id`, `nombre` y `apellido` de un registro.

 ```java
System.out.println("\n============= Consulta COUNT(), Mínimo valor, Máximo valor de una Tabla =============\n");

queryObject = criteria.createQuery(Object[].class);
from = queryObject.from(Cliente.class);

queryObject.multiselect(
    criteria.count(from.get("id")),
    criteria.sum(from.get("id")),
    criteria.max(from.get("id")),
    criteria.min(from.get("id")));

registro = em.createQuery(queryObject).getSingleResult();
count = (Long) registro[0];
sum = (Long) registro[1];
max = (Long) registro[2];
min = (Long) registro[3];
System.out.println("count = "+count+", sum = "+sum+", max = "+max+", min = "+min);
```

Este bloque de código realiza una consulta utilizando funciones agregadas (`COUNT`, `SUM`, `MAX`, `MIN`) en la entidad `Cliente`.

- `criteria.count(from.get("id"))`: Cuenta el número de registros en la tabla `Cliente`.
- `criteria.sum(from.get("id"))`: Calcula la suma de los valores del campo `id`.
- `criteria.max(from.get("id"))`: Encuentra el valor máximo del campo `id`.
- `criteria.min(from.get("id"))`: Encuentra el valor mínimo del campo `id`.

<h1 align="center">public class HibernateCriteriaBusquedaDinamica</h1>

```java
public class HibernateCriteriaBusquedaDinamica {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        System.out.println("Filtro para nombre: ");
        String nombre = s.nextLine();
        System.out.println("Filtro para apellido: ");
        String apellido = s.nextLine();
        System.out.println("Filtro para la forma de pago: ");
        String formaPago = s.nextLine();

        EntityManager em = JpaUtil.getEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Cliente> query = cb.createQuery(Cliente.class);
        Root<Cliente> from = query.from(Cliente.class);

        List<Predicate> condiciones = new ArrayList<>();

        if (nombre != null && !nombre.isEmpty()){
            condiciones.add(cb.equal(from.get("nombre"),nombre));
        }
        if (apellido != null && !apellido.isEmpty()){
            condiciones.add(cb.equal(from.get("apellido"),apellido));
        }
        if (formaPago != null && !formaPago.isEmpty()){
            condiciones.add(cb.equal(from.get("formaPago"),formaPago));
        }

        query.select(from).where(cb.and(condiciones.toArray(new Predicate[condiciones.size()])));

        List<Cliente> clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        em.close();
    }
}
```

El código en Java es un ejemplo de cómo realizar una búsqueda dinámica en la base de datos utilizando Hibernate y la API de Criteria. Permite filtrar registros de la entidad `Cliente` basándose en los criterios opcionales proporcionados por el usuario para el nombre, apellido y forma de pago.

  - Lectura de filtros desde la entrada del usuario: Utiliza un `Scanner` para leer los criterios de búsqueda opcionales desde la consola (nombre, apellido y forma de pago).

```java
Scanner s = new Scanner(System.in);

System.out.println("Filtro para nombre: ");
String nombre = s.nextLine();
System.out.println("Filtro para apellido: ");
String apellido = s.nextLine();
System.out.println("Filtro para la forma de pago: ");
String formaPago = s.nextLine();
```

  - Creación de una lista de condiciones dinámicas (`Predicate`): Se crean condiciones (`Predicate`) dinámicamente, verificando si cada filtro proporcionado por el usuario no está vacío. Si el filtro está presente, se agrega una condición correspondiente a la lista.

```java
List<Predicate> condiciones = new ArrayList<>();

if (nombre != null && !nombre.isEmpty()){
    condiciones.add(cb.equal(from.get("nombre"), nombre));
}
if (apellido != null && !apellido.isEmpty()){
    condiciones.add(cb.equal(from.get("apellido"), apellido));
}
if (formaPago != null && !formaPago.isEmpty()){
    condiciones.add(cb.equal(from.get("formaPago"), formaPago));
}
```

- Construcción de la consulta con las condiciones: Se seleccionan los registros de la entidad `Cliente` y se aplica un `where` con todas las condiciones (`Predicate`) combinadas usando `cb.and()`, lo que implica que todos los criterios especificados deben cumplirse.
