package io.swagger.error;

public class ErrorDetails {

		private int statusCode;
		private String type;
		private long timestamp;
		private String detail;
		private String devMessage;

	    public int getStatusCode() {
			return statusCode;
		}


		public void setStatusCode(int statusCode) {
			this.statusCode = statusCode;
		}


		public String getType() {
			return type;
		}


		public void setType(String type) {
			this.type = type;
		}


		public long getTimestamp() {
			return timestamp;
		}


		public void setTimestamp(long timestamp) {
			this.timestamp = timestamp;
		}


		public String getDetail() {
			return detail;
		}


		public void setDetail(String detail) {
			this.detail = detail;
		}

		public String getDevMessage() {
			return devMessage;
		}
		
		
		public void setDevMessage(String devMessage) {
			this.devMessage = devMessage;
		}


		public static final class Builder {

	    	private int statusCode;
			private String type;
			private long timestamp;
			private String detail;
			private String devMessage;

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
	        	this.detail = detail;
	        	return this;
	        }

	        public Builder devMessage(String devMessage) {
	            this.devMessage = devMessage;
	            return this;
	        }

	        public ErrorDetails build() {
	            ErrorDetails errorDetails = new ErrorDetails();
	            errorDetails.setStatusCode(statusCode);
	            errorDetails.setType(type);
	            errorDetails.setTimestamp(timestamp);
	            errorDetails.setDetail(detail);
	            errorDetails.setDevMessage(devMessage);
	            return errorDetails;
	        }
	    }
}
