package rxwriter.drug;

import org.junit.jupiter.api.*;

public class LifecycleTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("BeforeAll executed");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println(this + " BeforeEach executed");
    }

    @Test
    void testOne() {
        System.out.println(this + " Test one executed");
    }

    @Test
    void testTwo() {
        System.out.println(this + " Test two executed");
    }

    @AfterEach
    void afterEach() {
        System.out.println(this + " AfterEach executed");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("AfterAll executed");
    }
}
