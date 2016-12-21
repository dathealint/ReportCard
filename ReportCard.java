package datnguyen.com.reportcard;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by datnguyen on 12/21/16.
 */

public class ReportCard {

	// Some subjects
	public final static String SUBJECT_MATH = "MATH";
	public final static String SUBJECT_ENGLISH = "ENGLISH";
	public final static String SUBJECT_HISTORY = "HISTORY";

	public final static String MSG_NO_GRADE = "No Grade Yet";

	public int getStudentId() {
		return studentId;
	}

	/***
	 * studentId is unique identification of a student. Each report card presents grade report for only one student, so it's one-one relationship
	 * should be private as once initialized, cannot be changed
	 */
	private int studentId;

	/***
	 * A LinkedHashMap to contain all grades of student. Keys are subject, Values are an ArrayList of that subject (can have multiple grades for one subject)
	 */
	private LinkedHashMap<String, ArrayList<Double>> allGrades;

	public ReportCard(int sId) {
		this.studentId = sId;
		this.allGrades = new LinkedHashMap<>();
	}

	/***
	 * Method to add grade to a specific subject.
	 *
	 * @param subject subject to add grade to
	 * @param grade   grade to add to list of grades of that subject
	 */
	public void addGrade(String subject, Double grade) {
		// get current grade list of that subject
		ArrayList<Double> grades = this.allGrades.get(subject);
		if (grades == null) {
			grades = new ArrayList<>();
		}

		grades.add(grade);
		this.allGrades.remove(subject);
		this.allGrades.put(subject, grades);
	}

	/**
	 * Method to get grades of specified subject as a string.
	 *
	 * @param subject subject to get grades
	 * @return a string containing all of its grades, separated by comma
	 */
	public String getGradesOfSubjectAsString(String subject) {
		ArrayList<Double> grades = this.allGrades.get(subject);
		if (grades == null) {
			return "";
		}

		String stringGrades = TextUtils.join(", ", grades);
		return stringGrades;
	}

	/**
	 * @return Return a string containing all information about this reportcard, which is all subjects and their's grades
	 */
	@Override
	public String toString() {
		String exportString = "";

		if (this.allGrades.keySet().size() == 0) {
			exportString = MSG_NO_GRADE;
		} else {
			for (String key : this.allGrades.keySet()) {
				exportString = String.format("%sSubject: %s : Grades: %s\n", exportString, key, getGradesOfSubjectAsString(key));
			}
		}

		return exportString;
	}
}
