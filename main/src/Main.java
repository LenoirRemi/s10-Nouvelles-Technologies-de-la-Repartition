import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        MainJson instance  = new MainJson();
        MainXml instanceXml = new MainXml();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();

        // String responseGetJsonUsers = instance.httpRequestGetJson("http://localhost:8081/users");

        String responseGetJsonSpecificUserBefore = instance.httpRequestGetJson("http://localhost:8081/users/1");
        JsonElement je = jp.parse(responseGetJsonSpecificUserBefore);
        String prettyJsonStringBefore = gson.toJson(je);
        System.out.println(prettyJsonStringBefore);

        String responsePostXml = instanceXml.httpRequestPostXml("http://localhost:8081/transaction", 1, -100);
        System.out.println(responsePostXml);

        String responseresponseGetJsonSpecificUserAfter = instance.httpRequestGetJson("http://localhost:8081/users/1");
        JsonElement je2 = jp.parse(responseresponseGetJsonSpecificUserAfter);
        String prettyJsonStringAfter = gson.toJson(je2);
        System.out.println(prettyJsonStringAfter);

    }
}
