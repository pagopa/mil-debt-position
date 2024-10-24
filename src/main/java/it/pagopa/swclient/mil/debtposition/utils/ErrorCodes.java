package it.pagopa.swclient.mil.debtposition.utils;

import lombok.Getter;

@Getter
public final class ErrorCodes {
    private ErrorCodes() {
    }

    public static final String MODULE_ID = "00DP";

    /*
     * Validation errors code from 000001 to 000199
     */
    public static final String ERROR_REQUESTID_MUST_NOT_BE_NULL                                   = MODULE_ID + "000001";
    public static final String ERROR_AUTHORIZATION_MUST_NOT_BE_NULL                               = MODULE_ID + "000002";
    public static final String ERROR_CLIENT_ID_MUST_NOT_BE_NULL                                   = MODULE_ID + "000003";
    public static final String ERROR_SECRET_ID_MUST_NOT_BE_NULL                                   = MODULE_ID + "000004";
    public static final String ERROR_GRANT_TYPE_MUST_NOT_BE_NULL                                  = MODULE_ID + "000005";
    public static final String ERROR_FISCAL_CODE_MUST_NOT_BE_NULL                                 = MODULE_ID + "000006";

    /*
     * Service errors code from 000400 to 000500
     */
    public static final String ERROR_GENERIC_TOKEN                                                = MODULE_ID + "000400";
    public static final String ERROR_UNAUTHORIZED                                                 = MODULE_ID + "000401";
    public static final String ERROR_GENERIC_PAYMENT_NOTICES                                      = MODULE_ID + "000402";
    public static final String ERROR_GENERIC_TOKEN_INFO                                           = MODULE_ID + "000403";

    /*
     * Error descriptions
     */
    private static final String ERROR_REQUESTID_MUST_NOT_BE_NULL_DESCR = "RequestId must not be null";
    private static final String ERROR_AUTHORIZATION_MUST_NOT_BE_NULL_DESCR = "Authorization must not be null";
    private static final String ERROR_CLIENT_ID_MUST_NOT_BE_NULL_DESCR = "clientId must not be null";
    private static final String ERROR_SECRET_ID_MUST_NOT_BE_NULL_DESCR = "secretId must not be null";
    private static final String ERROR_GRANT_TYPE_MUST_NOT_BE_NULL_DESCR = "grantType must not be null";
    private static final String ERROR_FISCAL_CODE_MUST_NOT_BE_NULL_DESCR = "x-tax-code must not be null";

    private static final String ERROR_GENERIC_TOKEN_DESCR = "Unexpected error occurred during get token";
    private static final String ERROR_GENERIC_TOKEN_INFO_DESCR = "Unexpected error occurred during get token info";
    private static final String ERROR_PAYMENT_NOTICES_INFO_DESCR = "Unexpected error occurred during get payment notices";
    private static final String ERROR_UNAUTHORIZED_DESCR = "Authorization failed: access token is missing or invalid";

    /*
     * Error complete message
     */
    public static final String ERROR_REQUESTID_MUST_NOT_BE_NULL_MSG = "[" + ERROR_REQUESTID_MUST_NOT_BE_NULL + "] " + ERROR_REQUESTID_MUST_NOT_BE_NULL_DESCR;
    public static final String ERROR_AUTHORIZATION_MUST_NOT_BE_NULL_MSG = "[" + ERROR_AUTHORIZATION_MUST_NOT_BE_NULL + "] " + ERROR_AUTHORIZATION_MUST_NOT_BE_NULL_DESCR;
    public static final String ERROR_CLIENT_ID_MUST_NOT_BE_NULL_MSG = "[" + ERROR_CLIENT_ID_MUST_NOT_BE_NULL + "] " + ERROR_CLIENT_ID_MUST_NOT_BE_NULL_DESCR;
    public static final String ERROR_SECRET_ID_MUST_NOT_BE_NULL_MSG = "[" + ERROR_SECRET_ID_MUST_NOT_BE_NULL + "] " + ERROR_SECRET_ID_MUST_NOT_BE_NULL_DESCR;
    public static final String ERROR_GRANT_TYPE_MUST_NOT_BE_NULL_MSG = "[" + ERROR_GRANT_TYPE_MUST_NOT_BE_NULL + "] " + ERROR_GRANT_TYPE_MUST_NOT_BE_NULL_DESCR;
    public static final String ERROR_FISCAL_CODE_MUST_NOT_BE_NULL_MSG = "[" + ERROR_FISCAL_CODE_MUST_NOT_BE_NULL + "] " + ERROR_FISCAL_CODE_MUST_NOT_BE_NULL_DESCR;

    public static final String ERROR_GENERIC_TOKEN_MSG = "[" + ERROR_GENERIC_TOKEN + "] " + ERROR_GENERIC_TOKEN_DESCR;
    public static final String ERROR_GENERIC_TOKEN_INFO_MSG = "[" + ERROR_GENERIC_TOKEN_INFO + "] " + ERROR_GENERIC_TOKEN_INFO_DESCR;
    public static final String ERROR_GENERIC_PAYMENT_NOTICES_MSG = "[" + ERROR_GENERIC_PAYMENT_NOTICES + "] " + ERROR_PAYMENT_NOTICES_INFO_DESCR;
    public static final String ERROR_UNAUTHORIZED_MSG = "[" + ERROR_UNAUTHORIZED + "] " + ERROR_UNAUTHORIZED_DESCR;

}
