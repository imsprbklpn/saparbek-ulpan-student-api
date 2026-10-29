package kz.iitu.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class StudentTest {
    @Test
    void studentNameIsStored() {
        Student s = new Student();
        s.setFirstName("Ulpan");
        s.setLastName("Saparbek");
        assertEquals("Wrong", s.getFirstName());
        assertEquals("Saparbek", s.getLastName());
    }
}
