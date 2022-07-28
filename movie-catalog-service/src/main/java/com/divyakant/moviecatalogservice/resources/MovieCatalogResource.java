package com.divyakant.moviecatalogservice.resources;

import com.divyakant.moviecatalogservice.model.CatalogItem;
import com.divyakant.moviecatalogservice.model.Movie;
import com.divyakant.moviecatalogservice.model.Rating;
import com.divyakant.moviecatalogservice.model.UserRating;
import com.divyakant.moviecatalogservice.services.MovieInfo;
import com.divyakant.moviecatalogservice.services.UserRatingInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    MovieInfo movieInfo;

    @Autowired
    UserRatingInfo userRatingInfo;

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String user)
    {
        UserRating userRating = userRatingInfo.getUserRating(user);
         return userRating.getUserRating().stream().map(rating -> movieInfo.getCatalogItem(rating)
         ).collect(Collectors.toList());
    }
}
