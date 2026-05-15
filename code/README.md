## Montar contenedor con Dockerfile:

*Hacer el build de la imagen (Primera vez o después de cambios en código) en terminal:*

docker build -t proye-is .

*Correr el contenedor montando la carpeta db/ como volumen, para que la base de datos persista entre reinicios:*

docker run -p 8080:8080 -v $(pwd)/db:/app/db proye-is

o

sudo docker compose up --build

*corriendo en localhost:8080*


*Ver logs de contenedores (tracking de actividad basicamente)*

docker compose logs -f