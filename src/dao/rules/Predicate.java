package dao.rules;

import dao.sensors.GpsCoordinate;

/**
 *
 * @author Maisha Jauernig
 */
public abstract class Predicate {
    public abstract boolean test(GpsCoordinate c);
}
