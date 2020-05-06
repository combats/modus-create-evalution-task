package com.modus.create.gateway.service;

import java.util.UUID;

public interface TokenParser {

    UUID parseAccessToken(String accessToken);
}
