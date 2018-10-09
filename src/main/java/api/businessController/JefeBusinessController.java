package api.businessController;

import api.daos.DaoFactory;
import api.dtos.JefeDto;
import api.entities.Jefe;
import api.exceptions.NotFoundException;

public class JefeBusinessController {

    public String create(JefeDto jefeDto) {
        Jefe jefe = new Jefe(jefeDto.getNombre(), jefeDto.getTelefono());
        DaoFactory.getFactory().getJefeDao().save(jefe);
        return jefe.getId();
    }

    public void update(String id, JefeDto jefeDto){
        Jefe jefe = DaoFactory.getFactory().getJefeDao().read(id).orElseThrow(() -> new NotFoundException("User id: " + id));
        jefe.setNombre(jefeDto.getNombre());
        jefe.setTelefono(jefeDto.getTelefono());
        DaoFactory.getFactory().getJefeDao().save(jefe);
    }
}
