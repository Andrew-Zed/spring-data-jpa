package com.andrew.associations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayConversion {
    public static void main(String[] args) {
        List<String>  list = new ArrayList<>();
        list.add("Ada");
        list.add("Baba");
        list.add("Kaka");
        list.add("Clark");
        list.add("Kent");
        list.add("Ada");
        System.out.println(list);

        String[] array = list.toArray(new String[9]);
        array[8] = "Wendy";

        System.out.println(Arrays.stream(Arrays.stream(array).toArray()).count());

        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i] + " ======= " +  i);
        }
    }
}
