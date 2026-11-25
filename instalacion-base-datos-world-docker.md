# Instalación de la base de datos `world` en MySQL (Docker)

Guía rápida con los comandos necesarios para descargar, copiar e importar la base de datos de ejemplo `world` de MySQL dentro de un contenedor Docker (`diurno-mysql`).

---

## 1. Descargar y descomprimir en la máquina anfitriona
Ejecutar en la terminal local (fuera del contenedor):

```bash
curl -L -O https://downloads.mysql.com/docs/world-db.tar.gz
tar -xzvf world-db.tar.gz
```
*Esto creará la carpeta `world-db/` con el archivo `world.sql` en su interior.*

---

## 2. Copiar el script `.sql` dentro del contenedor
Enviar el archivo extraído a la carpeta temporal `/tmp` del contenedor:

```bash
docker cp world-db/world.sql diurno-mysql:/tmp/world.sql
```

---

## 3. Importar la base de datos en MySQL

### Opción A: De forma interactiva (entrando al contenedor)
1. Abrir una terminal `bash` dentro del contenedor:
   ```bash
   docker exec -it diurno-mysql bash
   ```
2. Iniciar sesión en el cliente de MySQL (pedirá la contraseña de `root`):
   ```bash
   mysql -u root -p
   ```
3. Una vez dentro del prompt `mysql>`, ejecutar el script:
   ```sql
   SOURCE /tmp/world.sql;
   ```
4. Verificar que la base de datos y sus tablas se han creado correctamente:
   ```sql
   USE world;
   SHOW TABLES;
   ```
   *(Deberían aparecer las tablas `city`, `country` y `countrylanguage`).*
5. Salir de MySQL y del contenedor:
   ```sql
   exit;
   ```
   ```bash
   exit
   ```

### Opción B: En un solo comando (desde fuera del contenedor)
Si se prefiere inyectar el archivo directamente sin abrir sesión interactiva:

```bash
docker exec -i diurno-mysql mysql -u root -p < world-db/world.sql
```

---

## 4. Limpieza de archivos temporales (Opcional)
Para eliminar los archivos descargados tanto en el equipo local como dentro del contenedor:

```bash
rm -rf world-db world-db.tar.gz
docker exec diurno-mysql rm /tmp/world.sql
```
