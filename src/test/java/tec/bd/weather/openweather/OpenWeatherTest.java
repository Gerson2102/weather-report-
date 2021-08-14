package tec.bd.weather.openweather;

import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class OpenWeatherTest {

    @Test
    public void whenCityIsPassedAsParameter_thenGetReport() throws Exception {
        MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody("{" +
                "    \"base\": \"stations\"," +
                "    \"clouds\": {" +
                "        \"all\": 100" +
                "    }," +
                "    \"cod\": 200," +
                "    \"coord\": {" +
                "        \"lat\": 10.0167," +
                "        \"lon\": -84.2167" +
                "    }," +
                "    \"dt\": 1627956160," +
                "    \"id\": 3624955," +
                "    \"main\": {" +
                "        \"feels_like\": 294.08," +
                "        \"humidity\": 85," +
                "        \"pressure\": 1016," +
                "        \"temp\": 293.75," +
                "        \"temp_max\": 294," +
                "        \"temp_min\": 293.75" +
                "    },\n" +
                "    \"name\": \"Alajuela\"," +
                "    \"sys\": {\n" +
                "        \"country\": \"CR\"," +
                "        \"id\": 2004086," +
                "        \"sunrise\": 1627903597," +
                "        \"sunset\": 1627948769," +
                "        \"type\": 2" +
                "    },\n" +
                "    \"timezone\": -21600," +
                "    \"visibility\": 1911," +
                "    \"weather\": [" +
                "        {" +
                "            \"description\": \"nubes\"," +
                "            \"icon\": \"04n\"," +
                "            \"id\": 804," +
                "            \"main\": \"Clouds\"" +
                "        }" +
                "    ]," +
                "    \"wind\": {" +
                "        \"deg\": 205," +
                "        \"gust\": 1.33," +
                "        \"speed\": 0.58" +
                "    }" +
                "}"));

        server.start();

        HttpUrl baseUrl = server.url("/data/2.5/weather/");

        IOpenWeatherResource openWeatherResource = createResource(baseUrl);
        IApiKeyResolver apiKeyResolver = mock(IApiKeyResolver.class);
        OpenWeather openWeather = new OpenWeather(openWeatherResource, apiKeyResolver);

        given(apiKeyResolver.resolveKey()).willReturn("test-api-key");

        var actual = openWeather.byCity("Alajuela");

        verify(apiKeyResolver, times(1)).resolveKey();

        assertThat(actual).isNotNull();

        RecordedRequest request1 = server.takeRequest();

        assertThat(request1.getPath()).containsOnlyOnce("q=Alajuela");
        assertThat(request1.getPath()).containsOnlyOnce("appId=test-api-key");

        server.shutdown();
    }

    private static IOpenWeatherResource createResource(HttpUrl baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(IOpenWeatherResource.class);
    }

    @Test
    public void when_zip_code_IsPassedAsParameter_thenGetReport() throws Exception {
        MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody("{" +
                "    \"base\": \"stations\"," +
                "    \"clouds\": {" +
                "        \"all\": 100" +
                "    }," +
                "    \"cod\": 200," +
                "    \"coord\": {" +
                "        \"lat\": 10.0167," +
                "        \"lon\": -84.2167" +
                "    }," +
                "    \"dt\": 1627956160," +
                "    \"id\": 3624955," +
                "    \"main\": {" +
                "        \"feels_like\": 294.08," +
                "        \"humidity\": 85," +
                "        \"pressure\": 1016," +
                "        \"temp\": 293.75," +
                "        \"temp_max\": 294," +
                "        \"temp_min\": 293.75" +
                "    },\n" +
                "    \"name\": \"Alajuela\"," +
                "    \"sys\": {\n" +
                "        \"country\": \"CR\"," +
                "        \"id\": 2004086," +
                "        \"sunrise\": 1627903597," +
                "        \"sunset\": 1627948769," +
                "        \"type\": 2" +
                "    },\n" +
                "    \"timezone\": -21600," +
                "    \"visibility\": 1911," +
                "    \"weather\": [" +
                "        {" +
                "            \"description\": \"nubes\"," +
                "            \"icon\": \"04n\"," +
                "            \"id\": 804," +
                "            \"main\": \"Clouds\"" +
                "        }" +
                "    ]," +
                "    \"wind\": {" +
                "        \"deg\": 205," +
                "        \"gust\": 1.33," +
                "        \"speed\": 0.58" +
                "    }" +
                "}"));

        server.start();

        HttpUrl baseUrl = server.url("/data/2.5/weather/");

        IOpenWeatherResource openWeatherResource = createResource_2(baseUrl);
        IApiKeyResolver apiKeyResolver = mock(IApiKeyResolver.class);
        OpenWeather openWeather = new OpenWeather(openWeatherResource, apiKeyResolver);

        given(apiKeyResolver.resolveKey()).willReturn("test-api-key");

        var actual = openWeather.byZipCode("70601");

        verify(apiKeyResolver, times(1)).resolveKey();

        assertThat(actual).isNotNull();

        RecordedRequest request1 = server.takeRequest();

        assertThat(request1.getPath()).containsOnlyOnce("zip=70601");
        assertThat(request1.getPath()).containsOnlyOnce("appId=test-api-key");

        server.shutdown();
    }

    private static IOpenWeatherResource createResource_2(HttpUrl baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(IOpenWeatherResource.class);
    }



}
