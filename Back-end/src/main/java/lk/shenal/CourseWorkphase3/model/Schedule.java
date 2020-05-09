package lk.shenal.CourseWorkphase3.model;


import java.time.LocalDate;

public class Schedule {
    private LocalDate pickUpDate;
    private LocalDate dropOfDate;

    public Schedule(LocalDate pickUpDate, LocalDate dropOfDate) {
        this.pickUpDate = pickUpDate;
        this.dropOfDate = dropOfDate;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "pickUpDate=" + pickUpDate +
                ", dropOfDate=" + dropOfDate +
                '}';
    }

    public LocalDate getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(LocalDate pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public LocalDate getDropOfDate() {
        return dropOfDate;
    }

    public void setDropOfDate(LocalDate dropOfDate) {
        this.dropOfDate = dropOfDate;
    }
}
