/*
 * SportChef – Sports Competition Management Software
 * Copyright (C) 2016 Marcus Fihlon
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ch.sportchef.ping.controller;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
public class PingService {

    private String hostname = null;

    public PingService() {
        this.hostname = System.getenv("HOSTNAME");
        if (this.hostname == null) {
            try {
                this.hostname = InetAddress.getLocalHost().getHostName();
            } catch (final UnknownHostException e) {
                log.error("Unable to determine hostname.", e);
            }
            if (this.hostname == null) {
                this.hostname = "unknown";
            }
        }
    }

    public String getPong() {
        return String.format("Pong from %s", this.hostname);
    }
}
