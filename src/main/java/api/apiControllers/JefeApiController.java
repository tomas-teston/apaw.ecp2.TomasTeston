package api.apiControllers;

import api.businessController.JefeBusinessController;
import api.dtos.JefeDto;
import api.exceptions.ArgumentNotValidException;

public class JefeApiController {

    public static final String JEFES = "/jefes";

    private JefeBusinessController jefeBusinessController = new JefeBusinessController();

    public String create(JefeDto jefeDto) {
        this.validate(jefeDto, "jefeDto");
        this.validate(jefeDto.getNombre(), "JefeDto nombre");
        this.validate(jefeDto.getTelefono(), "JefeDto telefono");
        return this.jefeBusinessController.create(jefeDto);
    }

    private void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + " is missing");
        }
    }
}
