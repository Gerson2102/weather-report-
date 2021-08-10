package tec.bd.weather.imn;

import tec.bd.weather.model.Report;
import tec.bd.weather.provider.BaseWeatherProvider;

public class IMNProvider extends BaseWeatherProvider<Report> {
    @Override
    protected Report fromProviderReport(Report providerReport) {
        return null;
    }

    @Override
    public Report byCity(String city) {
        return null;
    }

    @Override
    public Report byZipCode(String zipCode) {
        return null;
    }
}
