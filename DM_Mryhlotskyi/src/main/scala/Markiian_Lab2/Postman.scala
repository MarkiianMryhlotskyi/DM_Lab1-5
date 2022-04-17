package Markiian_Lab2

import scala.collection.mutable.ListBuffer
import scala.io.Source

class PostMan(graph: Graph) {

  private val cycles = new ListBuffer[ListBuffer[Edge]]
  private val edges = graph.edges


  def postManPath(): Unit = {
    while (edges.nonEmpty)
      cycles += makeCycle()
    println()
    println("Цикли:")
    cycles.foreach(println)

    if (cycles.length > 1)
      mergeCycles()
  }

  def makeCycle(): ListBuffer[Edge] = {
    val cycle = new ListBuffer[Edge]
    cycle += edges.head
    edges -= edges.head

    while(cycle.last.vertex2 != cycle.head.vertex1) {
      val edgesCopy = edges.clone()
      addToCycle(cycle, edgesCopy)
    }
    cycle
  }

  def addToCycle(cycle: ListBuffer[Edge], edgesCopy: ListBuffer[Edge]): Unit = {
    if (edgesCopy.nonEmpty) if (!cycle.contains(edgesCopy.head) && areConnected(cycle.last, edgesCopy)) {
      cycle += edgesCopy.head
      edges -= edgesCopy.head
      addToCycle(cycle, edgesCopy.tail)
    } else addToCycle(cycle, edgesCopy.tail)
  }

  def mergeCycles(): ListBuffer[Edge] = {

    val finalCycle = new ListBuffer[Edge]
    val commonVertex = findCommonVertex(cycles(0), cycles(1))

    if (commonVertex.contains("Помилка")) {
      return finalCycle
    }

    var isDeleted = false

    for (edge <- cycles(1)) {
      if (!isDeleted && edge.vertex2 == commonVertex) {
        finalCycle += edge
        finalCycle ++= cycles(0)
        cycles -= cycles(0)
        isDeleted = true
      }
      else finalCycle += edge
    }

    if (cycles.length > 1) mergeCycles()
    println("___________________________Кінцевий цикл___________________________")
    println(finalCycle)

    finalCycle
  }

  def findCommonVertex(cycle1: ListBuffer[Edge], cycle2: ListBuffer[Edge]): String = {
    for (edge1 <- cycle1) {
      for (edge2 <- cycle2) {
        if (edge1.vertex2 == edge2.vertex1) return edge1.vertex2
      }
    }

    "No common vertex. ERROR"
  }

  def areConnected(edge1: Edge, edgesCopy: ListBuffer[Edge]): Boolean = {
    val edge2 = edgesCopy.head

    if (edge1.vertex2 == edge2.vertex1) {
      true
    } else if (edge1.vertex2 == edge2.vertex2) {
      edges -= edge2
      edgesCopy(0) = Edge(edge2.vertex2, edge2.vertex1, 1)
      true
    } else false
  }
}