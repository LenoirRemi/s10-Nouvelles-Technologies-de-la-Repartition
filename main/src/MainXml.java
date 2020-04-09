import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainXml {
    public String httpRequestPostXml(String url_, int id, float balance) throws IOException {
        URL url = new URL (url_);

        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");

        con.setRequestProperty("Content-Type", "application/xml; utf-8");
        con.setRequestProperty("Accept", "application/xml");

        con.setDoOutput(true);

        String xmlInputString = "<Users>" +
                "<User id=\""+id+"\""+">" +
                "<balance>"+balance+"</balance>" +
                "</User>" +
                "</Users>";

        try(OutputStream os = con.getOutputStream()){
            byte[] input = xmlInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

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
            return response.toString();
        }
    }
}
