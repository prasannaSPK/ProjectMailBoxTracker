package com.example.fetchMailBoxOwnersMicroServiceCall.microServiceCall.controller;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.fetchMailBoxOwnersMicroServiceCall.microServiceCall.model.entity.Entity;
import com.example.fetchMailBoxOwnersMicroServiceCall.microServiceCall.model.entity.Restaurant;
import com.example.fetchMailBoxOwnersMicroServiceCall.microServiceCall.service.RestaurantService;
import com.example.fetchMailBoxOwnersMicroServiceCall.microServiceCall.valueobject.RestaurantVO;


@RestController
@RequestMapping("/api/mailboxowners")
public class RestaurantController {

    /**
     *
     */
    protected static final Logger logger = Logger.getLogger(RestaurantController.class.getName());

    /**
     *
     */
    protected RestaurantService restaurantService;

    /**
     *
     * @param restaurantService
     */
    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }



  
    @RequestMapping(value = "/{restaurant_id}", method = RequestMethod.GET)
    public ResponseEntity<Entity> findById(@PathVariable("restaurant_id") String id) {
       // logger.info(String.format("restaurant-service findById() invoked:{} for {} ", restaurantService.getClass().getName(), id));
        id = id.trim();
        Entity restaurant;
        try {
            restaurant = restaurantService.findById(id);
        } catch (Exception ex) {
            //logger.log(Level.WARNING, "Exception raised findById REST Call {0}", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return restaurant != null ? new ResponseEntity<>(restaurant, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

  
}
