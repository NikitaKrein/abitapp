package by.epam.krein.abitapp.controller.commandImpl;


import by.epam.krein.abitapp.controller.CommandName;
import by.epam.krein.abitapp.entity.User;
import by.epam.krein.abitapp.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class SignInButtonTests {

    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    @Test
    public void callCommandMethodTest() throws SQLException {
        SignInButton signInButton = new SignInButton();
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        UserService userService = Mockito.mock(UserService.class);
        signInButton.setUserService(userService);
        User user = Mockito.mock(User.class);
        HttpSession httpSession = Mockito.mock(HttpSession.class);
        when(request.getMethod()).thenReturn("POST");
        when(request.getParameter(eq(EMAIL))).thenReturn(EMAIL);
        when(request.getParameter(eq(PASSWORD))).thenReturn(PASSWORD);
        when(userService.signIn(eq(EMAIL))).thenReturn(user);

       // when(user.getPassword()).thenReturn(PASSWORD);
        when(request.getSession()).thenReturn(httpSession);
        Assertions.assertEquals(CommandName.PROFILE, signInButton.callCommandMethod(request));
        verify(httpSession, times(1)).setAttribute(eq("user"), eq(user));
    }

    @Test
    public void callCommandMethodTestSecond() throws SQLException {
        SignInButton signInButton = new SignInButton();
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        UserService userService = Mockito.mock(UserService.class);
        signInButton.setUserService(userService);
        User user = Mockito.mock(User.class);
        HttpSession httpSession = Mockito.mock(HttpSession.class);
        when(request.getMethod()).thenReturn("POST");
        when(request.getParameter(eq(EMAIL))).thenReturn(EMAIL);
        when(request.getParameter(eq(PASSWORD))).thenReturn(PASSWORD);
        when(userService.signIn(eq(EMAIL))).thenReturn(user);

       // when(user.getPassword()).thenReturn("wrongPassword");
        when(request.getSession()).thenReturn(httpSession);
        Assertions.assertEquals(CommandName.SIGN_IN_BUTTON, signInButton.callCommandMethod(request));
        verify(httpSession, never()).setAttribute(eq("user"), eq(user));
    }

    @Test
    public void callCommandMethodTestExp() throws SQLException {
        SignInButton signInButton = new SignInButton();
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        UserService userService = Mockito.mock(UserService.class);
        signInButton.setUserService(userService);
        User user = Mockito.mock(User.class);
        HttpSession httpSession = Mockito.mock(HttpSession.class);
        when(request.getMethod()).thenReturn("POST");
        when(request.getParameter(eq(EMAIL))).thenReturn(EMAIL);
        when(request.getParameter(eq(PASSWORD))).thenReturn(PASSWORD);
        when(userService.signIn(eq(EMAIL))).thenThrow(SQLException.class);

        //when(user.getPassword()).thenReturn("wrongPassword");
        when(request.getSession()).thenReturn(httpSession);
        Assertions.assertEquals(CommandName.SIGN_IN_BUTTON, signInButton.callCommandMethod(request));
        verify(httpSession, never()).setAttribute(eq("user"), eq(user));
    }

}
