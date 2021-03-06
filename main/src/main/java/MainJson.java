
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainJson {
    public String httpRequestGetJson(String url_) throws IOException {
        URL url = new URL (url_);

        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");

        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");

        con.setDoOutput(true);

        String method = con.getRequestMethod();
        int code = con.getResponseCode();
        System.out.println("Statut code de la requete "+method+" sur l'url "+url+" : "+code);
        System.out.println("Réponse :");

        try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))){
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            //System.out.println(response.toString());
            return response.toString();
        }
    }
}
