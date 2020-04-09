package com.istv.banq;

import java.io.StringWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import javax.jws.WebService;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@WebService(endpointInterface="com.istv.banq.BanqService")
public class BanqServiceImpl implements BanqService{
    @Override
    public Double checkBalance(final Integer id) {
        double balance = 0;
        try {
            URL url = new URL("http://localhost:8081/users/" + id.toString());
            String a = IOUtils.toString(url, StandardCharsets.UTF_8);
            JSONObject json = new JSONObject(a);
            balance = json.getDouble("balance");
        } catch (Exception e){
            System.out.println(e);
        }
        return balance;
    }

    @Override
    public String credit(Integer id, Double amount) {
        if(!updateBalance(id, amount)){
            return "error : impossible to update balance";
        }
        // return "success";
        // for debug purpose
        return "success : user credited with +" + amount + "€.";
    }

    @Override
    public String debit(Integer id, Double amount) {
        double balance = checkBalance(id);
        if(balance < amount){
            return "error : not enough balance";
        }
        amount = 0 - amount;
        if(!updateBalance(id, amount)){
            return "error : impossible to update balance";
        }
        // return "success";
        // for debug purpose
        return "success : user debited by " + amount + "€.";
    }

    private boolean updateBalance(Integer id, Double amount){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = docFactory.newDocumentBuilder();
            Document doc = documentBuilder.newDocument();

            Element root = doc.createElement("users");
            doc.appendChild(root);
            Element parent = doc.createElement("users");
            parent.setAttribute("id", id.toString());
            root.appendChild(parent);
            Element child = doc.createElement("balance");
            child.appendChild((doc.createTextNode(amount.toString())));
            parent.appendChild(child);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            StringWriter stringWriter = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(stringWriter));

            String query = stringWriter.getBuffer().toString();

            StringEntity entity = new StringEntity(query, ContentType.APPLICATION_XML);
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost("http://localhost:8081/transaction");
            request.setEntity(entity);
            HttpResponse response = httpClient.execute(request);
            HttpEntity ent = response.getEntity();
            String content = EntityUtils.toString(ent);
            System.out.println(content);
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }
}