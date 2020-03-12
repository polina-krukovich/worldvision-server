package com.epam.polinakrukovich.worldvision.command.impl;

import com.epam.polinakrukovich.worldvision.command.Command;
import com.epam.polinakrukovich.worldvision.command.exception.CommandException;
import com.epam.polinakrukovich.worldvision.service.ImageService;
import com.epam.polinakrukovich.worldvision.service.exception.ServiceException;
import com.epam.polinakrukovich.worldvision.service.factory.ServiceFactory;
import com.epam.polinakrukovich.worldvision.util.StorageUtil;
import com.epam.polinakrukovich.worldvision.util.exception.UtilException;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javatuples.Pair;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Retrieves file stream containing image to create and user ID
 * from the request. Saves the image to Google Cloud Storage
 * using {@link StorageUtil} to get <i>Google Cloud Storage path</i>
 * and <i>image URL</i>. Calls {@link ImageService#createImage} for
 * further processing.
 *
 * @see StorageUtil
 * @see ImageService
 * @see FileItemStream
 *
 * @author Polina Krukovich
 */
public class ImageCreateCommand implements Command {

    private Logger logger = LogManager.getLogger(getClass());

    /**
     * This method accepts HTTP request containing form data.
     * It uses {@link FileItemIterator} to iterate over form
     * fields and retrieve user ID value and file stream.
     * Then it calls {@link #uploadImage} to save image to
     * GCStorage and passes retrieved image URL and gcsPath
     * to {@link ImageService}.
     *
     * @param req http request.
     * @param resp http response.
     * @throws CommandException if an error errors encountered
     *                          while processing the request.
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            FileItemIterator iter = new ServletFileUpload().getItemIterator(req);
            String userId = null;
            String url = null;
            String gcsPath = null;
            while (iter.hasNext()) {
                FileItemStream item = iter.next();
                if (!item.isFormField()) {
                    Pair<String, String> path = uploadImage(item);
                    url = path.getValue0();
                    gcsPath = path.getValue1();
                } else {
                    userId = Streams.asString(item.openStream());
                }
            }
            ServiceFactory factory = ServiceFactory.getInstance();
            ImageService service = factory.getImageService();
            service.createImage(url, gcsPath, userId);
        } catch (FileUploadException | ServiceException | IOException e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage());
            throw new CommandException(e.getMessage());
        }
    }

    /**
     * Saves image in GCStorage using {@link StorageUtil}.
     *
     * @param item file item stream.
     * @return image URL and gcsPath.
     * @throws CommandException if an error occurred while
     *                          saving image to storage.
     */
    private Pair<String, String> uploadImage(FileItemStream item) throws CommandException {
        StorageUtil util = StorageUtil.getInstance();
        try {
            return util.uploadFile(item);
        } catch (UtilException e) {
            logger.error(e.getMessage());
            throw new CommandException(e.getMessage());
        }
    }
}
