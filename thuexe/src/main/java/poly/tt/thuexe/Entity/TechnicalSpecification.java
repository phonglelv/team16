package poly.tt.thuexe.Entity;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "TechnicalSpecifications")
public class TechnicalSpecification implements Serializable {
    
    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    // Thông số cơ bản của xe đạp
    @Column(columnDefinition = "nvarchar(255)")
    String frameMaterial;  // Chất liệu khung

    @Column(columnDefinition = "nvarchar(255)")
    String gearsCount;  // Số lượng bánh rang

    // Các thông số quan trọng cho người thuê xe
    @Column(columnDefinition = "nvarchar(255)")
    String size;  // Kích thước xe

    @Column(columnDefinition = "nvarchar(255)")
    String brakeHandle;  // Tay phanh

    @Column(columnDefinition = "nvarchar(255)")
    String transmission;  // Hệ thống truyền động

    @Column(columnDefinition = "nvarchar(255)")
    String gearShifters;  // Bộ chuyển số


    @Column(columnDefinition = "nvarchar(255)")
    String tireBrand;  // Thương hiệu lốp

    @Column(columnDefinition = "nvarchar(255)")
    String wheelRim;  // Loại vành xe

    @Column(columnDefinition = "nvarchar(255)")
    String weight;  // Trọng lượng

    // Mối quan hệ One-to-One    với entity Product
  @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    
}
