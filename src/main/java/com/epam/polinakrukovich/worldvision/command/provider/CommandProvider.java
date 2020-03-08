package com.epam.polinakrukovich.worldvision.command.provider;

import com.epam.polinakrukovich.worldvision.command.Command;
import com.epam.polinakrukovich.worldvision.command.exception.CommandException;
import com.epam.polinakrukovich.worldvision.command.impl.*;
import com.epam.polinakrukovich.worldvision.command.type.CommandType;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link CommandProvider} class provides simple mechanism for accessing
 * command instances based on the command type string.
 *
 * @see Command
 * @see CommandType
 *
 * @author Polina Krukovich
 */
public class CommandProvider {
    private static final class SingletonHolder {
        private static final CommandProvider instance = new CommandProvider();
    }

    private final Map<String, Command> repository = new HashMap<>();

    public static CommandProvider getInstance() {
        return SingletonHolder.instance;
    }

    private CommandProvider() {
        repository.put(CommandType.USER_CREATE, new UserCreateCommand());
        repository.put(CommandType.USER_VERIFY, new UserVerifyCommand());
        repository.put(CommandType.USER_DELETE, new UserDeleteCommand());
        repository.put(CommandType.IMAGE_CREATE, new ImageCreateCommand());
        repository.put(CommandType.IMAGE_LIST_USER, new ImageListUserCommand());
        repository.put(CommandType.IMAGE_LIST_TOP, new ImageListTopCommand());
        repository.put(CommandType.IMAGE_LIST_QUERY, new ImageListQueryCommand());
        repository.put(CommandType.IMAGE_DELETE, new ImageDeleteCommand());
        repository.put(CommandType.LIKE_CREATE, new LikeCreateCommand());
        repository.put(CommandType.LIKE_DELETE, new LikeDeleteCommand());
        repository.put(CommandType.DOWNLOAD_CREATE, new DownloadCreateCommand());
    }

    /**
     * Returns command that matches the specified command type.
     * If no command matches the provided command type, null is returned.
     *
     * @param commandType command type.
     * @return {@link Command} object or null if no command found.
     */
    public Command getCommand(String commandType) {
        return repository.get(commandType);
    }
}
