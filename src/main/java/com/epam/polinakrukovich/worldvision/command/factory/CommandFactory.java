package com.epam.polinakrukovich.worldvision.command.factory;

import com.epam.polinakrukovich.worldvision.command.Command;
import com.epam.polinakrukovich.worldvision.command.exception.CommandException;
import com.epam.polinakrukovich.worldvision.command.impl.*;
import com.epam.polinakrukovich.worldvision.command.type.CommandType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * {@link CommandFactory} class is used to create commands based on provided
 * action's url-pattern.
 *
 * @see Command
 * @see CommandType
 *
 * @author Polina Krukovich
 */
public class CommandFactory {
    private static final Logger logger = LogManager.getLogger(CommandFactory.class);

    /**
     * Checks whether the specified action matches one of the existing command
     * types, creates required command.
     *
     * @param action action to perform
     * @return {@link Command} object.
     * @throws CommandException if provided action does not match any command type.
     */
    public static Command createCommand(String action) throws CommandException {
        switch (action) {
            case CommandType.USER_CREATE: return new UserCreateCommand();
            case CommandType.USER_READ: return new UserReadCommand();
            case CommandType.USER_UPDATE: return new UserUpdateCommand();
            case CommandType.USER_DELETE: return new UserDeleteCommand();
            case CommandType.IMAGE_CREATE: return new ImageCreateCommand();
            case CommandType.IMAGE_LIST_USER: return new ImageListUserCommand();
            case CommandType.IMAGE_LIST_QUERY: return new ImageListQueryCommand();
            case CommandType.IMAGE_LIST_TOP: return new ImageListTopCommand();
            case CommandType.IMAGE_DELETE: return new ImageDeleteCommand();
            case CommandType.LIKE_CREATE: return new LikeCreateCommand();
            case CommandType.LIKE_DELETE: return new LikeDeleteCommand();
            case CommandType.DOWNLOAD_CREATE: return new DownloadCreateCommand();
            default: throw new CommandException("No command for action: " + action);
        }
    }
}
