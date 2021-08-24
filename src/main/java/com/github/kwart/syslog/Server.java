package com.github.kwart.syslog;

import java.net.UnknownHostException;

import org.productivity.java.syslog4j.SyslogRuntimeException;
import org.productivity.java.syslog4j.server.SyslogServer;
import org.productivity.java.syslog4j.server.SyslogServerConfigIF;

/**
 * Syslog server.
 *
 * @author Josef Cacek
 */
public class Server {

    public static final int SYSLOG_PORT = 9898;

    public static void main(String[] args) throws SyslogRuntimeException, UnknownHostException {

        // Details for the properties -
        // http://docs.oracle.com/javase/7/docs/technotes/guides/security/jsse/JSSERefGuide.html
        System.setProperty("jsse.enableSNIExtension", "false");
        // just in case...
        System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "true");
        System.setProperty("sun.security.ssl.allowLegacyHelloMessages", "true");

        // clear created server instances (TCP/UDP)
        SyslogServer.shutdown();

        String syslogProtocol = "tls";
        System.out.println("Simple syslog server (RFC-5424)");
        System.out.println("Usage:");
        System.out.println("  java -jar simple-syslog-server.jar [protocol]");
        System.out.println();
        System.out.println("Possible protocols: udp, tcp, tls");
        System.out.println();

        if (args.length > 0) {
            syslogProtocol = args[0];
        } else {
            System.err.println("No protocol provided. Defaulting to " + syslogProtocol);
        }

        SyslogServerConfigIF config = getSyslogConfig(syslogProtocol);
        if (config == null) {
            System.err.println("Unsupported Syslog protocol: " + syslogProtocol);
            System.exit(1);
        }
        config.setUseStructuredData(true);
        // config.setHost(InetAddress.getByName(null).getHostAddress());
        config.setHost("0.0.0.0");
        config.setPort(SYSLOG_PORT);

        System.out.println("Starting Simple Syslog Server");
        System.out.println("Protocol:     " + syslogProtocol);
        System.out.println("Bind address: " + config.getHost());
        System.out.println("Port:         " + config.getPort());

        // start syslog server
        SyslogServer.createThreadedInstance(syslogProtocol, config);
    }

    private static SyslogServerConfigIF getSyslogConfig(String syslogProtocol) {
        SyslogServerConfigIF config = null;
        if ("udp".equals(syslogProtocol)) {
            config = new UDPSyslogServerConfig();
        } else if ("tcp".equals(syslogProtocol)) {
            config = new TCPSyslogServerConfig();
        } else if ("tls".equals(syslogProtocol)) {
            config = new TLSSyslogServerConfig();
        }
        return config;
    }

}
