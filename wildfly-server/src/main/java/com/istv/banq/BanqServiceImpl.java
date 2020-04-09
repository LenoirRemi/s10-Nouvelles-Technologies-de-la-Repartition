package com.istv.banq;

import java.io.StringWriter;
import java.net.HttpURLConnection;
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
        double balance = checkBalance(id);
        balance += amount;
        if(updateBalance(id, amount)){
            return "error : impossible to update balance";
        }
        // return "success";
        // for debug purpose
        return "success : new balance " + balance;
    }

    @Override
    public String debit(Integer id, Double amount) {
        double balance = checkBalance(id);
        if(balance < amount){
            return "error : not enough balance";
        }
        balance -= amount;
        if(updateBalance(id, amount)){
            return "error : impossible to update balance";
        }
        // return "success";
        // for debug purpose
        return "success : new balance " + balance;
    }

    private boolean updateBalance(Integer id, Double amount){
        try {
            HttpURLConnection urlConnection;
            URL url = new URL("http://localhost:8081/transaction");
            urlConnection = (HttpURLConnection) url.openConnection();

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

            urlConnection.addRequestProperty("Content-Type", "application/POST");
            urlConnection.setRequestProperty("Content-Length", Integer.toString(query.length()));
            urlConnection.getOutputStream().write(query.getBytes(StandardCharsets.UTF_8));
            urlConnection.setDoOutput(true);
            urlConnection.disconnect();
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }
}