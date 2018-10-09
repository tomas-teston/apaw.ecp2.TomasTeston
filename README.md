# Arquitectura de un mini API-Rest simulado
> Este proyecto pretende ser un ejemplo sencillo de arquitectura de un API-Rest simulado para comprender las capas que intervienen y la organización de los diferentes tipos de test, con integración continua y control de la calidad del código
> #### [Máster en Ingeniería Web por la Universidad Politécnica de Madrid (miw-upm)](http://miw.etsisi.upm.es)
> #### Asignatura: *Arquitectura y Patrones para Aplicaciones Web*

[![Build Status](https://travis-ci.org/tomas-teston/apaw.ecp2.TomasTeston.svg?branch=master)](https://travis-ci.org/miw-upm/APAW-themes-layers)
[![Build Status](https://sonarcloud.io/api/project_badges/measure?project=es.upm.miw%3Aapaw.ecp2.TomasTeston&metric=alert_status)](https://travis-ci.org/miw-upm/APAW-themes-layers)

## Tecnologías necesarias
* Java
* Maven
* GitHub

## Diseño de entidades
[![UML](https://github.com/tomas-teston/apaw.ecp2.TomasTeston/blob/master/src/main/resources/modeloDominio.png)](https://github.com/tomas-teston/apaw.ecp2.TomasTeston/blob/master/src/main/resources/modeloDominio.png)

## Arquitectura
![themes-entities-class-diagram](https://github.com/miw-upm/APAW-themes-layers/blob/develop/docs/themes-architecture-diagram.png)

### Responsabilidades
#### Dispatcher
* Centraliza las peticiones y hace de repartidor
* Recupera los datos de la petición y los pasa como parámetros de método
* Captura las excepciones y las convierte en errores Http
#### restControllers
* Define el path del recurso
* Valida la entrada
* Traspasa la petición a los controladores de la capa de negocio
#### businessControllers
* Procesa la petición, apoyándose en los DAO’s
* Crea las entidades a partir de los DTO’s
* Gestiona la respuesta a partir de las entidades. Delega en los DTO’s la creación a partir de la entidad
#### daos
* Gestionan la BD
#### entities
* Son las entidades persistentes en la BD

## API
### POST /jefes
#### Parámetros del cuerpo
- `nombre`: String (**requerido**)
- `telefono`: Integer (**requerido**)
#### Respuesta
- 200 OK 
  - `id`: String
- 403 BAD_REQUEST
---
### PUT /jefes/{id}
#### Parámetros del cuerpo
- `nombre`: String (**requerido**)
- `telefono`: Integer (**requerido**)
#### Respuesta
- 200 OK 
- 403 BAD_REQUEST
- 404 NOT_FOUND
--- 
### POST /empleados
#### Parámetros del cuerpo
- `nombre`: String (**requerido**)
- `edad`: Integer (**requerido**)
- `departamento`: Departamento
- `jefeId`: String
#### Respuesta
- 200 OK 
- 403 BAD_REQUEST
---
### GET /empleados
#### Respuesta
- 200 OK 
  - `[ {id:String, nombre:String, edad:Integer} ]`: String
---
### DELETE /empleados/{id}
#### Respuesta
- 200 OK 
- 404 NOT_FOUND
---
### POST /empleados/{id}/nominas
#### Parámetros del cuerpo
- `salario`: Double (**requerido**)
#### Respuesta
- 200 OK 
- 403 BAD_REQUEST
- 404 NOT_FOUND
---
### PATH /empleados/{id}/departamento
#### Parámetros del cuerpo
- `departamento`: String (**requerido**)
#### Respuesta
- 200 OK 
- 403 BAD_REQUEST
- 404 NOT_FOUND
---
### GET /empleados/search?q=average:>=30
#### Respuesta
- 200 OK
  - `[ {id:String, nombre:String, edad:Integer} ]`
- 403 BAD_REQUEST
---