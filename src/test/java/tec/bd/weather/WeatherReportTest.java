package tec.bd.weather;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tec.bd.weather.model.Report;
import tec.bd.weather.provider.IWeatherProvider;

import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class WeatherReportTest {

    @Mock
    IWeatherProvider weatherProvider;

    @InjectMocks
    WeatherReport weatherReport;

    @Test
    public void GetWeatherReportByCity() throws Exception {

        var report = mock(Report.class);
        given(report.getCity()).willReturn("Alajuela");
        given(report.getTemperature()).willReturn(26f);
        given(report.getHumidity()).willReturn(26f);
        given(report.getMaxTemperature()).willReturn(27f);
        given(report.getMinTemperature()).willReturn(25f);
        given(report.getPressure()).willReturn(100f);

//        var report = new Report();
//        report.setCity("Alajuela");

        given(weatherProvider.byCity(anyString())).willReturn(report);

        var actual = weatherReport.getByCity("Alajuela");

        verify(weatherProvider, times(1)).byCity("Alajuela");

        assertThat(actual.getCity()).isEqualTo(report.getCity());
        assertThat(actual.getTemperature()).isEqualTo(report.getTemperature());
        assertThat(actual.getHumidity()).isEqualTo(report.getHumidity());
        assertThat(actual.getPressure()).isEqualTo(report.getPressure());
        assertThat(actual.getMaxTemperature()).isEqualTo(report.getMaxTemperature());
        assertThat(actual.getMinTemperature()).isEqualTo(report.getMinTemperature());
    }

    @Test
    public void GetWeatherReportBy_zip_code() throws Exception {

        var report = mock(Report.class);
        given(report.getZip_code()).willReturn("70601");
        given(report.getTemperature()).willReturn(26f);
        given(report.getHumidity()).willReturn(26f);
        given(report.getMaxTemperature()).willReturn(27f);
        given(report.getMinTemperature()).willReturn(25f);
        given(report.getPressure()).willReturn(100f);

//        var report = new Report();
//        report.setCity("Alajuela");

        given(weatherProvider.byZipCode(anyString())).willReturn(report);

        var actual = weatherReport.getByZipCode("70601");

        verify(weatherProvider, times(1)).byZipCode("70601");

        assertThat(actual.getZip_code()).isEqualTo(report.getZip_code());
        assertThat(actual.getTemperature()).isEqualTo(report.getTemperature());
        assertThat(actual.getHumidity()).isEqualTo(report.getHumidity());
        assertThat(actual.getPressure()).isEqualTo(report.getPressure());
        assertThat(actual.getMaxTemperature()).isEqualTo(report.getMaxTemperature());
        assertThat(actual.getMinTemperature()).isEqualTo(report.getMinTemperature());
    }


}
