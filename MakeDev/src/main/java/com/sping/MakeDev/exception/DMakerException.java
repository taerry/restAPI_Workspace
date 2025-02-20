package com.sping.MakeDev.exception;

public class DMakerException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private DMakerErrorCode dMakerErrorCode;
    private String detailMessage;
    
    public DMakerException(DMakerErrorCode errorCode) {
        super(errorCode.getMessage());
        this.dMakerErrorCode = errorCode;
        this.detailMessage = errorCode.getMessage();
    }

    public DMakerException(DMakerErrorCode errorCode, String detailMessage) {
        super(detailMessage);
        this.dMakerErrorCode = errorCode;
        this.detailMessage = detailMessage;
    }
    
    

}
