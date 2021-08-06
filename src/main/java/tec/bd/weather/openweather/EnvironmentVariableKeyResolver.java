package tec.bd.weather.openweather;

public class EnvironmentVariableKeyResolver implements IApiKeyResolver {

    private final static String API_ENV_VAR = "OW_API_KEY";

    @Override
    public String resolveKey() {
        return System.getenv(API_ENV_VAR);
    }
}
