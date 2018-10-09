package api.daos.memory;

import api.daos.DaoFactory;
import api.daos.EmpleadoDao;
import api.daos.JefeDao;

public class DaoMemoryFactory extends DaoFactory {

    private JefeDao jefeDao;

    private EmpleadoDao empleadoDao;

    @Override public JefeDao getJefeDao() {
        if (this.jefeDao == null) {
            this.jefeDao = new JefeDaoMemory();
        }
        return this.jefeDao;
    }

    @Override public EmpleadoDao getEmpleadoDao() {
        if (this.empleadoDao == null) {
            this.empleadoDao = new EmpleadoDaoMemory();
        }
        return this.empleadoDao;
    }
}
