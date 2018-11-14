package org.syn.bo;

import java.util.HashMap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

public class LogoffServiceDEVELOPMENT {

	public static void main(String args[]) {
		System.out.println("**************************************");
		HashMap<Integer, String> hm = new HashMap<Integer, String>();
		hm.put(0, "https://entitydefinition.development.symphoni.io/log?value=false");
		hm.put(1, "https://pageobject.development.symphoni.io/log?value=false");
		hm.put(2, "https://manifest.development.symphoni.io/log?value=false");
		hm.put(3, "https://layoutobject.development.symphoni.io/log?value=false");
		hm.put(4, "https://modules.development.symphoni.io/log?value=false");
		hm.put(5, "https://menus.development.symphoni.io/log?value=false");
		hm.put(6, "https://serviceurl.development.symphoni.io/log?value=false");
		hm.put(7, "https://deviceregistration.development.symphoni.io/log?value=false");
		hm.put(8, "https://parser.development.symphoni.io/log?value=false");
		hm.put(9, "https://solidityparser.development.symphoni.io/log?value=false");
		hm.put(10, "https://deviceparser.development.symphoni.io/log?value=false");
		hm.put(11, "https://jsonbuilder.development.symphoni.io/log?value=false");
		hm.put(12, "https://sendemail.development.symphoni.io/log?value=false");
		hm.put(13, "https://transaction.development.symphoni.io/log?value=false");
		hm.put(14, "https://transformer.development.symphoni.io/log?value=false");
		hm.put(15, "https://eventmetadata.development.symphoni.io/log?value=false");
		hm.put(16, "https://actionmetadata.development.symphoni.io/log?value=false");
		hm.put(17, "https://eventlogmetadata.development.symphoni.io/log?value=false");
		hm.put(18, "https://orm.development.symphoni.io/log?value=false");
		hm.put(19, "https://pojo.development.symphoni.io/log?value=false");
		hm.put(20, "https://jdo.development.symphoni.io/log?value=false");
		hm.put(21, "https://pojojdo.development.symphoni.io/log?value=false");
		hm.put(22, "https://dbinfo.development.symphoni.io/log?value=false");
		hm.put(23, "https://cachecontroller.development.symphoni.io/log?value=false");
		hm.put(24, "https://cachegenerator.development.symphoni.io/log?value=false");
		hm.put(25, "https://kafka.development.symphoni.io/log?value=false");
		hm.put(26, "https://codelessservice.development.symphoni.io/log?value=false");
		hm.put(27, "https://apimetadata.development.symphoni.io/log?value=false");
		hm.put(28, "https://adaptermetadata.development.symphoni.io/log?value=false");
		hm.put(29, "https://ui.development.symphoni.io/log?value=false");
		hm.put(30, "https://iot.development.symphoni.io/log?value=false");
		hm.put(31, "https://servicemetadata.development.symphoni.io/log?value=false");
		
		

		for (int i = 0; i <= 31; i++) {
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", "bearer eyJhbGciOiJSUzI1NiJ9.eyJ1c2VyX25hbWUiOiJzaGFpay5uYWltdWRkaW5Ac3luY29tcy5jb20iLCJleHR2ZiI6IjI5MkZFQzc2LTVGMUMtNDg2Ri04NUE1LTA5RDg4MDk2RjA5OCIsInNjb3BlIjpbImZ1bGwiXSwiZXhwIjoxNTMxMDQzOTQ5LCJhdXRob3JpdGllcyI6WyJNYW5hZ2VyIl0sImp0aSI6ImNkZDAxNzUwLTIzYWQtNDQ4My05YTE3LWZiM2ExMzFkMGJjZSIsImNsaWVudF9pZCI6ImNvZGVjbGllbnQifQ.aQSI43prJjkixNFtSmK0QEEuxpUwBro_yRCXUdmz-7x2SFx5sw4qrocZEhuPilVeRIWq03t3PN7E7JZZ0zHiBqDMR107QXLomuNzfxTwm48m0_-baQMG-m3FBZ6o7sGTdHtZ9JqdKEFZeA3biI_czLo_9qupI_e5Yb8k9RnZencEd9Ot__7JST5XS8dDwMQbCq5nVflfQybtYKkY6BZ2Z11kYhBBVf0N9fcwMLbgEbA_g16ZYzrllTP8tA9UpRRMiCg9KNqHyFVdf3eIkjmWC09EcKdYSgl-dMaytDVXSC3Bsctg0OqXt2CJRFjV7g2uGo-9zJj3BvrOAmm_8l8Thw");
			String response=makeRESTRequest(hm.get(i), HttpMethod.GET, headers);
		}

	}
	private static String makeRESTRequest( String url, HttpMethod httpMethod, HttpHeaders requestHeaders) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			if (requestHeaders == null)
				requestHeaders = new HttpHeaders();
			requestHeaders.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
			HttpEntity<String> request = new HttpEntity<String>(null, requestHeaders);
			ResponseEntity<String> response = restTemplate.exchange(url, httpMethod, request,String.class);
			System.out.println("Service " +url+ " is called and response is "+ response.getStatusCode());
			return response.getBody();
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println(e.getMessage());
		}
		return "";
	}
}
