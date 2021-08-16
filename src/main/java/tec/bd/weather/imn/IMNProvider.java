package tec.bd.weather.imn;

import tec.bd.weather.model.Report;
import tec.bd.weather.provider.BaseWeatherProvider;
import tec.bd.weather.provider.IWeatherProvider;

public class IMNProvider extends BaseWeatherProvider<Report> {

    private IWeatherProvider weatherProvider;

    public IMNProvider(IWeatherProvider weatherProvider) {
        this.weatherProvider = weatherProvider;
    }

    @Override
    protected Report fromProviderReport(Report providerReport) {
        return null;
    }

    @Override
    public Report byCity(String city) {
        var report = this.weatherProvider.byCity(city);
        return report;
    }

    @Override
    public Report byZipCode(String zipCode) {
        var report = this.weatherProvider.byZipCode(zipCode);
        return report;
    }
}
