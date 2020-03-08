package com.epam.polinakrukovich.worldvision.util;

import com.epam.polinakrukovich.worldvision.config.Config;
import com.epam.polinakrukovich.worldvision.util.exception.UtilException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Acl.User;
import com.google.cloud.storage.Acl.Role;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * Constructs {@link StorageUtil} object and initializes
     * Google Cloud Storage API.
     */
    private StorageUtil() {
        Config config = Config.getInstance();
        File saFile = new File(config.getSaFilePath());
        try (FileInputStream stream = new FileInputStream(saFile)) {
            StorageOptions options = StorageOptions.newBuilder()
                    .setCredentials(GoogleCredentials.fromStream(stream))
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
     * @param fileStream file stream.
     * @return file public link.
     * @throws UtilException if file upload fails.
     */
    public String uploadFile(FileItemStream fileStream) throws UtilException {
        String pattern = "-dd-MM-YYYY-HH-mm-ss-SSS";
        String dateString = DateTime.now(DateTimeZone.UTC)
                .toString(DateTimeFormat.forPattern(pattern));
        String newFileName = fileStream.getName() + dateString;
        try {
            List<Acl> acls = new ArrayList<>();
            acls.add(Acl.of(User.ofAllUsers(), Role.READER));
            BlobInfo blobInfo = storage.create(
                    BlobInfo.newBuilder(bucketName, newFileName).setAcl(acls).build(),
                    fileStream.openStream());
            return blobInfo.getMediaLink();
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new UtilException(e.getMessage());
        }
    }

}
