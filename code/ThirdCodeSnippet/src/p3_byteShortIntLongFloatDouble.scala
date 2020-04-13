object p3_byteShortIntLongFloatDouble {
  def byteShortIntLongFloatDouble_ops(): Unit = {
    val byte_val = 32
    val short_val = 23
    val int_val = 66
    val long_val = 128
    val float_val = 12.9
    val double_val = 32.5

    println(int_val == byte_val)
    println(float_val != double_val)
    println(int_val % short_val)
    println(short_val & byte_val)
    println(long_val * float_val)
    println(byte_val + long_val)
    println(int_val - float_val)
    println(short_val / int_val)
    println(byte_val < int_val)
    println(int_val << 2)
    println(float_val <= short_val)
    println(int_val > short_val)
    println(double_val >= float_val)
    println(int_val >> 3)
    println(int_val >>> 4)
    println(int_val ^ byte_val)
    println(+int_val)
    println(-int_val)
    println(~int_val)
    println(int_val | byte_val)
  }
}
