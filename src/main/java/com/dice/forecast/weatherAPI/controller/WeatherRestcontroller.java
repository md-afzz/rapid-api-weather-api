package com.dice.forecast.weatherAPI.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dice.forecast.weatherAPI.service.WeatherService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/weather-forecast/")
@RequiredArgsConstructor
public class WeatherRestcontroller{
	
	@Autowired
	@Qualifier("weatherServiceImpl")
	private WeatherService weatherService;

	@GetMapping(produces = "application/json",path = "/summary-by-location-name/{city}")
	public ResponseEntity<?> getForecastSummaryByLocationName(@RequestHeader(name = "client-id",required=true) String clientId ,
			@PathVariable("city") String city, @RequestHeader Map<String,String> headerMap){
		System.out.println("inside /summary-by-location-name/{city} ");
		if(!clientId.equalsIgnoreCase("secretInfo")) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error in Authentication using header ! Try with Different clientId");
		}
		System.out.println("clientID :: "+headerMap.get("client-id"));
		System.out.println("X-RapidAPI-Key "+headerMap.get("X-RapidAPI-Key".toLowerCase())+"  X-RapidAPI-Host  "+
				headerMap.get("X-RapidAPI-Host".toLowerCase()));
		headerMap.entrySet().stream().forEach(entry->System.out.println("key :: "+entry.getKey()+" :: value "+entry.getValue()));
		return this.weatherService.getRapidApiForecastSummaryByLocationName(city, headerMap);
	}
	
	@GetMapping(produces = "application/json",path = "/hourly-by-location-name/{city}")
	public ResponseEntity<?> getForecastHourlyByLocationName(@RequestHeader(name = "client-id",required=true) String clientId ,
			@RequestHeader HttpHeaders headers,
			@PathVariable("city") String city){
		System.out.println("Inside /hourly-by-location-name/{city}");
		if(!clientId.equalsIgnoreCase("secretInfo")) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error in Authentication using header ! Try with Different clientId");
		}
		System.out.println("X-RapidAPI-Key "+headers.get("X-RapidAPI-Key").get(0)+"  X-RapidAPI-Host  "+headers.get("X-RapidAPI-Host").get(0));
		return this.weatherService.getRapidApiForecastHourlyByLocationName(city,headers);
	}
	
}
