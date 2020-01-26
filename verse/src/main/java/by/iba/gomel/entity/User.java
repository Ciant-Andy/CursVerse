package by.iba.gomel.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Data
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min=3, max=30)
    private String username;
    @NotNull
    @Size(min=5, max=15)
    @Pattern(regexp = "(\\+375|0)[0-9]{9}")
    private String phone;
    @Column(name = "password")
    @Size(min = 3, message = "*Your password must have at least 5 characters")
    @NotNull(message = "*Please provide your password")
    private String password;
    @NotNull
    @Size(min=4, max=30)
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    private String email;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    @NotNull
    @Pattern(regexp = "^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|" +
            "(?<mastercard>5[1-5][0-9]{14})|" +
            "(?<discover>6(?:011|5[0-9]{2})[0-9]{12})|" +
            "(?<amex>3[47][0-9]{13})|" +
            "(?<diners>3(?:0[0-5]|[68][0-9])?[0-9]{11})|" +
            "(?<jcb>(?:2131|1800|35[0-9]{3})[0-9]{11}))$")
    private String card;
    @NotNull
    @Size(min=5, max=50)
    private String address;
    private String comment;
    private Long basketId;

    public User(String name, String phone, String email, String card, String address, String comment, Long basketId) {
        this.username = name;
        this.phone = phone;
        this.email = email;
        this.card = card;
        this.address = address;
        this.comment = comment;
        this.basketId = basketId;
    }

    public User() {
    }

    public User(@NotNull @Size(min = 3, max = 30) String username, @NotNull @Size(min = 5, max = 15) @Pattern(regexp = "(\\+375|0)[0-9]{9}") String phone, @Size(min = 3, message = "*Your password must have at least 5 characters") @NotNull(message = "*Please provide your password") String password, @NotNull @Size(min = 4, max = 30) @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$") String email, @NotNull @Pattern(regexp = "^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|" +
            "(?<mastercard>5[1-5][0-9]{14})|" +
            "(?<discover>6(?:011|5[0-9]{2})[0-9]{12})|" +
            "(?<amex>3[47][0-9]{13})|" +
            "(?<diners>3(?:0[0-5]|[68][0-9])?[0-9]{11})|" +
            "(?<jcb>(?:2131|1800|35[0-9]{3})[0-9]{11}))$") String card, @NotNull @Size(min = 5, max = 50) String address, String comment, Long basketId) {
        this.username = username;
        this.phone = phone;
        this.password = password;
        this.email = email;

        this.card = card;
        this.address = address;
        this.comment = comment;
        this.basketId = basketId;
    }
}
