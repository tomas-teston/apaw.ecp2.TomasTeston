package api.daos.memory;

import api.daos.JefeDao;
import api.entities.Jefe;

public class JefeDaoMemory extends GenericDaoMemory<Jefe> implements JefeDao {

    @Override public String getId(Jefe jefe) {
        return jefe.getId();
    }

    @Override public void setId(Jefe jefe, String id) {
        jefe.setId(id);
    }
}
