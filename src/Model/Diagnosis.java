package Model;

public class Diagnosis {
    private int diagnosisId;
    private String status;
    private int diseaseId;

    public Diagnosis() {}

    public Diagnosis(int diagnosisId, String status, int diseaseId) {
        this.diagnosisId = diagnosisId;
        this.diseaseId = diseaseId;
    }

    public int getDiagnosisId() {
        return diagnosisId;
    }

    public void setDiagnosisId(int diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

    public int getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(int diseaseId) {
        this.diseaseId = diseaseId;
    }

    @Override
    public String toString() {
        return "Diagnosis{" +
                "diagnosisId=" + diagnosisId +
                ", status='" + status + '\'' +
                ", diseaseId=" + diseaseId +
                '}';
    }
}

