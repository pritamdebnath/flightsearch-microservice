package com.micromakers.scheduledevent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.micromakers.notification.model.User;

@Component
public class ScheduledPolling {
	
	Map<String,String> hobbies;
	
	@Autowired
	private RestTemplate caller;
	
	@Value("${hostname}")
	private String hostname;
	
	@Scheduled(fixedDelay = 200)
	public void pollAndSendToGCM() {		
		StringBuilder sb = new StringBuilder();
		sb.append(hostname);
		sb.append("/findall");
		//Change following line to call from /findall	
		Map<String,?> uriVariables = new HashMap<>();
		List<User> users = (List<User>)(caller.exchange(sb.toString(),HttpMethod.GET, null, List.class,uriVariables));
		for(User u : users) {
			String notification = hobbies.get(u.getHobby());
			// Send to GCM -- particular channel
		}
	}
	
	@PostConstruct
	public void addNotificationsToHobbies() {
		hobbies = new HashMap<>();
		hobbies.put("music","There is a rock concert going on");
		hobbies.put("movies","There is an action film coming this weekend");
		hobbies.put("football","Watch Manchester vs Arsenal live");
	}
	

}
