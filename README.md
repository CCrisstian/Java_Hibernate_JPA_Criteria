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
