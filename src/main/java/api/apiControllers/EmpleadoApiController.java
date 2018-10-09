package api.apiControllers;

import api.businessController.EmpleadoBusinessController;
import api.dtos.EmpleadoCreationDto;
import api.exceptions.ArgumentNotValidException;

public class EmpleadoApiController {

    public static final String EMPLEADOS = "/empleados";

    private EmpleadoBusinessController empleadoBusinessController = new EmpleadoBusinessController();

    public String create(EmpleadoCreationDto empleadoCreationDto) {
        this.validate(empleadoCreationDto, "empleadoCreationDto");
        this.validate(empleadoCreationDto.getNombre(), "empleadoCreationDto nombre");
        this.validate(empleadoCreationDto.getEdad(), "empleadoCreationDto edad");
        return this.empleadoBusinessController.create(empleadoCreationDto);
    }


    private void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + " is missing");
        }
    }
}
