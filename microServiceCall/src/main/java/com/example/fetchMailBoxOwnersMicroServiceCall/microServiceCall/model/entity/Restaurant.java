package com.example.fetchMailBoxOwnersMicroServiceCall.microServiceCall.model.entity;
import  com.example.fetchMailBoxOwnersMicroServiceCall.microServiceCall.model.entity.Table;
import java.util.ArrayList;
import java.util.List;

public class Restaurant extends BaseEntity<String> {

    private List<Table> tables = new ArrayList<>();
    private String address;

    /**
     *
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @param name
     * @param id
     * @param Address
     * @param tables
     */
    public Restaurant(String name, String id, String address, List<Table> tables) {
        super(id, name);
        this.address = address;
        this.tables = tables;
    }

    /**
     *
     * @param tables
     */
    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    /**
     *
     * @return
     */
    public List<Table> getTables() {
        return tables;
    }

    /**
     * Overridden toString() method that return String presentation of the
     * Object
     *
     * @return
     */
    @Override
    public String toString() {
        return new StringBuilder("{id: ").append(id).append(", name: ")
                .append(name).append(", address: ").append(address).
                append(", tables: ").append(tables).append("}").toString();
    }
}
