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
import java.net.DatagramPacket;
import java.net.SocketException;

import org.productivity.java.syslog4j.SyslogConstants;
import org.productivity.java.syslog4j.SyslogRuntimeException;
import org.productivity.java.syslog4j.server.SyslogServerEventIF;
import org.productivity.java.syslog4j.server.impl.net.udp.UDPNetSyslogServer;

/**
 * UDP syslog server implementation for syslog4j.
 *
 * @author Josef Cacek
 */
public class UDPSyslogServer extends UDPNetSyslogServer {

	@Override
	public void shutdown() {
		super.shutdown();
		thread = null;
	}

	@Override
	public void run() {
		this.shutdown = false;
		try {
			this.ds = createDatagramSocket();
		} catch (Exception e) {
			System.err.println("Creating DatagramSocket failed");
			e.printStackTrace();
			throw new SyslogRuntimeException(e);
		}

		byte[] receiveData = new byte[SyslogConstants.SYSLOG_BUFFER_SIZE];

		while (!this.shutdown) {
			try {
				final DatagramPacket dp = new DatagramPacket(receiveData, receiveData.length);
				this.ds.receive(dp);
				final SyslogServerEventIF event = new Rfc5424SyslogEvent(receiveData, dp.getOffset(), dp.getLength());
				System.out.println(">>> Syslog message came: " + event);
			} catch (SocketException se) {
				se.printStackTrace();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
}
