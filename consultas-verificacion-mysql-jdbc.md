# Verificación de datos en MySQL y conexión JDBC

Guía rápida sobre cómo se obtienen los registros desde Java (JDBC) y cómo consultarlos directamente en el servidor MySQL dentro del contenedor Docker (`diurno-mysql`).

## 1. Desglose de la consulta en Java

En el código `ProbandoJDBC.java` se ejecuta la siguiente sentencia SQL:

```sql
SELECT * FROM city LIMIT 5;
```

* **`SELECT *`**: Selecciona todas las columnas de la tabla (`ID`, `Name`, `CountryCode`, `District`, `Population`).
* **`FROM city`**: Indica la tabla de origen dentro de la base de datos `world`.
* **`LIMIT 5`**: Restringe el resultado únicamente a los primeros 5 registros.

En Java, al recorrer el `ResultSet`, se extraen las tres primeras columnas de cada fila:
* `resul.getInt(1)` $\rightarrow$ `ID`
* `resul.getString(2)` $\rightarrow$ `Name`
* `resul.getString(3)` $\rightarrow$ `CountryCode`

## 2. Ver los registros directamente en MySQL (Docker)

### Entrar al cliente de MySQL del contenedor

Si no tienes una sesión abierta en `mysql>`, ejecuta desde tu terminal local:

```bash
docker exec -it diurno-mysql mysql -u root -p
```

### Ejecutar las consultas de comprobación

1. Seleccionar la base de datos `world`:

   ```sql
   USE world;
   ```

2. Ver las primeras 5 filas con **todas** sus columnas (igual que en el `SELECT` de Java):

   ```sql
   SELECT * FROM city LIMIT 5;
   ```

3. Ver exactamente las **3 columnas** que imprime el programa por consola:

   ```sql
   SELECT ID, Name, CountryCode FROM city LIMIT 5;
   ```

mysql> describe city;
   
---

<img width="1744" height="1560" alt="terminal-commads" src="https://github.com/user-attachments/assets/816f8a3e-4227-49fa-bc66-643c53176080" />
