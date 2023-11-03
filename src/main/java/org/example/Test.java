package org.example;

import java.util.ArrayList;
import java.util.List;

public class Test {

    List<String> testowa = new ArrayList<>();

    public void cos(){
        testowa.add("ys");
        testowa.add("www");
        testowa.add("eee");
        testowa.add("wis");
        testowa.add("krzys");
        testowa.add("mys");



    }
    public void eee(){

        cos();
        for (int i = 0; i < testowa.size(); i++) {
            System.out.println(i+1 +testowa.get(i));
        }

     //   System.out.println(testowa.);


    }



}
