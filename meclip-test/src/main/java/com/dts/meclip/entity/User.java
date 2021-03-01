package com.dts.meclip.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document(collection = "user")
public class User {
	 private Integer status;

	    @Field("social_id")
	    private String socialId;

	    private String mobile;

	    private String password;

	    @Field("access_token")
	    private String accessToken;

	    @Field("refresh_token")
	    private String refreshToken;

	    @Field("acc_type")
	    private Integer accType;

	    private String customerId;
	
}
