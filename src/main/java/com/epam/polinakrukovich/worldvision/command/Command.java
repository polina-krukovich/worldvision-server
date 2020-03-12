package com.epam.polinakrukovich.worldvision.command;

import com.epam.polinakrukovich.worldvision.command.exception.CommandException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface used in all server commands.
 *
 * @author Polina Krukovich
 */
public interface Command {
    /**
     * Executes the command.
     *
     * @param req http request
     * @param resp http response
     * @throws CommandException if command failed execution.
     */
    void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException;
}
