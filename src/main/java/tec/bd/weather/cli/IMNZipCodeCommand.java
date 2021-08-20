package tec.bd.weather.cli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;

@CommandLine.Command(name = "imn-zip", description = "Get weather forecast by zipCode IMN")
public class IMNZipCodeCommand implements Runnable{

    private static final Logger LOGGER = LoggerFactory.getLogger(IMNZipCodeCommand.class);

    private static ApplicationContext APP_CONTEXT = ApplicationContext.init();

    @CommandLine.Parameters(paramLabel = "<imn-zip code>", description = "zipCode to be resolved")
    private String zip_code;

    @Override
    public void run() {
        var weatherReport = APP_CONTEXT.getWeatherReport();
        var report = weatherReport.getByZipCode(zip_code);
        System.out.println(report);
    }

}
