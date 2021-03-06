package com.epam.polinakrukovich.worldvision.command.impl;

import com.epam.polinakrukovich.worldvision.command.Command;
import com.epam.polinakrukovich.worldvision.command.exception.CommandException;
import com.epam.polinakrukovich.worldvision.service.UserService;
import com.epam.polinakrukovich.worldvision.service.exception.ServiceException;
import com.epam.polinakrukovich.worldvision.service.factory.ServiceFactory;
import com.epam.polinakrukovich.worldvision.util.AuthUtil;
import com.epam.polinakrukovich.worldvision.util.exception.UtilException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserDeleteCommand implements Command {

    Logger logger = LogManager.getLogger(getClass());

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String userId = req.getParameter("userId");

        ServiceFactory factory = ServiceFactory.getInstance();
        UserService service = factory.getUserService();
        AuthUtil util = AuthUtil.getInstance();

        try {
            util.deleteUser(userId);
            service.deleteUser(userId);
        } catch (UtilException | ServiceException e) {
            logger.error(e.getMessage());
            throw new CommandException(e.getMessage());
        }
    }
}
