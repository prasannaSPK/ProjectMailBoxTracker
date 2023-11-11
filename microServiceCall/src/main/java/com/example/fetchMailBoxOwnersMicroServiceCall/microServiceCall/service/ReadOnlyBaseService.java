package com.example.fetchMailBoxOwnersMicroServiceCall.microServiceCall.service;

import  com.example.fetchMailBoxOwnersMicroServiceCall.microServiceCall.repository.Repository;

public abstract class ReadOnlyBaseService<TE, T> {

    private Repository<TE, T> repository;

    ReadOnlyBaseService(Repository<TE, T> repository) {
        this.repository = repository;
    }
}
