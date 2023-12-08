package poly.tt.thuexe.Service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import poly.tt.thuexe.Entity.Authority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    private final String username;
    private final String password;
    private String fullname;
    private String email;
    private String phone;
    private String address;
    private final List<Authority> authorities;

    public CustomUserDetails(String username, String password, String fullname, String email, String phone,
                             String address, List<Authority> authorities) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convert the list of authorities to a collection of GrantedAuthority
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole().getId()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Implement your logic here if needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Implement your logic here if needed
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Implement your logic here if needed
    }

    @Override
    public boolean isEnabled() {
        return true; // Implement your logic here if needed
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    // Additional getters and setters for other properties if needed
    // ...
}
