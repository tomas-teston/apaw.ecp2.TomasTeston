package api;

import org.junit.jupiter.api.Test;

import api.apiControllers.JefeApiController;
import api.dtos.JefeDto;
import http.Client;
import http.HttpException;
import http.HttpRequest;
import http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JefeTest {

    @Test
    void testCreateJefe() {
        this.createJefe();
    }

    private String createJefe() {
        HttpRequest request = HttpRequest.builder(JefeApiController.JEFES).body(new JefeDto("Pablo", "918551352")).post();
        return (String) new Client().submit(request).getBody();
    }


    @Test
    void testJefeInvalidRequest() {
        HttpRequest request = HttpRequest.builder(JefeApiController.JEFES).path("/invalid").body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreateJefeWithoutJefeDto() {
        HttpRequest request = HttpRequest.builder(JefeApiController.JEFES).body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreateJefeWithoutJefeDtoNombre() {
        HttpRequest request = HttpRequest.builder(JefeApiController.JEFES).body(new JefeDto(null, "")).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreateJefeWithoutJefeDtoTelefono() {
        HttpRequest request = HttpRequest.builder(JefeApiController.JEFES).body(new JefeDto("", null)).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

}
