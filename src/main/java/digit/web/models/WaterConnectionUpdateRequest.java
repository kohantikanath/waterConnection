package digit.web.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.egov.common.contract.request.RequestInfo;
import org.springframework.validation.annotation.Validated;

/**
 * WaterConnectionUpdateRequest
 */
@Validated
@jakarta.annotation.Generated(value = "org.egov.codegen.SpringBootCodegen", date = "2025-01-07T15:17:21.586197+05:30[Asia/Kolkata]")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WaterConnectionUpdateRequest {
    @JsonProperty("RequestInfo")

    @Valid
    private RequestInfo requestInfo = null;

    @JsonProperty("id")
    @NotNull

    private String id = null;

    @JsonProperty("connectionType")

    private String connectionType = null;

    @JsonProperty("additionalDetails")

    private Object additionalDetails = null;

    @JsonProperty("status")

    private Boolean status = null;

    @JsonProperty("propertyId")

    private String propertyId = null;


}