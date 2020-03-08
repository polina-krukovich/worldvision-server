package com.epam.polinakrukovich.worldvision.command.provider;

import com.epam.polinakrukovich.worldvision.command.Command;
import com.epam.polinakrukovich.worldvision.command.exception.CommandException;
import com.epam.polinakrukovich.worldvision.command.impl.*;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CommandProviderTest {

    @Test
    public void testCreateCommand_UserCreateCommand() throws CommandException {
        // When
        Command actual = CommandProvider.getCommand("/user/create");
        // Then
        assertTrue(actual instanceof UserCreateCommand);
    }

    @Test
    public void testCreateCommand_UserReadCommand() throws CommandException {
        // When
        Command actual = CommandProvider.getCommand("/user/read");
        // Then
        assertTrue(actual instanceof UserVerifyCommand);
    }

    @Test
    public void testCreateCommand_UserUpdateCommand() throws CommandException {
        // When
        Command actual = CommandProvider.getCommand("/user/update");
        // Then
        assertTrue(actual instanceof UserUpdateCommand);
    }

    @Test
    public void testCreateCommand_UserDeleteCommand() throws CommandException {
        // When
        Command actual = CommandProvider.getCommand("/user/delete");
        // Then
        assertTrue(actual instanceof UserDeleteCommand);
    }

    @Test
    public void testCreateCommand_ImageCreateCommand() throws CommandException {
        // When
        Command actual = CommandProvider.getCommand("/image/create");
        // Then
        assertTrue(actual instanceof ImageCreateCommand);
    }

    @Test
    public void testCreateCommand_ImageListQueryCommand() throws CommandException {
        // When
        Command actual = CommandProvider.getCommand("/image/list/query");
        // Then
        assertTrue(actual instanceof ImageListQueryCommand);
    }

    @Test
    public void testCreateCommand_ImageDeleteCommand() throws CommandException {
        // When
        Command actual = CommandProvider.getCommand("/image/delete");
        // Then
        assertTrue(actual instanceof ImageDeleteCommand);
    }

    @Test
    public void testCreateCommand_LikeCreateCommand() throws CommandException {
        // When
        Command actual = CommandProvider.getCommand("/like/create");
        // Then
        assertTrue(actual instanceof LikeCreateCommand);
    }

    @Test
    public void testCreateCommand_IncorrectCommand() {
        assertThrows(CommandException.class, () -> CommandProvider.getCommand("like/create"));
    }
}