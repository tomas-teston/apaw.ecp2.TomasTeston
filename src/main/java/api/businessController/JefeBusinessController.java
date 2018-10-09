package api.businessController;

import api.daos.DaoFactory;
import api.dtos.JefeDto;
import api.entities.Jefe;

public class JefeBusinessController {

    public String create(JefeDto jefeDto) {
        Jefe jefe = new Jefe(jefeDto.getNombre(), jefeDto.getTelefono());
        DaoFactory.getFactory().getJefeDao().save(jefe);
        return jefe.getId();
    }
}
