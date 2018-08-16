package develop;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ff.model.Weather;

public class TestWeather {

	@Test
	public void testCheckParameterString() {
		assertEquals(false, Weather.CheckParameter("999"));
		assertEquals(true, Weather.CheckParameter("90"));
		assertEquals(true, Weather.CheckParameter("00"));
	}

}
