package poly.tt.thuexe.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel implements Serializable {
    private  Integer id;
    private  String name;
    private  Integer category;
    private  String image;
    private   Double price;
    private  String status;
    private  Double weight;
    private   String frameMaterial;
    private   String gearsCount;
    private  String brakeSystem;
    private  String tireSize;
    private  String wheelType;
    private String displayPrice;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate = new Date();
    private  String description;
    private MultipartFile imageFile;
    private Boolean isEdit = false;
}
