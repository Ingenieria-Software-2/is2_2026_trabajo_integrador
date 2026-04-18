Para correr el contenedor:
Comando para hacer el build de la imagen (Primera vez o después de cambios en código):

docker build -t proye-is .

Correr el contenedor montando la carpeta db/ como volumen, para que la base de datos persista entre reinicios:

docker run -p 8080:8080 -v $(pwd)/db:/app/db proye-is


