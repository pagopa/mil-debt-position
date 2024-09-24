package it.pagopa.swclient.mil.debtposition.service;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PaymentNoticeService {

    private final MilAuthService milAuthService;

    public PaymentNoticeService(MilAuthService milAuthService) {
        this.milAuthService = milAuthService;
    }
}
