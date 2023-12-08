package poly.tt.thuexe.Model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailModel implements Serializable {
    Long id;

    @Min(value = 0, message = "Giá phải lớn hơn hoặc bằng 0")
    @NotNull(message = "Giá không được rỗng")
    @Max(value = 5000000, message = "Giá không được vượt quá 5,000,000")
    private Double price;

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
