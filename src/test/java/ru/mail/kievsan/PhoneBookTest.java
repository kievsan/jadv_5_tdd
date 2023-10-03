package ru.mail.kievsan;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;

import org.mockito.Mockito;
import static org.mockito.Mockito.never;
import static org.mockito.ArgumentMatchers.anyString;

import java.io.PrintStream;


public class PhoneBookTest {
    private PhoneBook bk;

    @BeforeEach
    void setUp() {
        bk = new PhoneBook();
    }

    @AfterEach
    void down() {
        bk = null;
    }

    @Test
    void addSuccess() {
        bk.add("USER1", "11111");
        bk.add("USER2", "22222");
        int contactsCount = bk.add("USER3", "33333");
        int expectedCount = 3;
        assertEquals(expectedCount, contactsCount);
    }

    @ParameterizedTest
    @EmptySource
    @NullSource
    void addEmptyNameFailure(String name) {
        String phone = "11111";
        int expectedCount = 0;
        int contactsCount = bk.add(name, phone);
        assertEquals(expectedCount, contactsCount);
    }

    @ParameterizedTest
    @EmptySource
    @NullSource
    void addEmptyPhoneFailure(String phone) {
        String name = "USER1";
        int expectedCount = 0;
        int contactsCount = bk.add(name, phone);
        assertEquals(expectedCount, contactsCount);
    }

    @Test
    void addSkipDoubleSuccess() {
        bk.add("USER1", "11111");
        bk.add("USER1", "11111");
        int contactsCount = bk.add("USER1", "2222");
        int expectedCount = 1;
        assertEquals(expectedCount, contactsCount);
    }

    @Test
    void findByNumberSuccess() {
        String expectedName = "USER1";
        String phone = "11111";
        bk.add(expectedName, phone);
        assertEquals(expectedName, bk.findByNumber(phone));
    }

    @ParameterizedTest
    @EmptySource
    @NullSource
    @ValueSource(strings = {"11111"})
    void findByNumberFailure(String phone) {
        String expectedName = "";
        assertEquals(expectedName, bk.findByNumber(phone));
    }

    @Test
    void findByNameSuccess() {
        String name = "USER1";
        String expectedPhone = "11111";
        bk.add(name, expectedPhone);
        assertEquals(expectedPhone, bk.findByName(name));
    }

    @ParameterizedTest
    @EmptySource
    @NullSource
    @ValueSource(strings = {"USER1"})
    void findByNameFailure(String name) {
        String expectedPhone = "";
        assertEquals(expectedPhone, bk.findByName(name));
    }

    @Test
    void printAllNamesEmptySuccess() {
        PrintStream systemOut = System.out;
        PrintStream outMock = Mockito.spy(System.out);

        System.setOut(outMock);
        try {
            bk.printAllNames();
        } finally {
            System.setOut(systemOut);
        }
        Mockito.verify(outMock, never()).print(anyString());
    }

}
