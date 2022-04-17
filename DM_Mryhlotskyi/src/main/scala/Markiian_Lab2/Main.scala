package Markiian_Lab2
import Markiian_Lab2.{Edge, Graph}

import scala.collection.mutable.ListBuffer
import scala.io.Source

object Main {
  private val fullPath = "C:\\Users\\marki\\Desktop\\DM_Mryhlotskyi\\src\\main\\scala\\"

  def main(args: Array[String]): Unit = {
    val graph = new Graph(fillPostManGraph())
    new PostMan(graph).postManPath()
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

  def fillPostManGraph(): ListBuffer[Edge] = {
    readFromFile("Markiian_Lab2/Postman.txt")
  }
}
