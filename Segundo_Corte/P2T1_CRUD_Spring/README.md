# CRUD con Spring

## Creación de Proyecto

Creamos un nuevo proyecto con ***IntelliJ IDEA Ultimate***:

![i](doc/01.jpeg)

## Spring Initializer

Luego, seleccionamos ***Spring Initializer*** para configurar el nombre, ubicación, lenguaje, tipo, grupo, artifact, package name, versión de Java, y empaquetado del proyecto, y pulsamos Next.

![i](doc/02.jpeg)

> En caso de no contar con la opción de ***Spring Initializer***, debemos instalar un plugin desde la vista inicial de ***IntelliJ IDEA Ultimate***, o yendo a la pestaña de **File > Settings**, en la vista de **Plugins** y buscando el plugin ***Spring Initializr and Assistant***
>
> ![i](doc/02_01.png)
>
>![i](doc/02_02.png)

## Selección de Dependencias

Luego, seleccionamos las siguientes dependencias para nuestro proyecto:

- Spring Web
- Spring Data JPA
- PostgreSQL Driver

![i](doc/03.jpeg)

![i](doc/04.jpeg)

Presionamos Finish para que IntelliJ abra el proyecto en una nueva ventana.

## Database en PostgreSQL

### Nuevo usuario (opcional)

Vamos a crear una nuevo usuario en PostgreSQL para el acceso desde Spring. Este es un paso opcional, pero recomendado.

![i](doc/13.png)

![i](doc/14.png)

![i](doc/15.png)

![i](doc/16.png)

### Database

Creamos una nueva base de datos, en mi caso he decidido llamarla `db_p2t1`, y le asigne el usuario `user_spring`:

![i](doc/17.png)

![i](doc/18.png)

### DDL (Data Definition Language)

Vamos a definir 2 tablas dentro de nuestra base de datos:

![i](doc/19.png)

![i](doc/20.png)

### DML (Data Manipulation Language)

Añadimos nuevas filas dentro de cada tabla:

![i](doc/21.png)

### Puerto del Servidor

Necesitamos estar seguros de en que puerto está corriendo nuestro servidor de PostgreSQL, por lo tanto debemos acceder a las propiedades del servidor, e ir a la pestaña de conexión:

![i](doc/22.png)

![i](doc/23.png)

## Añadir un DataSource al proyecto

En la interfaz del programa, podemos seleccionar la pestaña ***Database***, para añadir una nueva base de datos desde el signo de `+`. Como estamos usando PostgreSQL seleccionamos la fuente del mismo.

![i](doc/05.png)

Una vez seleccionada la DataSource de PostgreSQL, debemos añadir el Host, Puerto, Usuario y Contraseña, y el nombre de la base de datos a la que nos vamos a conectar. Es importante presionar en la opción de **Test Connection** para poder saber si los datos que ingresamos son correctos. Una vez estemos seguros, entonces podemos presionar en OK.

![i](doc/06.png)

Una vez terminada la configuración de la base de datos, podemos observar un estructura de árbol con la conexión de nuestra base de datos.

![i](doc/07.png)

## Hacer consultas a la DB desde IntelliJ

Cuando estamos en la pestaña de Database, tenemos disponible una consola para hacer consultas, lo primero que podemos hacer, es una consulta manual.

![i](doc/08.png)

El resultado que obtenemos en la parte inferior es la siguiente:

![i](doc/09.png)

También podemos hacer consultas de manera automática, al presionar click derecho sobre una tabla e ir a la sección de **SQL Scripts**, en donde tenemos una serie de consultas disponibles.

![i](doc/10.png)

Por ejemplo, seleccione la consulta **Select all rows from a table**, e inmediatamente aparece la siguiente consulta en la consola:

![i](doc/11.png)

Ejecutamos la consulta, y este es el resultado:

![i](doc/12.png)

## Hacer un Build al proyecto

Dentro de IntelliJ podemos hacer un build de nuestro proyecto, algo muy similar al script `build` que ejecutábamos en un proyecto de Node. Para ello, podemos presionar cualquiera de las siguientes opciones:

- Podemos acceder a la pestaña **Build** y seleccionar **Build Project**
  
  ![i](doc/25.png)

- En la parte superior derecha tenemos la opción de hacer un build mediante el botón con el icono de un martillo

  ![i](doc/24.png)

- En la parte inferior, también podemos encontrar la opción de **Build** y presionamos de nuevo en el icono del martillo.
  
  ![i](doc/26.png)

La salida que obtendremos en cualquiera de las opciones, será la siguiente:

![i](doc/27.png)

## Ejecutar el proyecto

Para correr nuestro proyecto tenemos de nuevo varias opciones:

- Podemos acceder a la pestaña **Run** y seleccionar **Run**, ya sea con el proyecto o seleccionándolo de manera manual
  
  ![i](doc/28.png)

- En la parte superior derecha tenemos la opción de Run mediante el botón con el icono de Play
  
  ![i](doc/29.png)

En la parte inferior, también podemos encontrar la opción de **Run** y presionamos de nuevo en el icono de Play.
  
  ![i](doc/30.png)

Actualmente, la salida que vamos a tener será la siguiente:

![i](doc/31.png)
![i](doc/32.png)
