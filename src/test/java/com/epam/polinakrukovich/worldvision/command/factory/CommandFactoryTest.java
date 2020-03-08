package com.epam.polinakrukovich.worldvision.command.factory;

import com.epam.polinakrukovich.worldvision.command.Command;
import com.epam.polinakrukovich.worldvision.command.exception.CommandException;
import com.epam.polinakrukovich.worldvision.command.impl.*;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CommandFactoryTest {

    @Test
    public void testCreateCommand_UserCreateCommand() throws CommandException {
        // When
        Command actual = CommandFactory.createCommand("/user/create");
        // Then
        assertTrue(actual instanceof UserCreateCommand);
    }

    @Test
    public void testCreateCommand_UserReadCommand() throws CommandException {
        // When
        Command actual = CommandFactory.createCommand("/user/read");
        // Then
        assertTrue(actual instanceof UserReadCommand);
    }

    @Test
    public void testCreateCommand_UserUpdateCommand() throws CommandException {
        // When
        Command actual = CommandFactory.createCommand("/user/update");
        // Then
        assertTrue(actual instanceof UserUpdateCommand);
    }

    @Test
    public void testCreateCommand_UserDeleteCommand() throws CommandException {
        // When
        Command actual = CommandFactory.createCommand("/user/delete");
        // Then
        assertTrue(actual instanceof UserDeleteCommand);
    }

    @Test
    public void testCreateCommand_ImageCreateCommand() throws CommandException {
        // When
        Command actual = CommandFactory.createCommand("/image/create");
        // Then
        assertTrue(actual instanceof ImageCreateCommand);
    }

    @Test
    public void testCreateCommand_ImageListQueryCommand() throws CommandException {
        // When
        Command actual = CommandFactory.createCommand("/image/list/query");
        // Then
        assertTrue(actual instanceof ImageListQueryCommand);
    }

    @Test
    public void testCreateCommand_ImageDeleteCommand() throws CommandException {
        // When
        Command actual = CommandFactory.createCommand("/image/delete");
        // Then
        assertTrue(actual instanceof ImageDeleteCommand);
    }

    @Test
    public void testCreateCommand_LikeCreateCommand() throws CommandException {
        // When
        Command actual = CommandFactory.createCommand("/like/create");
        // Then
        assertTrue(actual instanceof LikeCreateCommand);
    }

    @Test
    public void testCreateCommand_IncorrectCommand() {
        assertThrows(CommandException.class, () -> CommandFactory.createCommand("like/create"));
    }
}