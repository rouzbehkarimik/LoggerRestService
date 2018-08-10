package ir.techstorm.logmanager.controller;

import akka.actor.ActorRef;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Singleton;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

@Singleton
public class Main extends Controller {

    private static final Logger log = LoggerFactory.getLogger(Main.class);
    @BodyParser.Of(BodyParser.Text.class)
    public Result index() {
        log.warn(request().body().asText());
        return ok("log added");
    }
}
