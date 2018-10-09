package api;

import org.junit.jupiter.api.Test;

import api.apiControllers.EmpleadoApiController;
import api.apiControllers.JefeApiController;
import api.dtos.EmpleadoCreationDto;
import api.dtos.JefeDto;
import api.entities.Departamento;
import http.Client;
import http.HttpException;
import http.HttpRequest;
import http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class EmpleadoTest {

    @Test
    void testCreateEmpleado() {
        this.createEmpleado("Empleado uno", 30);
    }

    private String createEmpleado(String nombre, int edad) {
        String jefeId = this.createJefe();
        HttpRequest request = HttpRequest.builder(EmpleadoApiController.EMPLEADOS)
                .body(new EmpleadoCreationDto(nombre, edad, Departamento.VENTAS, jefeId)).post();
        return (String) new Client().submit(request).getBody();
    }

    private String createJefe() {
        HttpRequest request = HttpRequest.builder(JefeApiController.JEFES).body(new JefeDto("Manuel","918551324")).post();
        return (String) new Client().submit(request).getBody();
    }

    @Test
    void testCreateEmpleadoJefeIdNotFound() {
        HttpRequest request = HttpRequest.builder(EmpleadoApiController.EMPLEADOS)
                .body(new EmpleadoCreationDto("Manuel", 30, Departamento.DESARROLLO, "h3rFdEsw")).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }

    @Test
    void testCreateEmpleadoWithoutCategoryDepartamento() {
        HttpRequest request = HttpRequest.builder(EmpleadoApiController.EMPLEADOS)
                .body(new EmpleadoCreationDto("Manuel", 30,  null, null)).post();
        new Client().submit(request);
    }

}
