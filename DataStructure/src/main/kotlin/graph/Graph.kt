package graph

import kotlin.math.truncate

class Node(val data: Int, val adjacent: MutableList<Node>, var marked: Boolean) {
    constructor(data: Int) : this(data, mutableListOf<Node>(), false)

}

class Graph(val size: Int) {
    val nodes = Array<Node>(size + 1) { Node(it) }

    fun addEdge(n1: Int, n2: Int) {
        val node1 = nodes[n1]
        val node2 = nodes[n2]

        if (!node1.adjacent.contains(node2)) {
            node1.adjacent.add(node2)
        }
        if (!node2.adjacent.contains(node1)) {
            node2.adjacent.add(node1)
        }
    }

    fun dfs() {
        dfs(nodes[0])
    }

    fun dfs(node: Node) {
        println(node.data)
        node.marked = true
        for (ad in node.adjacent) {
            if (!ad.marked) {
                dfs(ad)
            }
        }
    }

    fun bfs(){
        val q= arrayListOf<Node>()
        q.add(nodes[0])
        nodes[0].marked=true

        while (q.isNotEmpty()){
            val temp=q.removeFirst()
            println(temp.data)

            for (ad in temp.adjacent){
                if (!ad.marked){
                    q.add(ad)
                    ad.marked=true
                }
            }
        }

    }
}

fun main() {
    val graph = Graph(8)
    graph.addEdge(0, 1)
    graph.addEdge(1, 2)
    graph.addEdge(1, 3)
    graph.addEdge(2, 4)
    graph.addEdge(2, 3)
    graph.addEdge(3, 4)
    graph.addEdge(3, 5)
    graph.addEdge(5, 6)
    graph.addEdge(5, 7)
    graph.addEdge(6, 8)
    graph.bfs()
}