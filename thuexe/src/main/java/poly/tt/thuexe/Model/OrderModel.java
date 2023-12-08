package poly.tt.thuexe.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import poly.tt.thuexe.Entity.Customer;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderModel implements Serializable {
    Long id;
    @NotBlank(message = "Địa chỉ không được để trống")
    String address;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Ngày không được để trống đúng định dạng")
    private Date createDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Ngày không được để trống đúng định dạng")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Ngày không được để trống đúng định dạng")
    private Date endDate;

    private String username;
     Boolean isEdit = false;
}
