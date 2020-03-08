package com.epam.polinakrukovich.worldvision.controller;

import com.epam.polinakrukovich.worldvision.command.Command;
import com.epam.polinakrukovich.worldvision.command.exception.CommandException;
import com.epam.polinakrukovich.worldvision.command.provider.CommandProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The main and only application servlet. Receives all requests and
 * uses {@link CommandProvider} to determine which command to execute.
 *
 * @see HttpServletRequest
 * @see HttpServletResponse
 * @see CommandProvider
 *
 * @author Polina Krukovich
 */
public class Controller extends HttpServlet {
    private final Logger logger = LogManager.getLogger(getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        processRequest(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) {
        CommandProvider provider = CommandProvider.getInstance();
        Command command = provider.getCommand(req.getRequestURI());
        if (null != command) {
            try {
                command.execute(req, resp);
            } catch (CommandException e) {
                logger.error(e.getMessage());
            }
        }
    }
}
