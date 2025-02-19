package digit.web.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * AuditDetails
 */
@Setter
@Validated
public class AuditDetails   {
        @JsonProperty("createdBy")

        private String createdBy = null;

        @JsonProperty("createdTime")

        private Long createdTime = null;

        @JsonProperty("lastModifiedBy")

        private String lastModifiedBy = null;

        @JsonProperty("lastModifiedTime")

        private Long lastModifiedTime = null;

        /**
         * Get createdBy
         * @return createdBy
         **/
        @ApiModelProperty(value = "")

        public String getCreatedBy() {
                return createdBy;
        }

        /**
         * Get createdTime
         * @return createdTime
         **/
        @ApiModelProperty(value = "")

        public Long getCreatedTime() {
                return createdTime;
        }

        /**
         * Get lastModifiedBy
         * @return lastModifiedBy
         **/
        @ApiModelProperty(value = "")

        public String getLastModifiedBy() {
                return lastModifiedBy;
        }

        /**
         * Get lastModifiedTime
         * @return lastModifiedTime
         **/
        @ApiModelProperty(value = "")

        public Long getLastModifiedTime() {
                return lastModifiedTime;
        }

        @Override
        public boolean equals(java.lang.Object o) {
                if (this == o) {
                        return true;
                }
                if (o == null || getClass() != o.getClass()) {
                        return false;
                }
                AuditDetails auditDetails = (AuditDetails) o;
                return Objects.equals(this.createdBy, auditDetails.createdBy) &&
                        Objects.equals(this.createdTime, auditDetails.createdTime) &&
                        Objects.equals(this.lastModifiedBy, auditDetails.lastModifiedBy) &&
                        Objects.equals(this.lastModifiedTime, auditDetails.lastModifiedTime);
        }

        @Override
        public int hashCode() {
                return Objects.hash(createdBy, createdTime, lastModifiedBy, lastModifiedTime);
        }

        @Override
        public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append("class AuditDetails {\n");

                sb.append("    createdBy: ").append(toIndentedString(createdBy)).append("\n");
                sb.append("    createdTime: ").append(toIndentedString(createdTime)).append("\n");
                sb.append("    lastModifiedBy: ").append(toIndentedString(lastModifiedBy)).append("\n");
                sb.append("    lastModifiedTime: ").append(toIndentedString(lastModifiedTime)).append("\n");
                sb.append("}");
                return sb.toString();
        }

        /**
         * Convert the given object to string with each line indented by 4 spaces
         * (except the first line).
         */
        private String toIndentedString(java.lang.Object o) {
                if (o == null) {
                        return "null";
                }
                return o.toString().replace("\n", "\n    ");
        }
}