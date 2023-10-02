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
    void add_success() {
        bk.add("USER1", "11111");
        bk.add("USER2", "22222");
        int contactsCount = bk.add("USER3", "33333");
        int expectedCount = 3;
        assertEquals(expectedCount, contactsCount);
    }

    @ParameterizedTest
    @EmptySource
    @NullSource
    void add_empty_name_failure(String name) {
        String phone = "11111";
        int expectedCount = 0;
        int contactsCount = bk.add(name, phone);
        assertEquals(expectedCount, contactsCount);
    }

}
