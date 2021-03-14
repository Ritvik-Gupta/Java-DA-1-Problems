package services.Constrained;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public final class Constrained {
   public final String value;

   private Constrained(String value) {
      this.value = value;
   }

   public static IConstrainedFor compile(Pattern regex, String errorMesssage, String structure) {
      return value -> {
         if (!regex.matcher(value).matches())
            throw new PatternSyntaxException(errorMesssage, structure, -1);
         return new Constrained(value);
      };
   }

   public static IConstrainedFor compile(String regex, String errorMessage, String structure) {
      return compile(Pattern.compile(regex), errorMessage, structure);
   }

   public static IConstrainedFor compile(Pattern regex, String errorMesssage) {
      return compile(regex, errorMesssage, regex.pattern());
   }

   public static IConstrainedFor compile(String regex, String errorMessage) {
      return compile(Pattern.compile(regex), errorMessage);
   }

   public String toString() {
      return this.value;
   }
}
