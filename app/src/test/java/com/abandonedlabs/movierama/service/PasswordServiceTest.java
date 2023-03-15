package com.abandonedlabs.movierama.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * The type Hash service test.
 */
public class PasswordServiceTest {
    /**
     * The Password encoder.
     */
    @Mock
    PasswordEncoder passwordEncoder;
    /**
     * The Hash service.
     */
    @InjectMocks
    PasswordService passwordService;

    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test b crypt password.
     *
     * @throws Exception the exception
     */
    @Test
    public void testBCryptPassword() throws Exception {
        String result = passwordService.bCryptPassword("password");
        Assert.assertEquals("replaceMeWithExpectedResult", result);
    }

    /**
     * Test matches.
     *
     * @throws Exception the exception
     */
    @Test
    public void testMatches() throws Exception {
        boolean result = passwordService.matches("rawPassword", "bCryptedPassord");
        Assert.assertEquals(true, result);
    }

    /**
     * Test sha 512.
     *
     * @throws Exception the exception
     */
    @Test
    public void testSha512() throws Exception {
        String result = passwordService.sha512("salt", "rawPassword");
        Assert.assertEquals("replaceMeWithExpectedResult", result);
    }

    /**
     * Test hash.
     *
     * @throws Exception the exception
     */
    @Test
    public void testHash() throws Exception {
        String result = passwordService.hash("algorithm", "salt", "rawPassword");
        Assert.assertEquals("replaceMeWithExpectedResult", result);
    }
}