# User_Managment

El proyecto consta de una clase DataInitializer (script de creación) que genera una bbdd temporal con data de 5 usuarios.

Pasos para probar:

1.- Asegurarse de tener JDK versión 8 instalada en la máquina

2.- Clonar proyecto desde:
https://github.com/manuelGarciaT/User_Managment

3.- Actualizar dependencias Maven

4.- Levantar el proyecto (Spring-boot:run)

5.- Desde el navegador abrir:
http://localhost:8080/swagger-ui/index.html

6.- Desplegar “user-controller” para visualizar cada una de las peticiones:
GET (buscar todo)
POST (crear usuario)
GET (buscar por ID)
DELETE (eliminar usuario)
PUT (actualizar usuario)

