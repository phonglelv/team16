package poly.tt.thuexe.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import poly.tt.thuexe.Entity.Customer;
import poly.tt.thuexe.Repository.CustomerRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerRepository accountService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomUserDetailsService(CustomerRepository accountService, PasswordEncoder passwordEncoder) {
        this.accountService = accountService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer account = accountService.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new CustomUserDetails(
                account.getUsername(),
                account.getPassword(),
                account.getFullname(),
                account.getEmail(),
                account.getPhone(),
                account.getAddress(),
                account.getAuthorities()
        );
    }
}
