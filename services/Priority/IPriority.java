package services.Priority;

@FunctionalInterface
public interface IPriority<T> {
   PriorityType priority(T valA, T valB);
}
