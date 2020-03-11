package com.epam.polinakrukovich.worldvision.util;

import com.epam.polinakrukovich.worldvision.config.Config;
import com.epam.polinakrukovich.worldvision.util.exception.UtilException;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Acl.User;
import com.google.cloud.storage.Acl.Role;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javatuples.Pair;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * {@link StorageUtil} class provides functionality for working with
 * Google Cloud Storage.
 *
 * @see Storage
 * @see StorageOptions
 * @see BlobInfo
 *
 * @author Polina Krukovich
 */
public class StorageUtil {
    private static class SingletonHolder {
        private static final StorageUtil instance = new StorageUtil();
    }

    private Logger logger = LogManager.getLogger(getClass());
    private Storage storage;
    private String bucketName;

    public static StorageUtil getInstance() {
        return SingletonHolder.instance;
    }

    /**
     * Constructs {@link StorageUtil} object and initializes
     * Google Cloud Storage API.
     */
    private StorageUtil() {
        Config config = Config.getInstance();
        String saFilePath = config.getSaFilePath();
        try (InputStream stream = Objects.requireNonNull(
                StorageUtil.class.getClassLoader().getResourceAsStream(saFilePath))) {
            StorageOptions options = StorageOptions.newBuilder()
                    .setCredentials(ServiceAccountCredentials.fromStream(stream))
                    .build();
            storage = options.getService();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        bucketName = config.getStorageBucketName();
    }

    /**
     * Uploads file from {@link FileItemStream} to the Cloud Storage
     * and returns the public link.
     *
     * @param item file stream.
     * @return file public link.
     * @throws UtilException if file upload fails.
     */
    public Pair<String, String> uploadFile(FileItemStream item) throws UtilException {
        String pattern = "dd-MM-YYYY-HH-mm-ss-SSS-";
        String dateString = DateTime.now(DateTimeZone.UTC)
                .toString(DateTimeFormat.forPattern(pattern));
        String newFileName = dateString + item.getName();
        BlobInfo blobInfo = null;
        try {
            blobInfo = storage.create(
                    BlobInfo.newBuilder(bucketName, newFileName).setAcl(new ArrayList<>(Arrays.asList(Acl.of(User.ofAllUsers(), Role.READER))))
                            .build(),
                    item.openStream());
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new UtilException(e.getMessage());
        }
        return Pair.with(blobInfo.getMediaLink(), String.format("gs://%s/%s", bucketName, blobInfo.getName()));
    }

}
