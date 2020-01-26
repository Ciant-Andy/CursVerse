package by.iba.gomel.security;

import by.iba.gomel.entity.Role;
import by.iba.gomel.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class SiteUserDetails implements UserDetails {
    private String username;
    private String password;
    private Set<Role> role;
    private List<GrantedAuthority> list = new ArrayList<>();

    public SiteUserDetails() {
    }

    public SiteUserDetails(User user){
        this.username=user.getUsername();
        this.password=user.getPassword();
        this.role=user.getRoles();

        for(Role r : role){
            list.add(new SimpleGrantedAuthority(r.getName())) ;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return list;
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
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
