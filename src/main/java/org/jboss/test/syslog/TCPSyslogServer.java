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
import java.net.Socket;
import java.util.Collections;

import org.productivity.java.syslog4j.server.impl.net.tcp.TCPNetSyslogServer;

/**
 * Syslog4j server for TCP protocol implementation.
 *
 * @author Josef Cacek
 */
public class TCPSyslogServer extends TCPNetSyslogServer {

	@SuppressWarnings("unchecked")
	public TCPSyslogServer() {
		sockets = Collections.synchronizedSet(sockets);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		try {
			System.out.println("Creating Syslog server socket");
			this.serverSocket = createServerSocket();
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (!this.shutdown) {
			try {
				final Socket socket = this.serverSocket.accept();
				System.out.println("Handling Syslog client " + socket.getInetAddress());
				new Thread(new TCPSyslogSocketHandler(this.sockets, this, socket)).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
