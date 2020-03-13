package com.epam.polinakrukovich.worldvision.command.impl;

import com.epam.polinakrukovich.worldvision.command.Command;
import com.epam.polinakrukovich.worldvision.command.exception.CommandException;
import com.epam.polinakrukovich.worldvision.service.DownloadService;
import com.epam.polinakrukovich.worldvision.service.exception.ServiceException;
import com.epam.polinakrukovich.worldvision.service.factory.ServiceFactory;
import org.apache.commons.logging.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DownloadListTimeCommand implements Command {

    Logger logger = LogManager.getLogger(getClass());

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String daysPassedString = req.getParameter("daysPassed");

        ServiceFactory factory = ServiceFactory.getInstance();
        DownloadService service = factory.getDownloadService();

        try {
            int daysPassed = Integer.parseInt(daysPassedString);
            int downloadsCount = service.readDownloadsCountByCreationTime(daysPassed);
            resp.getWriter().print(downloadsCount);
        } catch (NumberFormatException | IOException | ServiceException e) {
            logger.error(e.getMessage());
            throw new CommandException(e.getMessage());
        }
    }
}
