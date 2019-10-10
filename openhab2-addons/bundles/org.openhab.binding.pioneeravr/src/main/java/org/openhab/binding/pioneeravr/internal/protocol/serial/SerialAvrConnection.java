/**
 * Copyright (c) 2010-2019 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.pioneeravr.internal.protocol.serial;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.openhab.binding.pioneeravr.internal.protocol.StreamAvrConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gnu.io.NRSerialPort;

/**
 * A class that wraps the communication to a Pioneer AVR devices through a serial port
 *
 * @author Antoine Besnard - Initial contribution
 */
public class SerialAvrConnection extends StreamAvrConnection {

    private final Logger logger = LoggerFactory.getLogger(SerialAvrConnection.class);

    private static final Integer LINK_SPEED = 9600;

    private String portName;

    private NRSerialPort serialPort;

    public SerialAvrConnection(String portName) {
        this.portName = portName;
    }

    @Override
    protected void openConnection() throws IOException {
        if (isPortNameExist(portName)) {
            serialPort = new NRSerialPort(portName, LINK_SPEED);

            boolean isConnected = serialPort.connect();

            if (!isConnected) {
                throw new IOException("Failed to connect on port " + portName);
            }

            logger.debug("Connected to {}", getConnectionName());
        } else {
            throw new IOException("Serial port with name " + portName + " does not exist. Available port names: "
                    + NRSerialPort.getAvailableSerialPorts());
        }
    }

    /**
     * Check if the Serial with the given name exist.
     *
     * @param portName
     * @return
     */
    private boolean isPortNameExist(String portName) {
        return NRSerialPort.getAvailableSerialPorts().contains(portName);
    }

    @Override
    public boolean isConnected() {
        return serialPort != null && serialPort.isConnected();
    }

    @Override
    public void close() {
        super.close();
        if (serialPort != null) {
            serialPort.disconnect();
            serialPort = null;
            logger.debug("Closed port {}", portName);
        }
    }

    @Override
    public String getConnectionName() {
        return portName;
    }

    @Override
    protected InputStream getInputStream() throws IOException {
        return serialPort.getInputStream();
    }

    @Override
    protected OutputStream getOutputStream() throws IOException {
        return serialPort.getOutputStream();
    }

}
