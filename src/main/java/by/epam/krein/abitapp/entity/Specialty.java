package by.epam.krein.abitapp.entity;

import java.util.ArrayList;
import java.util.List;

public class Specialty extends University{

    private String name;
    private String information;
    private int admissionPlanForFree;
    private int admissionPlanForPaid;
    private int admissionPlanForCorrespondenceCourseForFree;
    private int admissionPlanForCorrespondenceCourseForPaid;
    private University university;
    List<Exam> nameOfExams;

    public Specialty() {
    }

    public int getAdmissionPlanForFree() {
        return admissionPlanForFree;
    }

    public void setAdmissionPlanForFree(int admissionPlanForFree) {
        this.admissionPlanForFree = admissionPlanForFree;
    }

    public int getAdmissionPlanForPaid() {
        return admissionPlanForPaid;
    }

    public void setAdmissionPlanForPaid(int admissionPlanForPaid) {
        this.admissionPlanForPaid = admissionPlanForPaid;
    }

    public int getAdmissionPlanForCorrespondenceCourseForFree() {
        return admissionPlanForCorrespondenceCourseForFree;
    }

    public void setAdmissionPlanForCorrespondenceCourseForFree(int admissionPlanForCorrespondenceCourseForFree) {
        this.admissionPlanForCorrespondenceCourseForFree = admissionPlanForCorrespondenceCourseForFree;
    }

    public int getAdmissionPlanForCorrespondenceCourseForPaid() {
        return admissionPlanForCorrespondenceCourseForPaid;
    }

    public void setAdmissionPlanForCorrespondenceCourseForPaid(int admissionPlanForCorrespondenceCourseForPaid) {
        this.admissionPlanForCorrespondenceCourseForPaid = admissionPlanForCorrespondenceCourseForPaid;
    }

    public List<Exam> getNameOfExams() {
        return (ArrayList<Exam>)nameOfExams;
    }

    public void setNameOfExams(List<Exam> nameOfExams) {
        this.nameOfExams = nameOfExams;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getInformation() {
        return information;
    }

    @Override
    public void setInformation(String information) {
        this.information = information;
    }

    @Override
    public University getUniversity() {
        return university;
    }

    @Override
    public void setUniversity(University university) {
        this.university = university;
    }
}
