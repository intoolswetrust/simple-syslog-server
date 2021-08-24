package com.github.kwart.syslog;

import org.productivity.java.syslog4j.server.SyslogServerIF;
import org.productivity.java.syslog4j.server.impl.net.tcp.TCPNetSyslogServerConfig;

/**
 * Configuration class for {@link TCPSyslogServer}.
 *
 * @author Josef Cacek
 */
public class TCPSyslogServerConfig extends TCPNetSyslogServerConfig {

    private static final long serialVersionUID = 1L;

    @Override
    public Class<? extends SyslogServerIF> getSyslogServerClass() {
        return TCPSyslogServer.class;
    }
}
