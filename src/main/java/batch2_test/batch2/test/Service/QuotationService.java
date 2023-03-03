package batch2_test.batch2.test.Service;

import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import batch2_test.batch2.test.Model.Cart;
import batch2_test.batch2.test.Model.Quotation;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class QuotationService {

    String URL = "https://quotation.chuklee.com/quotation";

    public Quotation getQuotations(List<String> items) throws Exception {

        Quotation quotation = new Quotation();

        JsonArrayBuilder jsonArr = Json.createArrayBuilder();

        for (int i = 0; i < items.size(); i++) {
            jsonArr.add(items.get(i));
        }

        JsonArray jsonArray = jsonArr.build();

        System.out.println(jsonArray);

        RequestEntity<String> req = RequestEntity
                .post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(jsonArray.toString());

        RestTemplate template = new RestTemplate();

        ResponseEntity<String> resp = template.exchange(req, String.class);

        // if (null == resp) {
        // JsonObject json = Json.createObjectBuilder()
        // .add("error", "item not found")
        // .build();
        // return ResponseEntity.status(404)
        // .body(json.toString());
        // }

        System.out.printf("Status code: %s\n".formatted(resp.getStatusCode()));

        String payload = resp.getBody();

        System.out.printf("Payload: %s\n", payload);

        JsonReader reader = Json.createReader(new StringReader(payload));
        JsonObject json = reader.readObject();

        quotation.setQuoteId(json.getString("quoteId"));

        JsonArray respArr = json.getJsonArray("quotations");

        Map<String, Float> quotations = new HashMap<>();

        for (int i = 0; i < respArr.size(); i++) {
            JsonObject jsonFile = respArr.getJsonObject(i);
            String item = jsonFile.getString("item");
            // I couldnt read float values! There is no getDouble or getFloat....
            float price = (float) jsonFile.getJsonNumber("unitPrice").doubleValue();
            System.out.println(price);
            quotations.put(item, price);
        }
        quotation.setQuotations(quotations);

        return quotation;

    }

    public Float calculateCost(Quotation quotation, Cart cart) {

        Map<String, Float> quotations = quotation.getQuotations();
        Map<String, Integer> itemList = cart.getItemList();

        Float totalCost = 0f;

        System.out.println(cart.getItemList().toString());

        for (String item : itemList.keySet()) {

            if (quotations.containsKey(item)) {

                totalCost += itemList.get(item) * quotations.get(item);
            }
        }

        System.out.println(totalCost);
        return totalCost;

    }
}
