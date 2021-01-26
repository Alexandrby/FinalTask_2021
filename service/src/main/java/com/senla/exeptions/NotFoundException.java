package com.senla.exeptions;

public class NotFoundException extends RuntimeException {
    private String entityName;
    private Integer id;

    public NotFoundException(String entityName, Integer id) {
        this.entityName = entityName;
        this.id = id;
    }

    @Override
    public String getMessage() {
        if(id !=null) {
            return String.format("Entity %s not found for id [%s]", entityName, id);
        } else {
            return String.format("There are no any %s entities", entityName);
        }
    }
}