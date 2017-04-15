package main.de.grzb.szeibernaeticks.szeibernaeticks;

import javax.vecmath.Vector2d;

import io.netty.util.internal.ConcurrentSet;

public final class BodyPart {

    public static final BodyPart bones = createBodyPart("bones", new Vector2d(0, 0));
    public static final BodyPart stomach = createBodyPart("stomach", new Vector2d());
    public static final BodyPart eyes = createBodyPart("eyes", new Vector2d());
    public static final BodyPart joints = createBodyPart("joints", new Vector2d());
    private static ConcurrentSet<BodyPart> bodySet = new ConcurrentSet<BodyPart>();

    private String name;
    private Vector2d location;

    private BodyPart() {

    }

    /**
     * Creates and returns a BodyPart with the given name and location, if no
     * BodyPart with such a name exists. <br>
     * <br>
     * If such a BodyPart does exists, this return that one instead.
     *
     * @param name
     *            The name of the BodyPart.
     * @param location
     *            The Vector describing its location on a image of a Entity.
     * @return A BodyPart with the specified name.
     */
    private static BodyPart createBodyPart(String name, Vector2d location) {
        // Check whether a BodyPart with the given name exists
        for(BodyPart b : bodySet) {
            if(b.name == name) {
                return b;
            }
        }

        // If it doesn't, create it and add it to the list.
        BodyPart part = new BodyPart();
        part.name = name;
        part.location = location;
        bodySet.add(part);
        return part;
    }

    public String getName() {
        return name;
    }

    public Vector2d getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof BodyPart) {
            BodyPart part = (BodyPart) o;
            return part.name == name;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
