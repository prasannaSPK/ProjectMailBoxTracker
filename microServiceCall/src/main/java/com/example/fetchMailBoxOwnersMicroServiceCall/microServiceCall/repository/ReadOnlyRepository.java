package com.example.fetchMailBoxOwnersMicroServiceCall.microServiceCall.repository;

import java.util.Collection;

import com.example.fetchMailBoxOwnersMicroServiceCall.microServiceCall.model.entity.Entity;


public interface ReadOnlyRepository<TE, T> {

    //long Count;
    /**
     *
     * @param id
     * @return
     */
    boolean contains(T id);

    /**
     *
     * @param id
     * @return
     */
    Entity get(T id);

    /**
     *
     * @return
     */
    Collection<TE> getAll();
}
