package ir.techstorm.logmanager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

public class Main extends Controller {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    @BodyParser.Of(BodyParser.Text.class)
    public Result index() {



        log.warn(request().body().asText());
        return ok("log added");
    }
}
