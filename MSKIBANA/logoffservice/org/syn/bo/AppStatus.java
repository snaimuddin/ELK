package org.syn.bo;

import java.util.HashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
public class AppStatus {
	public static void main(String[] args) {
		SpringApplication.run(AppStatus.class, args);
	}

	
	@Scheduled(fixedDelay=1000)
	public void scheduletask() {

		HashMap<Integer, String> hm = new HashMap<Integer, String>();
		//hm.put(0, "https://kafka.invent.sparkster.me/");
		//hm.put(1, "https://cep.invent.sparkster.me/cep");
		hm.put(0, "https://mqtt.invent.sparkster.me/");
		for (int i = 0; i <= 0; i++) {
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization",
					"bearer eyJhbGciOiJSUzI1NiJ9.eyJ1c2VyX25hbWUiOiJzaGFpay5uYWltdWRkaW5Ac3luY29tcy5jb20iLCJleHR2ZiI6IjI5MkZFQzc2LTVGMUMtNDg2Ri04NUE1LTA5RDg4MDk2RjA5OCIsInNjb3BlIjpbImZ1bGwiXSwiZXhwIjoxNTM3MzM4MzE0LCJhdXRob3JpdGllcyI6WyJNYW5hZ2VyIl0sImp0aSI6Ijg5ZDMyZTc1LWFiMGItNDcyYi04MmFjLTk3YjAxZDY4NzEwMSIsImNsaWVudF9pZCI6ImNvZGVjbGllbnQifQ.ek0TvYFk9P61DqfO9ssKvPr4cZUGlt9v452LkUNV74YSDpQlG7DxezqG8R1BysLyGMCC-trpaD_RXDjzwWqf9e2s0PrupUyX5zjyrE6DgZ00UP3UDuxqp6XEPk1lOClkQID3yUlqpey2MqTNidZ02Rf6C5G_AG-qoY9curvdDasImTFv1_eWTCSjUBpjYXqL-b_pznRQOGH5FqsdaVXtsLt9dd1TJrCLEkE2JdH5sxKTA_XP9cIw38DyGG7PRSE51KYI79VB4sJXkNwufkf7S2hUFfj5RvNCEa4VCw5blo191kyUdLOQU7UekdBoKum14x7cne-x9oXehlECFNrVRw");
			String response = makeRESTRequest(hm.get(i), HttpMethod.GET, headers);
			System.err.println("Response Code is " + response);
		}
	}

	private static String makeRESTRequest(String url, HttpMethod httpMethod, HttpHeaders requestHeaders) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			if (requestHeaders == null)
				requestHeaders = new HttpHeaders();
			requestHeaders.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
			HttpEntity<String> request = new HttpEntity<String>(null, requestHeaders);
			ResponseEntity<String> response = restTemplate.exchange(url, httpMethod, request, String.class);
			System.out.println("response " + response);
			System.out.println("Service " + url + " is called and response is " + response.getStatusCode());
			return response.getStatusCode().toString();
		} catch (Exception e) {
				
			RestTemplate restTemplate = new RestTemplate();
		
			System.out.println("err msg =" + e.getMessage());
			String appname = url.split("//")[1].split(".invent")[0];
			System.out.println("appname " + appname);
			 
			if (e.getMessage().equals("503 Service Unavailable")) {
				System.out.println("Restart the Service");

				String marathonurl = "http://10.21.0.109:8080/v2/apps/" + appname + "/";
				requestHeaders.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);

				HttpEntity<String> request = new HttpEntity<String>("{\"id\": \"/" + appname + "\", \"instances\": 1 }",
						requestHeaders);
				ResponseEntity<String> responsefrommarathon = restTemplate.exchange(marathonurl, HttpMethod.PUT,
						request, String.class);
				System.out.println("response from marathon " + responsefrommarathon);

			} else {
				System.out.println("Nothing");
			}
			 
		}
		return "";
	}
}
