package com.hoaxify.backend.fraud;

public interface FraudSensitiveService<T> {

  T handlePostFraudCheck(T requestObject);
}
