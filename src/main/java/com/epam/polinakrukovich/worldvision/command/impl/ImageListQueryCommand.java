package com.epam.polinakrukovich.worldvision.command.impl;

import com.epam.polinakrukovich.worldvision.command.Command;
import com.epam.polinakrukovich.worldvision.command.exception.CommandException;
import com.epam.polinakrukovich.worldvision.entity.Image;
import com.epam.polinakrukovich.worldvision.service.ImageService;
import com.epam.polinakrukovich.worldvision.service.exception.ServiceException;
import com.epam.polinakrukovich.worldvision.service.factory.ServiceFactory;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * This command is used to get all images matching the specified query.
 * Three parameters are taken into account:
 *      1) Tags - keywords describing the image;
 *      2) Color - one of the image primary colors;
 *      3) Creation Time - number of days passed since the image was uploaded.
 * P.S. Mot all parameters may be specified.
 *
 * It uses methods of {@link ImageService} class to get images matching individual
 * parameters and then merges them using Set operations.
 *
 * Returns JSON result.
 *
 * @see Set
 * @see ImageService
 *
 * @author Polina Krukovich
 */
public class ImageListQueryCommand implements Command {

    private Logger logger = LogManager.getLogger(getClass());

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String queryTags = req.getParameter("q");
        String queryColorId = req.getParameter("colorId");
        String queryDaysPassed = req.getParameter("daysPassed");

        Set<Image> imageSet = new HashSet<>();

        ServiceFactory factory = ServiceFactory.getInstance();
        ImageService service = factory.getImageService();
        try {
            if (queryTags != null && !queryTags.isEmpty()) {
                String[] tags = queryTags.split(" ");
                for (String tag : tags) {
                    Image[] images = service.listImagesByTag(tag);
                    imageSet.addAll(Arrays.asList(images));
                }
            }
            if (queryColorId != null && !queryColorId.isEmpty()) {
                int colorId = Integer.parseInt(queryColorId);
                Set<Image> images = new HashSet<>(Arrays.asList(service.listImagesByColor(colorId)));
                if (queryTags == null || queryTags.isEmpty()) {
                    imageSet.addAll(images);
                } else {
                    imageSet.retainAll(images);
                }
            }
            if (queryDaysPassed != null && !queryDaysPassed.isEmpty()) {
                int daysPassed = Integer.parseInt(queryDaysPassed);
                Set<Image> images = new HashSet<>(Arrays.asList(service.listImagesByCreationTime(daysPassed)));
                if ((queryTags == null || queryTags.isEmpty()) && (queryColorId == null || queryColorId.isEmpty())) {
                    imageSet.addAll(images);
                } else {
                    imageSet.retainAll(images);
                }
            }
            Image[] images = new Image[imageSet.size()];
            imageSet.toArray(images);
            Gson gson = new Gson();
            String jsonString = gson.toJson(images);
            resp.getWriter().print(jsonString);
        } catch (ServiceException | NumberFormatException | IOException e) {
            logger.error(e.getMessage());
            throw new CommandException(e.getMessage());
        }
    }
}
