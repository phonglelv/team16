package poly.tt.thuexe.Entity;
import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "Reports")
public class Report  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
   @Column(nullable = false, columnDefinition = "nvarchar(255)")
     String reportContent;
	@Temporal(TemporalType.DATE)
	@Column(name = "Createdate")
	Date createDate = new Date();
	@Column( columnDefinition = "nvarchar(255)")
    String reportName;
	@ManyToOne
	@JoinColumn(name = "Username")
	Customer customer;
	

}