package app.Student;

import java.util.Arrays;
import java.util.List;

public enum BranchType {
   BCE("Computer Science and Engineering"), 
   ECE("Electroncis and Communication Engineering"), 
   EEE("Electrical and Electronics Engineering");

   public final String branchName;

   private BranchType(String branchName) {
      this.branchName = branchName;
   }

   public static BranchType getBranch(String branchAlias) throws Exception {
      for (BranchType branch : allBranches)
         if (branchAlias.equals(branch.name()))
            return branch;
      throw new Exception("Invalid Branch Alias Provided");
   }

   private static final List<BranchType> allBranches = Arrays.asList(values());
}
