package digit.web.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WaterConnectionSearchCriteria {
    @JsonProperty("tenantId")
    @NotNull
    private String tenantId = null;


    @JsonProperty("applicationNo")
    @Size(max = 64)
    private String applicationNo = null;

    @JsonProperty("ids")
    @Size(max=50)
    private List<String> ids = null;
}