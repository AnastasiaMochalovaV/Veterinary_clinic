package Model;

public class Animal {
    private int animalId;
    private String name;
    private int breedId;
    private int ownerId;
    private int diagnosisId;

    public Animal() {

    }

    public Animal(int animalId, String name, int breedId, int ownerId, int diagnosisId) {
        this.animalId = animalId;
        this.name = name;
        this.breedId = breedId;
        this.ownerId = ownerId;
        this.diagnosisId = diagnosisId;
    }

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBreedId() {
        return breedId;
    }

    public void setBreedId(int breedId) {
        this.breedId = breedId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getDiagnosisId() {
        return diagnosisId;
    }

    public void setDiagnosisId(int diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "animalId=" + animalId +
                ", name='" + name + '\'' +
                ", breedId=" + breedId +
                ", ownerId=" + ownerId +
                ", diagnosisId=" + diagnosisId +
                '}';
    }
}

