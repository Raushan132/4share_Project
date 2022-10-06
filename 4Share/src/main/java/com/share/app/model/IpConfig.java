package com.share.app.model;


import java.net.InetAddress;
import java.net.UnknownHostException;


import lombok.Getter;

@Getter
public class IpConfig {
	
	private String location;
	    public IpConfig() throws UnknownHostException {
	    	InetAddress ip=InetAddress.getLocalHost();
	    	location=ip.getHostAddress();
			location+=":8080/4share";
			
	    }
}
