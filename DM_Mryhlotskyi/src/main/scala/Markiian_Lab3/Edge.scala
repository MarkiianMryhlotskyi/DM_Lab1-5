package Markiian_Lab3

import scala.collection.mutable

case class Edge(var vertex1: String, var vertex2: String, wage: Integer = 1) {
  def compareVertices(that: Edge): Boolean = {
    if (this.vertex1 == that.vertex1
      || this.vertex2 == that.vertex1
      || this.vertex1 == that.vertex2
      || this.vertex2 == that.vertex2) true
    else false
  }

  def getCommonVertice(that: Edge): String = {

    if (this.vertex1 == that.vertex1
      || this.vertex2 == that.vertex1) that.vertex1
    else that.vertex2
  }

  val vertices: List[String] = List(vertex1, vertex2)

  def isPresent(vertices: mutable.HashSet[String]): Boolean = {
    if (vertices.contains(this.vertex2) || vertices.contains(this.vertex1)) false else true
  }

  def equals(that: Edge): Boolean = {
    if ((this.vertex1 == that.vertex1 && this.vertex2 == that.vertex2) ||
      (this.vertex2 == that.vertex1 && this.vertex1 == that.vertex2)) true
    else false
  }
}
