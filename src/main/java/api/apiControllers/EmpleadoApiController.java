package api.apiControllers;

import java.util.List;

import api.businessController.EmpleadoBusinessController;
import api.dtos.EmpleadoCreationDto;
import api.dtos.EmpleadoListAllDto;
import api.entities.Departamento;
import api.exceptions.ArgumentNotValidException;

public class EmpleadoApiController {

    public static final String EMPLEADOS = "/empleados";

    public static final String ID_ID = "/{id}";

    public static final String NOMINAS = "/nominas";

    public static final String DEPARTAMENTO = "/departamento";

    public static final String SEARCH = "/search";

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

    public void createNomina(String nominaId, Double salario){
        this.validate(salario, "salario");
        if (salario < 0) {
            throw new ArgumentNotValidException("the salary must be greater than 0");
        }
        this.empleadoBusinessController.createNomina(nominaId, salario);
    }

    public void updateDepartamento(String empleadoId, Departamento departamento) {
        this.validate(departamento, "departamento");
        this.empleadoBusinessController.updateCategory(empleadoId, departamento);
    }

    public List<EmpleadoListAllDto> find(String query) {
        this.validate(query, "query param q");
        if (!"average".equals(query.split(":>=")[0])) {
            throw new ArgumentNotValidException("query param q is incorrect, missing 'average:>='");
        }
        return this.empleadoBusinessController.findBySalaryAverageGreaterThanEqual(Double.valueOf(query.split(":>=")[1]));
    }

    private void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + " is missing");
        }
    }
}
