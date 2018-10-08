package api.daos;

import org.apache.logging.log4j.LogManager;

public abstract class DaoFactory {

    private static DaoFactory factory = null;

    public static DaoFactory getFactory() {
        assert factory != null;
        return factory;
    }

    public static void setFactory(DaoFactory factory) {
        DaoFactory.factory = factory;
        LogManager.getLogger(DaoFactory.class).debug("   create DaoMemoryFactory");

    }
}
