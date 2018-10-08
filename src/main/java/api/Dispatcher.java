package api;

import api.daos.DaoFactory;
import api.exceptions.ArgumentNotValidException;
import api.exceptions.NotFoundException;
import api.exceptions.RequestInvalidException;
import http.HttpRequest;
import http.HttpResponse;
import http.HttpStatus;

public class Dispatcher {

    static {
        DaoFactory.setFactory(new DaoMemoryFactory());
    }

    private UserApiController userApiController = new UserApiController();

    private SuggestionApiController suggestionApiController = new SuggestionApiController();

    private ThemeApiController themeApiController = new ThemeApiController();

    public void submit(HttpRequest request, HttpResponse response) {
        String ERROR_MESSAGE = "{'error':'%S'}";
        try {
            switch (request.getMethod()) {
                case POST:
                    this.doPost(request, response);
                    break;
                case GET:
                    this.doGet(request, response);
                    break;
                case PUT:
                    this.doPut(request);
                    break;
                case PATCH:
                    this.doPatch(request);
                    break;
                case DELETE:
                    this.doDelete(request);
                    break;
                default: // Unexpected
                    throw new RequestInvalidException("method error: " + request.getMethod());
            }
        } catch (ArgumentNotValidException | RequestInvalidException exception) {
            response.setBody(String.format(ERROR_MESSAGE, exception.getMessage()));
            response.setStatus(HttpStatus.BAD_REQUEST);
        } catch (NotFoundException exception) {
            response.setBody(String.format(ERROR_MESSAGE, exception.getMessage()));
            response.setStatus(HttpStatus.NOT_FOUND);
        } catch (Exception exception) {  // Unexpected
            exception.printStackTrace();
            response.setBody(String.format(ERROR_MESSAGE, exception));
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void doPost(HttpRequest request, HttpResponse response) {
        if (request.isEqualsPath(UserApiController.USERS)) {
            response.setBody(this.userApiController.create((UserDto) request.getBody()));
        } else if (request.isEqualsPath(SuggestionApiController.SUGGESTIONS)) {
            this.suggestionApiController.create((SuggestionDto) request.getBody());
        } else if (request.isEqualsPath(ThemeApiController.THEMES)) {
            response.setBody(this.themeApiController.create((ThemeCreationDto) request.getBody()));
        } else if (request.isEqualsPath(ThemeApiController.THEMES + ThemeApiController.ID_ID + ThemeApiController.VOTES)) {
            this.themeApiController.createVote(request.getPath(1), (Integer) request.getBody());
        } else {
            throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
        }
    }

    private void doGet(HttpRequest request, HttpResponse response) {
        if (request.isEqualsPath(ThemeApiController.THEMES)) {
            response.setBody(this.themeApiController.readAll());
        } else if (request.isEqualsPath(ThemeApiController.THEMES + ThemeApiController.ID_ID + ThemeApiController.AVERAGE)) {
            response.setBody(this.themeApiController.readAverage(request.getPath(1)));
        } else if (request.isEqualsPath(ThemeApiController.THEMES + ThemeApiController.SEARCH)) {
            response.setBody(this.themeApiController.find(request.getParams().get("q")));
        } else {
            throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
        }
    }

    private void doPut(HttpRequest request) {
        if (request.isEqualsPath(UserApiController.USERS + UserApiController.ID_ID)) {
            this.userApiController.update(request.getPath(1), (UserDto) request.getBody());
        } else {
            throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
        }
    }

    private void doPatch(HttpRequest request) {
        if (request.isEqualsPath(ThemeApiController.THEMES + ThemeApiController.ID_ID + ThemeApiController.CATEGORY)) {
            this.themeApiController.updateCategory(request.getPath(1), (Category) request.getBody());
        } else {
            throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
        }
    }

    private void doDelete(HttpRequest request) {
        if (request.isEqualsPath(ThemeApiController.THEMES + ThemeApiController.ID_ID)) {
            this.themeApiController.delete(request.getPath(1));
        } else {
            throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
        }
    }

}
