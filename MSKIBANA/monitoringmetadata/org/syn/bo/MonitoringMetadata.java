package org.syn.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.syn.bo.component.soa.core.ServiceConnector;
import org.syn.bo.component.soa.model.RequestData;
import org.syn.bo.component.soa.security.model.SecurityContext;

@RestController
public class MonitoringMetadata {
	@Autowired
	private ServiceConnector connector;
	@Autowired
	private SecurityContext securityContext;
	private String response;
	@Autowired
	  private ServiceConnector serviceConnector;

	@RequestMapping("/monitoringmetadata")
	public String execute(RequestData reqdata) {
		System.out.println("**************************************");
		String methodtype = reqdata.getMethod();
		if ("POST".equals(methodtype)) {
			postdata(reqdata);
		} else if ("GET".equals(methodtype)) {
			getdata(reqdata);
		}
		 return serviceConnector.parseResponse(response);

	}

	private void getdata(RequestData reqdata) {
		// TODO Auto-generated method stub
		try{
		String inputdata="{\"entityinfo\":{\"tenantid\":\"292FEC76-5F1C-486F-85A5-09D88096F098\",\"httpmethod\":\"get\",\"entity\":\"MonitoringMetadata\",\"timestamp\":\"2018-02-14 14:40:21.197\"},\"collections\":{\"LukMTActions\":{\"rowfilter\":[],\"rowset\":[\"actionId\",\"actionName\"],\"meta\":{\"pkname\":\"null\",\"fkname\":\"null\",\"parentreference\":\"null\"}},\"MonitoringConditionMetadata\":{\"rowfilter\":[],\"rowset\":[\"conditionId\",\"userId\",\"blocklyLogic\",\"javascriptLogic\",\"actionId\"],\"meta\":{\"pkname\":\"null\",\"fkname\":\"null\",\"parentreference\":\"null\"}}}}";
		ResponseEntity<String> response =connector.connect("orm", inputdata,HttpMethod.GET);
	
		this.response = serviceConnector.parseResponse(response);
		
		}catch(Exception e){
			
		}
	}

	private void postdata(RequestData reqdata) {
		// TODO Auto-generated method stub

		try {
			String inputdata=reqdata.getData();
			ResponseEntity<String> response =connector.connect("orm", inputdata,HttpMethod.POST);
			this.response = serviceConnector.parseResponse(response);
		} catch (Exception e) {

		}
	}
}
