package tec.bd.weather;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tec.bd.weather.imn.IMNProvider;
import tec.bd.weather.openweather.*;
import tec.bd.weather.provider.IWeatherProvider;

import java.io.File;

public class ApplicationContext {

    private final static String BASE_URL = "https://api.openweathermap.org";

    private IOpenWeatherResource openWeatherResource;

    private IApiKeyResolver apiKeyResolver;

    private IWeatherProvider openWeatherProvider;

    private WeatherReport weatherReport;

    private IMNProvider imnProvider;


    public ApplicationContext() {

    }

    public IWeatherProvider getOpenWeatherProvider() {
        return openWeatherProvider;
    }

    public WeatherReport getWeatherReport() {
        return weatherReport;
    }

    public static ApplicationContext init() {
        ApplicationContext applicationContext = new ApplicationContext();
        applicationContext.openWeatherResource = initOpenWeatherResource();
        applicationContext.apiKeyResolver = initApiKeyResolver();
        applicationContext.openWeatherProvider =
                initOpenWeatherProvider(applicationContext.openWeatherResource, applicationContext.apiKeyResolver);
        applicationContext.weatherReport = initWeatherReport(applicationContext.openWeatherProvider);
        applicationContext.imnProvider = init_IMNProvider(applicationContext.openWeatherProvider);
        return applicationContext;
    }

    private static IOpenWeatherResource initOpenWeatherResource() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(IOpenWeatherResource.class);
    }

    private static IApiKeyResolver initApiKeyResolver() {
        var environmentVariableKeyResolver = new EnvironmentVariableKeyResolver();
        if(null != environmentVariableKeyResolver.resolveKey()) {
            System.out.println("Loading OW from Env variable");
            return environmentVariableKeyResolver;
        }
        System.out.println("Loading OW from Local file");
        return new LocalFileApiKeyResolver(new File("./api-key.properties"));
    }

    private static IWeatherProvider initOpenWeatherProvider(IOpenWeatherResource openWeatherResource, IApiKeyResolver apiKeyResolver) {
        return new OpenWeather(openWeatherResource, apiKeyResolver);
    }

    private static WeatherReport initWeatherReport(IWeatherProvider weatherProvider) {
        return new WeatherReport(weatherProvider);
    }

    private static IMNProvider init_IMNProvider(IWeatherProvider weatherProvider) {
        return new IMNProvider(weatherProvider);
    }



}
