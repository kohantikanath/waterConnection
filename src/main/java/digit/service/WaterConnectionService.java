package digit.service;

import org.egov.tracer.model.CustomException;
import org.springframework.util.CollectionUtils;
import digit.enrichment.WaterConnectionEnrichment;
import digit.kafka.Producer;
import digit.repository.WaterConnectionRepository;
import digit.validators.WaterConnectionValidator;
import digit.web.models.WaterConnection;
import digit.web.models.WaterConnectionCreateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import digit.web.models.WaterConnectionSearchCriteria;
import digit.web.models.WaterConnectionUpdateRequest;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class WaterConnectionService {

    @Autowired
    private WaterConnectionValidator validator;

    @Autowired
    private WaterConnectionEnrichment enrichmentUtil;

    @Autowired
    private Producer producer;

    @Autowired
    private UserService userService;

    @Autowired
    private WaterConnectionRepository waterConnectionRepository;


    /**
     * Registers a new WaterConnection based on the create request.
     *
     * @param createRequest The request object containing details of the WaterConnection to be created.
     * @return The enriched and validated WaterConnection object.
     */
    public WaterConnection createWaterConnection(WaterConnectionCreateRequest createRequest) {
        // Validate the water connection request
        validator.validateWaterConnectionRequest(createRequest);

        // Enrich the water connection with required details
        enrichmentUtil.enrichWaterConnection(createRequest);

        userService.callUserService(createRequest);
//
        // Initiate workflow for the new application
        //workflowService.updateWorkflowStatus(createRequest);

        // Push the application to the Kafka topic for persistence
        producer.push("save-bt-application", createRequest);

        // Return the enriched WaterConnection object
        return createRequest.getWaterConnection();
    }

    public List<WaterConnection> searchWaterConnections(WaterConnectionSearchCriteria searchCriteria) {
        // Fetch connections from the database according to the given search criteria
        List<WaterConnection> connections = waterConnectionRepository.getConnections(searchCriteria);

        // If no connections are found matching the given criteria, return an empty list
        if (CollectionUtils.isEmpty(connections)) {
            return new ArrayList<>();
        }

        // Return the found connections
        return connections;
    }



    public WaterConnection updateWaterConnection(WaterConnectionUpdateRequest waterConnectionUpdateRequest ){
        WaterConnection existingWaterConnection = waterConnectionRepository.getConnections(
                WaterConnectionSearchCriteria.builder()
                        .applicationNo(waterConnectionUpdateRequest.getId())
                        .build()
        ).stream().findFirst().orElse(null);

        if (existingWaterConnection == null) {
            throw new CustomException("EG_WC_NOT_FOUND", "Water connection with ID " + waterConnectionUpdateRequest.getId() + " not found.");
        }
        existingWaterConnection.setId(waterConnectionUpdateRequest.getId());
        existingWaterConnection.setConnectionType(waterConnectionUpdateRequest.getConnectionType());
        existingWaterConnection.setAdditionalDetails(waterConnectionUpdateRequest.getAdditionalDetails());
//        existingWaterConnection.setStatus(waterConnectionUpdateRequest.getStatus());
        existingWaterConnection.setPropertyId(waterConnectionUpdateRequest.getPropertyId());


        producer.push("update-bt-application", waterConnectionUpdateRequest);

        return existingWaterConnection;


    }

}