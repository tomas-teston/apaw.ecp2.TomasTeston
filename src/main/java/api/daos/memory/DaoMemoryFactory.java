package api.daos.memory;

import api.daos.DaoFactory;
import api.daos.JefeDao;

public class DaoMemoryFactory extends DaoFactory {

    private JefeDao jefeDao;

    @Override public JefeDao getJefeDao() {
        if (this.jefeDao == null) {
            this.jefeDao = new JefeDaoMemory();
        }
        return this.jefeDao;
    }
}
