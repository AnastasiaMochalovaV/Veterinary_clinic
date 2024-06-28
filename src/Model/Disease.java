package Model;

public class Disease {
    private int diseaseId;
    private String scientificName;
    private String commonName;

    public Disease() {
    }

    public Disease(int diseaseId, String scientificName, String commonName) {
        this.diseaseId = diseaseId;
        this.scientificName = scientificName;
        this.commonName = commonName;
    }

    public int getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(int diseaseId) {
        this.diseaseId = diseaseId;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    @Override
    public String toString() {
        return "Disease{" +
                "diseaseId=" + diseaseId +
                ", scientificName='" + scientificName + '\'' +
                ", commonName='" + commonName + '\'' +
                '}';
    }
}

