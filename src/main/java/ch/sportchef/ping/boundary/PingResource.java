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
package ch.sportchef.ping.boundary;

import ch.sportchef.ping.controller.PingService;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import spark.Request;
import spark.Response;

import static spark.Spark.get;

@UtilityClass
public class PingResource {

    public static void registerRoutes(@NonNull final PingService pingService) {
        get("/ping", (final Request request, final Response response) -> {
            response.header("Content-Type", "text/plain");
            return pingService.getPong();
        });
    }

}
