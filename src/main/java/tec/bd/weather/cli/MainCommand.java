package tec.bd.weather.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.HelpCommand;

@Command(
        name = "WeatherReport",
        subcommands = {
                WeatherCommand.class,
                WeatherCommand_zip_code.class,
                HelpCommand.class },
        description = "Weather forecasts for cities around the world")
public class MainCommand implements Runnable {

    @Override
    public void run() {

    }
}
