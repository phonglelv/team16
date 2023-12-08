package poly.tt.thuexe.Config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // Your custom logic goes here

        // Check if the user should be logged out
        if (shouldLogout(authentication)) {
            // Perform logout logic (invalidate session, etc.)
            request.getSession().invalidate();

            // Redirect the user to the login page
            response.sendRedirect("/security/login/form");
        } else {
            // Redirect the user to a specific page after successful authentication
            response.sendRedirect("/security/logoff/success");
        }
    }

    private boolean shouldLogout(Authentication authentication) {
        // Implement your logic to determine if the user should be logged out
        // For example, check a flag in the authentication object or database
        return true; // Adjust this condition based on your requirements
    }
}
