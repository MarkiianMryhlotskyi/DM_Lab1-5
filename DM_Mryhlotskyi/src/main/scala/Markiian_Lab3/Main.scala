package Markiian_Lab3
import Markiian_Lab3.{Edge, Graph}
import scala.io.Source
import scala.collection.mutable.ListBuffer

object Main {
  private val fullPath = "C:\\Users\\marki\\Desktop\\DM_Mryhlotskyi\\src\\main\\scala\\"
  def main(args: Array[String]): Unit = {
    val graph = new Graph(fillSalesmanGraph())
    //new Salesman(new Markiian_Lab3.Graph(fillSalesmanGraph())).gamiltonCycle()
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
  def fillSalesmanGraph(): ListBuffer[Edge] = {
    readFromFile("salesman.txt")
  }
}
