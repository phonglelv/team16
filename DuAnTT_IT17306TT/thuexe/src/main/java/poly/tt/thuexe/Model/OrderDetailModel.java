package poly.tt.thuexe.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailModel implements Serializable {
    Long id;

    @Min(value = 0, message = "Giá phải lớn hơn hoặc bằng 0")
    @Max(value = 10, message = "Giá phải nhỏ hơn hoặc bằng 10")
    @NotNull(message = "Giá không được rỗng")
    @DecimalMin(value = "0.0", inclusive = false, message = "Phải lớn hơn 0")
    @DecimalMax(value = "5000000.0", message = "Giá từ 5 triệu trở xuống 5,000,000")
    Double price;
    @Min(value = 0, message = "Số lượng phải lớn hơn hoặc bằng 0")
    @Max(value = 10, message = "Số lượng phải nhỏ hơn hoặc bằng 10")

    @NotNull(message = "Số lượng không được rỗng")
    @Min(value = 0, message = "Số lượng phải lớn hơn hoặc bằng 0")
    @Max(value = 10, message = "Số lượng phải nhỏ hơn hoặc bằng 10")
    @Digits(integer = Integer.MAX_VALUE, fraction = 0, message = "Số lượng chỉ được chứa số")
    Integer quantity;

    @NotBlank(message = "Trạng thái không được để trống")
    @Pattern(regexp = "^[\\p{L} ]+$", message = "Trạng thái chỉ được chứa chữ cái và khoảng trắng")
    String status;
    @NotNull(message = "Ngày giao hàng không được để trống")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deliveryDate;
    Integer productId;
    Long orderId;

    Boolean isEdit = false;
}
