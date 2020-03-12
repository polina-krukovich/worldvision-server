package com.epam.polinakrukovich.worldvision.command.impl;

import com.epam.polinakrukovich.worldvision.command.Command;
import com.epam.polinakrukovich.worldvision.command.exception.CommandException;
import com.epam.polinakrukovich.worldvision.service.UserService;
import com.epam.polinakrukovich.worldvision.service.exception.ServiceException;
import com.epam.polinakrukovich.worldvision.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserCreateCommand implements Command {

    private Logger logger = LogManager.getLogger(getClass());

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String userId = req.getParameter("userId");
        if (userId != null && !userId.isEmpty()) {
            ServiceFactory factory = ServiceFactory.getInstance();
            UserService service = factory.getUserService();
            try {
                service.createUser(userId);
            } catch (ServiceException e) {
                logger.error(e.getMessage());
                throw new CommandException(e.getMessage());
            }
        }
    }
}
