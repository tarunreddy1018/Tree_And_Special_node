                      /* THIS IS A COMMENT */

package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

class Graph {
    private int nodes;
    private ArrayList<Integer> graph[];
    private boolean visited[];
    private int array[];

    public Graph(int nodes,int array[]) {
        this.nodes = nodes;
        this.array = array;
        graph = new ArrayList[nodes+1];
        visited = new boolean[nodes+1];
        for(int i = 0;i <= nodes;i++) {
            graph[i] = new ArrayList<>();
        }
    }

    public void addEdge(int node1,int node2) {
        graph[node1].add(node2);
        graph[node2].add(node1);
    }

    public int countDistinctNodePaths(int root, HashSet<Integer> set) {
        int tempSize = set.size();
        set.add(array[root-1]);
        if(set.size() == tempSize) {
            visited[root] = true;
            return 0;
        }

        visited[root] = true;
        int sum = 0;
        ArrayList<Integer> childs = graph[root];
        for(int i = 0;i < childs.size();i++) {
            int child = childs.get(i);
            if(visited[child] == false) {
                sum += countDistinctNodePaths(child,set);
            }
        }
        set.remove(array[root-1]);
        return (1+sum);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        FastIO fast = new FastIO(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder outputString = new StringBuilder();

        int nodes = fast.readInt();
        int array[] = new int[nodes];
        for(int i = 0;i < nodes;i++) {
            array[i] = fast.readInt();
        }
        Graph graphObject = new Graph(nodes,array);
        for(int i = 0;i < nodes-1;i++) {
            int u = fast.readInt();
            int v = fast.readInt();
            graphObject.addEdge(u,v);
        }
        outputString.append(graphObject.countDistinctNodePaths(1,new HashSet<Integer>()));
        bw.write(outputString.toString());
        bw.close();
    }
}


class FastIO
{
    private InputStream stream;
    private byte[] buf = new byte[1024];

    private int curChar;

    private int numChars;

    public FastIO(InputStream stream) {
        this.stream = stream;
    }

    public int read() {
        if (numChars == -1)
            throw new RuntimeException();
        if (curChar >= numChars) {
            curChar = 0;
            try
            {
                numChars = stream.read(buf);
            }
            catch (IOException e)
            {
                throw new RuntimeException();
            }
            if (numChars <= 0)
                return -1;
        }
        return buf[curChar++];
    }

    public String readString() {
        final StringBuilder stringBuilder = new StringBuilder();
        int c = read();
        while (isSpaceChar(c))
            c = read();
        do
        {
            stringBuilder.append((char) c);
            c = read();
        } while (!isSpaceChar(c));
        return stringBuilder.toString();
    }

    public int readInt()
    {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-')
        {
            sgn = -1;
            c = read();
        }
        int res = 0;
        do
        {
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public long readLong() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        long res = 0;
        do {
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public boolean isSpaceChar(int c)
    {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }
}
