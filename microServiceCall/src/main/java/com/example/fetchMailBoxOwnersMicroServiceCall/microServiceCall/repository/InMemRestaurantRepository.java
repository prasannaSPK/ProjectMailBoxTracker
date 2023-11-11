package com.example.fetchMailBoxOwnersMicroServiceCall.microServiceCall.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

import com.example.fetchMailBoxOwnersMicroServiceCall.microServiceCall.model.entity.Entity;
import com.example.fetchMailBoxOwnersMicroServiceCall.microServiceCall.model.entity.Restaurant;

@Repository("restaurantRepository")
public class InMemRestaurantRepository implements RestaurantRepository<Restaurant, String> {

    private Map<String, Restaurant> entities;


    public InMemRestaurantRepository() {
        entities = new HashMap();
        Restaurant restaurant = new Restaurant("Surya Prakash", "206", "Flat: 6106, Building 6, Cyan SouthCreek", null);
        entities.put("1", restaurant);
        restaurant = new Restaurant("Sai prasanna Kumar", "207", "Flat: 6107, Building 6, Cyan SouthCreek Apartments", null);
        entities.put("2", restaurant);
        restaurant = new Restaurant("Gowtami", "709", "Flat: 7109, Building 7, Cyan SouthCreek", null);
        entities.put("3", restaurant);
        restaurant = new Restaurant("Pranith N", "504", "Flat: 5104, Building 5,Foster street Cyan SouthCreek", null);
        entities.put("4", restaurant);
        restaurant = new Restaurant("Akhil deepak", "906", "Flat: 9106, Building 9, Cyan SouthCreek", null);
        entities.put("5", restaurant);
       
    }

    /**
     * Check if given restaurant name already exist.
     *
     * @param name
     * @return true if already exist, else false
     */
    @Override
    public boolean containsName(String name) {
        try {
            return this.findByName(name).size() > 0;
        } catch (Exception ex) {
            //Exception Handler
        }
        return false;
    }

    /**
     *
     * @param entity
     */
    @Override
    public void add(Restaurant entity) {
        entities.put(entity.getId(), entity);
    }

    /**
     *
     * @param id
     */
    @Override
    public void remove(String id) {
        if (entities.containsKey(id)) {
            entities.remove(id);
        }
    }

    /**
     *
     * @param entity
     */
    @Override
    public void update(Restaurant entity) {
        if (entities.containsKey(entity.getId())) {
            entities.put(entity.getId(), entity);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public boolean contains(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Entity get(String id) {
        return entities.get(id);
    }

    /**
     *
     * @return
     */
    @Override
    public Collection<Restaurant> getAll() {
        return entities.values();
    }

    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public Collection<Restaurant> findByName(String name) throws Exception {
        Collection<Restaurant> restaurants = new ArrayList();
        int noOfChars = name.length();
        entities.forEach((k, v) -> {
            if (v.getName().toLowerCase().contains(name.toLowerCase().subSequence(0, noOfChars))) {
                restaurants.add(v);
            }
        });
        return restaurants;
    }

}


