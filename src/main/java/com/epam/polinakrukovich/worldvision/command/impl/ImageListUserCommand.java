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

/**
 * Retrieves user ID from request parameter and passes it
 * to the {@link ImageService#listImagesByUser} to get
 * images uploaded by specified user.
 * Returns JSON result.
 *
 * @see ImageService
 *
 * @author Polina Krukovich
 */
public class ImageListUserCommand implements Command {

    private Logger logger = LogManager.getLogger(getClass());

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String userId = req.getParameter("userId");
        if (userId != null && !userId.isEmpty()) {
            ServiceFactory factory = ServiceFactory.getInstance();
            ImageService service = factory.getImageService();
            try {
                Image[] images =  service.listImagesByUser(userId);
                Gson gson = new Gson();
                String jsonString = gson.toJson(images);
                resp.getWriter().print(jsonString);
            } catch (ServiceException | IOException e) {
                logger.error(e.getMessage());
                throw new CommandException(e.getMessage());
            }
        }
    }
}
