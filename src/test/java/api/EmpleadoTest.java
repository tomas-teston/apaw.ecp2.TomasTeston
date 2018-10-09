package api;

import java.util.List;

import org.junit.jupiter.api.Test;

import api.apiControllers.EmpleadoApiController;
import api.apiControllers.JefeApiController;
import api.dtos.EmpleadoCreationDto;
import api.dtos.EmpleadoListAllDto;
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

    @Test
    void testReadAll() {
        for (int i = 0; i < 5; i++) {
            this.createEmpleado("Empleado - " + i, 30+i);
        }
        HttpRequest request = HttpRequest.builder(EmpleadoApiController.EMPLEADOS).get();
        List<EmpleadoListAllDto> empleados = (List<EmpleadoListAllDto>) new Client().submit(request).getBody();
        assertTrue(empleados.size() >= 5);
    }

    @Test
    void testDelete() {
        String id = this.createEmpleado("Manuel", 30);
        HttpRequest request1 = HttpRequest.builder(EmpleadoApiController.EMPLEADOS).get();
        int count = ((List<EmpleadoListAllDto>) new Client().submit(request1).getBody()).size();
        HttpRequest request2 = HttpRequest.builder(EmpleadoApiController.EMPLEADOS).path(JefeApiController.ID_ID)
                .expandPath(id).delete();
        new Client().submit(request2);
        assertTrue(((List<EmpleadoListAllDto>) new Client().submit(request1).getBody()).size() < count);
    }

    @Test
    void testNominaEmpleado() {
        String id = this.createEmpleado("Manuel", 30);
        nominaEmpleado(id, 1500);
    }

    private void nominaEmpleado(String emmpleadoId, double salario) {
        HttpRequest request = HttpRequest.builder(EmpleadoApiController.EMPLEADOS).path(EmpleadoApiController.ID_ID)
                .expandPath(emmpleadoId).path(EmpleadoApiController.NOMINAS).body(salario).post();
        new Client().submit(request);
    }

    @Test
    void testNominaEmpleadoEmpleadoIdNotFound() {
        HttpException exception = assertThrows(HttpException.class, () -> this.nominaEmpleado("h3rFdEsw", 5000));
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }

    @Test
    void testNominaEmpleadoNominaSalaryGreaterThan0() {
        String id = this.createEmpleado("Manuel", 30);
        HttpException exception = assertThrows(HttpException.class, () -> this.nominaEmpleado("h3rFdEsw", -1));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testUpdateDepartamento() {
        String id = this.createEmpleado("Manuel", 30);
        HttpRequest request = HttpRequest.builder(EmpleadoApiController.EMPLEADOS).path(EmpleadoApiController.ID_ID)
                .expandPath(id).path(EmpleadoApiController.DEPARTAMENTO).body(Departamento.RRHH).patch();
        new Client().submit(request);
    }

}
