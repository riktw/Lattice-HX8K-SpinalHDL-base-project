package mylib

import spinal.core._
import spinal.sim._
import spinal.core.sim._
import scala.math.pow

import scala.util.Random


//BlinkySimWave's testbench
object BlinkySimWave {

class asInt(b: Boolean) {
  def toInt = if(b) 1 else 0
}

implicit def convertBooleanToInt(b: Boolean) = new asInt(b)

  def main(args: Array[String]) {
    SimConfig.allOptimisation.withWave.doSim(new Blinky(20)){dut =>
      dut.clockDomain.forkStimulus(period = 10)
    
      for(x <- 0 until pow(2,8).asInstanceOf[Int]) {
      
        dut.clockDomain.waitSampling(pow(2,12).asInstanceOf[Int])
        
        val led0 = dut.io.LED0.toBoolean
        val led1 = dut.io.LED1.toBoolean
        val led2 = dut.io.LED2.toBoolean
        val led3 = dut.io.LED3.toBoolean
        val led4 = dut.io.LED4.toBoolean
        val led5 = dut.io.LED5.toBoolean
        val led6 = dut.io.LED6.toBoolean
        val led7 = dut.io.LED7.toBoolean
        
        val LEDs : Int = (led0 toInt) + ((led1 toInt) << 1) + ((led2 toInt) << 2) + ((led3 toInt) << 3) + ((led4 toInt) << 4) + ((led5 toInt) << 5) + ((led6 toInt) << 6) + ((led7 toInt) << 7)  
        assert(LEDs == x)
      }

    }
  }
}

//BlinkySim's testbench
object BlinkySim {

class asInt(b: Boolean) {
  def toInt = if(b) 1 else 0
}

implicit def convertBooleanToInt(b: Boolean) = new asInt(b)

  def main(args: Array[String]) {
    SimConfig.allOptimisation.doSim(new Blinky(20)){dut =>
      dut.clockDomain.forkStimulus(period = 10)
    
      for(x <- 0 until pow(2,8).asInstanceOf[Int]) {
      
        dut.clockDomain.waitSampling(pow(2,12).asInstanceOf[Int])

        val led0 = dut.io.LED0.toBoolean
        val led1 = dut.io.LED1.toBoolean
        val led2 = dut.io.LED2.toBoolean
        val led3 = dut.io.LED3.toBoolean
        val led4 = dut.io.LED4.toBoolean
        val led5 = dut.io.LED5.toBoolean
        val led6 = dut.io.LED6.toBoolean
        val led7 = dut.io.LED7.toBoolean
        
        val LEDs : Int = (led0 toInt) + ((led1 toInt) << 1) + ((led2 toInt) << 2) + ((led3 toInt) << 3) + ((led4 toInt) << 4) + ((led5 toInt) << 5) + ((led6 toInt) << 6) + ((led7 toInt) << 7)  
        assert(LEDs == x)
      }

    }
  }
}
 
