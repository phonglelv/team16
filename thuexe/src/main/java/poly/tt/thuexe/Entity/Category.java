package poly.tt.thuexe.Entity;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;
@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "Categories")
public class Category implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	@Column(nullable = false, columnDefinition = "nvarchar(255)")
	String name;
	@JsonIgnore
	@OneToMany(mappedBy = "category")
	List<Product> products;
	
}
