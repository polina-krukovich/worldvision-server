package com.epam.polinakrukovich.worldvision.command.impl;

import com.epam.polinakrukovich.worldvision.command.Command;
import com.epam.polinakrukovich.worldvision.command.exception.CommandException;
import com.epam.polinakrukovich.worldvision.service.ImageService;
import com.epam.polinakrukovich.worldvision.service.exception.ServiceException;
import com.epam.polinakrukovich.worldvision.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageDeleteCommand implements Command {

    Logger logger = LogManager.getLogger(getClass());

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String imageUrl = req.getParameter("imageUrl");
        if (imageUrl != null && !imageUrl.isEmpty()) {
            ServiceFactory factory = ServiceFactory.getInstance();
            ImageService service = factory.getImageService();
            try {
                service.deleteImage(imageUrl);
            } catch (ServiceException e) {
                logger.error(e.getMessage());
                throw new CommandException(e.getMessage());
            }
        }
    }
}
