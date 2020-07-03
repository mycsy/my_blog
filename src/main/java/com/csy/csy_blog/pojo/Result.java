package com.csy.csy_blog.pojo;

import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;

@Log4j2
public class Result {

	
	private boolean success = false;
	
	private String message;
	
	private ResultCode resultCode = ResultCode.SYSTEM_ERROR;
	
	private Map<String,Object> model = new HashMap<String,Object>(32);
	
	private Object defaultModel;
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
		if(success == true){
			resultCode = ResultCode.SUCCESS;
		}
	}

	public ResultCode getResultCode() {
		return resultCode;
	}

	public void setResultCode(ResultCode resultCode) {
		this.resultCode = resultCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Result(){}
	
	public Result(boolean success){
		this.success = success;
	}
	
	
	public Result addModel(String key,Object value){
		this.model.put(key, value);
		return this;
	}
	
	public Result addModel(Map<String,Object> kv){
        if(kv != null)
		    this.model.putAll(kv);
		return this;
	}

    public Object getModel(String key) {
        return this.model.get(key);
    }
	
	public Map<String,Object> getAll(){
		return this.model;
	}

	public void setError(Result result, String errorMsg, Exception e) {
		result.setMessage(errorMsg);
		result.setSuccess(false);
		log.error(errorMsg+"--->{}", e);
	}
}
