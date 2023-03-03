package ibf2022ssf.assessment.service;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import ibf2022ssf.assessment.model.Quotation;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

@RestController
public class QuotationService {
    public Quotation getQuotations(List<String> items) throws Exception{
        JSONArray json_array = new JSONArray(items);

        RequestEntity<String> req = RequestEntity
                                    .post("https://quotation.chuklee.com/quotation")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .body(json_array.toString(), String.class);

        RestTemplate template = new RestTemplate();

        ResponseEntity<String> resp = template.exchange(req, String.class);

        try {
            InputStream is = new ByteArrayInputStream(resp.getBody().getBytes()); 
            JsonReader reader = Json.createReader(is);
            JsonObject data = reader.readObject();
            Quotation quote = new Quotation();
            String quoteId = data.getValue("quoteId").toString();
            quote.setQuoteId(quoteId);
            Map<String, Float> quotations = data.getJsonObject("quotations").toMap();
            quote.setQuotations(quotations);
            
            return quote;

        }
        catch(Exception e){
            InputStream is = new ByteArrayInputStream(resp.getBody().getBytes());
            JsonReader reader = Json.createReader(is);
            JsonObject data = reader.readObject();
            String errormessage= data.error;
            System.out.println(errormessage);
        }
    }
}
