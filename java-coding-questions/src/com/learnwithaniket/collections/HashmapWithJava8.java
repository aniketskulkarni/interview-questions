package com.learnwithaniket.collections;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/***
 * Question: Given the below input
 *  Map<Integer, String> student = new HashMap<>();
 *
 *  student.put(1, "computer");
 *  student.put(2, "history");
 *  student.put(3, "computer");
 *  student.put(4, "physics");
 *  student.put(10, "computer");
 *  student.put(5, "computer");
 *
 *  Write logic to support below instructions (Java 1.8 + with-out using direct IF/while condition/s)
 *
 * 1. Get the consolidated entries by applying  below conditions
 *
 *       1.a)  Entity value must be "computer"
 *       1.b)  Skip/exclude this entity  (10,"computer")
 *       1.c)  Add new entity as  (6,"computer")
 *
 * 2. Modify the entity keys as per below patterns and pushed to new or existing collection
 *
 * Expected output  :
 *
 *  (101, "computer");
 *  (301, "computer");
 *  (501, "computer");
 *  (601, "computer");
 */
public class HashmapWithJava8 {

    public static void main(String[] args) {
        Map<Integer, String> student = new HashMap<>();

        student.put(1, "computer");
        student.put(2, "history");
        student.put(3, "computer");
        student.put(4, "physics");
        student.put(10, "computer");
        student.put(5, "computer");

        Map<Integer, String> s = student.entrySet()
                .stream()
                .filter(map -> map.getValue().equals("computer") && map.getKey() != 10)
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue())); //1.a & 1.b

        s.put(6, "computer"); //1.c

        Map<Integer, String> computerStudents = s.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(map -> (map.getKey() * 100 + 1), map -> map.getValue(),
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new)); //2

        System.out.println(computerStudents);
    }
}
