package com.vivekkant.http.facebook;

import java.util.List;

public class TokenDebug {
	
	private String appId;
	private String type;
	private long expiry;
	boolean isValid;
	private long issuedAt;
	List<String> scopes;
	private String userId;
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getExpiry() {
		return expiry;
	}
	public void setExpiry(long expiry) {
		this.expiry = expiry;
	}
	public boolean isValid() {
		return isValid;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	public long getIssuedAt() {
		return issuedAt;
	}
	public void setIssuedAt(long issuedAt) {
		this.issuedAt = issuedAt;
	}
	public List<String> getScopes() {
		return scopes;
	}
	public void setScopes(List<String> scopes) {
		this.scopes = scopes;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "TokenDebug [appId=" + appId + ", type=" + type + ", expiry=" + expiry + ", isValid=" + isValid
				+ ", issuedAt=" + issuedAt + ", scopes=" + scopes + ", userId=" + userId + "]";
	}

	
}
