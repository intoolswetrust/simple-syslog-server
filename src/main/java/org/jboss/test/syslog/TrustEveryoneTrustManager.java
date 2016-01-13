/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2013, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.test.syslog;

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
	 * @see javax.net.ssl.X509TrustManager#checkClientTrusted(java.security.cert.X509Certificate[],
	 *      java.lang.String)
	 */
	@Override
	public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		// nothing to do here
	}

	/**
	 * Trust all server certificates.
	 *
	 * @see javax.net.ssl.X509TrustManager#checkServerTrusted(java.security.cert.X509Certificate[],
	 *      java.lang.String)
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
