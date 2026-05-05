package edu.matc.inventory.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.matc.inventory.dto.LegendaryEffectDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class LegendaryEffectDao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private static final String API_URL = "http://inventory-api-fo76-env.eba-kvcd43xn.us-east-2.elasticbeanstalk.com/api/inventories/legendary-effects";

    public List<LegendaryEffectDto> getLegendaryEffects() throws Exception {
        return callApi(API_URL);
    }

    public List<LegendaryEffectDto> getLegendaryEffectsByStar(int star) throws Exception {
        return callApi(API_URL + "?star=" + star);
    }

    private List<LegendaryEffectDto> callApi(String url) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        logger.debug("API response status: {}", response.statusCode());

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        return mapper.readValue(response.body(), new TypeReference<List<LegendaryEffectDto>>() {});
    }
}