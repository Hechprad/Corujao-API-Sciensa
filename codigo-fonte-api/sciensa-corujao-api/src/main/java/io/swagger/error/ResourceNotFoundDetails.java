package io.swagger.error;

// Retorno do status notfound
public class ResourceNotFoundDetails extends ErrorDetails {
	
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

        public ResourceNotFoundDetails build() {
            ResourceNotFoundDetails resourceNotFoundDetails = new ResourceNotFoundDetails();
            resourceNotFoundDetails.setStatusCode(statusCode);
            resourceNotFoundDetails.setType(type);
            resourceNotFoundDetails.setTimestamp(timestamp);
            resourceNotFoundDetails.setDetail(detail);
            resourceNotFoundDetails.setDevMessage(devMessage);
            return resourceNotFoundDetails;
        }

    }
}
