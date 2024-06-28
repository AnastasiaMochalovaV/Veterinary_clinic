package Model;

public class BreedDisease {
    private int breedDiseaseId;
    private int breedId;
    private int diseaseId;

    public BreedDisease() {
    }

    public BreedDisease(int breedDiseaseId, int breedId, int diseaseId) {
        this.breedDiseaseId = breedDiseaseId;
        this.breedId = breedId;
        this.diseaseId = diseaseId;
    }

    public int getBreedDiseaseId() {
        return breedDiseaseId;
    }

    public void setBreedDiseaseId(int breedDiseaseId) {
        this.breedDiseaseId = breedDiseaseId;
    }

    public int getBreedId() {
        return breedId;
    }

    public void setBreedId(int breedId) {
        this.breedId = breedId;
    }

    public int getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(int diseaseId) {
        this.diseaseId = diseaseId;
    }

    @Override
    public String toString() {
        return "BreedDisease{" +
                "breedDiseaseId=" + breedDiseaseId +
                ", breedId=" + breedId +
                ", diseaseId=" + diseaseId +
                '}';
    }
}

