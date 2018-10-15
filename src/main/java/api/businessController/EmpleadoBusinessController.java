package api.businessController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import api.daos.DaoFactory;
import api.dtos.EmpleadoCreationDto;
import api.dtos.EmpleadoListAllDto;
import api.entities.Departamento;
import api.entities.Empleado;
import api.entities.Jefe;
import api.entities.Nomina;
import api.entities.NominaBuilder;
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

    public void createNomina(String nominaId, Double salario) {
        Empleado empleado = DaoFactory.getFactory().getEmpleadoDao().read(nominaId)
                .orElseThrow(() -> new NotFoundException("Empleado (" + nominaId + ")"));
        empleado.getNominas().add(new NominaBuilder().salario(salario).build());
        DaoFactory.getFactory().getEmpleadoDao().save(empleado);
    }

    public void delete(String id) {
        DaoFactory.getFactory().getEmpleadoDao().deleteById(id);
    }

    public void updateCategory(String empleadoId, Departamento departamento) {
        Empleado empleado = DaoFactory.getFactory().getEmpleadoDao().read(empleadoId)
                .orElseThrow(() -> new NotFoundException("Empleado (" + empleadoId + ")"));
        empleado.setDepartamento(departamento);
        DaoFactory.getFactory().getEmpleadoDao().save(empleado);
    }

    public List<EmpleadoListAllDto> findBySalaryAverageGreaterThanEqual(Double value) {
        List<EmpleadoListAllDto> empleados = DaoFactory.getFactory().getEmpleadoDao().findByNominasNotEmpty().stream()
                .filter(empleadao -> this.average(empleadao) >= value)
                .map((empleado) -> new EmpleadoListAllDto(empleado.getId(), empleado.getNombre(), empleado.getEdad())).collect(Collectors.toList());
        return empleados;
    }

    private Double average(Empleado empleado) {
        return empleado.getNominas()
                .stream().mapToDouble(Nomina::getSalario).average()
                .orElse(Double.NaN);
    }

}
