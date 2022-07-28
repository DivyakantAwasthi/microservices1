package com.divyakant.moviecatalogservice.services;

import com.divyakant.moviecatalogservice.model.Rating;
import com.divyakant.moviecatalogservice.model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class UserRatingInfo {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackUserRating")
    public UserRating getUserRating(String user) {
        return restTemplate.getForObject("http://rating-data-service/ratingsdata/users/" + user, UserRating.class);
    }

    public UserRating getFallbackUserRating(String user){
        UserRating userRating = new UserRating();
        userRating.setUserId(user);
        userRating.setUserRating(Arrays.asList(new Rating("0",0)));
        return userRating;
    }
}
