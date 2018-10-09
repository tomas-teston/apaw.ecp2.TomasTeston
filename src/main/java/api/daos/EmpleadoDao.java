package api.daos;

import java.util.List;

import api.entities.Empleado;

public interface EmpleadoDao extends GenericDao<Empleado, String> {
    List<Empleado> findByNominasNotEmpty();
}
