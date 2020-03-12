package com.epam.polinakrukovich.worldvision.util;

import com.epam.polinakrukovich.worldvision.config.Config;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.StorageOptions;
import com.google.cloud.translate.Detection;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import com.google.common.annotations.VisibleForTesting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * {@link TranslateUtil} class contains text translation functions implemented
 * using Google Cloud Translation API.
 *
 * @see Translate
 * @see Translation
 * @see Detection
 *
 * @author Polina Krukovich
 */
public class TranslateUtil {
    private static final class SingletonHolder {
        private static final TranslateUtil instance = new TranslateUtil();
    }

    private Logger logger = LogManager.getLogger(getClass());
    private Translate translate;

    public static TranslateUtil getInstance() {
        return SingletonHolder.instance;
    }

    /**
     * Constructs {@link TranslateUtil} object and initializes the
     * Translation API.
     */
    @VisibleForTesting
    TranslateUtil() {
        Config config = Config.getInstance();
        String saFilePath = config.getSaFilePath();
        try (InputStream stream = Objects.requireNonNull(
                TranslateUtil.class.getClassLoader().getResourceAsStream(saFilePath))) {
            TranslateOptions options = TranslateOptions.newBuilder()
                    .setCredentials(GoogleCredentials.fromStream(stream))
                    .build();
            translate = options.getService();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Translates provided text from {@code sourceLang} to {@code targetLang}.
     *
     * @param sourceLang source language.
     * @param targetLang target language.
     * @param text text to translate.
     * @return translated text.
     */
    public String translate(String sourceLang, String targetLang, String text) {
        Translation translation = translate.translate(
                text,
                Translate.TranslateOption.sourceLanguage(sourceLang),
                Translate.TranslateOption.targetLanguage(targetLang),
                Translate.TranslateOption.model("nmt"));
        return translation.getTranslatedText();
    }

    /**
     * Detects the language of a text.
     *
     * @param text text to detect language.
     * @return language in short format, e.g. "en", "ru", "de"...
     */
    public String detectLanguage(String text) {
        Detection detection = translate.detect(text);
        return detection.getLanguage();
    }
}
