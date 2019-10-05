package io.swagger.error;

public class ValidationErrorDetails extends ErrorDetails {

	private String field;
	private String fieldMessage;
	
	public String getField() {
		return field;
	}

	public String getFieldMessage() {
		return fieldMessage;
	}


	public static final class Builder {
		private int statusCode;
		private String type;
		private long timestamp;
		private String devMessage;
		private String detail;
		private String field;
		private String fieldMessage;
		
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

        public Builder field(String field) {
        	this.field = field;
        	return this;
        }

        public Builder fieldMessage(String fieldMessage) {
        	this.fieldMessage = fieldMessage;
        	return this;
        }

        public ValidationErrorDetails build() {
        	ValidationErrorDetails validationErrorDetail = new ValidationErrorDetails();
            validationErrorDetail.setStatusCode(statusCode);
            validationErrorDetail.setType(type);
            validationErrorDetail.setTimestamp(timestamp);
            validationErrorDetail.setDetail(detail);
            validationErrorDetail.setDevMessage(devMessage);
            validationErrorDetail.field = field;
            validationErrorDetail. fieldMessage = fieldMessage;
            return validationErrorDetail;
        }

    }
}
