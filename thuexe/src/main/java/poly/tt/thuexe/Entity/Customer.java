package poly.tt.thuexe.Entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Customers")
public class Customer  implements Serializable{
	@Id
	String username;
	String password;
	@Column(nullable = false, columnDefinition = "nvarchar(255)")
	String fullname;
	String email;
	 String phone;
	 @Column( columnDefinition = "nvarchar(255)")
     String address;
	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	List<Order> orders;
	
	@JsonIgnore
	@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
	List<Authority> authorities;


    @JsonIgnore
	@OneToMany(mappedBy = "customer")
	List<Report> reports;

    public Customer(String username, String encode, String address, String email, String fullname, String phone) {
    }

    public Customer() {

    }
}
