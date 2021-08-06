package tec.bd.weather.openweather;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.*;

public class LocalFileApiKeyResolverTest {

    @Test
    public void resolveApiKeyFromLocalFile() throws Exception {

        var fileUri = this.getClass().getClassLoader().getResource("test-api-key.properties").toURI();
        var apiKeyFile = new File(fileUri);
        LocalFileApiKeyResolver apiKeyResolver = new LocalFileApiKeyResolver(apiKeyFile);

        var actual = apiKeyResolver.resolveKey();

        assertThat(actual).isEqualTo("api-key-here");

    }
}
