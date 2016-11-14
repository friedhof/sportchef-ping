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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PongService.class)
public class PongServiceTest {

    @Test
    public void getPong() {
        // arrange
        final PongService pongService = new PongService();

        // act
        final String pong = pongService.getPong();

        // assert
        final String timeStr = ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);
        assertThat(pong, containsString(" at " + timeStr));
        assertThat(pong, not(startsWith("Pong from null")));
        assertThat(pong, startsWith("Pong from "));
    }

    @Test
    public void getPongWithoutEnvironmentVariable() {
        // arrange
        mockStatic(System.class);
        when(System.getenv("HOSTNAME")).thenReturn(null);
        final PongService pongService = new PongService();

        // act
        final String pong = pongService.getPong();

        // assert
        final String timeStr = ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);
        assertThat(pong, containsString(" at " + timeStr));
        assertThat(pong, not(startsWith("Pong from null")));
        assertThat(pong, startsWith("Pong from "));
    }

    @Test
    public void getPongWithUnknownHostException() throws UnknownHostException {
        // arrange
        mockStatic(System.class);
        when(System.getenv("HOSTNAME")).thenReturn(null);
        mockStatic(InetAddress.class);
        when(InetAddress.getLocalHost()).thenThrow(new UnknownHostException());
        final PongService pongService = new PongService();

        // act
        final String pong = pongService.getPong();

        // assert
        final String timeStr = ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);
        assertThat(pong, containsString(" at " + timeStr));
        assertThat(pong, startsWith("Pong from unknown "));
    }

}