import java.util.Scanner;

public class Test {
  static final int infinitive = 999999999;

  private static void print_path(int[] path, int destination, int source) {
    if (destination != source) {
      if (path[destination] == -1) {
        System.out.println("Path not found!!");
        return;
      }
      print_path(path, path[destination], source);
      System.out.printf("%d ", path[destination] + 1);
    }
  }

  private static int[] dijkstra_algorithm(int[][][] graph, int source, int[] path) {
    int count_vertices = graph.length;
    int[] cost = new int[count_vertices];
    boolean[] visited = new boolean[count_vertices];
    int[] queue = new int[count_vertices];
    // initialize
    for (int i = 0; i < count_vertices; i++) {
      visited[i] = false;
      path[i] = -1;
      cost[i] = infinitive;
      queue[i] = -1;
    }
    HeapPQueue heapPQueue = new HeapPQueue(queue);
    cost[source] = 0;
    path[source] = -1;
    int current = source;
    System.out.printf("Inserting %d\n", source + 1);
    while (true) {
      visited[current] = true;
      for (int i = 0; i < graph[current].length; i++) {
        int v = graph[current][i][0];
        if (visited[v]) {
          continue;
        }
        if (!heapPQueue.isVisited(v))
          System.out.printf("Inserting %d\n", v + 1);
        heapPQueue.insert(v);
        int alt = cost[current] + graph[current][i][1];
        if (alt < cost[v]) {
          if (cost[v] != infinitive)
            System.out.printf("Updating the value of %d in the queue\n", v + 1);
          cost[v] = alt;
          path[v] = current;
        }
      }
      System.out.printf("Deleting %d\n", current + 1);
      heapPQueue.removeMin(current);
      if (heapPQueue.isEmpty())
        break;
      int min_cost = infinitive;
      int index = 0;
      for (int i = 0; i < count_vertices; i++) {
        if (heapPQueue.isVisited(i)) {
          if (cost[i] < min_cost) {
            min_cost = cost[i];
            index = i;
          }
        }
      }
      current = index;
    }
    return cost;
  }

  private static void print_graph(int[][][] graph) {
    int count_vertices = graph.length;
    System.out.println(count_vertices);
    for (int i = 0; i < count_vertices; i++) {
      System.out.print(i + 1 + " ");
      for (int j = 0; j < graph[i].length; j++) {
        System.out.printf("%d %d ", graph[i][j][0] + 1, graph[i][j][1]);
      }
      System.out.print("\n");
    }
  }

  public static void main(String[] args) {
    // read graph file
    int count_vertices;
    int[][][] graph;
    Scanner sc = new Scanner(System.in);
    count_vertices = Integer.parseInt(sc.nextLine());
    graph = new int[count_vertices][][];
    for (int i = 0; i < count_vertices; i++) {
      String tempLine = sc.nextLine();
      String[] numbers = tempLine.split(" ");
      int vertex = Integer.parseInt(numbers[0]) - 1;
      graph[vertex] = new int[(numbers.length - 1) / 2][2];
      for (int j = 1; j <= numbers.length / 2; j++) {
        graph[vertex][j - 1] = new int[] { Integer.parseInt(numbers[j * 2 - 1]) - 1,
            Integer.parseInt(numbers[j * 2]) };
      }
    }
    // print graph
    print_graph(graph);
    int source = 0;
    int[] path = new int[count_vertices];
    int[] cost = dijkstra_algorithm(graph, source, path);
    for (int i = 0; i < count_vertices; i++) {
      if (cost[i] == infinitive) {
        System.out.printf("Shortest path to %d: not connected\n", i + 1);
      }
      System.out.printf("Shortest path to %d: ", i + 1);
      print_path(path, i, source);
      System.out.printf("%d: cost = %d\n", i + 1, cost[i]);
    }
  }
}
