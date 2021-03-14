package app.ArraySort;

import java.util.Arrays;
import java.util.List;

public enum SortAlgo {
   SELECTION, BUBBLE, INSERTION, QUICK;

   public final static List<SortAlgo> iterate = Arrays.asList(values());
}
