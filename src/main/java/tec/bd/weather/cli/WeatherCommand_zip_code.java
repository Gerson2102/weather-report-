package tec.bd.weather.cli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import tec.bd.weather.ApplicationContext;

@Command(name = "zipCode", description = "Get weather forecast for zipCode")
public class WeatherCommand_zip_code implements Runnable{

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherCommand_zip_code.class);

    private static ApplicationContext APP_CONTEXT = ApplicationContext.init();

    @Parameters(paramLabel = "<zipCode code, country code>", description = "zipCode to be resolved")
    private String zip_code;

    @Override
    public void run() {
        var weatherReport = APP_CONTEXT.getWeatherReport();
        var report = weatherReport.getByZipCode(zip_code);
        System.out.println(report);
    }
}
