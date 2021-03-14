package app.Student;

public final class SubjectRecord {
   public final String subjectName;
   private final int maxMarks;
   public final float marks;

   public SubjectRecord(String subjectName, int maxMarks, float marks) throws Exception {
      this.subjectName = subjectName;
      this.maxMarks = maxMarks;
      if (marks < 0)
         throw new Exception("Marks should atleast be 0 points");
      if (marks > maxMarks)
         throw new Exception("Marks cannot Exceed Maximum Marks points");
      this.marks = marks;
   }

   public char getRank() {
      if (this.marks > maxMarks * 0.90)
         return 'S';
      if (this.marks > maxMarks * 0.80)
         return 'A';
      if (this.marks > maxMarks * 0.65)
         return 'B';
      if (this.marks > maxMarks * 0.50)
         return 'C';
      if (this.marks > maxMarks * 0.40)
         return 'D';
      return 'F';
   }

   public String toString() {
      return this.subjectName + "\t->\t" 
         + this.marks + " / " + this.maxMarks + "\t( " + this.getRank() + " )";
   }
}
