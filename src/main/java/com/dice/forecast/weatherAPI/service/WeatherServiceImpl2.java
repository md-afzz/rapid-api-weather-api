package com.dice.forecast.weatherAPI.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
//@Service("weatherServiceImpl2")
@Qualifier("weatherServiceImpl2")
public class WeatherServiceImpl2 implements WeatherService{

	@Override
	public ResponseEntity<?> getRapidApiForecastSummaryByLocationName(String city, Map<String, String> headerMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> getRapidApiForecastHourlyByLocationName(String city, HttpHeaders headers) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
