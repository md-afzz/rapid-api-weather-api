package com.dice.forecast.weatherAPI.service;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;


public interface WeatherService {

	public ResponseEntity<?> getRapidApiForecastSummaryByLocationName(String city,Map<String, String> headerMap);

	public ResponseEntity<?> getRapidApiForecastHourlyByLocationName(String city, HttpHeaders headers);
	

}
