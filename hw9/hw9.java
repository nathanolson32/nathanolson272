import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class hw9 {



    //START OF UNION FIND ALGORITHM


    class UnionFind {
        int[] parents;

        public UnionFind(int n) {
            this.parents = new int[n];
            for (int i = 0; i < n; i++) {
                this.parents[i] = i;
            }
        }

        public void union(int node1, int node2) {
            int node1Component = find(node1);
            int node2Component = find(node2);
            this.parents[node1Component] = node2Component;
        }

        public int find(int node) {
            while (this.parents[node] != node) {
                node = this.parents[node];
            }
            return node;
        }
    }

    class Solution {
        public int minimumCost(int n, int[][] connections) {
            UnionFind uf = new UnionFind(n + 1);
            Arrays.sort(connections, new Comparator<int[]>() {
                @Override
                public int compare(final int[] a1, final int[] a2) {
                    return a1[2] - a2[2];
                }
            });
            int edgeCount = 0;
            int connectionIndex = 0;
            int weight = 0;
            for (int i = 0; i < connections.length; i++) {
                int[] connection = connections[i];
                int nodeAComponent = uf.find(connection[0]);
                int nodeBComponent = uf.find(connection[1]);
                if (nodeAComponent != nodeBComponent) {
                    uf.union(nodeAComponent, nodeBComponent);
                    weight += connection[2];
                    edgeCount++;
                }
                if (edgeCount == n - 1) {
                    break;
                }
            }
            if (edgeCount == n - 1) {
                return weight;
            }
            return -1;
        }
    }


    class KrushkalMST {
        static class Edge {
            int source;
            int destination;
            int weight;

            public Edge(int source, int destination, int weight) {
                this.source = source;
                this.destination = destination;
                this.weight = weight;
            }
        }

        static class Graph {
            int vertices;
            ArrayList<Edge> allEdges = new ArrayList<>();

            Graph(int vertices) {
                this.vertices = vertices;
            }

            public void addEgde(int source, int destination, int weight) {
                Edge edge = new Edge(source, destination, weight);
                allEdges.add(edge);
            }

            public void kruskalMST() {
                PriorityQueue<Edge> pq = new PriorityQueue<>(allEdges.size(), Comparator.comparingInt(o -> o.weight));
                for (int i = 0; i < allEdges.size(); i++) {
                    pq.add(allEdges.get(i));
                }
                int[] parent = new int[vertices];
                makeSet(parent);
                ArrayList<Edge> mst = new ArrayList<>();
                int index = 0;
                while (index < vertices - 1) {
                    Edge edge = pq.remove();
                    int x_set = find(parent, edge.source);
                    int y_set = find(parent, edge.destination);
                    if (x_set == y_set) {
                    } else {
                        mst.add(edge);
                        index++;
                        union(parent, x_set, y_set);
                    }
                }
                System.out.println("Minimum Spanning Tree: ");
                printGraph(mst);
            }

            public void makeSet(int[] parent) {
                for (int i = 0; i < vertices; i++) {
                    parent[i] = i;
                }
            }

            public int find(int[] parent, int vertex) {
                if (parent[vertex] != vertex)
                    return find(parent, parent[vertex]);
                ;
                return vertex;
            }

            public void union(int[] parent, int x, int y) {
                int x_set_parent = find(parent, x);
                int y_set_parent = find(parent, y);
                parent[y_set_parent] = x_set_parent;
            }

            public void printGraph(ArrayList<Edge> edgeList) {
                for (int i = 0; i < edgeList.size(); i++) {
                    Edge edge = edgeList.get(i);
                    System.out.println("Edge-" + i + " source: " + edge.source +
                            " destination: " + edge.destination +
                            " weight: " + edge.weight);
                }
            }
        }

        public static void main(String[] args) {
            int vertices = 6;
            Graph graph = new Graph(vertices);
            graph.addEgde(0, 1, 4);
            graph.addEgde(0, 2, 3);
            graph.addEgde(1, 2, 1);
            graph.addEgde(1, 3, 2);
            graph.addEgde(2, 3, 4);
            graph.addEgde(3, 4, 2);
            graph.addEgde(4, 5, 6);
            graph.kruskalMST();
        }
    }




    // START OF THE HW8-REPEATED ALGORITHM CODE


    static class Graph {
        static int numVertex;
        static int numEdges;
        ArrayList<ArrayList<Integer>> graph;
        boolean[] marked;
        int components;
        static ArrayList<Edge> edgeSet;
        int numTrees;
        HashSet<Integer> comp;
        int maxCCSize;


        public Graph() {
            numVertex = 0;
            numEdges = 0;
            graph = new ArrayList<>();
            components = 0;
            edgeSet = new ArrayList<>();
        }

        public Graph(int vertexCount) {
            numVertex = vertexCount;
            graph = new ArrayList<>(numVertex);
            for (int i = 0; i < numVertex; i++) {
                graph.add(new ArrayList<>());
            }
            marked = new boolean[numVertex];
            components = 0;
            numTrees = 0;
            comp = new HashSet<>();
            maxCCSize = 0;
        }

        public void depthFirstTraversal() {
            for (int i = 0; i < numVertex; i++)
                if (!marked[i]) {
                    components++;
                    comp = new HashSet<>();
                    searchDF(i);
                    numTrees(comp);
                }
            System.out.println("number of connected components = " + components);
        }

        public void searchDF(int v) {
            System.out.println(v);
            comp.add(v);
            marked[v] = true;
            ArrayList<Integer> neighbors = getNeighbors(v);
            for (Integer u : neighbors)
                if (!marked[u]) searchDF(u);
        }

        public void breadthFirstTraversal() {
            components = 0;
            marked = new boolean[numVertex];
            for (int i = 0; i < numVertex; i++) {
                if (!marked[i]) {
                    System.out.println(maxCCSize);
                    components++;
                    searchBF(i);
                }
            }
            System.out.println("number of connected components = " + components);
        }


        public void searchBF(int v) {
            Queue<Integer> que = new LinkedList<>();
            que.add(v);
            marked[v] = true;
            int size = 0;
            while (!que.isEmpty()) {
                int p = que.remove();
                size++;
                for (Integer u : getNeighbors(p))
                    if (!marked[u]) {
                        que.add(u);
                        marked[u] = true;
                    }
            }
            if (size > maxCCSize) maxCCSize = size;
            System.out.println(maxCCSize);
        }


        public ArrayList<Integer> getNeighbors(int u) {
            return graph.get(u);
        }

        public boolean edgePresent(int u, int v) {
            return (graph.get(u).contains(v));
        }

        public boolean isTree(HashSet<Integer> cc) {
            int countEdges = 0;
            if (cc.size() == 1) return true;
            else
                for (Integer vertex : cc) {
                    countEdges += getNeighbors(vertex).size();
                }
            if (countEdges == cc.size() - 1) return true;
            else return false;
        }

        public void numTrees(HashSet<Integer> cc) {
            if (isTree(cc)) numTrees++;
        }

        public void addEdge(int u, int v) {
            // lets assume that the vertices are already created.
            if (u >= 0 && u < numVertex && v >= 0 && v < numVertex) {
                if (!edgePresent(u, v))
                    graph.get(u).add(v);
                if (!edgePresent(v, u))
                    graph.get(v).add(u);
                numEdges++;
            } else {
                throw new IndexOutOfBoundsException();
            }
        }

        public static Graph readAndStoreGraph(String fileName) {
            int maxV = 0;
            edgeSet = new ArrayList<>();
            try {
                Scanner sc = new Scanner(new File(fileName));
                String s;
                //graph.add(new ArrayList<Integer>());
                while (sc.hasNextLine()) {
                    s = sc.nextLine();
                    if (s.isEmpty()) continue;
                    String[] line = s.split(",");
                    int v1 = Integer.parseInt(line[0]);
                    int v2 = Integer.parseInt(line[1]);
                    int p = Math.max(v1, v2);
                    if (p > maxV) maxV = p;
                    edgeSet.add(new Edge(v1, v2));
                }
            } catch (FileNotFoundException e) {
            }

            Graph gr = new Graph(maxV + 1);
            numEdges = edgeSet.size();
            return gr;
        }

        public static void main(String[] args) {
            String file = "artist_edges.txt";
            Graph gr = readAndStoreGraph(file);
            System.out.println("# vertices " + numVertex);
            for (Edge e : edgeSet)
                gr.addEdge(e.v1, e.v2);
            System.out.println("# edges  " + numEdges);
            long t1 = System.currentTimeMillis();
            gr.depthFirstTraversal();
            long t2 = System.currentTimeMillis();
            System.out.println("time taken =  " + (t2 - t1) * 1.0 / 1000);
            System.out.println("number of Trees " + gr.numTrees);
            long t3 = System.currentTimeMillis();
            gr.breadthFirstTraversal();
            long t4 = System.currentTimeMillis();
            System.out.println("time taken =  " + (t4 - t3) * 1.0 / 1000);
            System.out.println("max connected component size = " + gr.maxCCSize);
        }
    }


    // START OF THE PRIORITYQUEUE ALGORITHM



    static class Pair<U extends Comparable<U>,
            V extends Comparable<V>>
            implements Comparable<Pair<U, V>> {

        public final U a;
        public final V b;

        private Pair(U a, V b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            Pair<?, ?> pair = (Pair<?, ?>) o;
            if (!a.equals(pair.a))
                return false;
            return b.equals(pair.b);
        }

        @Override
        public int hashCode() {
            return 31 * a.hashCode() + b.hashCode();
        }

        @Override
        public String toString() {
            return "(" + a + ", " + b + ")";
        }

        @Override
        public int compareTo(Pair<U, V> o) {
            return getV().compareTo(o.getV());
        }

        private U getU() {
            return a;
        }

        private V getV() {
            return b;
        }
    }

    static class Graph2 {

        int vertices;
        ArrayList[] edges;

        static Pair<Pair<Integer, Integer>,
                Integer>
                minCostEdge;

        Graph2(int vertices) {
            minCostEdge = new Pair<>(new Pair<>(1, 1),
                    Integer.MAX_VALUE);
            this.vertices = vertices;
            edges = new ArrayList[vertices + 1];
            for (int i = 0; i <= vertices; i++) {
                edges[i]
                        = new ArrayList<Pair<Integer, Integer>>();
            }
        }

        void addEdge(int a, int b, int weight) {
            edges[a].add(new Pair<>(b, weight));
            edges[b].add(new Pair<>(a, weight));
            if (weight < minCostEdge.b) {
                minCostEdge
                        = new Pair<>(new Pair<>(a, b), weight);
            }
        }

        void MST() {
            PriorityQueue<Pair<Pair<Integer, Integer>,
                    Integer>>
                    priorityQueue
                    = new PriorityQueue<>();
            Iterator<Pair<Integer, Integer>> iterator
                    = edges[minCostEdge.a.a].listIterator();
            while (iterator.hasNext()) {
                Pair<Integer, Integer> pair
                        = iterator.next();
                priorityQueue.add(
                        new Pair<>(
                                new Pair<>(minCostEdge.a.a, pair.a),
                                pair.b));
            }
            iterator = edges[minCostEdge.a.b].listIterator();
            while (iterator.hasNext()) {
                Pair<Integer, Integer> pair = iterator.next();
                priorityQueue.add(
                        new Pair<>(
                                new Pair<>(minCostEdge.a.b, pair.a),
                                pair.b));
            }
            Set<Integer> addedVertices = new HashSet<>();
            Set<Pair<Pair<Integer, Integer>, Integer>> addedEdges
                    = new HashSet<>();
            while (addedEdges.size() < vertices - 1) {
                Pair<Pair<Integer, Integer>, Integer> pair
                        = priorityQueue.poll();
                if (!addedVertices.contains(pair.a.a)) {
                    addedVertices.add(pair.a.a);
                    addedEdges.add(pair);
                    iterator = edges[pair.a.a].listIterator();
                    while (iterator.hasNext()) {
                        Pair<Integer, Integer> pair1
                                = iterator.next();
                        priorityQueue.add(
                                new Pair<>(
                                        new Pair<>(pair.a.a, pair1.a),
                                        pair1.b));
                    }
                }
                if (!addedVertices.contains(pair.a.b)) {
                    addedVertices.add(pair.a.b);
                    addedEdges.add(pair);
                    iterator = edges[pair.a.b].listIterator();
                    while (iterator.hasNext()) {
                        Pair<Integer, Integer> pair1
                                = iterator.next();
                        priorityQueue
                                .add(
                                        new Pair<>(
                                                new Pair<>(pair.a.b, pair1.a),
                                                pair1.b));
                    }
                }
            }

            Iterator<Pair<Pair<Integer, Integer>, Integer>> iterator1
                    = addedEdges.iterator();
            System.out.println("((A"
                    + ", "
                    + "B)"
                    + ", "
                    + "Cost)");
            while (iterator1.hasNext()) {
                System.out.println(iterator1.next());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Graph2 g = new Graph2(9);
        g.addEdge(0, 1, 4);
        g.addEdge(0, 7, 8);
        g.addEdge(1, 2, 8);
        g.addEdge(1, 7, 11);
        g.addEdge(2, 3, 7);
        g.addEdge(2, 8, 2);
        g.addEdge(2, 5, 4);
        g.addEdge(3, 4, 9);
        g.addEdge(3, 5, 14);
        g.addEdge(4, 5, 10);
        g.addEdge(5, 6, 2);
        g.addEdge(6, 7, 1);
        g.addEdge(6, 8, 6);
        g.addEdge(7, 8, 7);
        g.MST();
    }

}

