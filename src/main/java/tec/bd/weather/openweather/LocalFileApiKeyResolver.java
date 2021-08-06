package tec.bd.weather.openweather;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class LocalFileApiKeyResolver implements IApiKeyResolver {

    private final static String API_KEY_PROP = "weather-report.api.key";

    private File apiKeyFile;

    public LocalFileApiKeyResolver(File apiKeyFile) {
        this.apiKeyFile = apiKeyFile;
    }

    @Override
    public String resolveKey() {
        try (InputStream input = new FileInputStream(this.apiKeyFile)) {
            Properties prop = new Properties();
//            if (input == null) {
//                System.out.println("Sorry, unable to find config.properties");
//                return;
//            }
            //load a properties file from class path, inside static method
            prop.load(input);
            //get the property value and print it out
            return prop.getProperty(API_KEY_PROP);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }
}
