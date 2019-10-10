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
package org.openhab.binding.bluetooth.bluegiga.internal.command.attributeclient;

import java.util.UUID;

import org.openhab.binding.bluetooth.bluegiga.internal.BlueGigaCommand;

/**
 * Class to implement the BlueGiga command <b>readByType</b>.
 * <p>
 * The command reads the value of each attribute of a given type (UUID) and in a given attribute
 * handle range. The command can for example be used to discover the characteristic
 * declarations (UUID: 0x2803) within a service.
 * <p>
 * This class provides methods for processing BlueGiga API commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class BlueGigaReadByTypeCommand extends BlueGigaCommand {
    public static int COMMAND_CLASS = 0x04;
    public static int COMMAND_METHOD = 0x02;

    /**
     * Connection handle
     * <p>
     * BlueGiga API type is <i>uint8</i> - Java type is {@link int}
     */
    private int connection;

    /**
     * First attribute handle
     * <p>
     * BlueGiga API type is <i>uint16</i> - Java type is {@link int}
     */
    private int start;

    /**
     * Last attribute handle
     * <p>
     * BlueGiga API type is <i>uint16</i> - Java type is {@link int}
     */
    private int end;

    /**
     * Attribute type (UUID)
     * <p>
     * BlueGiga API type is <i>uuid</i> - Java type is {@link UUID}
     */
    private UUID uuid;

    /**
     * Connection handle
     *
     * @param connection the connection to set as {@link int}
     */
    public void setConnection(int connection) {
        this.connection = connection;
    }
    /**
     * First attribute handle
     *
     * @param start the start to set as {@link int}
     */
    public void setStart(int start) {
        this.start = start;
    }
    /**
     * Last attribute handle
     *
     * @param end the end to set as {@link int}
     */
    public void setEnd(int end) {
        this.end = end;
    }
    /**
     * Attribute type (UUID)
     *
     * @param uuid the uuid to set as {@link UUID}
     */
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public int[] serialize() {
        // Serialize the header
        serializeHeader(COMMAND_CLASS, COMMAND_METHOD);

        // Serialize the fields
        serializeUInt8(connection);
        serializeUInt16(start);
        serializeUInt16(end);
        serializeUuid(uuid);

        return getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("BlueGigaReadByTypeCommand [connection=");
        builder.append(connection);
        builder.append(", start=");
        builder.append(start);
        builder.append(", end=");
        builder.append(end);
        builder.append(", uuid=");
        builder.append(uuid);
        builder.append(']');
        return builder.toString();
    }
}
