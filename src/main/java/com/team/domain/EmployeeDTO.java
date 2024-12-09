package com.team.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class EmployeeDTO implements UserDetails {
    private Integer employeeNo;
    private String employeeCi;
    private String employeeName;
    private String employeeId;
    private String employeePassword;
    private String employeeBirthDate;
    @Pattern(regexp = "(010|011|017) - [0-9]-[3,4]-[0-9]{4}")
    private String employeePhoneNumber;
    @Email
    private String employeeEmail;
    private String employeeAddress;
    private String employeeDetailedAddress;
    private String employeeGender;
    private String employeeDept;
    private List<FileDTO> file;




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("READ"));
    }

    @Override
    public String getPassword() {
        return this.employeePassword;
    }

    @Override
    public String getUsername() {
        return this.employeeId;
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
