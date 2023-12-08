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
public class ReportModel implements Serializable {

    Long id;
    @NotBlank( message = "Nội dung không đươc để trống")
    @Pattern(regexp = "^[\\p{L} ]+$", message = "Nội dung chỉ được chứa chữ cái và khoảng trắng")
    String reportContent;
    @NotNull(message = "Ngày không được để trống")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date createDate = new Date();
    @NotBlank( message = "Tiêu đề không đươc để trống")
    @Pattern(regexp = "^[\\p{L} ]+$", message = "Tiêu đề chỉ được chứa chữ cái và khoảng trắng")
    String reportName;
    String username;
    Boolean isEdit = false;
}
