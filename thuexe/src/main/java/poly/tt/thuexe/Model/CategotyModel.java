package poly.tt.thuexe.Model;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategotyModel implements Serializable {
    private Integer id;
    @NotBlank
    @Pattern(regexp = "^[\\p{L} ]+$", message = "Tên chỉ được chứa chữ cái và khoảng trắng")
    private String name;
    private Boolean isEdit = false;

}
