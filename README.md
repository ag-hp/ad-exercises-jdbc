<h1 align="center">ACCESO A DATOS - JDBC Y MYSQL</h1>

<div align="center">
  <a href="https://www.java.com/"><img src="https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white" alt="Java"></a>
  &nbsp;
  <a href="https://www.mysql.com/"><img src="https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=mysql&logoColor=white" alt="MySQL"></a>
  &nbsp;
  <a href="https://www.docker.com/"><img src="https://img.shields.io/badge/Docker-2496ED?style=flat&logo=docker&logoColor=white" alt="Docker"></a>
  &nbsp;
  <a href="https://www.jetbrains.com/idea/"><img src="https://img.shields.io/badge/IntelliJ_IDEA-000000?style=flat&logo=intellij-idea&logoColor=white" alt="IntelliJ IDEA"></a>
  &nbsp;
  <a href="LICENSE"><img src="https://img.shields.io/badge/License-Educational-blue?style=flat" alt="Educational License"></a>
  &nbsp;
  <a href="#"><img src="https://img.shields.io/badge/Ejercicios-JDBC-orange?style=flat" alt="Ejercicios JDBC"></a>
  &nbsp;
  <img src="https://img.shields.io/badge/Estado-En_Proceso-yellow?style=flat" alt="En Proceso">
</div>

<br>

## Configuración del entorno (MySQL en Docker + Base de datos World)

Para realizar las prácticas de conexión con **JDBC**, utilizamos la base de datos de ejemplo `world` desplegada sobre un contenedor de **Docker** con MySQL.

* **Documentación oficial e instalación de World DB:** [MySQL World Database Setup](https://dev.mysql.com/doc/world-setup/en/world-setup-installation.html)
* **Guía de despliegue en Docker:** [instalacion-base-datos-world-docker.md](./instalacion-base-datos-world-docker.md)

### Despliegue rápido de World DB en Docker

```bash
# 1. Descargar y descomprimir en la máquina anfitriona
curl -L -O https://downloads.mysql.com/docs/world-db.tar.gz
tar -xzvf world-db.tar.gz

# 2. Copiar el script dentro del contenedor
docker cp world-db/world.sql diurno-mysql:/tmp/world.sql

# 3. Entrar al contenedor e iniciar sesión en MySQL
docker exec -it diurno-mysql bash
mysql -u root -p
```

```sql
SOURCE /tmp/world.sql;
USE world;
SHOW TABLES;
```

---

## 1. Dependencia Maven (`pom.xml`)

Para establecer la conexión desde Java con MySQL 8/9 es necesario incluir el conector oficial en el `pom.xml`:

```xml
<dependencies>
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <version>9.1.0</version>
    </dependency>
</dependencies>
```

---

## 2. Parámetros de conexión JDBC

| Parámetro | Valor | Descripción |
| :--- | :--- | :--- |
| **Driver** | `com.mysql.cj.jdbc.Driver` | Controlador JDBC oficial de MySQL |
| **URL JDBC** | `jdbc:mysql://localhost:3306/world` | Cadena de conexión al contenedor local y esquema `world` |
| **Usuario** | `root` | Usuario administrador de MySQL |
| **Esquema / BD** | `world` | Tablas disponibles: `city`, `country`, `countrylanguage` |

---

## 3. Clases principales de la API JDBC (`java.sql`)

| Clase / Interfaz (`java.sql`) | Descripción | Uso principal |
| :--- | :--- | :--- |
| `DriverManager` | Gestiona los controladores JDBC registrados. | `DriverManager.getConnection(url, user, pass)` |
| `Connection` | Representa la sesión abierta con la base de datos. | `conexion.createStatement()` / `conexion.close()` |
| `Statement` | Permite enviar sentencias SQL estáticas a la BD. | `sentencia.executeQuery(sql)` / `executeUpdate(sql)` |
| `PreparedStatement` | Sentencia SQL precompilada con parámetros (`?`). | Evita inyección SQL y mejora el rendimiento. |
| `ResultSet` | Tabla de datos resultante de ejecutar un `SELECT`. | `resul.next()`, `resul.getInt()`, `resul.getString()` |

---

## 4. Verificación en MySQL

```bash
docker exec -it diurno-mysql mysql -u root -p
```

```sql
USE world;
SELECT * FROM city LIMIT 5;
SELECT ID, Name, CountryCode FROM city LIMIT 5;
```

---

<p align="center">
  <a href="https://github.com/ag-hp/dam.git"><img src="https://img.shields.io/badge/DESARROLLO_DE_APLICACIONES_MULTIPLATAFORMA-000000?style=for-the-badge&logo=github&logoColor=white&labelColor=000000" alt="Ver Repositorio Completo Desarrollo de Aplicaciones Multiplataforma"></a>
</p>
