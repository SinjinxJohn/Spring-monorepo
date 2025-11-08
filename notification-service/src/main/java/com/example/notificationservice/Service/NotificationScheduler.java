package com.example.notificationservice.Service;




//import com.example.todo.Repository.TodoRepository;
//import com.example.todo.Service.TodoServiceImp.TodoServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class NotificationScheduler {


    private final RestTemplate restTemplate;

    @Scheduled(cron = "*/30 * * * * *")
    public void sendDailyNotification(){
        String url = "http://TODO-SERVICE/api/v1/tasks/get-count-tasks";
        ResponseEntity<Integer> response = restTemplate.exchange(url, HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Integer>() {
                });
      int pendingCounts = response.getBody();

        if(pendingCounts>0){
            System.out.println("User has " + pendingCounts+ " tasks left");
        }

    }
}
