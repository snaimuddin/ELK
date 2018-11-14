package org.syn.bo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.elasticsearch.client.transport.TransportClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.syn.bo.component.soa.core.ServiceConnector;
import org.syn.bo.component.soa.model.RequestData;
import org.syn.bo.component.soa.security.model.SecurityContext;

//@EnableScheduling
@RestController
public class Webhooktest {
	@Autowired
	private ServiceConnector connector;
	@Autowired
	private SecurityContext securityContext;

	// @Scheduled(cron = "0 * * * * ?")
	@RequestMapping("/webhook")
	public String test(RequestData data) {
		System.out.println("New webhook COMPONENT");
		String inputdata = data.getData();
		JSONObject inputJson = new JSONObject(inputdata);
		String serviceName = inputJson.getString("serviceName");
		String timeTaken = inputJson.getString("timeTaken");
		String toemail=inputJson.getString("toEmail");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		try {
			System.out.println("Execute after every 1Min ........" + new Date());

			CloseableHttpClient httpclient = HttpClients.createDefault();
			CloseableHttpResponse response = null;
			HttpEntity entity;

			String query = "logDate:" + df.format(new Date()) + "%20AND%20timeTaken:%3E" + timeTaken+ "%20AND%20serviceName:" + serviceName + "";

			String esurl = "https://logelasticsearch-staging.invent.symphoni.io/logs/_search?q=" + query + "";

			System.out.println("esurl " + esurl);
			HttpGet httpget = new HttpGet(esurl);
			response = httpclient.execute(httpget);
			entity = response.getEntity();
			StringBuilder serverResponse = new StringBuilder();
			if (entity != null) {
				BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
				String line = null;
				while ((line = br.readLine()) != null)
					serverResponse.append(line + "\n");
				
			}
			JSONObject jsonobj = new JSONObject(serverResponse.toString());

			JSONObject hits = jsonobj.getJSONObject("hits");
			System.out.println("hits-  " + hits);

			long totallogs = hits.getLong("total");
			System.out.println("Total Hits " + totallogs);

			if (totallogs > 0) {
				System.out.println("Calling  sentEmailNotification Service.............. ");
				String inputtosendemail = "{\"serviceName\":" + serviceName + ",\"timeTaken\": "+ timeTaken + ",\"toEmail\":"+toemail+"}";

				ResponseEntity<String> responsefromemailservice =connector.connect("sendemailnotification", inputtosendemail,HttpMethod.POST);

				 System.out.println("responsesFROM-Service- " + responsefromemailservice.getBody());

			} else {
				System.out.println("No record Exist with that input criteria");
				return "No Record Found";
			}

		} catch (Exception e) {
			e.getMessage();

		}

		return "emailnotificationservicecalled";
	}
}
