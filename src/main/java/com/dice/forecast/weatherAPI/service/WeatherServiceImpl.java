package com.dice.forecast.weatherAPI.service;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.slf4j.Slf4j;

@Service("weatherServiceImpl")
@Slf4j
public class WeatherServiceImpl implements WeatherService{
	
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(WeatherServiceImpl.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public ResponseEntity<?> getRapidApiForecastSummaryByLocationName(String city,Map<String,String> headerMap) {
		// TODO Auto-generated method stub
		System.out.println("city name "+city);
		System.out.println(this.getClass().getSimpleName()+":  "+"getRapidApiForecastSummaryByLocationName");
		System.out.println("X-RapidAPI-Key "+headerMap.get("X-RapidAPI-Key".toLowerCase())
		+"  X-RapidAPI-Host  "+headerMap.get("X-RapidAPI-Host".toLowerCase()));
		ResponseEntity<?> response;
		try {
			String url="https://forecast9.p.rapidapi.com/rapidapi/forecast/"+city+"/summary/";
			HttpHeaders headers=new HttpHeaders();
			headers.set("X-RapidAPI-Key", headerMap.get("X-RapidAPI-Key".toLowerCase()));
			headers.set("X-RapidAPI-Host", headerMap.get("X-RapidAPI-Host".toLowerCase()));
			System.out.println("url::: "+url);
			//https://forecast9.p.rapidapi.com/rapidapi/forecast/Berlin/summary/
			response=restTemplate.exchange(url, HttpMethod.GET,new HttpEntity<>(headers),String.class);
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			try {
				org.json.JSONObject json=new JSONObject(response.getBody().toString());
				System.out.println("response body:: "+json.toString(5));
			}catch(JSONException e) {
				log.error("Invalid JSON format ",e);
			}
			System.out.println("response code ::: "+response.getStatusCode().value());
			
		}catch(Exception e) {
			log.error("Something went wrong while getting information from Rapid API!! ", e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Something went wrong while getting information from Rapid API!! ");
		}
		
		
		return response;
	}

	@Override
	public ResponseEntity<?> getRapidApiForecastHourlyByLocationName(String city, HttpHeaders headers) {
		// TODO Auto-generated method stub
		System.out.println("city name "+city);
		System.out.println(this.getClass().getSimpleName()+":  "+"getRapidApiForecastHourlyByLocationName");
		System.out.println("X-RapidAPI-Key "+headers.get("X-RapidAPI-Key").get(0)+"  X-RapidAPI-Host  "+headers.get("X-RapidAPI-Host").get(0));
		
		HttpHeaders headerss=new HttpHeaders();
		headerss.set("X-RapidAPI-Key", headers.get("X-RapidAPI-Key").get(0));
		headerss.set("X-RapidAPI-Host", headers.get("X-RapidAPI-Host").get(0));
		ResponseEntity<?> response;
		try {
			String url="https://forecast9.p.rapidapi.com/rapidapi/forecast/"+city+"/hourly/";
			//https://forecast9.p.rapidapi.com/rapidapi/forecast/Berlin/hourly/
			System.out.println("url::: "+url);
			response=restTemplate.exchange(url, HttpMethod.GET,new HttpEntity<>(headers),String.class);
			
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			try {
				org.json.JSONObject json=new JSONObject(response.getBody().toString());
				System.out.println("response body:: "+json.toString(5));
			}catch(JSONException e) {
				log.error("Invalid JSON format ",e);
			}
			System.out.println("response code ::: "+response.getStatusCode().value());
			
		}catch(Exception e) {
			log.error("Something went wrong while getting information from Rapid API!! ", e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Something went wrong while getting information from Rapid API!! ");
		}
		
		
		return response;
	}

}
