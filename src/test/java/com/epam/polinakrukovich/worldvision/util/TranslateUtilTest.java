package com.epam.polinakrukovich.worldvision.util;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TranslateUtilTest {
    @Test
    public void testGetInstance_CompareWithDirectlyCreatedInstance_ExpectedNotEqual() {
        assertNotEquals(new TranslateUtil(), TranslateUtil.getInstance());
    }

    @Test
    public void testGetInstance_WhenCalledTwoTimes_ExpectedOneCreatedInstance() {
        assertEquals(TranslateUtil.getInstance(), TranslateUtil.getInstance());
    }

    @Test
    public void testDetectLanguage_GivenEnglish_ExpectedEnglish() {
        TranslateUtil translateUtil = new TranslateUtil();
        // Given
        String expected = "en";
        // When
        String actual = translateUtil.detectLanguage("sky");
        // Then
        assertEquals(actual, expected);
    }

    @Test
    public void testDetectLanguage_GivenRussian_ExpectedRussian() {
        TranslateUtil translateUtil = new TranslateUtil();
        // Given
        String expected = "ru";
        // When
        String actual = translateUtil.detectLanguage("небо");
        // Then
        assertEquals(actual, expected);
    }

    @Test
    public void testDetectLanguage_GivenGerman_ExpectedGerman() {
        TranslateUtil translateUtil = new TranslateUtil();
        // Given
        String expected = "de";
        // When
        String actual = translateUtil.detectLanguage("Himmel");
        // Then
        assertEquals(actual, expected);
    }

    @Test
    public void testTranslate_FromRussianToEnglish() {
        TranslateUtil translateUtil = new TranslateUtil();
        // Given
        String expected = "sky";
        // When
        String actual = translateUtil.translate("ru", "en", "небо");
        // Then
        assertEquals(actual, expected);
    }

    @Test
    public void testTranslate_FromRussianToGerman() {
        TranslateUtil translateUtil = new TranslateUtil();
        // Given
        String expected = "Himmel";
        // When
        String actual = translateUtil.translate("ru", "de", "небо");
        // Then
        assertEquals(actual, expected);
    }
}