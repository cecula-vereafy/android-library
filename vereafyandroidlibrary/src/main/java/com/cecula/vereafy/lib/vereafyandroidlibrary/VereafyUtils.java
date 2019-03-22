package com.cecula.vereafy.lib.vereafyandroidlibrary;

public final class VereafyUtils {

    private VereafyUtils(){

    }

    private static final String BASE_URL = "https://api.cecula.com";

    public static final String INITIALIZATION_ENDPOINT = BASE_URL+"/twofactor/init";
    public static final String COMPLETION_ENDPOINT = BASE_URL+"/twofactor/complete";
    public static final String RESEND_ENDPOINT = BASE_URL+"/twofactor/resend";
    public static final String GET_BALANCE_ENDPOINT = BASE_URL+"/account/tfabalance";

}
