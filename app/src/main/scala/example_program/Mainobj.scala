package example_program

object Mainobj extends App{

  val filepath = "C:\\Users\\Brahmananda Rao\\Desktop\\TASK\\CRUD_OP\\app\\src\\main\\scala\\crud_op\\main\\data.csv"
  val x = new Insertingdata
  x.insert(filepath)

}
