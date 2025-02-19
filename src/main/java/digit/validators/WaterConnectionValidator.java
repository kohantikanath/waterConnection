package digit.validators;

import digit.repository.WaterConnectionRepository;
import digit.web.models.WaterConnection;
import org.egov.tracer.model.CustomException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import digit.web.models.WaterConnectionCreateRequest;
import digit.web.models.WaterConnectionSearchCriteria;

import java.util.List;


@Component
public class WaterConnectionValidator {

    private final WaterConnectionRepository waterConnectionRepository;

    public WaterConnectionValidator(WaterConnectionRepository waterConnectionRepository) {
        this.waterConnectionRepository = waterConnectionRepository;
    }

    /**
     * Validates the WaterConnectionRequest to ensure mandatory fields are present.
     *
     * @param waterConnectionRequest The water connection request to validate.
     */
    public void validateWaterConnectionRequest(WaterConnectionCreateRequest waterConnectionRequest) {
        if (ObjectUtils.isEmpty(waterConnectionRequest.getWaterConnection().getTenantId())) {
            throw new CustomException("EG_WC_ERR", "tenantId is mandatory for creating water connection applications");
        }
    }


    public WaterConnection validateApplicationExistence(WaterConnection waterConnection) {
        List<WaterConnection> existingApplications = waterConnectionRepository.getConnections(
                WaterConnectionSearchCriteria.builder()
                        .applicationNo(waterConnection.getApplicationNo())
                        .tenantId(waterConnection.getTenantId())
                        .build()
        );

        if (existingApplications.isEmpty()) {
            throw new CustomException("EG_WC_NOT_FOUND", "No existing application found with application number: " + waterConnection.getApplicationNo());
        }

        return existingApplications.get(0);
    }
}