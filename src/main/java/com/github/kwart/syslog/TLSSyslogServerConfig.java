package com.github.kwart.syslog;

import org.productivity.java.syslog4j.server.SyslogServerIF;
import org.productivity.java.syslog4j.server.impl.net.tcp.ssl.SSLTCPNetSyslogServerConfig;

/**
 * Configuration class for {@link TLSSyslogServer}.
 *
 * @author Josef Cacek
 */
public class TLSSyslogServerConfig extends SSLTCPNetSyslogServerConfig {

    private static final long serialVersionUID = 1L;

    @Override
    public Class<? extends SyslogServerIF> getSyslogServerClass() {
        return TLSSyslogServer.class;
    }

}
