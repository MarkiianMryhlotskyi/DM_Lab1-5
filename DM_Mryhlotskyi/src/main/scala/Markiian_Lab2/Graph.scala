package Markiian_Lab2

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class Graph(var edges : ListBuffer[Edge]) {

  def EdgesSortedByWage(): ListBuffer[Edge] = this.edges.sortBy(_.wage)

  def pop: Edge = {
    val head = edges.head
    edges -= head
    head
  }

  def print(printableEdges: ListBuffer[Edge] = this.edges) : Unit =  printableEdges.foreach(println)

  def isEmpty: Boolean = edges.isEmpty

  def getVertices : mutable.HashSet[String] = {
    val vertices = new mutable.HashSet[String]()
    this.edges.foreach(e => { vertices.add(e.vertex1); vertices.add(e.vertex2)})
    vertices
  }

  def equals(that: Graph): Boolean = edges.equals(that.edges)

  def swap(vertexToSwap1: String, vertexToSwap2: String): Unit = {
    val newEdges = new ListBuffer[Edge]
    val edgeWage = 1

    for (edge <- this.edges) {
      if (sameVertices(edge, vertexToSwap1, vertexToSwap2)) {
        newEdges += Edge(edge.vertex2, edge.vertex1, edge.wage)
      } else if (edge.vertex1 == vertexToSwap1) {
        newEdges += Edge(vertexToSwap2, edge.vertex2, edgeWage)
      }
      else if (edge.vertex2 == vertexToSwap1) {
        newEdges += Edge(edge.vertex1, vertexToSwap2, edgeWage)
      } else if (edge.vertex1 == vertexToSwap2) {
        newEdges += Edge(vertexToSwap1, edge.vertex2, edgeWage)
      } else if (edge.vertex2 == vertexToSwap2) {
        newEdges += Edge(edge.vertex1, vertexToSwap1, edgeWage)
      } else newEdges += edge
    }

    this.edges = newEdges
  }

  def sameVertices(edge: Edge, vertex1: String, vertex2: String): Boolean = {
    (edge.vertex1 == vertex1 && edge.vertex2 == vertex2) ||
      (edge.vertex1 == vertex2 && edge.vertex2 == vertex1)
  }
}
