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

### Consulta 1: `SELECT * FROM city LIMIT 5;`

| ID | Name | CountryCode | District | Population |
| :--- | :--- | :--- | :--- | :--- |
| 1 | Kabul | AFG | Kabol | 1780000 |
| 2 | Qandahar | AFG | Qandahar | 237500 |
| 3 | Herat | AFG | Herat | 186800 |
| 4 | Mazar-e-Sharif | AFG | Balkh | 127800 |
| 5 | Amsterdam | NLD | Noord-Holland | 731200 |

*5 rows in set (0.00 sec)*

---

### Consulta 2: `SELECT ID, Name, CountryCode FROM city LIMIT 5;`

| ID | Name | CountryCode |
| :--- | :--- | :--- |
| 1 | Kabul | AFG |
| 2 | Qandahar | AFG |
| 3 | Herat | AFG |
| 4 | Mazar-e-Sharif | AFG |
| 5 | Amsterdam | NLD |

*5 rows in set (0.00 sec)*
