package org.syn.bo;

import java.util.HashMap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class LogoffServicePRODUCTION {
	public static void main(String[] args) {
		System.out.println("**************************************");
		HashMap<Integer, String> hm = new HashMap<Integer, String>();
		hm.put(0, "https://entitydefinition.invent.sparkster.me/log?value=false");
		hm.put(1, "https://pageobject.invent.sparkster.me/log?value=false");
		hm.put(2, "https://manifest.invent.sparkster.me/log?value=false");
		hm.put(3, "https://layoutobject.invent.sparkster.me/log?value=false");
		hm.put(4, "https://modules.invent.sparkster.me/log?value=false");
		hm.put(5, "https://menus.invent.sparkster.me/log?value=false");
		hm.put(6, "https://serviceurl.invent.sparkster.me/log?value=false");
		hm.put(7, "https://deviceregistration.invent.sparkster.me/log?value=false");
		hm.put(8, "https://parser.invent.sparkster.me/log?value=false");
		hm.put(9, "https://solidityparser.invent.sparkster.me/log?value=false");
		hm.put(10, "https://deviceparser.invent.sparkster.me/log?value=false");
		hm.put(11, "https://jsonbuilder.invent.sparkster.me/log?value=false");
		hm.put(12, "https://sendemail.invent.sparkster.me/log?value=false");
		hm.put(13, "https://transaction.invent.sparkster.me/log?value=false");
		hm.put(14, "https://transformer.invent.sparkster.me/log?value=false");
		hm.put(15, "https://eventmetadata.invent.sparkster.me/log?value=false");
		hm.put(16, "https://actionmetadata.invent.sparkster.me/log?value=false");
		hm.put(17, "https://eventlogmetadata.invent.sparkster.me/log?value=false");
		hm.put(18, "https://orm.invent.sparkster.me/log?value=false");
		hm.put(19, "https://pojo.invent.sparkster.me/log?value=false");
		hm.put(20, "https://jdo.invent.sparkster.me/log?value=false");
		hm.put(21, "https://pojojdo.invent.sparkster.me/log?value=false");
		hm.put(22, "https://dbinfo.invent.sparkster.me/log?value=false");
		hm.put(23, "https://cachecontroller.invent.sparkster.me/log?value=false");
		hm.put(24, "https://cachegenerator.invent.sparkster.me/log?value=false");
		hm.put(25, "https://kafka.invent.sparkster.me/log?value=false");
		hm.put(26, "https://codelessservice.invent.sparkster.me/log?value=false");
		hm.put(27, "https://apimetadata.invent.sparkster.me/log?value=false");
		hm.put(28, "https://adaptermetadata.invent.sparkster.me/log?value=false");
		hm.put(29, "https://ui.invent.sparkster.me/log?value=false");
		hm.put(30, "https://iot.invent.sparkster.me/log?value=false");
		hm.put(31, "https://servicemetadata.invent.sparkster.me/log?value=false");

		for (int i = 0; i <= 31; i++) {
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization",
					"Bearer eyJhbGciOiJSUzI1NiJ9.eyJ1c2VyX25hbWUiOiJwcmFzYWQubkBzeW5jb21zLmNvbSIsImF1dGhvcml0aWVzIjpbIk1hbmFnZXIiXSwianRpIjoiOWMwNzRkYWQtMDliNC00MTBhLWI2NWItZDIwYmZjZGI4ZTNkIiwiZXh0dmYiOiIyOTJGRUM3Ni01RjFDLTQ4NkYtODVBNS0wOUQ4ODA5NkYwOTgiLCJjbGllbnRfaWQiOiJpbnRlcm5hbCIsInNjb3BlIjpbImludGVybmFsIl19.caSwSaLBJpNtI1X4iVsHteNPPfa_lCngc5tGXUV5W86mntCcAvF-gvES5BfzwGZI-3mO9lPoSE7F9NNcK8b5YAYozxs3zNX1ji8dNsCqchUBLzKN3RMo7g461vCAYZ1pHnwg85otR5pB5YI7XkvGPooiTvt7RXFFEb8IR5941_fBl8Dbjw2y4iIiwaGSi1DqKP6qZzgwd18Sjudk6UmIEQCCV9wCetGOF5Xrfd-Ym7xAZbvC9-bEakrMyahfWBlQyKjwYdrTosmQc_H3Uk6l78QdAtwHq2TQZfQSIBXryeq379Ux9nbI7aHL7Zm5FOrJudWj0sF547_YEo_oQNpX4Q");
			String response = makeRESTRequest(hm.get(i), HttpMethod.GET, headers);
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
			System.out.println("Service " + url + " is called and response is " + response.getStatusCode());
			return response.getBody();
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println(e.getMessage());
		}
		return "";
	}
}
