package com.example.demo2;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Demo2ApplicationTests {
    private final Logger logger = LoggerFactory.getLogger(Demo2ApplicationTests.class);

    @Test
    void contextLoads() {
        Object obj = "new String()";
        String res = testStringy(obj);
        logger.info("Stringy 1 "+res);

        Object obj2 = 2;
        String res2 = testStringy(obj2);
        logger.info("Stringy 2 "+res2);

    }

    private String testStringy(Object obj) {
        if (obj instanceof String s) {
            return s.toLowerCase();
        } else {
            return "cant";
        }
    }


}
