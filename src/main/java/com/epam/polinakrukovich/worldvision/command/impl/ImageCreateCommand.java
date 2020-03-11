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
import java.io.InputStream;

public class ImageCreateCommand implements Command {
    private Logger logger = LogManager.getLogger(getClass());

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        try {
            FileItemIterator iter = new ServletFileUpload().getItemIterator(req);
            String uid = null;
            String url = null;
            String gcsPath = null;
            while (iter.hasNext()) {
                FileItemStream item = iter.next();
                if (!item.isFormField()) {
                    Pair<String, String> path = uploadImage(item);
                    url = path.getValue0();
                    gcsPath = path.getValue1();
                } else {
                    uid = Streams.asString(item.openStream());
                }
            }
            ServiceFactory factory = ServiceFactory.getInstance();
            ImageService service = factory.getImageService();
            service.createImage(url, gcsPath, uid);
        } catch (FileUploadException | ServiceException | IOException e) {
            logger.error(e.getMessage());
            throw new CommandException(e.getMessage());
        }
    }

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
