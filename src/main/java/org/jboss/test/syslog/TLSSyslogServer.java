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

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;

import javax.net.ServerSocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.commons.io.IOUtils;
import org.productivity.java.syslog4j.SyslogRuntimeException;

/**
 * TCP syslog server implementation for syslog4j.
 *
 * @author Josef Cacek
 */
public class TLSSyslogServer extends TCPSyslogServer {

	private SSLContext sslContext;

	/**
	 * Creates custom sslContext from keystore and truststore configured in
	 *
	 * @see org.productivity.java.syslog4j.server.impl.net.tcp.TCPNetSyslogServer#initialize()
	 */
	@Override
	public void initialize() throws SyslogRuntimeException {
		super.initialize();

		try {
			final KeyStore keystore = KeyStore.getInstance("JKS");
			final InputStream is = getClass().getResourceAsStream("/server.keystore");
			if (is == null) {
				System.err.println("Server keystore not found.");
			}
			final char[] keystorePwd = "123456".toCharArray();
			try {
				keystore.load(is, keystorePwd);
			} finally {
				IOUtils.closeQuietly(is);
			}

			final KeyManagerFactory keyManagerFactory = KeyManagerFactory
					.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			keyManagerFactory.init(keystore, keystorePwd);

			sslContext = SSLContext.getInstance("TLS");
			sslContext.init(keyManagerFactory.getKeyManagers(), new TrustManager[] { new TrustEveryoneTrustManager() },
					null);
		} catch (Exception e) {
			System.err.println("Exception occured during SSLContext for TLS syslog server initialization");
			e.printStackTrace();
			throw new SyslogRuntimeException(e);
		}
	}

	/**
	 * Returns {@link ServerSocketFactory} from custom {@link SSLContext}
	 * instance created in {@link #initialize()} method.
	 *
	 * @see org.productivity.java.syslog4j.server.impl.net.tcp.TCPNetSyslogServer#getServerSocketFactory()
	 */
	@Override
	protected ServerSocketFactory getServerSocketFactory() throws IOException {
		return sslContext.getServerSocketFactory();
	}

}
