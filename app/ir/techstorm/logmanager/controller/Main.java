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

    private HashMap<String, ActorRef> actorBag;

    @BodyParser.Of(BodyParser.Text.class)
    public Result index() {

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


        return ok("log added");
    }

    private JsonNode lastTimeRecommended(JsonNode node) throws Exception {
        StringBuilder finalResult = new StringBuilder();

        URL url = new URL("http://127.0.0.1:9200" + "/_search?size=1");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Basic a2liYW5hX2FkbWluOkB3MyQwTTNLIXwzYU5h");
        conn.setRequestProperty("Content-Type", "application/json");
        for (JsonNode singleItem : node.get("items")) {
            String input = "{\n" +
                    "  \"sort\" : [\n" +
                    "        { \"@timestamp\" : {\"order\" : \"desc\"}}],\n" +
                    "  \"query\": {\n" +
                    "    \"bool\": {\n" +
                    "      \"must\": [\n" +
                    "        {\n" +
                    "          \"match\": {\n" +
                    "            \"message.action\": \"recommend\"\n" +
                    "          }\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"match\": {\n" +
                    "            \"message.items.item\": \"" + singleItem.get("item").asText("nothing") + "\"\n" +
                    "          }\n" +
                    "        }\n" +
                    "      ]\n" +
                    "    \n" +
                    "    }\n" +
                    "  }\n" +
                    "}";

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();
            System.err.println(conn.getResponseCode());
            System.err.println(conn.getResponseMessage());
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                finalResult.append(output);
            }
            conn.disconnect();
        }
        ObjectMapper om = new ObjectMapper();
        JsonNode temp = om.readTree(finalResult.toString());
        JsonNode hits = (temp.get("hits").get("hits"));
        if (hits.isArray()) {
            for (JsonNode hit : hits) {
                ObjectNode tempNode = ((ObjectNode) node).put("RecDate", (hit.get("_source").get("Date").asText()));
                tempNode.put("RecID", hit.get("_id").asText());
                return (JsonNode) tempNode;
            }
        }

        return null;

    }


}
