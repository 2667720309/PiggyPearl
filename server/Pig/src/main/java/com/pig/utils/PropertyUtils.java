package com.pig.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Blackke
 */
public class PropertyUtils {


    public static <DO> DO dtoToDo(Object dtoEntity, Class<DO> doClass) {
        if (dtoEntity == null) {
            return null;
        }
        if (doClass == null) {
            return null;
        }
        try {
            DO newInstance = doClass.newInstance();
            // DTO转换DO
            BeanUtils.copyProperties(dtoEntity, newInstance);
            return newInstance;
        } catch (Exception e) {
            return null;
        }
    }

    public static <DTO> DTO doToDto(Object doEntity, Class<DTO> dtoClass) {
        if (doEntity == null) {
            return null;
        }
        if (dtoClass == null) {
            return null;
        }
        try {
            DTO newInstance = dtoClass.newInstance();
            // DO转换DTO
            BeanUtils.copyProperties(doEntity, newInstance);
            return newInstance;
        } catch (Exception e) {
            return null;
        }
    }

    public static <S, D> List<D> listToList(List<S> sourceList, Class<D> destinationClass) {
        System.out.println(sourceList);
        return sourceList.stream().map(source -> {
            D destination;
            try {
                destination = destinationClass.newInstance();
                System.out.println(destination);
                BeanUtils.copyProperties(source, destination);
            } catch (Exception e) {

                return null;
            }
            return destination;
        }).collect(Collectors.toList());
    }
}
