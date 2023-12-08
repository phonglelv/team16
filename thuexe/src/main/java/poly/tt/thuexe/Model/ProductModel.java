package poly.tt.thuexe.Model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel implements Serializable {
    private  Integer id;
    private  String name;
    private  Integer category;
  
    private   Double price;
    private  String status;
    private String displayPrice;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate = new Date();
    private  String description;
	
    private String image;
  
    private MultipartFile imageFile;  
    private Boolean isEdit = false;
}
