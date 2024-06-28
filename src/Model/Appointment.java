package Model;

public class Appointment {
    private int appointmentId;
    private String date;
    private String time;
    private int animalId;
    private int doctorId;
    private int diagnosisId;

    public Appointment() {
    }

    public Appointment(int appointmentId, String date, String time, int animalId, int doctorId, int diagnosisId) {
        this.appointmentId = appointmentId;
        this.date = date;
        this.time = time;
        this.animalId = animalId;
        this.doctorId = doctorId;
        this.diagnosisId = diagnosisId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getDiagnosisId() {
        return diagnosisId;
    }

    public void setDiagnosisId(int diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId=" + appointmentId +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", animalId=" + animalId +
                ", doctorId=" + doctorId +
                ", diagnosisId=" + diagnosisId +
                '}';
    }
}
