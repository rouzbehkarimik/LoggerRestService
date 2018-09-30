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
<<<<<<< HEAD

      /*  ObjectMapper om = new ObjectMapper();
        try {
            JsonNode rnode = om.readTree(request().body().asText());
            if ("0".equals(rnode.get("userId").asText("0"))) {
                throw new Exception("This is a recommend.");
            }
            if (rnode.get("items").isArray() == false) throw new Exception("Not An Array");
            JsonNode finalNode = lastTimeRecommended(rnode);
            if (finalNode == null) throw new Exception("Result Empty");
            log.warn(rnode.toString());
        } catch (Exception e) {
            e.printStackTrace(); */
            log.warn(request().body().asText());


=======
        log.warn(request().body().asText());
>>>>>>> 1d07d3c50efd15ee8f11ebc87554902b605bd1ee
        return ok("log added");
    }
}
