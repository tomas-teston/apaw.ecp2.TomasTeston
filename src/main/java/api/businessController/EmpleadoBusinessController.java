package api.businessController;

import java.util.stream.Collectors;

import api.daos.DaoFactory;
import api.dtos.EmpleadoCreationDto;
import api.entities.Empleado;
import api.entities.Jefe;
import api.exceptions.NotFoundException;

public class EmpleadoBusinessController {

    public String create(EmpleadoCreationDto empleadoCreationDto) {
        Jefe jefe = null;
        if (empleadoCreationDto.getJefeId() != null) {
            jefe = DaoFactory.getFactory().getJefeDao().read(empleadoCreationDto.getJefeId())
                    .orElseThrow(() -> new NotFoundException("User (" + empleadoCreationDto.getJefeId() + ")"));
        }
        Empleado empleado = new Empleado(empleadoCreationDto.getNombre(), empleadoCreationDto.getEdad(), empleadoCreationDto.getDepartamento(), jefe);
        DaoFactory.getFactory().getEmpleadoDao().save(empleado);
        return empleado.getId();
    }

}
