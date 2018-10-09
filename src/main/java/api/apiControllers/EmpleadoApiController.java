package api.apiControllers;

import java.util.List;

import api.businessController.EmpleadoBusinessController;
import api.dtos.EmpleadoCreationDto;
import api.dtos.EmpleadoListAllDto;
import api.exceptions.ArgumentNotValidException;

public class EmpleadoApiController {

    public static final String EMPLEADOS = "/empleados";

    public static final String ID_ID = "/{id}";

    private EmpleadoBusinessController empleadoBusinessController = new EmpleadoBusinessController();

    public String create(EmpleadoCreationDto empleadoCreationDto) {
        this.validate(empleadoCreationDto, "empleadoCreationDto");
        this.validate(empleadoCreationDto.getNombre(), "empleadoCreationDto nombre");
        this.validate(empleadoCreationDto.getEdad(), "empleadoCreationDto edad");
        return this.empleadoBusinessController.create(empleadoCreationDto);
    }

    public List<EmpleadoListAllDto> readAll() {
        return this.empleadoBusinessController.readAll();
    }

    public void delete(String id) {
        this.empleadoBusinessController.delete(id);
    }

    private void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + " is missing");
        }
    }
}
