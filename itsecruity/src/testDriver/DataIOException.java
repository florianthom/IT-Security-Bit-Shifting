/*
 * Copyright (C) 2018 Burkhard Messer <burkhard.messer@htw-berlin.de>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package testDriver;

/**
 *
 * @author Burkhard Messer
 */
public class DataIOException extends RuntimeException {

    /**
     * Creates a new instance of <code>ScannerException</code> without detail
     * message.
     */
    public DataIOException() {
    }

    /**
     * Constructs an instance of <code>ScannerException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public DataIOException(final String msg) {
        super(msg);
    }
}
