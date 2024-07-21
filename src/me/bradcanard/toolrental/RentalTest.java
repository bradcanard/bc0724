package me.bradcanard.toolrental;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RentalTest {
    private Inventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
    }

    @Test
    void test1() {
        System.out.println("Test 1");
        Tool jakr = inventory.getTool("JAKR");
        Rental rental = new Rental(jakr, LocalDate.of(2015, 9, 3), 5, 101);
        assertEquals("The discount percent must be between 0 and 100.", rental.checkout(), "Invalid discount rate should produce error message.");
        System.out.println(rental.checkout());
        System.out.println(System.lineSeparator());
    }

    @Test
    void test2() {
        System.out.println("Test 2");
        Tool ladw = inventory.getTool("LADW");
        Rental rental = new Rental(ladw, LocalDate.of(2020, 7, 2), 3, 10);
        assertEquals(3.58f, rental.getTotalCharge(), "The total charge should be $3.58.");
        System.out.println(rental.checkout());
        System.out.println(System.lineSeparator());
    }

    @Test
    void test3() {
        System.out.println("Test 3");
        Tool chns = inventory.getTool("CHNS");
        Rental rental = new Rental(chns, LocalDate.of(2015, 7, 2), 5, 25);
        assertEquals(3.35f, rental.getTotalCharge(), "The total charge should be $3.35");
        System.out.println(rental.checkout());
        System.out.println(System.lineSeparator());
    }

    @Test
    void test4() {
        System.out.println("Test 4");
        Tool jakd = inventory.getTool("JAKD");
        Rental rental = new Rental(jakd, LocalDate.of(2015, 9, 3), 6, 0);
        assertEquals(8.97f, rental.getTotalCharge(), "The total charge should be $8.97.");
        System.out.println(rental.checkout());
        System.out.println(System.lineSeparator());
    }

    @Test
    void test5() {
        System.out.println("Test 5");
        Tool jakr = inventory.getTool("JAKR");
        Rental rental = new Rental(jakr, LocalDate.of(2015, 7, 2), 9, 0);
        assertEquals(14.95f, rental.getTotalCharge(), "The total charge should be $14.95.");
        System.out.println(rental.checkout());
        System.out.println(System.lineSeparator());
    }

    @Test
    void test6() {
        System.out.println("Test 6");
        Tool jakr = inventory.getTool("JAKR");
        Rental rental = new Rental(jakr, LocalDate.of(2020, 7, 2), 4, 50);
        assertEquals(1.50f, rental.getTotalCharge(), "The total charge should be $1.50.");
        System.out.println(rental.checkout());
        System.out.println(System.lineSeparator());
    }

    @Test
    void testWeekday() {
        Tool tool = inventory.getTool("LADW");
        Rental rental = new Rental(tool, LocalDate.of(2024, 7, 18), 1, 0);
        assertEquals(1.99f, rental.getTotalCharge(), "The total charge should be 1.99.");
    }

    @Test
    void testWeekendCharge() {
        Tool tool = inventory.getTool("LADW");
        Rental rental = new Rental(tool, LocalDate.of(2024, 7, 19), 2, 0);
        assertEquals(3.98f, rental.getTotalCharge(), "The total charge should be 3.98.");
    }

    @Test
    void testWeekendNoCharge() {
        Tool tool = inventory.getTool("JAKR");
        Rental rental = new Rental(tool, LocalDate.of(2024, 7, 19), 2, 0);
        assertEquals(0, rental.getTotalCharge(), "The total charge should be 0.");
    }

    @Test
    void testRentalDaysLessThan1() {
        Tool jakr = inventory.getTool("JAKR");
        Rental rental = new Rental(jakr, LocalDate.of(2024, 7, 19), 0, 0);
        assertEquals("The rental days must be greater than 0.", rental.checkout(), "Invalid rental days should produce error message.");
    }

    @Test
    void testInvalidDiscount() {
        Tool tool = inventory.getTool("JAKR");
        Rental rental = new Rental(tool, LocalDate.of(2024, 7, 19), 10, -5);
        assertEquals("The discount percent must be between 0 and 100.", rental.checkout(), "Invalid discount rate should produce error message.");

        rental = new Rental(tool, LocalDate.of(2024, 7, 19), 10, 101);
        assertEquals("The discount percent must be between 0 and 100.", rental.checkout(), "Invalid discount rate should produce error message.");
    }

    @Test
    void test10DiscountPercent() {
        Tool tool = inventory.getTool("JAKR");
        Rental rental = new Rental(tool, LocalDate.of(2024, 7, 15), 1, 10);
        assertEquals(2.69f, rental.getTotalCharge(), "The total charge should be 2.69.");
    }

    @Test
    void testLaborDay() {
        Tool tool = inventory.getTool("CHNS");
        Rental rental = new Rental(tool, LocalDate.of(2024, 9, 1), 1, 0);
        assertEquals(1.49f, rental.getTotalCharge(), "The total charge should be 1.49.");
    }

    @Test
    void testIndependenceDay() {
        Tool tool = inventory.getTool("CHNS");
        Rental rental = new Rental(tool, LocalDate.of(2024, 7, 3), 1, 0);
        assertEquals(1.49f, rental.getTotalCharge(), "The total charge should be 1.49.");
    }
}
