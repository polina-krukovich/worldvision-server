package com.epam.polinakrukovich.worldvision.command;

import com.epam.polinakrukovich.worldvision.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException;
}
