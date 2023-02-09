package com.daxiong.mall;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class MallApplicationTests {
    @Test
    void test() {
    }
    public List<String> removeSubfolders(String[] folder) {
        Set<String> set = new HashSet<>();
        for (String str : folder)
            set.add(str);
        List<String> res = new ArrayList<>();
        for (String str : folder) {
            if (recursive(str.substring(0, str.lastIndexOf("/")), set)) {
                res.add(str);
            }
        }
        return res;
    }

    private boolean recursive(String prefix, Set<String> set) {
        if (prefix.length() == 0) {
            return true;
        }
        if (set.contains(prefix))
            return false;
        return recursive(prefix.substring(0, prefix.lastIndexOf("/")), set);
    }
}
