package com.senla.mapper;

public interface MapperAPI<E, D> {

     D toDto(E entity);
     E toEntity(D abstractDTO);

}
