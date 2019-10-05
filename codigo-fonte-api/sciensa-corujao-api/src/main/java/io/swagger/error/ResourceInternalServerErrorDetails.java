package io.swagger.error;

// Retorno do status internal server error
public class ResourceInternalServerErrorDetails extends ErrorDetails {
	
	public static final class Builder {
		private int statusCode;
		private String type;
		private long timestamp;
		private String devMessage;
		private String detail;
		
        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder statusCode(int statusCode) {
        	this.statusCode = statusCode;
        	return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder detail(String detail) {
			this.detail= detail;
			return this;
		}
        
        public Builder devMessage(String devMessage) {
            this.devMessage = devMessage;
            return this;
        }

        public ResourceInternalServerErrorDetails build() {
            ResourceInternalServerErrorDetails resourceInternalServerErrorDetails = new ResourceInternalServerErrorDetails();
            resourceInternalServerErrorDetails.setStatusCode(statusCode);
            resourceInternalServerErrorDetails.setType(type);
            resourceInternalServerErrorDetails.setTimestamp(timestamp);
            resourceInternalServerErrorDetails.setDetail(detail);
            resourceInternalServerErrorDetails.setDevMessage(devMessage);
            return resourceInternalServerErrorDetails;
        }

    }
}
