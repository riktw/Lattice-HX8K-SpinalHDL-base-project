package mylib

import spinal.core._
import spinal.lib._
import spinal.lib.com.uart._
import spinal.lib.fsm._

class Blinky(countWidth: Int) extends Component {
  val io = new Bundle{
    val LED0 = out Bool
    val LED1 = out Bool
    val LED2 = out Bool
    val LED3 = out Bool
    val LED4 = out Bool
    val LED5 = out Bool
    val LED6 = out Bool
    val LED7 = out Bool
  }

  val counter = Reg(UInt(countWidth bit)) init (0)
  
  counter := counter + 1
  
  io.LED0 := counter(counter.getWidth - 8)
  io.LED1 := counter(counter.getWidth - 7)
  io.LED2 := counter(counter.getWidth - 6)
  io.LED3 := counter(counter.getWidth - 5)
  io.LED4 := counter(counter.getWidth - 4)
  io.LED5 := counter(counter.getWidth - 3)
  io.LED6 := counter(counter.getWidth - 2)
  io.LED7 := counter(counter.getWidth - 1)
  
}

//Generate the UartText's Verilog
object BlinkyVerilog {
  def main(args: Array[String]) {
    SpinalVerilog(new Blinky(28))
  }
}
