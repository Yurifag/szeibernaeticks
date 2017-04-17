package main.de.grzb.szeibernaeticks.szeibernaeticks;

import javax.vecmath.Vector2d;

import io.netty.util.internal.ConcurrentSet;

public final class BodyPart {

    private static ConcurrentSet<BodyPart> bodySet;

    public static final BodyPart BONES;
    public static final BodyPart STOMACH;
    public static final BodyPart EYES;
    public static final BodyPart JOINTS;
    public static final BodyPart VEINS;

    private String name;
    private Vector2d location;

    private BodyPart() {

    }

    static {
        bodySet = new ConcurrentSet<BodyPart>();
        BONES = createBodyPart("bones", new Vector2d(0, 0));
        STOMACH = createBodyPart("stomach", new Vector2d());
        EYES = createBodyPart("eyes", new Vector2d());
        JOINTS = createBodyPart("joints", new Vector2d());
        VEINS = createBodyPart("veins", new Vector2d());
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

    public static ConcurrentSet<BodyPart> getBodySet() {
        return bodySet;
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
