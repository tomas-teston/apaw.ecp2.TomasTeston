package api.businessController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import api.daos.DaoFactory;
import api.dtos.EmpleadoCreationDto;
import api.dtos.EmpleadoListAllDto;
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

    public List<EmpleadoListAllDto> readAll() {
        List<Empleado> empleados = DaoFactory.getFactory().getEmpleadoDao().findAll();
        List<EmpleadoListAllDto> listAllDto = new ArrayList<>();
        for (Empleado empleado : empleados) {
            listAllDto.add(new EmpleadoListAllDto(empleado.getId(), empleado.getNombre(), empleado.getEdad()));
        }
        return listAllDto;
    }

}
