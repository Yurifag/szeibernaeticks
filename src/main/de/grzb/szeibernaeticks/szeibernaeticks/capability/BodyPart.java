package main.de.grzb.szeibernaeticks.szeibernaeticks.capability;

public final class BodyPart {

  public static final BodyPart bones = new BodyPart("bones");
  public static final BodyPart stomach = new BodyPart("stomach");
  public static final BodyPart eyes = new BodyPart("eyes");
  public static final BodyPart joints = new BodyPart("joints");

  private String name;

  public BodyPart(String name) {
    this.name = name;
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
