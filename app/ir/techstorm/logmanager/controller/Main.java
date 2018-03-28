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


        String msg = "{" +
                "\"vendor\":\"taghche\"," +
                "\"level\":\"gold\"," +
                "\"device\":\"ios\"," +
                "\"action\":\"entity 5435346654 suggested\"," +
                "\"user\":\"9876535674456\"" +
                "}";
        String msg2 = "{" +
                "\"vendor\":\"taghche\"," +
                "\"level\":\"gold\"," +
                "\"device\":\"ios\"," +
                "\"action\":\"entity 5435346654 visited\"," +
                "\"user\":\"9876535674456\"" +
                "}";
        String msg3 = "{" +
                "\"vendor\":\"taghche\"," +
                "\"level\":\"gold\"," +
                "\"device\":\"ios\"," +
                "\"action\":\"entity 5435346654 added to basket\"," +
                "\"user\":\"9876535674456\"" +
                "}";
        //System.out.println("bodyPart is:"+request().body().asText());
        //log.warn(request().body().asText());


        //log.warn("{\"user\":\"rouzi\",\"level\":\"gold\",\"os\":\"ios\",\"action\":\"entity 54534645 suggested\"}");
        log.warn(request().body().asText());
        return ok("Hello World!");
    }
}
