# Examen Arquitectura Empresarial

Repositorio del examen práctico con un microservicio Spring Boot protegido con Amazon Cognito.

Nombre del repositorio:
**examen_003_ae_Caillagua_Edgar**

---

## Cómo ejecutar el servicio

### 1. Levantar la base de datos (PostgreSQL)

El proyecto incluye un `docker-compose.yml`.

```bash
docker compose up -d

Ejecutar el servicio
./gradlew clean bootRun


El servicio se levanta en:
http://localhost:8080


Cómo configurar variables de entorno necesarias

La configuración se realiza en el archivo:

src/main/resources/application.yml


Configuración de seguridad con Amazon Cognito:

spring:
  security:
    oauth2:
      resourceserver:
        jwt:
          issuer-uri: https://cognito-idp.us-east-2.amazonaws.com/us-east-2_AhNXLlgkY


Cómo probar cada endpoint
1. Endpoint público (sin autenticación)
GET /public/health


Ejemplo:

curl http://localhost:8080/public/health


Respuesta esperada:

{
  "status": "OK"
}

2. Endpoints autenticados (requieren token)

Se debe usar el access_token generado por Amazon Cognito.

En Postman:

Authorization → Bearer Token

Pegar el access_token

Endpoints:

GET /api/rules
GET /api/rules/{id}

3. Endpoints ADMIN

Requieren un usuario que pertenezca al grupo ADMIN en Cognito.

Endpoints:

POST /api/rules
PUT /api/rules/{id}
PATCH /api/rules/{id}/toggle

Claims utilizados
updatedBy

Claim utilizado: sub

Se obtiene del token JWT y se usa para auditoría de creación y actualización de registros.

Rol ADMIN

Claim utilizado: cognito:groups

Valor esperado: ADMIN

Ejemplo dentro del JWT:

"cognito:groups": ["ADMIN"]