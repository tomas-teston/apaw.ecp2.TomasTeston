package api;

import api.apiControllers.EmpleadoApiController;
import api.apiControllers.JefeApiController;
import api.daos.DaoFactory;
import api.daos.memory.DaoMemoryFactory;
import api.dtos.EmpleadoCreationDto;
import api.dtos.JefeDto;
import api.entities.Departamento;
import api.exceptions.ArgumentNotValidException;
import api.exceptions.NotFoundException;
import api.exceptions.RequestInvalidException;
import http.HttpRequest;
import http.HttpResponse;
import http.HttpStatus;

public class Dispatcher {

    static {
        DaoFactory.setFactory(new DaoMemoryFactory());
    }

    private JefeApiController jefeApiController = new JefeApiController();

    private EmpleadoApiController empleadoApiController = new EmpleadoApiController();


    public void submit(HttpRequest request, HttpResponse response) {
        String ERROR_MESSAGE = "{'error':'%S'}";
        try {
            switch (request.getMethod()) {
                case POST:
                    this.doPost(request, response);
                    break;
                case PUT:
                    this.doPut(request);
                    break;
                case GET:
                    this.doGet(request, response);
                    break;
                case DELETE:
                    this.doDelete(request);
                    break;
                case PATCH:
                    this.doPatch(request);
                    break;
                default: // Unexpected
                    throw new RequestInvalidException("method error: " + request.getMethod());
            }

        } catch(ArgumentNotValidException | RequestInvalidException exception) {
            response.setBody(String.format(ERROR_MESSAGE, exception.getMessage()));
            response.setStatus(HttpStatus.BAD_REQUEST);
        } catch (NotFoundException exception) {
            response.setBody(String.format(ERROR_MESSAGE, exception.getMessage()));
            response.setStatus(HttpStatus.NOT_FOUND);
        } catch(Exception exception) {
            exception.printStackTrace();
            response.setBody(String.format(ERROR_MESSAGE, exception));
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void doPost(HttpRequest request, HttpResponse response) {
        if (request.isEqualsPath(JefeApiController.JEFES)) {
            response.setBody(this.jefeApiController.create((JefeDto) request.getBody()));
        } else if (request.isEqualsPath(EmpleadoApiController.EMPLEADOS)) {
            response.setBody(this.empleadoApiController.create((EmpleadoCreationDto) request.getBody()));
        } else if (request.isEqualsPath(EmpleadoApiController.EMPLEADOS + EmpleadoApiController.ID_ID + EmpleadoApiController.NOMINAS)) {
            this.empleadoApiController.createNomina(request.getPath(1), (Double) request.getBody());
        } else {
            throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
        }
    }

    private void doPut(HttpRequest request) {
        if (request.isEqualsPath(JefeApiController.JEFES + JefeApiController.ID_ID)) {
            this.jefeApiController.update(request.getPath(1), (JefeDto) request.getBody());
        } else {
            throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
        }
    }

    private void doGet(HttpRequest request, HttpResponse response) {
        if (request.isEqualsPath(EmpleadoApiController.EMPLEADOS)) {
            response.setBody(this.empleadoApiController.readAll());
        } else if (request.isEqualsPath(EmpleadoApiController.EMPLEADOS + EmpleadoApiController.SEARCH)) {
            response.setBody(this.empleadoApiController.find(request.getParams().get("q")));
        } else {
            throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
        }
    }

    private void doDelete(HttpRequest request) {
        if (request.isEqualsPath(EmpleadoApiController.EMPLEADOS + EmpleadoApiController.ID_ID)) {
            this.empleadoApiController.delete(request.getPath(1));
        } else {
            throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
        }
    }

    private void doPatch(HttpRequest request) {
        if (request.isEqualsPath(EmpleadoApiController.EMPLEADOS + EmpleadoApiController.ID_ID + EmpleadoApiController.DEPARTAMENTO)) {
            this.empleadoApiController.updateDepartamento(request.getPath(1), (Departamento) request.getBody());
        } else {
            throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
        }
    }

}
