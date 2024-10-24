package it.pagopa.swclient.mil.debtposition.util;

import it.pagopa.swclient.mil.debtposition.model.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class TestData {

    public static final String FISCAL_CODE = "RSSMRA85M01H501Z";
    public static final String REQUEST_ID = "a3f4b7d2-4c5e-9f01-a234-5b6c7d8e9f0a";
    public static final String VALID_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
    public static final Long PA_TAX_CODE = 15376371009L;
    public static final Long NOTICE_NUMBER = 485564829563528563L;
    public static final Long AMOUNT = 12345L;
    public static final String DESCRIPTION = "Health ticket for chest x-ray";
    public static final LocalDate DUE_DATE = LocalDate.now();

    public static TokenResponse getTokenResponse() {
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(VALID_TOKEN);
        tokenResponse.setType("Bearer");
        tokenResponse.setExpiresIn(3600L);
        return tokenResponse;
    }

    public static TokenInfoRequest getTokenInfoRequest() {
        TokenInfoRequest tokenInfoRequest = new TokenInfoRequest();
        tokenInfoRequest.setToken(VALID_TOKEN);
        return tokenInfoRequest;
    }

    public static TokenInfoResponse getTokenInfoResponse() {
        TokenInfoResponse tokenInfoResponse = new TokenInfoResponse();
        tokenInfoResponse.setFiscalCode(FISCAL_CODE);
        return tokenInfoResponse;
    }

    public static Debt getDebt() {
        return Debt.builder()
                .paTaxCode(PA_TAX_CODE)
                .noticeNumber(NOTICE_NUMBER)
                .amount(AMOUNT)
                .description(DESCRIPTION)
                .dueDate(DUE_DATE)
                .build();
    }

    public static Installment getInstallment() {
        return Installment.builder()
                .paTaxCode(PA_TAX_CODE)
                .nav(NOTICE_NUMBER)
                .amount(AMOUNT)
                .description(DESCRIPTION)
                .dueDate(DUE_DATE)
                .build();
    }

    public static PaymentOption getPaymentOption() {
        return PaymentOption.builder()
                .installments(Collections.singletonList(getInstallment()))
                .build();
    }

    public static PaymentNoticesResponse getPaymentNoticesResponse() {
        return PaymentNoticesResponse.builder()
                .paymentOptions(Collections.singletonList(getPaymentOption()))
                .build();
    }

    public static Map<String, String> getMilHeaders() {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("RequestId", REQUEST_ID);
        headerMap.put("AcquirerId", "4585625");
        headerMap.put("Channel", "POS");
        headerMap.put("MerchantId", "33444433488");
        headerMap.put("TerminalId", "0aB9wXyZ");
        headerMap.put("Authorization", VALID_TOKEN);
        return headerMap;
    }

}

