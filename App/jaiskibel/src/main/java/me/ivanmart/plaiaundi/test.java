package me.ivanmart.plaiaundi;


import me.ivanmart.plaiaundi.Menus.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class test {
    public static void main(String[] args) {
        String[] lista = new String[]{"Hola", "Adios"};
        ArrayList<String[]> vals = new ArrayList<>();
        vals.add(new String[]{"Tets", "testt"});
        vals.add(new String[]{"", ""});
        vals.add(new String[]{"TEST12345", ""});
        vals.add(new String[]{"a", "asadasdadasd"});
        System.out.println(Util.generateTable(lista, vals));
    }
}
