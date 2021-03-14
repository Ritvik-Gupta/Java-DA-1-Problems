package app.Student;

import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Scanner;

import services.Console.Console;
import services.Constrained.Constrained;
import services.Constrained.IConstrainedFor;

public final class Student {
    private static final Scanner S = new Scanner(System.in); 
    
    private static final IConstrainedFor constrainedRegNum = Constrained.compile(
        "^1[5-9][A-Z]{3}\\d{4}$",
        "Invalid Registration Number specified",
        "<YEAR><BRANCH>xxxx ( where x is a digit and denotes the unique Reg No. )"
    );

    private final String name;
    private final Constrained regNum;
    private final BranchType branch;
    private final int year, semester;
    private final ArrayList<SubjectRecord> subjects;

    
    private static String withSuffix(int num) {
        int unit = num % 10;
        return num + ((unit == 1) ? "st" : (unit == 2) ? "nd" : (unit == 3) ? "rd" : "th");
    }

    private Student(String name, Constrained regNum, BranchType branch, int year, int semester) {
        this.name = name;
        this.regNum = regNum;
        this.branch = branch;
        this.year = year;
        this.semester = semester;
        this.subjects = new ArrayList<>();
    }

    private SubjectRecord getOverallRecord() throws Exception {
        float totalMarks = 0;
        for (SubjectRecord subject : this.subjects)
            totalMarks += subject.marks;
        return new SubjectRecord("Overall", this.subjects.size() * 100, totalMarks);
    }

    private static Student createStudent() throws Exception {
        Console.log("\nFill out the following Application :\n");

        Console.log("\tName :\t");
        String name = S.nextLine();

        Console.log("\tRegistration Number :\t");
        Constrained regNum = constrainedRegNum.with(S.nextLine());

        int regNumYear = Integer.valueOf(regNum.value.substring(0, 2));
        String regNumBranchAlias = regNum.value.substring(2, 5);

        YearMonth date = YearMonth.now();
        int year = date.getYear() - 2000 - regNumYear;
        boolean isFirstHalfOfYear = date.getMonth().compareTo(Month.JUNE) <= 0;
        int semester = 2 * year + (isFirstHalfOfYear ? 0 : 1);

        BranchType branch = BranchType.getBranch(regNumBranchAlias);
        Student student = new Student(name, regNum, branch, year, semester);

        return student;
    }

    private void print() throws Exception {
        Console.log("\nStudent Name :\t", this.name);
        Console.log("\nRegistration Number :\t", this.regNum);
        Console.log("\n", withSuffix(this.year), " Year, ", withSuffix(this.semester), "Semester student");
        Console.log("\nStudying :\t", this.branch.branchName);
        Console.log("\n\n\t|\tMarksheet");
        Console.log("\n--------|\t------------------------------------");
        this.subjects.forEach(subject -> Console.log("\n\t|\t", subject));
        Console.log("\n--------|\t------------------------------------");
        Console.log("\n\t|\t", this.getOverallRecord(), "\n\n");
    }

    public static void main(String[] args) throws Exception {
        Student student = createStudent();
        
        Console.log("\tNumber of Subjects this semester :\t");
        int numSubs = S.nextInt();

        Console.log("\nEnter Subject Name and Marks in ", numSubs, " subjects :\n");
        for (int pos = 0; pos < numSubs; ++pos) {
            Console.log("\t( ", pos + 1, " ) :\t");
            String subjectName = S.next();
            float marks = S.nextFloat();
            student.subjects.add(new SubjectRecord(subjectName, 100, marks));
        }

        student.print();
    }
}
