package Markiian_Lab5
import Markiian_Lab5.{Edge, Graph}

import scala.io.Source
import scala.collection.mutable.ListBuffer

object Main {
  private val fullPath = "C:\\Users\\marki\\Desktop\\DM_Mryhlotskyi\\src\\main\\scala\\"
  def main(args: Array[String]): Unit = {
    //val graph = new Graph(fillIsomorphicGraph())

    val isomorph = new Graph(fillIsomorphicGraph())
    val isomorphic = new Graph(fill2IsomorphicGraph())
    println(isomorph.edges)
    println(isomorphic.edges)
    val isomorphismChecker = new Isomorphism(isomorph)

    isomorphismChecker.changeVerticeNames(isomorphic)
    isomorphismChecker.antiFlex(isomorphic, isomorphic.getVertices.size - 1)
    println("Are isomorphic? " + isomorphismChecker.general)
  }



  def readFromFile(filePath: String): ListBuffer[Edge] = {
    val edges = new ListBuffer[Edge]

    val bufferedSource = Source.fromFile(fullPath + filePath)
    for (line <- bufferedSource.getLines) {
      val edgeParams = line.split(" ")
      if (edgeParams.length > 2)
        edges += Edge(edgeParams(0), edgeParams(1), edgeParams(2).toInt)
      else edges += Edge(edgeParams(0), edgeParams(1))
    }

    bufferedSource.close

    edges
  }

  def fillIsomorphicGraph(): ListBuffer[Edge] = {
    readFromFile("Markiian_Lab5/isomorphic.txt")
  }

  def fill2IsomorphicGraph(): ListBuffer[Edge] = {
    readFromFile("Markiian_Lab5/isomorphism1.txt")
  }






}