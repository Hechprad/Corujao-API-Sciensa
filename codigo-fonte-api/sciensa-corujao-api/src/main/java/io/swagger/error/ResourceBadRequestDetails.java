package io.swagger.error;

//Retorno do status badrequest
public class ResourceBadRequestDetails extends ErrorDetails {
	
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

        public ResourceBadRequestDetails build() {
            ResourceBadRequestDetails resourceBadRequestDetails = new ResourceBadRequestDetails();
            resourceBadRequestDetails.setStatusCode(statusCode);
            resourceBadRequestDetails.setType(type);
            resourceBadRequestDetails.setTimestamp(timestamp);
            resourceBadRequestDetails.setDetail(detail);
            resourceBadRequestDetails.setDevMessage(devMessage);
            return resourceBadRequestDetails;
        }

    }
}
