package tec.bd.weather.cli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;

@CommandLine.Command(name = "imn-city", description = "Get weather forecast for city IMN")
public class IMNCityCommand implements Runnable{

    private static final Logger LOGGER = LoggerFactory.getLogger(IMNCityCommand.class);

    private static ApplicationContext APP_CONTEXT = ApplicationContext.init();

    @CommandLine.Parameters(paramLabel = "<imn-city name>", description = "city name to be resolved")
    private String cityName;

    @Override
    public void run() {

        var weatherReport = APP_CONTEXT.getWeatherReport();
        var report = weatherReport.getByCity(cityName);
        System.out.println(report);

    }

}
