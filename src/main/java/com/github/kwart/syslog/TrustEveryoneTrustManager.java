package com.github.kwart.syslog;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * Dummy trust manager, which trusts everyone.
 * 
 * @author Josef Cacek
 */
public class TrustEveryoneTrustManager implements X509TrustManager {

    private static final X509Certificate[] EMPTY_ACCEPTED_ISSUERS = new X509Certificate[0];

    /**
     * Trust all client certificates.
     *
     * @see javax.net.ssl.X509TrustManager#checkClientTrusted(java.security.cert.X509Certificate[], java.lang.String)
     */
    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        // nothing to do here
    }

    /**
     * Trust all server certificates.
     *
     * @see javax.net.ssl.X509TrustManager#checkServerTrusted(java.security.cert.X509Certificate[], java.lang.String)
     */
    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        // nothing to do here
    }

    /**
     * Returns empty array.
     *
     * @see javax.net.ssl.X509TrustManager#getAcceptedIssuers()
     */
    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return EMPTY_ACCEPTED_ISSUERS;
    }
}
