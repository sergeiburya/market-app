package com.example.marketapp.service.util;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class ProductSortService {
    public Sort getSort(String sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        if (sortBy.contains(":")) {
            String[] sortingFields = sortBy.split(";");
            for (String sortingField : sortingFields) {
                Sort.Order order;
                if (sortingField.contains(":")) {
                    String[] fieldsAndDirections = sortingField.split(";");
                    order = new Sort.Order(Sort.Direction.valueOf(fieldsAndDirections[1]),
                            fieldsAndDirections[0]);
                } else {
                    order = new Sort.Order(Sort.Direction.ASC, sortingField);
                }
            }

        } else {
            Sort.Order order = new Sort.Order(Sort.Direction.ASC, sortBy);
            orders.add(order);
        }
        return Sort.by(orders);
    }
}
