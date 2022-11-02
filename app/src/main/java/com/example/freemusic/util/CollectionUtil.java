package com.example.freemusic.util;

import java.util.List;

public class CollectionUtil {

    public static boolean equals(List<Object> list, List<Object> list1) {
        if (list == null && list1 == null) {
            return true;
        }
        if (list == null || list1 == null) {
            return false;
        }
        if (list.size() != list1.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).equals(list1.get(i))) {
                return false;
            }
        }
        return true;
    }
}
