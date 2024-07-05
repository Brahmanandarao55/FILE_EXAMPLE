package example_program

import java.io.File
import java.sql.{ PreparedStatement, SQLException}
import scala.io.Source

class Insertingdata {

  val con = DatabaseConnection.getConnection

  def insert(file:String): Unit = {

    try {
      val startTime = System.currentTimeMillis()
      val bufferedSource = Source.fromFile(new File(file))
      val lines = bufferedSource.getLines().drop(1)
      val employees = lines.map { line =>
        val fields = line.split(",").map(_.trim)
        Employee(fields(0).toInt, fields(1), fields(2).toInt)
      }.toList

      val query: String =
        """
          |INSERT INTO employees values(?,?,?);
          |""".stripMargin
      val statement: PreparedStatement = con.prepareStatement(query)
      employees.foreach { employee =>
        try {
          statement.setInt(1, employee.id)
          statement.setString(2, employee.name)
          statement.setInt(3, employee.age)
          statement.executeUpdate()
        }
        catch {
          case e: SQLException => e.printStackTrace()
        }
      }


      bufferedSource.close()
      val endTime = System.currentTimeMillis()
      val totalTimeSeconds = (endTime - startTime) / 1000.0
      println(s"Total insertion time: ${totalTimeSeconds} seconds")

    }
  }
}

