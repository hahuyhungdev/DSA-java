package graph;

public interface GraphADT<T> extends Iterable<T> {
  void addVertex(T vertex);

  void removeVertex(T vertex);

  void addEdge(T vertex1, T vertex2);

  void removeEdge(T vertex1, T vertex2);

  boolean hasEdge(T vertex1, T vertex2);

  int size();

  boolean isEmpty();

}
