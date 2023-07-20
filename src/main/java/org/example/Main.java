package org.example;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        a(new ArrayList<>(Arrays.asList(1,2,3,4)));
    }

    public static <T extends Object> void a (List<Object> a){
        a.add("sdf");
        System.out.println(a.get(4));
    }
}
