package tec.bd.weather.openweather;

import retrofit2.Call;
import tec.bd.weather.provider.BaseWeatherProvider;
import tec.bd.weather.model.Report;

import java.util.Map;

public class OpenWeather extends BaseWeatherProvider<OpenWeatherReport> {

    private IOpenWeatherResource openWeatherResource;
    private IApiKeyResolver apiKeyResolver;

    public OpenWeather(IOpenWeatherResource openWeatherResource, IApiKeyResolver apiKeyResolver) {
        this.openWeatherResource = openWeatherResource;
        this.apiKeyResolver = apiKeyResolver;
    }

    @Override
    public Report byCity(String city) {
        try {
            var options = this.queryStringOptions(city);
            Call<OpenWeatherReport> openWeatherReportCall = this.openWeatherResource.get(options);
            OpenWeatherReport openWeatherReport = openWeatherReportCall.execute().body();
            return this.fromProviderReport(openWeatherReport);
        } catch (Exception e) {
            throw new RuntimeException("Error when calling remote provider", e);
        }
    }

    @Override
    public Report byZipCode(String zip_code) {
        //TODO: aqui es la tarea
        try {
            var options = this.queryStringOptions_zip_code(zip_code);
            Call<OpenWeatherReport> openWeatherReportCall = this.openWeatherResource.get(options);
            OpenWeatherReport openWeatherReport = openWeatherReportCall.execute().body();
            return this.fromProviderReport(openWeatherReport);
        } catch (Exception e) {
            throw new RuntimeException("Error when calling remote provider", e);
        }
    }


    @Override
    protected Report fromProviderReport(OpenWeatherReport providerReport) {
        var report = new Report();
        report.setCity(providerReport.getName());
        report.setHumidity(providerReport.getMain().getHumidity());
        report.setTemperature(providerReport.getMain().getTemp());
        report.setPressure(providerReport.getMain().getPressure());
        report.setMaxTemperature(providerReport.getMain().getTempMax());
        report.setMinTemperature(providerReport.getMain().getTempMin());
        return report;
    }

    /*Builder Pattern*/

    private Map<String, String> queryStringOptions(String city) {
        return Map.of("q", city, "appId", this.apiKeyResolver.resolveKey());
    }

    private Map<String, String> queryStringOptions_zip_code(String zip_code) {
        return Map.of("zip", zip_code, "appId", this.apiKeyResolver.resolveKey());
    }
}
