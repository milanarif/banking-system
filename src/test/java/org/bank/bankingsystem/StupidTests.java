package org.bank.bankingsystem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StupidTests {
    
    @Test
    void passTest() {
        assert(true);
    }

    @Test
    void failTest() {
        assert(false);
    }

}
