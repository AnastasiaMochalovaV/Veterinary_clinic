package Model;

public class Breed {
    private int breedId;
    private String name;

    public Breed() {}

    public Breed(int breedId, String name) {
        this.breedId = breedId;
        this.name = name;
    }

    public int getBreedId() {
        return breedId;
    }

    public void setBreedId(int breedId) {
        this.breedId = breedId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Breed{" +
                "breedId=" + breedId +
                ", name='" + name + '\'' +
                '}';
    }
}

