package com.epam.polinakrukovich.worldvision.command.provider;

import com.epam.polinakrukovich.worldvision.command.Command;
import com.epam.polinakrukovich.worldvision.command.exception.CommandException;
import com.epam.polinakrukovich.worldvision.command.impl.*;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CommandProviderTest {

    @Test
    public void testCreateCommand_UserCreateCommand()  {
        CommandProvider provider = new CommandProvider();
        // When
        Command actual = provider.getCommand("/user/create");
        // Then
        assertTrue(actual instanceof UserCreateCommand);
    }

    @Test
    public void testCreateCommand_UserDeleteCommand () {
        CommandProvider provider = new CommandProvider();
        // When
        Command actual = provider.getCommand("/user/delete");
        // Then
        assertTrue(actual instanceof UserDeleteCommand);
    }

    @Test
    public void testCreateCommand_ImageCreateCommand()  {
        CommandProvider provider = new CommandProvider();
        // When
        Command actual = provider.getCommand("/image/create");
        // Then
        assertTrue(actual instanceof ImageCreateCommand);
    }

    @Test
    public void testCreateCommand_ImageListQueryCommand()  {
        CommandProvider provider = new CommandProvider();
        // When
        Command actual = provider.getCommand("/image/list/query");
        // Then
        assertTrue(actual instanceof ImageListQueryCommand);
    }

    @Test
    public void testCreateCommand_ImageDeleteCommand()  {
        CommandProvider provider = new CommandProvider();
        // When
        Command actual = provider.getCommand("/image/delete");
        // Then
        assertTrue(actual instanceof ImageDeleteCommand);
    }
}