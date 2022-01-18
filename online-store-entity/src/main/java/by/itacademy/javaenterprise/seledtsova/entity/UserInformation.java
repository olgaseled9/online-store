package by.itacademy.javaenterprise.seledtsova.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserInformation {

    private String telephone;
    private String email;

    @Override
    public String toString() {
        return "UserInformation{" +
                "telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
