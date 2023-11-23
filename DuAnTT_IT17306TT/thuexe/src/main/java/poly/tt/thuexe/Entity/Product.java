package poly.tt.thuexe.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity @Table(name = "Products")
public class Product  implements Serializable{
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	@Column(nullable = false, columnDefinition = "nvarchar(255)")
	String name;
	String image;
	Double price;
    @Column(columnDefinition = "nvarchar(255)")
    String status; // để sau
    Double weight;
	@Column( columnDefinition = "nvarchar(255)")
    String frameMaterial;
	@Column( columnDefinition = "nvarchar(255)")
    String gearsCount;
	@Column( columnDefinition = "nvarchar(255)")
    String brakeSystem;
	@Column( columnDefinition = "nvarchar(255)")
    String tireSize;
	@Column( columnDefinition = "nvarchar(255)")
    String wheelType;
	@Column( columnDefinition = "nvarchar(255)")
	String displayPrice;
	@Temporal(TemporalType.DATE)
	@Column(name = "Createdate")
	Date createDate = new Date();
	@Column(nullable = false, columnDefinition = "nvarchar(MAX)")
  	private String description;
	@ManyToOne
	@JoinColumn(name = "Categoryid")
	Category category;
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<OrderDetail> orderDetails;	
}