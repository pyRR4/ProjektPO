package com.example.projektpo.service.implementation;

import com.example.projektpo.service.contract.ExternalSystemServiceContract;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalSystemServiceImpl implements ExternalSystemServiceContract {

    private final RestTemplate restTemplate;
    private final String externalSystemUrl = "http://localhost:8081/warnings";
    private final static Logger logger = LoggerFactory.getLogger(ExternalSystemServiceImpl.class);

    @Autowired
    public ExternalSystemServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String fetchWarnings() {
        try {
            String warnings = restTemplate.getForObject(externalSystemUrl, String.class);

            if (warnings != null)
                logger.debug(warnings);

            return warnings != null ? warnings : "";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "";
        }
    }
}
