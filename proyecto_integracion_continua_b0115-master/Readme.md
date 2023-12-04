## Descripción
Aplicación creada para la materia integración continua, del politecnico gran colombiano, esta aplicación usa la api publica de https://api.rawg.io, para obtener información sobre los juegos relacionados con un desarrollador de videojuegos, solo se consta de un endpoint disponible '/developer/{name}'

## Arquitectura
Se usa Java 17 y programación reactiva con project Reactor para la implementación de los flujos y redis para el manejo de una caché local de los recursos utilizados.

### Flujo
* Ingresa request.
* Busca en redis.
* Sino en cuentra genera mutiples request a la api publica de rawg para obtener el desarrollador e información de sus juegos.
* Almacena en redis lo encontrado.
* Retorna el resultado al usuario.

### Herramientas
* Java 17.
* Springboot.
* Project Reactor.
* Redis.

### CI/CD
Links e información privada de CI/CD no es incluida por seguridad
* Jenkins.
* Travis CI.
* Codeship.

## Cómo ejecutar la aplicación (usando Docker Compose)
1. Asegúrate de tener Docker y Docker Compose instalados en tu máquina.
2. Clona este repositorio en tu máquina local.
3. Navega al directorio del proyecto utilizando la terminal o línea de comandos.
4. Ejecuta el siguiente comando:
    ```
    docker-compose up --build
    ```
5. Vas a ver un print de un valor que fue guardado y recuperado del redis en el otro.