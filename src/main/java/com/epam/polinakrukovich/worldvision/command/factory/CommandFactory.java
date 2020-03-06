package com.epam.polinakrukovich.worldvision.command.factory;

import com.epam.polinakrukovich.worldvision.command.Command;
import com.epam.polinakrukovich.worldvision.command.exception.CommandException;
import com.epam.polinakrukovich.worldvision.command.impl.*;
import com.epam.polinakrukovich.worldvision.command.type.CommandType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandFactory {
    private static final Logger logger = LogManager.getLogger(CommandFactory.class);

    public static Command createCommand(String action) throws CommandException {
        CommandType commandType;
        try {
            commandType = CommandType.valueOf(action);
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            throw new CommandException(e.getMessage(), e);
        }
        switch (commandType) {
            case USER_CREATE: return new UserCreateCommand();
            case USER_READ: return new UserReadCommand();
            case USER_UPDATE: return new UserUpdateCommand();
            case USER_DELETE: return new UserDeleteCommand();
            case IMAGE_CREATE: return new ImageCreateCommand();
            case IMAGE_LIST_USER: return new ImageListUserCommand();
            case IMAGE_LIST_QUERY: return new ImageListQueryCommand();
            case IMAGE_LIST_TOP: return new ImageListTopCommand();
            case IMAGE_DELETE: return new ImageDeleteCommand();
            case LIKE_CREATE: return new LikeCreateCommand();
            case LIKE_DELETE: return new LikeDeleteCommand();
            case DOWNLOAD_CREATE: return new DownloadCreateCommand();
            default: throw new CommandException(String.format("No command for action: %s", action));
        }
    }
}
